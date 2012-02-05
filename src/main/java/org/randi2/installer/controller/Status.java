package org.randi2.installer.controller;

import org.randi2.installer.model.enumerations.StatusEnum;

public class Status {

	/**
	 * Erzeugt einen Status der in StatusService verwaltet wird. Jeder Schritt
	 * der Installation hat einen Status
	 */

	private StatusEnum status;
	private boolean active;
	private Main main;

	public Status(Main main) {
		this.main = main;
	}

	/**
	 * 
	 * @param status
	 *            Setzte den Status
	 */
	public void setStatus(StatusEnum status) {
		if (status != this.status) {
			this.status = status;
			main.getStatusbar().removeAll();
			main.getStatusbar().initBar();
			main.getMainFrame().remove(main.getMainFrame().getStatusText());
			if (status.equals(StatusEnum.SUCCESS))
				main.getMainFrame()
						.getStatusText()
						.setText(
								main.getConf().getlProp()
										.getProperty("error.actStatus"));
			main.getMainFrame().add(main.getMainFrame().getStatusText());
		}
	}

	public StatusEnum getStatus() {
		return this.status;
	}

	/**
	 * @return Gibt an, ob ein Status zurzeit bearbeitet wird
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param Setzt
	 *            , ob ein Status zurzeit bearbeitet wird
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

}
