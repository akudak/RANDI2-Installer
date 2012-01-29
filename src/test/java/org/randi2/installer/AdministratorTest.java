package org.randi2.installer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import org.randi2.installer.model.Administrator;
import org.junit.BeforeClass;
import org.junit.Test;

public class AdministratorTest {

	private static Administrator ADMIN;
	private final static String USERNAME = "administrator@example.com";
	private final static String USERNAME2 = "USERNAME2";
	private final static String USERNAME_TOSHORT = " ";
	private final static String USERNAME_TOLONG = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
	private final static String SHA = "b110305f87eac883525c414568c3a847290039f30a3aa534b5e603a3ac604264";
	private final static String SHA_TOSHORT = "b110305f87eac883525c414568c3a8";
	private final static String PASSWORD = "1$password";
	private final static String PASSWORD2 = "1$heidelberg";

	@BeforeClass
	public static void setUpBeforeClass() {
		ADMIN = new Administrator();
	}

	@Test
	public void setUsername() {
		// Setzte Benutzername und vergleiche
		assertTrue(ADMIN.setUsername(USERNAME));
		assertEquals(ADMIN.getUsername(), USERNAME);
		assertNotSame(ADMIN.getUsername(), USERNAME2);

		// Setzte ungueltigen Benutzername
		// Erwate false
		assertFalse(ADMIN.setUsername(USERNAME_TOSHORT));
		assertFalse(ADMIN.setUsername(USERNAME_TOLONG));
	}

	@Test
	public void ecode() {
		// Setzt das Passwort und vergleich es mit dem SHA Wert.
		assertTrue(ADMIN.econde(PASSWORD, PASSWORD));
		assertEquals(ADMIN.getPassword(), SHA);

		// Setzte gueltigen SHA Wert
		assertTrue(ADMIN.setPassword(SHA));

		// Negativ Test

		// Setzte zwei ungleiche Passwoertet
		assertFalse(ADMIN.econde(PASSWORD, PASSWORD2));

		// Setzte zu kurzen SHA Wert

		assertFalse(ADMIN.setPassword(SHA_TOSHORT));

	}
}
