package org.randi2.installer.model;

/**
 * 
 * @author andreas Erzeugt ein Objekt der Klasse Administrator
 * 
 */

public class Administrator extends Person {

	private String username;
	private String prefLocale;

	/** The Constant MAX_USERNAME_LENGTH. */
	public final static int MAX_USERNAME_LENGTH = 100;

	/** The Constant MIN_USERNAME_LENGTH. */
	public final static int MIN_USERNAME_LENGTH = 5;

	
	public Administrator()
	{
		
	}
	/**
	 * @return Benutzername
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * 
	 * @param Setzte Benutzername
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
	 * @param Setzet Sprache
	 */
	public void setPrefLocale(String prefLocale) {
		this.prefLocale = prefLocale;
	}

}
