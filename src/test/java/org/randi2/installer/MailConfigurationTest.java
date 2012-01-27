package org.randi2.installer;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.randi2.installer.controller.configuration.MailConfiguration;

public class MailConfigurationTest {

	private static MailConfiguration MAILCONF;
	private final static String SERVERPATH = "/Users/Tomcat";
	private final static String EMPTY = "";
	private final static String NAME = "Lola";
	private final static String FAIL = "FAIL";
	private final static String NULL = null;
	private final static String PASSWORD1 = "1$password";
	private final static String PASSWORD2 = "1$password";
	private final static String JARPath = "datei.jar";

	@BeforeClass
	public static void setUpBeforeClass() {
		MAILCONF = new MailConfiguration();
	}

	@Test
	public void setServer() {
		// Positiv Test
		assertTrue(MAILCONF.setServer(SERVERPATH));

		assertEquals(SERVERPATH, MAILCONF.getServer());
		// Negativ Test

		// Setzet leeren String
		assertFalse(MAILCONF.setServer(EMPTY));

		// Darf nicht null sein
		assertFalse(MAILCONF.setServer(NULL));
	}

	@Test
	public void setUsername() {
		// Positiv Test
		assertTrue(MAILCONF.setUsername(NAME));

		assertEquals(NAME, MAILCONF.getUsername());
		// Negativ Test

		// Setzet leeren String
		assertFalse(MAILCONF.setUsername(EMPTY));

		// Darf nicht null sein
		assertFalse(MAILCONF.setUsername(NULL));
	}

	@Test
	public void setPassword() {
		// Positiv Test
		assertTrue(MAILCONF.setPassword(PASSWORD1, PASSWORD2));

		assertEquals(PASSWORD1, MAILCONF.getPassword());
		// Negativ Test

		// Setzet leeren String
		assertFalse(MAILCONF.setPassword(EMPTY, EMPTY));

		// Darf nicht null sein
		assertFalse(MAILCONF.setPassword(NULL, NULL));
	}

	@Test
	public void setJarPath() {

		// Positiv Test
		assertTrue(MAILCONF.setJarPath(JARPath));

		// Schaue, ob es erfolgreich gesetzt wurde
		assertEquals(JARPath, MAILCONF.getJarPath());

		// Endet es auf jar
		assertTrue(MAILCONF.getJarPath().endsWith("jar"));

		// Negativ Test

		// Setzte leeren String
		assertFalse(MAILCONF.setJarPath(EMPTY));

		// Darf nicht null sein
		assertFalse(MAILCONF.setJarPath(NULL));

		// Setzte fail (File Chooser setzt fail, wenn kein Pfad ausgewahlt wird
		assertFalse(MAILCONF.setJarPath(FAIL));
	}
}
