package org.randi2.installer.controller;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 
 * @author andreas
 * 
 *         Verwaltet eine Liste mit den jeweiligem Status eines
 *         Installationsschritts
 */
public class StatusService {

	private Iterator<Status> iterator;
	private ArrayList<Status> statusList;

	public StatusService() {
		this.statusList = new ArrayList<Status>();
	}

	/**
	 * @return Liste mit den Statuseintraegen
	 */
	public ArrayList<Status> getStatusList() {
		return statusList;
	}

	/**
	 * @param Setzte
	 *            Statusliste
	 */
	public void setStatusList(ArrayList<Status> statusList) {
		this.statusList = statusList;
	}

	public Status getAkt() {
		Status act = null;
		iterator = statusList.iterator();
		while (iterator.hasNext()) {
			act = (Status) iterator.next();
			if (act.isActive())
				return act;
		}
		return act;
	}

	/**
	 * 
	 * @param act
	 * @return liefe den nachsten Status
	 */
	public Status getNext(Status act) {
		Status pointer = null;
		Status next = null;
		iterator = statusList.iterator();
		while (iterator.hasNext()) {
			pointer = (Status) iterator.next();
			if (pointer == act) {
				if (iterator.hasNext())
					next = iterator.next();
			}
		}
		return next;
	}
}
