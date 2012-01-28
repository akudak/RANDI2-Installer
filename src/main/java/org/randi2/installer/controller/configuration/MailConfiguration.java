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
	 * @return Server IP
	 */
	public String getServer() {
		return server;
	}

	/**
	 * @param Setzte Server IP
	 */
	public boolean setServer(String server) {
		if (server != null && !server.isEmpty()) {
			this.server = server;
			return true;
		} else
			return false;
	}

	/**
	 * @return Benutzername
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param Setzte Benutzername
	 */
	public boolean setUsername(String username) {
		if (username != null && !username.isEmpty()) {
			this.username = username;
			return true;
		} else
			return false;
	}

	/**
	 * @return Password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param Setzte Password
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
	 * @return Pfad zur JMA Datei
	 */
	public String getJarPath() {
		return jarPath;
	}

	/**
	 * @param Pfad zur JMA Datei
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
