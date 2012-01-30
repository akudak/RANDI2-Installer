package org.randi2.installer.view.steps;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.randi2.installer.view.MainPanel;
import org.randi2.installer.io.Chooser;
import org.randi2.installer.controller.Main;

public class WizardStep1 extends MainPanel {

	private static final long serialVersionUID = 3421572243258252728L;
	private JTextField downloadPathT;

	public WizardStep1(final Main main) {
		super(main);
		initGUI();
	}


	public void initGUI() {
		JLabel downloadTomcat = new JLabel(main.getConf().getlProp()
				.getProperty("label.downloadTomcat"));
		downloadTomcat.setLocation(10, 10);
		downloadTomcat.setSize(600, 20);

		this.add(downloadTomcat);

		JButton downloadButton = new JButton(main.getConf().getlProp()
				.getProperty("button.download"));
		downloadButton.setLocation(10, 40);
		downloadButton.setSize(70, 20);

		this.add(downloadButton);

		JLabel downloadPfadL = new JLabel(main.getConf().getlProp()
				.getProperty("label.downloadPath"));
		downloadPfadL.setLocation(10, 90);
		downloadPfadL.setSize(400, 20);

		this.add(downloadPfadL);
		
		JLabel downloadTomcatL = new JLabel(main.getConf().getlProp()
				.getProperty("label.downloadTomcatPath"));
		downloadTomcatL.setLocation(10, 120);
		downloadTomcatL.setSize(600, 20);

		this.add(downloadTomcatL);

		downloadPathT = new JTextField();
		downloadPathT.setSize(350, 20);
		downloadPathT.setLocation(100, 160);
		downloadPathT.setEditable(false);

		this.add(downloadPathT);
		JButton downloadPfadB = new JButton(main.getConf().getlProp()
				.getProperty("button.open"));
		downloadPfadB.setLocation(10, 160);
		downloadPfadB.setSize(70, 20);

		this.add(downloadPfadB);

		downloadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.getUrlService().openURL("TOMCAT");
			}
		});

		downloadPfadB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.getStatusService().getAkt().setStatus(1);
				Chooser fileOpen = new Chooser(main);

				if (main.getConf().setServerPath(
						fileOpen.getDirectorie(main.getConf(), "")))
					downloadPathT.setText(main.getConf().getServerPath());
				else
					main.getStatusService().getAkt().setStatus(-1);

				if (main.getStatusService().getAkt().getStatus() != -1)
					main.getStatusService().getAkt().setStatus(1);
				else
					main.getMainFrame().getStatusText().setText(
							(main.getConf().getlProp()
									.getProperty("error.tomcat")));
			}
		});
	}
}
