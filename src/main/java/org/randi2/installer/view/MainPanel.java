package org.randi2.installer.view;

import java.awt.Color;
import javax.swing.JPanel;
import org.randi2.installer.controller.Main;
import org.randi2.installer.controller.Status;

/**
 * 
 * @author andreas Erzeugt das MainPanel. Die Objekte werden in den WizardSteps
 *         hinzugefuegt.
 */
public class MainPanel extends JPanel {

	private static final long serialVersionUID = -2859437001178109758L;
	private Status status;
	public Main main;

	private static final int OPEN = 0;

	public MainPanel(Main main) {
		super();
		this.main = main;
		init();
	}

	public void init() {
		this.setSize(800, 300);
		this.setLocation(100, 220);
		this.setBackground(Color.WHITE);
		status = new Status(main);
		status.setStatus(OPEN);
	}

	/**
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

}
