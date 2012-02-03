package org.randi2.installer.controller.configuration;

import java.util.Locale;
import java.util.Properties;
import org.randi2.installer.controller.Main;


public class Configuration {

	private String info_server;
	private String info_hoster;
	private String website;
	private String serverPath;
	private Properties lProp;
	private Locale language;
	private String randi2Path;
	private String jdbcPath;
	private String jafPath;
	private String mail_from;
	private String hostingInstGER;
	private String hostingInstUS;
	private String disclaimerGER;
	private String disclaimerUS;
	private String logoPath;

	/**
	 * @return Text zum Info Server
	 */
	public String getInfo_server() {
		return info_server;
	}

	/**
	 * @return Text zum Info Hoster
	 */
	public String getInfo_hoster() {
		return info_hoster;
	}

	/**
	 * @param Setzt Text zum Info Server
	 */
	public boolean setInfo_server(String info_server) {
		if (info_server != null && !info_server.isEmpty()) {
			this.info_server = info_server;
			return true;
		} else
			return false;
	}

	/**
	 * @param Setzt Text zum Info Hoster
	 */
	public boolean setInfo_hoster(String info_hoster) {
		if (info_hoster != null && !info_hoster.isEmpty()) {
			this.info_hoster = info_hoster;
			return true;
		} else
			return false;
	}

	/**
	 * @return Webseite des Unternehmens
	 */
	public String getWebsite() {
		return website;
	}

	/**
	 * @param Setzte Webseite
	 */
	public boolean setWebsite(String website) {
		if (website != null && !website.isEmpty()) {
			this.website = website;
			return true;
		} else
			return false;
	}

	/**
	 * @return Pfad zr Tomcat Installation
	 */
	public String getServerPath() {
		return serverPath;
	}

	/**
	 * @param  Setzte den Pfad zur Tomcat Installation
	 */
	public boolean setServerPath(String serverPath) {
		if (serverPath != null && !serverPath.equalsIgnoreCase("fail")
				&& !serverPath.isEmpty()) {
			this.serverPath = serverPath;
			return true;
		} else
			return false;
	}

	/**
	 * 
	 * @return Sprach Properties-Datei
	 */
	public Properties getlProp() {
		return this.lProp;
	}
	
	/**
	 * 
	 * @param language
	 * @param statusService
	 * 
	 * Lade Sprach Properties-Datei
	 */

	public void loadLanguageProperties(Locale language, Main main) {
		if (Locale.GERMANY.equals(language)) {
		lProp=	main.getProp().loadProperties(ClassLoader
					.getSystemResource("labels_de_DE.properties"));
			this.language = language;
		} else {
		lProp=	main.getProp().loadProperties(ClassLoader
					.getSystemResource("labels_us_US.properties"));
			this.language = language;
		}
	}

	/**
	 * @return Sprache
	 */
	public Locale getLanguage() {
		return language;
	}

	/**
	 * @param Setzte Sprache
	 */
	public void setLanguage(Locale language) {
		this.language = language;
	}

	/**
	 * @param Setze Properties der Sprachdatei
	 */
	public void setlProp(Properties lProp) {
		this.lProp = lProp;
	}

	/**
	 * @return Pfad zur RANDI2.war Datei
	 */
	public String getRandi2Path() {
		return randi2Path;
	}

	/**
	 * @param Setzte Pafad zur RANDI2.war Datei
	 */
	
	public boolean setRandi2Path(String randi2Path) {
		if (randi2Path != null && !randi2Path.equalsIgnoreCase("fail")
				&& !randi2Path.isEmpty() && randi2Path.endsWith("war")) {
			this.randi2Path = randi2Path;
			return true;
		} else
			return false;
	}

	/**
	 * @return Pfad zur JDBC.jar Datei
	 */
	public String getJDBCPath() {
		return jdbcPath;
	}

	/**
	 * @param Setzte Pfad zur JDBC.jar Datei
	 */
	public boolean setJDBCPath(String jdbcPath) {
		if (jdbcPath != null && !jdbcPath.equalsIgnoreCase("fail")
				&& !jdbcPath.isEmpty() && jdbcPath.endsWith("jar")) {
			this.jdbcPath = jdbcPath;
			return true;
		} else
			return false;
	}

	/**
	 * @return Pfad zur JAF.jar Datei
	 */
	public String getJAFPath() {
		return jafPath;
	}

	/**
	 * @param Setez Pfad zur JAF.jar Datei
	 */
	public boolean setJAFPath(String jafPath) {
		if (jafPath != null && !jafPath.equalsIgnoreCase("fail")
				&& !jafPath.isEmpty()) {
			this.jafPath = jafPath;
			return true;
		} else
			return false;
	}

	/**
	 * @return Absender E-Mail Adresse
	 */
	public String getMail_from() {
		return mail_from;
	}

	/**
	 * @param Setzte Absender E-Mail Adresse
	 */
	public boolean setMail_from(String mail_from) {
		if (mail_from != null && !mail_from.isEmpty()) {
			this.mail_from = mail_from;
			return true;
		} else
			return false;
	}

	/**
	 * @return deutschen Text zur Installation
	 */
	public String getHostingInstGER() {
		return hostingInstGER;
	}

	/**
	 * @param Setzte deutschen Text zur Installation
	 */
	public boolean setHostingInstGER(String hostingInstGER) {
		if (hostingInstGER != null && !hostingInstGER.isEmpty()) {
			this.hostingInstGER = hostingInstGER;
			return true;
		} else
			return false;
	}

	/**
	 * @return englischen Text zur Installation
	 */
	public String getHostingInstUS() {
		return hostingInstUS;
	}

	/**
	 * @param Setzte englischen Text zur Installation
	 */
	public boolean setHostingInstUS(String hostingInstUS) {
		if (hostingInstUS != null && !hostingInstUS.isEmpty()) {
			this.hostingInstUS = hostingInstUS;
			return true;
		} else
			return false;
	}

	/**
	 * @return Dislaimer deutsch
	 */
	public String getDisclaimerGER() {
		return disclaimerGER;
	}

	/**
	 * @param Setzte deutschen Disclaimer
	 */
	public boolean setDisclaimerGER(String disclaimerGER) {
		if (disclaimerGER != null && !disclaimerGER.isEmpty()) {
			this.disclaimerGER = disclaimerGER;
			return true;
		} else
			return false;
	}

	/**
	 * @return Dislaimer englisch
	 */
	public String getDisclaimerUS() {
		return disclaimerUS;
	}

	/**
	 * @param Setzte englischen Disclaimer
	 */
	public boolean setDisclaimerUS(String disclaimerUS) {
		if (disclaimerUS != null && !disclaimerUS.isEmpty()) {
			this.disclaimerUS = disclaimerUS;
			return true;
		} else
			return false;
	}

	/**
	 * @return Pfad zum Logo
	 */
	public String getLogoPath() {
		return logoPath;
	}

	/**
	 * @param Setzte Logo Pfad
	 */
	public boolean setLogoPath(String logoPath) {
		if (logoPath != null && !logoPath.equalsIgnoreCase("fail")
				&& !logoPath.isEmpty()) {
			this.logoPath = logoPath;
			return true;
		} else
			return false;
	}
}
