package org.randi2.installer.view.steps;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import org.randi2.installer.model.enumerations.StatusEnum;
import org.randi2.installer.view.MainPanel;
import org.randi2.installer.controller.Main;

public class WizardStep20 extends MainPanel {
	private static final long serialVersionUID = -8550721687345988331L;

	public WizardStep20(final Main main) {
		super(main);
		initGUI();
	}

	public void initGUI() {
		JLabel exitL = new JLabel(main.getConf().getlProp()
				.getProperty("label.exit"));
		exitL.setLocation(10, 10);
		exitL.setSize(600, 20);
		this.add(exitL);

		JLabel restartTomcatL = new JLabel(main.getConf().getlProp()
				.getProperty("label.startRANDI2"));
		restartTomcatL.setLocation(10, 50);
		restartTomcatL.setSize(600, 20);

		this.add(restartTomcatL);

		JButton startTomcatMacB = new JButton(main.getConf().getlProp()
				.getProperty("button.goMac"));
		startTomcatMacB.setLocation(10, 90);
		startTomcatMacB.setSize(90, 20);

		this.add(startTomcatMacB);

		JButton startTomcatWinB = new JButton(main.getConf().getlProp()
				.getProperty("button.goWin"));
		startTomcatWinB.setLocation(120, 90);
		startTomcatWinB.setSize(90, 20);

		this.add(startTomcatWinB);

		startTomcatMacB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.getStatusService().getAkt().setStatus(StatusEnum.SUCCESS);
				main.startTomcatMac();
				main.getUrlService().openURL("randi2START");
			}
		});

		startTomcatWinB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.getStatusService().getAkt().setStatus(StatusEnum.SUCCESS);
				main.startTomcatWin();
				main.getUrlService().openURL("randi2START");
			}
		});
	}
}
