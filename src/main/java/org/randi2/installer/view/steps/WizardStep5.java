package org.randi2.installer.view.steps;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.randi2.installer.view.MainPanel;
import org.randi2.installer.model.io.Chooser;
import org.randi2.installer.controller.Main;

public class WizardStep5 extends MainPanel {

	private static final long serialVersionUID = 5408357068663615612L;
	private JTextField downloadPathT;

	public WizardStep5(final Main main) {
		super(main);
		initGUI();
	}

	public void initGUI() {
		JLabel downloadTomcat = new JLabel(main.getConf().getlProp()
				.getProperty("label.downloadRandi"));
		downloadTomcat.setLocation(10, 10);
		downloadTomcat.setSize(600, 20);

		this.add(downloadTomcat);

		JButton downloadButton = new JButton(main.getConf().getlProp()
				.getProperty("button.download"));
		downloadButton.setLocation(10, 40);
		downloadButton.setSize(70, 20);

		this.add(downloadButton);

		JLabel downloadPfadL = new JLabel(main.getConf().getlProp()
				.getProperty("label.warPfad"));
		downloadPfadL.setLocation(10, 90);
		downloadPfadL.setSize(400, 20);

		this.add(downloadPfadL);

		downloadPathT = new JTextField();
		downloadPathT.setSize(350, 20);
		downloadPathT.setLocation(100, 120);
		downloadPathT.setEditable(false);

		this.add(downloadPathT);

		JButton downloadPfadB = new JButton(main.getConf().getlProp()
				.getProperty("button.pfad"));
		downloadPfadB.setLocation(10, 120);
		downloadPfadB.setSize(70, 20);

		this.add(downloadPfadB);

		downloadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.getUrlService().openURL("RANDI2");
			}
		});

		downloadPfadB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.getStatusService().getAkt().setStatus(1);
				Chooser fileOpen = new Chooser();
				if (!main.getConf().setRandi2Path(fileOpen.getFile("war"))) {
					main.getStatusService().getAkt().setStatus(-1);
					main.getMainFrame()
							.aktStatusPanel(
									(main.getConf().getlProp()
											.getProperty("error.war")));
				}

				downloadPathT.setText(main.getConf().getRandi2Path());
				if (main.getStatusService().getAkt().getStatus() != -1)
					main.copyRandi2();

			}
		});
	}
}
