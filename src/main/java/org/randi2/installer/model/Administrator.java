package org.randi2.installer.model;

import org.randi2.installer.validations.PasswordValidator;
import org.springframework.security.providers.encoding.ShaPasswordEncoder;

/**
 * 
 * @author andreas Erzeugt ein Objekt der Klasse Administrator
 * 
 */

public class Administrator extends Person {

	private String username;
	private String prefLocale;
	private String password;
	/** The Constant MAX_USERNAME_LENGTH. */
	public final static int MAX_USERNAME_LENGTH = 100;

	/** The Constant MIN_USERNAME_LENGTH. */
	public final static int MIN_USERNAME_LENGTH = 5;

	/**
	 * @return Benutzername
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * 
	 * @param Setzte
	 *            Benutzername
	 * 
	 */
	public boolean setUsername(String username) {
		if (username.length() >= MIN_USERNAME_LENGTH
				&& username.length() <= MAX_USERNAME_LENGTH) {
			this.username = username;
			return true;
		} else
			return false;
	}

	/**
	 * @return Sprache fuer die DB
	 */
	public String getPrefLocale() {
		return prefLocale;
	}

	/**
	 * @param Setzet
	 *            Sprache
	 */
	public void setPrefLocale(String prefLocale) {
		this.prefLocale = prefLocale;
	}

	/**
	 * Setzt das codierte Passwort
	 * 
	 * @param password
	 */

	public boolean setPassword(String password) {
		PasswordValidator passwordValidator = new PasswordValidator();
		if(passwordValidator.isValid(password))
		{
		this.password = password;
		return true;
		}
		else
			return false;
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
		PasswordValidator passwordValidator = new PasswordValidator();
		if (password1 != null && !password1.isEmpty() && password1.equals(password2) && passwordValidator.isValid(password2)){
			ShaPasswordEncoder passwordEncoder = new ShaPasswordEncoder(256);
			setPassword(passwordEncoder.encodePassword(password1,
					this.getUsername()));
			return true;
		} else
			return false;

	}
}
