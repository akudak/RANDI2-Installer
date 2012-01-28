package org.randi2.installer.model;

/**
 * 
 * @author andreas Erzeugt ein Objekt der Klasse ContactPerson. Erbt von person
 */
public class ContactPerson extends Person {
	
	private Center center;
	
	/**
	 * @return Zentrum
	 */
	public Center getCenter() {
		return center;
	}

	/**
	 * @param Setzte Zentrum
	 */
	public void setCenter(Center center) {
		this.center = center;
	}
}
