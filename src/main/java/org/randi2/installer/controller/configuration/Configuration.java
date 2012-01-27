package org.randi2.installer.controller.configuration;

import java.util.Properties;

import org.randi2.installer.controller.StatusService;

import org.randi2.installer.model.enumerations.*;
import org.randi2.installer.model.io.IO_properties;

public class Configuration {

	private String info_server;
	private String info_hoster;
	private String website;
	private String serverPath;
	private Properties lProp;
	private Language language;
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
	 * @return the info_server
	 */
	public String getInfo_server() {
		return info_server;
	}

	/**
	 * @return the info_hoster
	 */
	public String getInfo_hoster() {
		return info_hoster;
	}

	/**
	 * @param info_server
	 *            the info_server to set
	 */
	public boolean setInfo_server(String info_server) {
		if (info_server != null && !info_server.isEmpty()) {
			this.info_server = info_server;
			return true;
		} else
			return false;
	}

	/**
	 * @param info_hoster
	 *            the info_hoster to set
	 */
	public boolean setInfo_hoster(String info_hoster) {
		if (info_hoster != null && !info_hoster.isEmpty()) {
			this.info_hoster = info_hoster;
			return true;
		} else
			return false;
	}

	/**
	 * @return the website
	 */
	public String getWebsite() {
		return website;
	}

	/**
	 * @param webseite
	 *            the website to set
	 */
	public boolean setWebsite(String website) {
		if (website != null && !website.isEmpty()) {
			this.website = website;
			return true;
		} else
			return false;
	}

	/**
	 * @return the tomcatPath
	 */
	public String getServerPath() {
		return serverPath;
	}

	/**
	 * @param tomcatPath
	 *            the tomcatPath to set
	 */
	public boolean setServerPath(String serverPath) {
		if (serverPath != null && !serverPath.equalsIgnoreCase("fail")
				&& !serverPath.isEmpty()) {
			this.serverPath = serverPath;
			return true;
		} else
			return false;
	}

	public Properties getlProp() {
		return this.lProp;
	}

	public void loadProperties(Language language, StatusService statusService) {
		IO_properties io_prop = new IO_properties(statusService);
		if (Language.GER.equals(language)) {
			lProp = io_prop.loadProperties(ClassLoader
					.getSystemResource("labels_de_DE.properties"));
			this.language = language;
		} else {
			lProp = io_prop.loadProperties(ClassLoader
					.getSystemResource("labels_us_US.properties"));
			this.language = language;
		}
	}

	/**
	 * @return the language
	 */
	public Language getLanguage() {
		return language;
	}

	/**
	 * @param language
	 *            the language to set
	 */
	public void setLanguage(Language language) {
		this.language = language;
	}

	/**
	 * @param lProp
	 *            the lProp to set
	 */
	public void setlProp(Properties lProp) {
		this.lProp = lProp;
	}

	/**
	 * @return the randi2Path
	 */
	public String getRandi2Path() {
		return randi2Path;
	}

	/**
	 * @param randi2Path
	 *            the randi2Path to set
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
	 * @return the jDBCPath
	 */
	public String getJDBCPath() {
		return jdbcPath;
	}

	/**
	 * @param jDBCPath
	 *            the jDBCPath to set
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
	 * @return the jafPath
	 */
	public String getJAFPath() {
		return jafPath;
	}

	/**
	 * @param jafPath
	 *            the jafPath to set
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
	 * @return the mail_from
	 */
	public String getMail_from() {
		return mail_from;
	}

	/**
	 * @param mail_from
	 *            the mail_from to set
	 */
	public boolean setMail_from(String mail_from) {
		if (mail_from != null && !mail_from.isEmpty()) {
			this.mail_from = mail_from;
			return true;
		} else
			return false;
	}

	/**
	 * @return the hostingInstGER
	 */
	public String getHostingInstGER() {
		return hostingInstGER;
	}

	/**
	 * @param hostingInstGER
	 *            the hostingInstGER to set
	 */
	public boolean setHostingInstGER(String hostingInstGER) {
		if (hostingInstGER != null && !hostingInstGER.isEmpty()) {
			this.hostingInstGER = hostingInstGER;
			return true;
		} else
			return false;
	}

	/**
	 * @return the hostingInstUS
	 */
	public String getHostingInstUS() {
		return hostingInstUS;
	}

	/**
	 * @param hostingInstUS
	 *            the hostingInstUS to set
	 */
	public boolean setHostingInstUS(String hostingInstUS) {
		if (hostingInstUS != null && !hostingInstUS.isEmpty()) {
			this.hostingInstUS = hostingInstUS;
			return true;
		} else
			return false;
	}

	/**
	 * @return the disclaimerGER
	 */
	public String getDisclaimerGER() {
		return disclaimerGER;
	}

	/**
	 * @param disclaimerGER
	 *            the disclaimerGER to set
	 */
	public boolean setDisclaimerGER(String disclaimerGER) {
		if (disclaimerGER != null && !disclaimerGER.isEmpty()) {
			this.disclaimerGER = disclaimerGER;
			return true;
		} else
			return false;
	}

	/**
	 * @return the disclaimerUS
	 */
	public String getDisclaimerUS() {
		return disclaimerUS;
	}

	/**
	 * @param disclaimerUS
	 *            the disclaimerUS to set
	 */
	public boolean setDisclaimerUS(String disclaimerUS) {
		if (disclaimerUS != null && !disclaimerUS.isEmpty()) {
			this.disclaimerUS = disclaimerUS;
			return true;
		} else
			return false;
	}

	/**
	 * @return the logoPath
	 */
	public String getLogoPath() {
		return logoPath;
	}

	/**
	 * @param logoPath
	 *            the logoPath to set
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
