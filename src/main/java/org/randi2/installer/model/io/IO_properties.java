package org.randi2.installer.model.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import org.randi2.installer.controller.StatusService;
import org.randi2.installer.controller.configuration.Configuration;

/**
 * 
 * @author andreas Aendert Einstellungen in den Properties.
 */

public class IO_properties {
	private StatusService statusService;

	public IO_properties(StatusService statusService) {
		this.statusService = statusService;
	}

	public void infoData(Configuration conf) {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(
					conf.getServerPath()
							+ "webapps/RANDI2/WEB-INF/classes/META-INF/configuration.properties"));
		} catch (IOException e) {
			statusService.getAkt().setStatus(-1);
		}
		prop.setProperty("mail.from", conf.getMail_from());
		prop.setProperty("info.server", conf.getInfo_server());
		prop.setProperty("info.hoster", conf.getInfo_hoster());
		try {
			prop.store(
					new FileOutputStream(
							conf.getServerPath()
									+ "webapps/RANDI2/WEB-INF/classes/META-INF/configuration.properties"),
					null);
		} catch (IOException e) {
			statusService.getAkt().setStatus(-1);
		}
	}

	public void labelsGER(Configuration conf) {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(
					conf.getServerPath()
							+ "webapps/RANDI2/WEB-INF/classes/de/randi2/jsf/i18n/labels_de_DE.properties"));
		} catch (IOException e) {
			statusService.getAkt().setStatus(-1);
		}
		prop.setProperty("pages.registration.terms", conf.getDisclaimerGER());
		prop.setProperty("pages.aboutPopup.hostingInst",
				conf.getHostingInstGER());
		try {
			prop.store(
					new FileOutputStream(
							conf.getServerPath()
									+ "webapps/RANDI2/WEB-INF/classes/de/randi2/jsf/i18n/labels_de_DE.properties"),
					null);
		} catch (IOException e) {
			statusService.getAkt().setStatus(-1);
		}
	}

	public void labelsUS(Configuration conf) {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(
					conf.getServerPath()
							+ "webapps/RANDI2/WEB-INF/classes/de/randi2/jsf/i18n/labels_en_US.properties"));
		} catch (IOException e) {
			statusService.getAkt().setStatus(-1);
		}
		prop.setProperty("pages.registration.terms", conf.getDisclaimerUS());
		prop.setProperty("pages.aboutPopup.hostingInst",
				conf.getHostingInstUS());
		try {
			prop.store(
					new FileOutputStream(
							conf.getServerPath()
									+ "webapps/RANDI2/WEB-INF/classes/de/randi2/jsf/i18n/labels_en_US.properties"),
					null);
		} catch (IOException e) {
			statusService.getAkt().setStatus(-1);
		}
	}

	public void editWebsite(Configuration conf) {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(conf.getServerPath()
					+ "webapps/RANDI2/RANDI2.properties"));
		} catch (IOException e) {
			statusService.getAkt().setStatus(-1);
		}
		prop.setProperty("website2", conf.getWebsite());
		try {
			prop.store(new FileOutputStream(conf.getServerPath()
					+ "webapps/RANDI2/RANDI2.properties"), null);
		} catch (IOException e) {
			statusService.getAkt().setStatus(-1);
		}
	}

	public Properties loadProperties(URL pfad) {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(pfad.getFile()));
		} catch (IOException e) {
			statusService.getAkt().setStatus(-1);
		}
		return prop;
	}
}
