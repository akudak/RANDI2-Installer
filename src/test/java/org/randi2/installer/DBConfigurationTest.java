package org.randi2.installer;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.randi2.installer.controller.configuration.DBConfiguration;

public class DBConfigurationTest {

	private final static String SERVERPATH = "/Users/Tomcat";
	private final static String EMPTY = "";
	private final static String NAME = "Lola";
	private final static String NULL = null;
	private final static String FAIL = "fail";
	private final static String PASSWORD1 = "1$password";
	private final static String PASSWORD2 = "1$password";
	private final static String SQLPATH = "datei.sql";
	private static DBConfiguration DBCONF;

	@BeforeClass
	public static void setUpBeforeClass() {
		DBCONF = new DBConfiguration();
	}

	@Test
	public void setServer() {
		// Positiv Test
		assertTrue(DBCONF.setServer(SERVERPATH));

		assertEquals(SERVERPATH, DBCONF.getServer());
		// Negativ Test

		// Setzet leeren String
		assertFalse(DBCONF.setServer(EMPTY));

		// Darf nicht null sein
		assertFalse(DBCONF.setServer(NULL));
	}

	@Test
	public void setUsername() {
		// Positiv Test
		assertTrue(DBCONF.setUsername(NAME));

		assertEquals(NAME, DBCONF.getUsername());
		// Negativ Test

		// Setzet leeren String
		assertFalse(DBCONF.setUsername(EMPTY));

		// Darf nicht null sein
		assertFalse(DBCONF.setUsername(NULL));
	}

	@Test
	public void setPassword() {
		// Positiv Test
		assertTrue(DBCONF.setPassword(PASSWORD1, PASSWORD2));

		assertEquals(PASSWORD1, DBCONF.getPassword());
		// Negativ Test

		// Setzet leeren String
		assertFalse(DBCONF.setPassword(EMPTY, EMPTY));

		// Darf nicht null sein
		assertFalse(DBCONF.setPassword(NULL, NULL));
	}

	public void setInitDBPath() {
		// Positiv Test
		assertTrue(DBCONF.setInitDBPath(SQLPATH));

		// Schaue, ob es erfolgreich gesetzt wurde
		assertEquals(DBCONF, DBCONF.getInitDBPath());

		// Endet es auf jar
		assertTrue(DBCONF.getInitDBPath().endsWith(".sql"));

		// Negativ Test

		// Setzte leeren String
		assertFalse(DBCONF.setInitDBPath(EMPTY));

		// Darf nicht null sein
		assertFalse(DBCONF.setInitDBPath(NULL));

		// Setzte fail (File Chooser setzt fail, wenn kein Pfad ausgewahlt wird
		assertFalse(DBCONF.setInitDBPath(FAIL));
	}

}
