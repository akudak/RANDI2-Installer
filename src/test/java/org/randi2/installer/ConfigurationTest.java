package org.randi2.installer;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;
import org.randi2.installer.controller.configuration.Configuration;

public class ConfigurationTest {

	private static Configuration CONF;
	private final static String SERVERPATH = "/Users/Tomcat";
	private final static String EMPTY = "";
	private final static String FAIL = "FAIL";
	private final static String NULL = null;
	private final static String TEXT = "text";
	private final static String RANDI2PATH = "datei.war";
	private final static String JARPath = "datei.jar";

	@BeforeClass
	public static void setUpBeforeClass() {
		CONF = new Configuration();
	}

	@Test
	public void setServerPath() {
		// Positiv Test
		assertTrue(CONF.setServerPath(SERVERPATH));

		assertEquals(SERVERPATH, CONF.getServerPath());
		// Negativ Test

		// Setzet leeren String
		assertFalse(CONF.setServerPath(EMPTY));

		// Setzte fail (File Chooser setzt fail, wenn kein Pfad ausgewahlt wird
		assertFalse(CONF.setServerPath(FAIL));

		// Darf nicht null sein
		assertFalse(CONF.setServerPath(NULL));
	}

	@Test
	public void setInfo_server() {
		// Positiv Test
		assertTrue(CONF.setInfo_server(TEXT));

		// Schaue, ob es erfolgreich gesetzt wurde
		assertEquals(TEXT, CONF.getInfo_server());

		// Negativ Test

		// Setzte leeren String
		assertFalse(CONF.setInfo_server(EMPTY));

		// Darf nicht null sein
		assertFalse(CONF.setInfo_server(NULL));
	}

	@Test
	public void setInfo_hoster() {

		// Positiv Test
		assertTrue(CONF.setInfo_hoster(TEXT));

		// Schaue, ob es erfolgreich gesetzt wurde
		assertEquals(TEXT, CONF.getInfo_hoster());

		// Negativ Test

		// Setzte leeren String
		assertFalse(CONF.setInfo_hoster(EMPTY));

		// Darf nicht null sein
		assertFalse(CONF.setInfo_hoster(NULL));
	}

	@Test
	public void setWebsite() {

		// Positiv Test
		assertTrue(CONF.setWebsite(TEXT));

		// Schaue, ob es erfolgreich gesetzt wurde
		assertEquals(TEXT, CONF.getWebsite());

		// Negativ Test

		// Setzte leeren String
		assertFalse(CONF.setWebsite(EMPTY));

		// Darf nicht null sein
		assertFalse(CONF.setWebsite(NULL));
	}

	@Test
	public void setRandi2Path() {

		// Positiv Test
		assertTrue(CONF.setRandi2Path(RANDI2PATH));

		// Schaue, ob es erfolgreich gesetzt wurde
		assertEquals(RANDI2PATH, CONF.getRandi2Path());

		// Endet es auf war
		assertTrue(CONF.getRandi2Path().endsWith(".war"));
		// Negativ Test

		// Setzte leeren String
		assertFalse(CONF.setRandi2Path(EMPTY));

		// Darf nicht null sein
		assertFalse(CONF.setRandi2Path(NULL));

		// Setzte fail (File Chooser setzt fail, wenn kein Pfad ausgewahlt wird
		assertFalse(CONF.setServerPath(FAIL));
	}

	@Test
	public void setJDBCPath() {

		// Positiv Test
		assertTrue(CONF.setJDBCPath(JARPath));

		// Schaue, ob es erfolgreich gesetzt wurde
		assertEquals(JARPath, CONF.getJDBCPath());

		// Endet es auf jar
		assertTrue(CONF.getJDBCPath().endsWith("jar"));

		// Negativ Test

		// Setzte leeren String
		assertFalse(CONF.setJDBCPath(EMPTY));

		// Darf nicht null sein
		assertFalse(CONF.setJDBCPath(NULL));

		// Setzte fail (File Chooser setzt fail, wenn kein Pfad ausgewahlt wird
		assertFalse(CONF.setServerPath(FAIL));
	}

