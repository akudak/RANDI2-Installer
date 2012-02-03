package org.randi2.installer.controller.configuration;

/**
 * 
 * @author andreas Speichert die Konfigurationen fuer die Datenbank
 */
public class DBConfiguration {

	private boolean mySQl;
	private boolean postgre;
	private String server;
	private String username;
	private String password;
	private String usernameCon;
	private String passwordCon;
	private String initDBPath;
	private boolean database;
	private String name;
	
	
	/**
	 * @return Gibt an, ob MySQL verwendet wird
	 */
	public boolean isMySQL() {
		return mySQl;
	}

	/**
	 * @param Setzte die Verwendung von MySQL
	 */
	public void setMySQL(boolean mySQl) {
		this.mySQl = mySQl;
	}

	/**
	 * @return Gibt an, ob PostgeSQL verwendet wird
	 */
	public boolean isPostgre() {
		return postgre;
	}

	/**
	 * @param Setzte Verwendung von PostgreSQL
	 */
	public void setPostgre(boolean postgre) {
		this.postgre = postgre;
	}

	/**
	 * @return Datenbank Server IP
	 */
	public String getServer() {
		return server;
	}

	/**
	 * @param Setzte Datenbank Server IP
	 */
	public boolean setServer(String server) {
		if (server != null && !server.isEmpty()) {
			this.server = server;
			return true;
		} else
			return false;
	}

	/**
	 * @return Datenbankbenutzer fuer Datenbank randi2DB
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param Setzet Datenbankbenutzer
	 */
	public boolean setUsername(String username) {
		if (username != null && !username.isEmpty()) {
			this.username = username;
			return true;
		} else
			return false;
	}

	/**
	 * @return DB Passwort fuer randi2DB
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param Setzte DB Passwort
	 */
	public boolean setPassword(String password1, String password2) {
		if (password1.equals(password2)) {
			this.password = password1;
			return true;
		} else
			return false;
	}

	/**
	 * Passwort fuer die Datenbank Verbidnung
	 * @return
	 */
	public String getPasswordCon() {
		return passwordCon;
	}

	public boolean setPasswordCon(String passwordCon1, String passwordCon2) {
		if (passwordCon1.equals(passwordCon2)) {
			this.passwordCon = passwordCon1;
			return true;
		} else
			return false;
	}

	/**
	 * Benutzername fuer die Datenbank Verbidnung
	 * @return
	 */
	public boolean setUsernameCon(String usernameCon) {
		if (usernameCon != null && !usernameCon.isEmpty()) {
			this.usernameCon = usernameCon;
			return true;
		} else
			return false;
	}
	
	public String getUsernameCon() {
		return usernameCon;
	}
	
	
	/**
	 * @return Pfad zur sql Datei
	 */
	public String getInitDBPath() {
		return initDBPath;
	}

	/**
	 * @param Setzte Pfad zur SQL Datei
	 */
	public boolean setInitDBPath(String initDBPath) {
		if (initDBPath != null && !initDBPath.equalsIgnoreCase("fail")
				&& !initDBPath.isEmpty() && initDBPath.endsWith("sql")) {
			this.initDBPath = initDBPath;
			return true;
		} else
			return false;
	}

	/**
	 * 
	 * @return Gibt an, ob bereits eine Datenbank verwendet wird
	 */
	public boolean isDatabase() {
		return database;
	}

	/**
	 * 
	 * @param Setzt, ob bereits eine Datenbank verwendet wird
	 */
	public void setDatabase(boolean database) {
		this.database = database;
	}

	public String getName() {
		return name;
	}

	public boolean setName(String name) {
		if (name != null && !name.isEmpty()) {
			this.name = name;
			return true;
		} else
			return false;
	}
}
