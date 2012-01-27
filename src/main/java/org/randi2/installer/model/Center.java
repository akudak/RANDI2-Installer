package org.randi2.installer.model;

import org.randi2.installer.utility.validations.PasswordValidator;

/**
 * 
 * @author andreas Erzeugt ein Objekt der Klasse Center
 */
public class Center {

	private String country;
	private String city;
	private String plz;
	private String street;
	private String name;
	private Person contactPerson;
	private String password;
	private long id;

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public boolean setCountry(String country) {
		if (country != null && !country.isEmpty()) {
			this.country = country;
			return true;
		} else
			return false;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public boolean setCity(String city) {
		if (city != null && !city.isEmpty()) {
			this.city = city;
			return true;
		} else
			return false;
	}

	/**
	 * @return the plz
	 */
	public String getPlz() {
		return plz;
	}

	/**
	 * @param plz
	 *            the plz to set
	 */
	public boolean setPlz(String plz) {
		if (plz != null && !plz.isEmpty()) {
			this.plz = plz;
			return true;
		} else
			return false;
	}

	/**
	 * @return the streat
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @param streat
	 *            the streat to set
	 */
	public boolean setStreet(String street) {
		if (street != null && !street.isEmpty()) {
			this.street = street;
			return true;
		} else
			return false;
	}

	/**
	 * @return the ansprechpartner
	 */
	public Person getContactPerson() {
		return contactPerson;
	}

	/**
	 * @param ansprechpartner
	 *            the ansprechpartner to set
	 */
	public void setContactPerson(Person contactPerson) {
		this.contactPerson = contactPerson;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public boolean setName(String name) {
		if (name != null && !name.isEmpty()) {
			this.name = name;
			return true;
		} else
			return false;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public boolean setPassword(String password1, String password2) {
		PasswordValidator passwordValidator = new PasswordValidator();
		if (password1.equals(password2) && passwordValidator.isValid(password2)) {
			this.password = password1;
			return true;
		} else
			return false;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

}