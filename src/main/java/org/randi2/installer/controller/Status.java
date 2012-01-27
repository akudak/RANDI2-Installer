package org.randi2.installer.controller;

public class Status {

	/**
	 * Erzeugt einen Status der in StatusService verwaltet wird. Jeder Schritt
	 * der Installation hat einen Status
	 */

	private int status;
	private boolean aktive;
	private boolean visible;
	private Main main;

	public Status(Main main) {
		this.main = main;
	}

	/**
	 * @return the visible
	 */
	public boolean isVisible() {
		return visible;
	}

	/**
	 * @param visible
	 *            the visible to set
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public void setStatus(int status) {
		if (status != this.status) {
			this.status = status;
			main.getStatusbar().removeAll();
			main.getStatusbar().initBar();
		}
	}

	public int getStatus() {
		return this.status;
	}

	/**
	 * @return the aktive
	 */
	public boolean isAktive() {
		return aktive;
	}

	/**
	 * @param aktive
	 *            the aktive to set
	 */
	public void setAktive(boolean aktive) {
		this.aktive = aktive;
	}

}