	@Test
	public void setJAFPath() {

		// Positiv Test
		assertTrue(CONF.setJAFPath(JARPath));

		// Schaue, ob es erfolgreich gesetzt wurde
		assertEquals(JARPath, CONF.getJAFPath());

		// Endet es auf jar
		assertTrue(CONF.getJAFPath().endsWith("jar"));

		// Negativ Test

		// Setzte leeren String
		assertFalse(CONF.setJAFPath(EMPTY));

		// Darf nicht null sein
		assertFalse(CONF.setJAFPath(NULL));

		// Setzte fail (File Chooser setzt fail, wenn kein Pfad ausgewahlt wird
		assertFalse(CONF.setServerPath(FAIL));
	}

	@Test
	public void setMail_from() {

		// Positiv Test
		assertTrue(CONF.setMail_from(TEXT));

		// Schaue, ob es erfolgreich gesetzt wurde
		assertEquals(TEXT, CONF.getMail_from());

		// Negativ Test

		// Setzte leeren String
		assertFalse(CONF.setMail_from(EMPTY));

		// Darf nicht null sein
		assertFalse(CONF.setMail_from(NULL));
	}

	@Test
	public void setHostingInstGER() {

		// Positiv Test
		assertTrue(CONF.setHostingInstGER(TEXT));

		// Schaue, ob es erfolgreich gesetzt wurde
		assertEquals(TEXT, CONF.getHostingInstGER());

		// Negativ Test

		// Setzte leeren String
		assertFalse(CONF.setHostingInstGER(EMPTY));

		// Darf nicht null sein
		assertFalse(CONF.setHostingInstGER(NULL));
	}

	@Test
	public void setHostingInstUS() {

		// Positiv Test
		assertTrue(CONF.setHostingInstUS(TEXT));

		// Schaue, ob es erfolgreich gesetzt wurde
		assertEquals(TEXT, CONF.getHostingInstUS());

		// Negativ Test

		// Setzte leeren String
		assertFalse(CONF.setHostingInstUS(EMPTY));

		// Darf nicht null sein
		assertFalse(CONF.setHostingInstUS(NULL));
	}

	@Test
	public void setDisclaimerGER() {

		// Positiv Test
		assertTrue(CONF.setDisclaimerGER(TEXT));

		// Schaue, ob es erfolgreich gesetzt wurde
		assertEquals(TEXT, CONF.getDisclaimerGER());

		// Negativ Test

		// Setzte leeren String
		assertFalse(CONF.setDisclaimerGER(EMPTY));

		// Darf nicht null sein
		assertFalse(CONF.setDisclaimerGER(NULL));
	}

	@Test
	public void setDisclaimerUS() {

		// Positiv Test
		assertTrue(CONF.setDisclaimerUS(TEXT));

		// Schaue, ob es erfolgreich gesetzt wurde
		assertEquals(TEXT, CONF.getDisclaimerUS());

		// Negativ Test

		// Setzte leeren String
		assertFalse(CONF.setDisclaimerUS(EMPTY));

		// Darf nicht null sein
		assertFalse(CONF.setDisclaimerUS(NULL));
	}

	@Test
	public void setLogoPath() {

		// Positiv Test
		assertTrue(CONF.setLogoPath(TEXT));

		// Schaue, ob es erfolgreich gesetzt wurde
		assertEquals(TEXT, CONF.getLogoPath());

		// Negativ Test

		// Setzte leeren String
		assertFalse(CONF.setLogoPath(EMPTY));

		// Darf nicht null sein
		assertFalse(CONF.setLogoPath(NULL));

		// Setzte fail (File Chooser setzt fail, wenn kein Pfad ausgewahlt wird
		assertFalse(CONF.setLogoPath(FAIL));
	}

}
