package org.randi2.installer.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import org.randi2.installer.controller.Main;
import org.randi2.installer.model.enumerations.StatusEnum;

/**
 * 
 * @author andreas Aendert Einstellungen in den Properties.
 */

public class IOProperties {
	private Main main;

	public IOProperties(Main main) {
		this.main = main;
	}

	/** 
	 * aendert Tomcat/webapps/RANDI2/WEB-INF/classes/META-INF/configuration.properties
	 */
	public void infoData() {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(
					main.getConf().getServerPath()
							+ "webapps/RANDI2/WEB-INF/classes/META-INF/configuration.properties"));
		} catch (IOException e) {
			main.getStatusService().getAkt().setStatus(StatusEnum.FAIL);
		}
		prop.setProperty("mail.from", main.getConf().getMail_from());
		prop.setProperty("info.server", main.getConf().getInfo_server());
		prop.setProperty("info.hoster", main.getConf().getInfo_hoster());
		try {
			prop.store(
					new FileOutputStream(
							main.getConf().getServerPath()
									+ "webapps/RANDI2/WEB-INF/classes/META-INF/configuration.properties"),
					null);
		} catch (IOException e) {
			main.getStatusService().getAkt().setStatus(StatusEnum.FAIL);
			main.getMainFrame().getStatusText().setText(main.getConf().getlProp()
					.getProperty("error.configuration"));
		}
	}

	/** 
	 * aendert Tomcat/webapps/RANDI2/WEB-INF/classes/de/randi2/jsf/i18n/labels_de_DE.properties
	 */
	public void labelsGER() {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(
					main.getConf().getServerPath()
							+ "webapps/RANDI2/WEB-INF/classes/de/randi2/jsf/i18n/labels_de_DE.properties"));
		} catch (IOException e) {
			main.getStatusService().getAkt().setStatus(StatusEnum.FAIL);
		}
		prop.setProperty("pages.registration.terms", main.getConf().getDisclaimerGER());
		prop.setProperty("pages.aboutPopup.hostingInst",
				main.getConf().getHostingInstGER());
		try {
			prop.store(
					new FileOutputStream(
							main.getConf().getServerPath()
									+ "webapps/RANDI2/WEB-INF/classes/de/randi2/jsf/i18n/labels_de_DE.properties"),
					null);
		} catch (IOException e) {
			main.getStatusService().getAkt().setStatus(StatusEnum.FAIL);
			main.getMainFrame().getStatusText().setText(main.getConf().getlProp()
					.getProperty("error.language"));
		}
	}
	
/**
 * aendert Tomcat/webapps/RANDI2/WEB-INF/classes/de/randi2/jsf/i18n/labels_en_US.properties
 */
	public void labelsUS() {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(
					main.getConf().getServerPath()
							+ "webapps/RANDI2/WEB-INF/classes/de/randi2/jsf/i18n/labels_en_US.properties"));
		} catch (IOException e) {
			main.getStatusService().getAkt().setStatus(StatusEnum.FAIL);
		}
		prop.setProperty("pages.registration.terms", main.getConf().getDisclaimerUS());
		prop.setProperty("pages.aboutPopup.hostingInst",
				main.getConf().getHostingInstUS());
		try {
			prop.store(
					new FileOutputStream(
							main.getConf().getServerPath()
									+ "webapps/RANDI2/WEB-INF/classes/de/randi2/jsf/i18n/labels_en_US.properties"),
					null);
		} catch (IOException e) {
			main.getStatusService().getAkt().setStatus(StatusEnum.FAIL);
			main.getMainFrame().getStatusText().setText(main.getConf().getlProp()
					.getProperty("error.language"));
		}
	}

	/**
	 * aender Tomcat/webapps/RANDI2/RANDI2.properties
	 */
	public void editWebsite() {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(main.getConf().getServerPath()
					+ "webapps/RANDI2/RANDI2.properties"));
		} catch (IOException e) {
			main.getStatusService().getAkt().setStatus(StatusEnum.FAIL);
		}
		prop.setProperty("website2", main.getConf().getWebsite());
		if(main.getCenter().isSelfRegistration())
		prop.setProperty("selfRegistration", "true");
		else
			prop.setProperty("selfRegistration", "false");
		try {
			prop.store(new FileOutputStream(main.getConf().getServerPath()
					+ "webapps/RANDI2/RANDI2.properties"), null);
		} catch (IOException e) {
			main.getStatusService().getAkt().setStatus(StatusEnum.FAIL);
			main.getMainFrame().getStatusText().setText(main.getConf().getlProp()
					.getProperty("error.language"));
		}
	}

	/**
	 * Laedet eine beliebige Properties-Datei und gibt sie zurueck
	 * @param pfad
	 * @return
	 */
	public Properties loadProperties(URL pfad) {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(pfad.getFile()));
		} catch (IOException e) {
			main.getStatusService().getAkt().setStatus(StatusEnum.FAIL);
			main.getMainFrame().getStatusText().setText(main.getConf().getlProp()
					.getProperty("error.loadFile"));
		}
		return prop;
	}
}
