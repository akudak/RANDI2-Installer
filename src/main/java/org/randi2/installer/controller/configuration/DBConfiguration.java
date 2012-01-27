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
	private String initDBPath;

	/**
	 * @return the mysql
	 */
	public boolean isMySQL() {
		return mySQl;
	}

	/**
	 * @param mysql
	 *            the mysql to set
	 */
	public void setMySQL(boolean mySQl) {
		this.mySQl = mySQl;
	}

	/**
	 * @return the postgre
	 */
	public boolean isPostgre() {
		return postgre;
	}

	/**
	 * @param postgre
	 *            the postgre to set
	 */
	public void setPostgre(boolean postgre) {
		this.postgre = postgre;
	}

	/**
	 * @return the server
	 */
	public String getServer() {
		return server;
	}

	/**
	 * @param server
	 *            the server to set
	 */
	public boolean setServer(String server) {
		if (server != null && !server.isEmpty()) {
			this.server = server;
			return true;
		} else
			return false;
	}

	/**
	 * @return the user
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public boolean setUsername(String username) {
		if (username != null && !username.isEmpty()) {
			this.username = username;
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
		if (password1 != null && password1.equals(password2)
				&& !password1.isEmpty()) {
			this.password = password1;
			return true;
		} else
			return false;
	}

	/**
	 * @return the initDBPath
	 */
	public String getInitDBPath() {
		return initDBPath;
	}

	/**
	 * @param initDBPath
	 *            the initDBPath to set
	 */
	public boolean setInitDBPath(String initDBPath) {
		if (initDBPath != null && !initDBPath.equalsIgnoreCase("fail")
				&& !initDBPath.isEmpty() && initDBPath.endsWith("sql")) {
			this.initDBPath = initDBPath;
			return true;
		} else
			return false;
	}
}
