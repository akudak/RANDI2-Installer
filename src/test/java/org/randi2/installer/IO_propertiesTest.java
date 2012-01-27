package org.randi2.installer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import org.randi2.installer.model.io.IO_properties;
import org.junit.BeforeClass;
import org.junit.Test;
import org.randi2.installer.controller.StatusService;
import org.randi2.installer.controller.configuration.Configuration;

public class IO_propertiesTest {

	private static IO_properties IO_PROP;
	private static StatusService STATUSSERVICE;
	private static Configuration CONF;
	private static final String MAIL_FROM = "andreas";
	private static final String INFO_SERVER = "Randi2.de";
	private static final String INFO_HOSTER = "RANDI2 TEAM";
	private static final String PAGES_REGISTRTION_TERMS = "Disclaimer";
	private static final String PAGES_ABOUTPOPUP_HOSTINGINST = "HostingInst";
	private static final String WEBSITE = "mykudak.com";

	@BeforeClass
	public static void setUpBeforeClass() {
		STATUSSERVICE = new StatusService();
		CONF = new Configuration();
		CONF.setMail_from(MAIL_FROM);
		CONF.setInfo_server(INFO_SERVER);
		CONF.setInfo_hoster(INFO_HOSTER);
		CONF.setDisclaimerGER(PAGES_REGISTRTION_TERMS);
		CONF.setHostingInstGER(PAGES_ABOUTPOPUP_HOSTINGINST);
		CONF.setDisclaimerUS(PAGES_REGISTRTION_TERMS);
		CONF.setHostingInstUS(PAGES_REGISTRTION_TERMS);
		CONF.setServerPath(ClassLoader.getSystemResource("")
				.getFile());
		CONF.setWebsite(WEBSITE);
		IO_PROP = new IO_properties(STATUSSERVICE);
	}

	@Test
	public void infoData() throws FileNotFoundException, IOException {
		// Schreibe Datei
		IO_PROP.infoData(CONF);

		// Lese Datei
		Properties prop = new Properties();
		prop.load(new FileInputStream(
				CONF.getServerPath()
						+ "/webapps/RANDI2/WEB-INF/classes/META-INF/configuration.properties"));

		// Positv Test
		assertEquals(prop.getProperty("info.hoster"), INFO_HOSTER);
		assertEquals(prop.getProperty("info.server"), INFO_SERVER);
		assertEquals(prop.getProperty("mail.from"), MAIL_FROM);

		// Negativ Test
		assertNotSame(prop.getProperty("info.hoster"), INFO_SERVER);
		assertNotSame(prop.getProperty("info.server"), MAIL_FROM);
		assertNotSame(prop.getProperty("mail.from"), INFO_SERVER);
	}

	@Test
	public void labesGER() throws FileNotFoundException, IOException {
		IO_PROP.labelsGER(CONF);
		Properties prop = new Properties();
		prop.load(new FileInputStream(
				CONF.getServerPath()
						+ "/webapps/RANDI2/WEB-INF/classes/de/randi2/jsf/i18n/labels_de_DE.properties"));

		// Positv Test
		assertEquals(prop.getProperty("pages.registration.terms"),
				PAGES_REGISTRTION_TERMS);
		assertEquals(prop.getProperty("pages.aboutPopup.hostingInst"),
				PAGES_ABOUTPOPUP_HOSTINGINST);

		// Negativ Test
		assertNotSame(prop.getProperty("pages.registration.terms"),
				PAGES_ABOUTPOPUP_HOSTINGINST);
		assertNotSame(prop.getProperty("pages.aboutPopup.hostingInst"),
				PAGES_REGISTRTION_TERMS);
	}

	@Test
	public void labesUS() throws FileNotFoundException, IOException {
		IO_PROP.labelsUS(CONF);
		Properties prop = new Properties();
		prop.load(new FileInputStream(
				CONF.getServerPath()
						+ "/webapps/RANDI2/WEB-INF/classes/de/randi2/jsf/i18n/labels_de_DE.properties"));

		// Positv Test
		assertEquals(prop.getProperty("pages.registration.terms"),
				PAGES_REGISTRTION_TERMS);
		assertEquals(prop.getProperty("pages.aboutPopup.hostingInst"),
				PAGES_ABOUTPOPUP_HOSTINGINST);

		// Negativ Test
		assertNotSame(prop.getProperty("pages.registration.terms"),
				PAGES_ABOUTPOPUP_HOSTINGINST);
		assertNotSame(prop.getProperty("pages.aboutPopup.hostingInst"),
				PAGES_REGISTRTION_TERMS);
	}

	@Test
	public void editWebsite() throws FileNotFoundException, IOException {
		IO_PROP.editWebsite(CONF);
		Properties prop = new Properties();
		prop.load(new FileInputStream(CONF.getServerPath()
				+ "/webapps/RANDI2/RANDI2.properties"));

		// Positv Test
		assertEquals(prop.getProperty("website2"), WEBSITE);

		// Negativ Test
		assertNotSame(prop.getProperty("website2"), PAGES_REGISTRTION_TERMS);
	}

	@Test
	public void loadProperties() throws MalformedURLException {
		IO_PROP.editWebsite(CONF);
		URL url = new URL("file:" + CONF.getServerPath()
				+ "webapps/RANDI2/RANDI2.properties");
		Properties prop = new Properties();
		prop = IO_PROP.loadProperties(url);

		// Positv Test
		assertEquals(prop.getProperty("website2"), WEBSITE);

		// Negativ Test
		assertNotSame(prop.getProperty("website2"), PAGES_REGISTRTION_TERMS);
	}
}
