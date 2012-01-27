package org.randi2.installer.model;

import org.randi2.installer.utility.validations.EMailRANDI2Validator;
import org.randi2.installer.utility.validations.PasswordValidator;
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
	private String password;
	private Center center;
	private Gender sex;
	private long id;

	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @param firstname
	 *            the firstname to set
	 */
	public boolean setFirstname(String firstname) {
		if (firstname != null && !firstname.isEmpty()) {
			this.firstname = firstname;
			return true;
		} else
			return false;
	}

	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @param surname
	 *            the surname to set
	 */
	public boolean setSurname(String surname) {
		if (surname != null && !surname.isEmpty()) {
			this.surname = surname;
			return true;
		} else
			return false;
	}

	/**
	 * @return the academicTitle
	 */
	public String getAcademicTitle() {
		return academicTitle;
	}

	/**
	 * @param academicTitle
	 *            the academicTitle to set
	 */
	public void setAcademicTitle(String academicTitle) {
		this.academicTitle = academicTitle;
	}

	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * @param mail
	 *            the mail to set
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
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            the phone to set
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
	 * @return the mobil
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobil
	 *            the mobile to set
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
	 * @return the fax
	 */
	public String getFax() {
		return fax;
	}

	/**
	 * @param fax
	 *            the fax to set
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
	 * @return the center
	 */
	public Center getCenter() {
		return center;
	}

	/**
	 * @param center
	 *            the center to set
	 */
	public void setCenter(Center center) {
		this.center = center;
	}

	/**
	 * @return the sex
	 */
	public Gender getSex() {
		return sex;
	}

	/**
	 * @param male
	 *            the sex to set
	 */
	public void setSex(Gender sex) {
		this.sex = sex;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
