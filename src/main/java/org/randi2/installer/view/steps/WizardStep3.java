package org.randi2.installer.view.steps;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;

import org.randi2.installer.view.MainPanel;
import org.randi2.installer.controller.Main;

public class WizardStep3 extends MainPanel {

	private static final long serialVersionUID = 6292599537615039172L;

	public WizardStep3(final Main main) {
		super(main);
		initGUI();
	}

	public void initGUI() {
		JLabel downloadXAMPP = new JLabel(main.getConf().getlProp()
				.getProperty("label.downloadXAMPP"));
		downloadXAMPP.setLocation(10, 10);
		downloadXAMPP.setSize(600, 20);

		this.add(downloadXAMPP);

		JLabel downloadXAMPP2 = new JLabel(main.getConf().getlProp()
				.getProperty("label.downloadXAMPP2"));
		downloadXAMPP2.setLocation(10, 50);
		downloadXAMPP2.setSize(600, 20);

		this.add(downloadXAMPP2);

		JLabel downloadXAMPP3 = new JLabel(main.getConf().getlProp()
				.getProperty("label.downloadXAMPP3"));
		downloadXAMPP3.setLocation(10, 90);
		downloadXAMPP3.setSize(600, 20);
		this.add(downloadXAMPP3);

		this.add(downloadXAMPP);

		JButton downloadButton = new JButton(main.getConf().getlProp()
				.getProperty("button.download"));
		downloadButton.setLocation(10, 130);
		downloadButton.setSize(70, 20);

		this.add(downloadButton);

		downloadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.getStatusService().getAkt().setStatus(1);
				main.getUrlService().openURL("XAMPP");
			}
		});
	}
}
