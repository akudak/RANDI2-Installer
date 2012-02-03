package org.randi2.installer.view;

import java.awt.Color;
import javax.swing.JPanel;
import org.randi2.installer.controller.Main;
import org.randi2.installer.controller.Status;
import org.randi2.installer.model.enumerations.StatusEnum;

/**
 * 
 * @author andreas Erzeugt das MainPanel. Die Objekte werden in den WizardSteps
 *         hinzugefuegt.
 */
public class MainPanel extends JPanel {

	private static final long serialVersionUID = -2859437001178109758L;
	private Status status;
	public Main main;

	public MainPanel(Main main) {
		super();
		this.main = main;
		init();
	}

	/**
	 * Erstelle Objekte
	 */
	public void init() {
		this.setSize(800, 320);
		this.setLocation(100, 200);
		this.setBackground(Color.WHITE);
		status = new Status(main);
		status.setStatus(StatusEnum.UNMACHINED);
	}

	/**
	 * @return Gibt den aktuellen Status zurueck
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * @param Setzt den aktuellen Status
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

}
