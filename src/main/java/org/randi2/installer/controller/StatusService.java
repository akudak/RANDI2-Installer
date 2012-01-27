package org.randi2.installer.controller;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 
 * @author andreas
 * 
 *         Verwaltet eine Liste mit jeweiligem Status eines
 *         Installationsschritts
 */
public class StatusService {

	private Iterator<Status> iterator;
	private ArrayList<Status> statusList;

	public StatusService() {
		this.statusList = new ArrayList<Status>();
	}

	/**
	 * @return the statusList
	 */
	public ArrayList<Status> getStatusList() {
		return statusList;
	}

	/**
	 * @param statusList
	 *            the statusList to set
	 */
	public void setStatusList(ArrayList<Status> statusList) {
		this.statusList = statusList;
	}

	/**
	 * Setzt den aktuellen Status. Ein Status kann auch nachtraeglich
	 * Fehlschalgen z.B. wenn die Eintragung in der DB fehlschlaegt.
	 * 
	 * @param status
	 */
/*
	public void setAktStatus(int status) {
		Status akt;
		Status prev = null;
		Status prev2 = null;
		int i = 0;

		iterator = statusList.iterator();
		while (iterator.hasNext()) {
			akt = (Status) iterator.next();
			i++;
			if (akt.isAktive()) {
				akt.setStatus(status);

				if (i == 12) {
					prev.setStatus(status);
				}

				if (i == 15) {
					prev.setStatus(status);
					prev2.setStatus(status);
				}
			}
			prev2 = prev;
			prev = akt;
		}
	}
*/
	public Status getAkt() {
		Status akt = null;
		iterator = statusList.iterator();
		while (iterator.hasNext()) {
			akt = (Status) iterator.next();
			if (akt.isAktive())
				return akt;
		}
		return akt;
	}
	public Status getNext(Status akt)
	{
		Status zeiger = null;
		Status next = null;
		iterator = statusList.iterator();
		while (iterator.hasNext()) {
			zeiger = (Status) iterator.next();
			if (zeiger==akt) {
				if(iterator.hasNext())
				next = iterator.next();
			}
		}
		return next;
	}
}
