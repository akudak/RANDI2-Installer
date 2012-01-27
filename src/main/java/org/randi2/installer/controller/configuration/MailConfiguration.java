package org.randi2.installer.controller.configuration;

/**
 * 
 * @author andreas Speichert Konfiguerationen zum Mail Server
 */
public class MailConfiguration {

	private String server;
	private String username;
	private String password;
	private String jarPath;

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
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
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
		if (password1 != null && !password1.isEmpty()
				&& password1.equals(password2)) {
			this.password = password1;
			return true;
		} else
			return false;
	}

	/**
	 * @return the jarPath
	 */
	public String getJarPath() {
		return jarPath;
	}

	/**
	 * @param jarPath
	 *            the jarPath to set
	 */
	public boolean setJarPath(String jarPath) {
		if (jarPath != null && !jarPath.equalsIgnoreCase("fail")
				&& !jarPath.isEmpty() && jarPath.endsWith("jar")) {
			this.jarPath = jarPath;
			return true;
		} else
			return false;
	}
}
