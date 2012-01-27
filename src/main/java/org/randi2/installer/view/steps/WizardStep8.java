package org.randi2.installer.view.steps;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;

import org.randi2.installer.view.MainPanel;
import org.randi2.installer.model.io.Chooser;
import org.randi2.installer.controller.Main;

public class WizardStep8 extends MainPanel {

	private static final long serialVersionUID = -2168605279876124181L;

	public WizardStep8(final Main main) {
		super(main);
		initGUI();
	}

	public void initGUI() {
		JLabel downloadMail = new JLabel(main.getConf().getlProp()
				.getProperty("label.mailDownload"));
		downloadMail.setLocation(10, 10);
		downloadMail.setSize(600, 20);

		this.add(downloadMail);

		JButton downloadButton = new JButton(main.getConf().getlProp()
				.getProperty("button.download"));
		downloadButton.setLocation(10, 40);
		downloadButton.setSize(70, 20);

		this.add(downloadButton);

		downloadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.getUrlService().openURL("JAVA_MAIL");
			}
		});

		JLabel downloadPfadL = new JLabel(main.getConf().getlProp()
				.getProperty("label.jarMailPfad"));
		downloadPfadL.setLocation(10, 90);
		downloadPfadL.setSize(400, 20);

		this.add(downloadPfadL);

		JButton downloadPfadB = new JButton(main.getConf().getlProp()
				.getProperty("button.pfad"));
		downloadPfadB.setLocation(10, 120);
		downloadPfadB.setSize(70, 20);

		this.add(downloadPfadB);

		downloadPfadB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.getStatusService().getAkt().setStatus(1);
				Chooser fileOpen = new Chooser();
				if (!main.getMailConf().setJarPath(fileOpen.getFile("jar"))) {
					main.getStatusService().getAkt().setStatus(-1);
					main.getMainFrame()
							.aktStatusPanel(
									(main.getConf().getlProp()
											.getProperty("error.jar")));
				}
				main.copyMail();
				main.getStatusbar().removeAll();
				main.getStatusbar().initBar();
			}
		});
	}
}