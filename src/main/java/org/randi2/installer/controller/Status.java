package org.randi2.installer.controller;

public class Status {

	/**
	 * Erzeugt einen Status der in StatusService verwaltet wird. Jeder Schritt
	 * der Installation hat einen Status
	 */

	private int status;
	private boolean active;
	private boolean visible;
	private Main main;

	public Status(Main main) {
		this.main = main;
	}

	/**
	 * @return Gibt an, ob ein Status in der Statusbar angezeigt wird
	 */
	public boolean isVisible() {
		return visible;
	}

	/**
	 * @param Setzt, ob ein Status in der Statusbar angezeigt wird
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	/**
	 * 
	 * @param status
	 *  Setzte den Status
	 */
	public void setStatus(int status) {
		if (status != this.status) {
			this.status = status;
			main.getStatusbar().removeAll();
			main.getStatusbar().initBar();
			main.getMainFrame().remove(main.getMainFrame().getStatusText());
			main.getMainFrame().add(main.getMainFrame().getStatusText());
		}
	}

	public int getStatus() {
		return this.status;
	}

	/**
	 * @return Gibt an, ob ein Status zur Zeit bearbeitet wird
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param Setzt, , ob ein Status zur Zeit bearbeitet wird
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

}
