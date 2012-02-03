package org.randi2.installer.model;

import org.springframework.security.providers.encoding.ShaPasswordEncoder;

/**
 * 
 * @author andreas Erzeugt ein Objekt der Klasse Center
 */
public class Center {

	private String country;
	private String city;
	private String postcode;
	private String street;
	private String name;
	private Person contactPerson;
	private String password;
	private boolean selfRegistration;
	private long id;

	/**
	 * @return Land des Zentrums
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param Setzet
	 *            Land
	 */
	public boolean setCountry(String country) {
		if (country != null && !country.isEmpty()) {
			this.country = country;
			return true;
		} else
			return false;
	}

	/**
	 * @return Stadt des Zentrums
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param Setzet
	 *            Stadt
	 */
	public boolean setCity(String city) {
		if (city != null && !city.isEmpty()) {
			this.city = city;
			return true;
		} else
			return false;
	}

	/**
	 * @return Postleitzahl
	 */
	public String getPostcode() {
		return postcode;
	}

	/**
	 * @param Setzte
	 *            Postleitzahl
	 */
	public boolean setPostcode(String postcode) {
		if (postcode != null && !postcode.isEmpty()) {
			this.postcode = postcode;
			return true;
		} else
			return false;
	}

	/**
	 * @return Strasse
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @param Setzte
	 *            Strasse
	 */
	public boolean setStreet(String street) {
		if (street != null && !street.isEmpty()) {
			this.street = street;
			return true;
		} else
			return false;
	}

	/**
	 * @return Ansprechpartner
	 */
	public Person getContactPerson() {
		return contactPerson;
	}

	/**
	 * @param Setzte
	 *            Ansprechpartner
	 */
	public void setContactPerson(Person contactPerson) {
		this.contactPerson = contactPerson;
	}

	/**
	 * @return Name des Zentrum
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param Setze
	 *            Name
	 * 
	 */
	public boolean setName(String name) {
		if (name != null && !name.isEmpty()) {
			this.name = name;
			return true;
		} else
			return false;
	}

	/**
	 * Setzt das codierte Passwort
	 * 
	 * @param password
	 */

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gibt das codierte Passwort wieder
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 
	 * Codiert das Passwort
	 * 
	 * @param password1
	 * @param password2
	 * @return
	 */
	public boolean econde(String password1, String password2) {
		if (password1.equals(password2)) {
			ShaPasswordEncoder passwordEncoder = new ShaPasswordEncoder(256);
			setPassword(passwordEncoder.encodePassword(password1,
					"DSJGEWI8SDG46ASGJAER436234ERDFZHS9346"));
			return true;
		} else
			return false;

	}

	/**
	 * @return ID
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param Setzte
	 *            ID
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * 
	 * @return Legt fest, ob sich neue Benutzer selbst in einem Zentrum anmelden
	 *         duerfen
	 */
	public boolean isSelfRegistration() {
		return selfRegistration;
	}

	/**
	 * 
	 * @param Setzte
	 *            selfRegistration
	 */
	public void setSelfRegistration(boolean selfRegistration) {
		this.selfRegistration = selfRegistration;
	}
}