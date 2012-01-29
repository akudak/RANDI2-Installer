package org.randi2.installer.model;

import org.randi2.installer.utility.validations.EMailRANDI2Validator;
import org.randi2.installer.utility.validations.TelephoneNumberValidator;
import org.randi2.installer.model.enumerations.Gender;

/**
 * 
 * @author andreas Verwaltet Objekte vom Typ Person
 */
public class Person {

	private String firstname;
	private String surname;
	private String academicTitle;
	private String mail;
	private String phone;
	private String mobile;
	private String fax;
	private Gender sex;
	private long id;

	/**
	 * @return Vorname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @param Setzte Vorname
	 */
	public boolean setFirstname(String firstname) {
		if (firstname != null && !firstname.isEmpty()) {
			this.firstname = firstname;
			return true;
		} else
			return false;
	}

	/**
	 * @return Nachname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @param Setzte Nachname
	 */
	public boolean setSurname(String surname) {
		if (surname != null && !surname.isEmpty()) {
			this.surname = surname;
			return true;
		} else
			return false;
	}

	/**
	 * @return Setzte Title
	 */
	public String getAcademicTitle() {
		return academicTitle;
	}

	/**
	 * @param Setzte Title
	 */
	public void setAcademicTitle(String academicTitle) {
		this.academicTitle = academicTitle;
	}

	/**
	 * @return E-Mail Adresse
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * @param Setzte E-Mail Adresse
	 */
	public boolean setMail(String mail) {
		EMailRANDI2Validator emailRANDI2Validator = new EMailRANDI2Validator();
		if (emailRANDI2Validator.isValid(mail)) {
			this.mail = mail;
			return true;
		}
		return false;

	}

	/**
	 * @return Telefonnummer
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param Setzte Telefonnummer
	 */
	public boolean setPhone(String phone) {
		{
			TelephoneNumberValidator telephoneNumberValidator = new TelephoneNumberValidator();
			if (telephoneNumberValidator.isValid(phone)) {
				this.phone = phone;
				return true;
			} else
				return false;
		}
	}

	/**
	 * @return Mobilfunknummer
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param Setzte Mobilfunknummer
	 */
	public boolean setMobile(String mobile) {
		{
			TelephoneNumberValidator telephoneNumberValidator = new TelephoneNumberValidator();
			if (telephoneNumberValidator.isValid(mobile)) {
				this.mobile = mobile;
				return true;
			} else
				return false;
		}

	}

	/**
	 * @return Faxnummer
	 */
	public String getFax() {
		return fax;
	}

	/**
	 * @param Setzte Faxnummer
	 */
	public boolean setFax(String fax) {
		{
			TelephoneNumberValidator telephoneNumberValidator = new TelephoneNumberValidator();
			if (telephoneNumberValidator.isValid(fax)) {
				this.fax = fax;
				return true;
			} else
				return false;
		}

	}


	/**
	 * @return Geschlecht
	 */
	public Gender getSex() {
		return sex;
	}

	/**
	 * @param Setzte Geschlecht
	 */
	public void setSex(Gender sex) {
		this.sex = sex;
	}

	/**
	 * 
	 * @return ID
	 */
	public long getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 * Setzte ID
	 */
	public void setId(long id) {
		this.id = id;
	}
}
