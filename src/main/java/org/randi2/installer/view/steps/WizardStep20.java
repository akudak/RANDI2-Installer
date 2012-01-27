package org.randi2.installer.view.steps;

import javax.swing.JLabel;

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
		exitL.setLocation(10, 30);
		exitL.setSize(600, 20);
		this.add(exitL);
	}
}
