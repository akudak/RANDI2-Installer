package org.randi2.installer;

import static org.junit.Assert.*;

import org.randi2.installer.model.Person;
import org.junit.BeforeClass;
import org.junit.Test;

public class PersonTest {

	private static Person PERSON;
	private final static String PASSWORD_OK = "1$password";
	private final static String PASSWORD2_TOSHORT = "www";
	private final static String PASSWORD2_TOLONG = "wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww";
	private final static String EMPTY = "";
	private final static String NAME = "Lola";
	private final static String NULL = null;
	private final static String EMAIL = "lola@randi2.de";
	private final static String EMAIL_FAIL = "e@r";
	private final static String PHONE = "012213-312321";
	private final static String PHONE2 = "012213312321";
	private final static String PHONE_FAIL = "0.12a";

	@BeforeClass
	public static void setUpBeforeClass() {
		PERSON = new Person();
	}

	@Test
	public void setPassword() {
		// Positive Test
		assertTrue(PERSON.setPassword(PASSWORD_OK, PASSWORD_OK));

		// Schaue, ob es richtig gestezt wurde
		assertEquals(PASSWORD_OK, PERSON.getPassword());

		// Negativ Test

		// Passwort ist zu kurz
		assertFalse(PERSON.setPassword(PASSWORD2_TOSHORT, PASSWORD2_TOSHORT));

		// Passwort ist zu lang
		assertFalse(PERSON.setPassword(PASSWORD2_TOLONG, PASSWORD2_TOLONG));

		// Ungleiche passwoerter
		assertFalse(PERSON.setPassword(PASSWORD_OK, PASSWORD2_TOSHORT));

	}

	@Test
	public void setFirstname() {
		// Positiv Test
		assertTrue(PERSON.setFirstname(NAME));

		assertEquals(NAME, PERSON.getFirstname());
		// Negativ Test

		// Setzet leeren String
		assertFalse(PERSON.setFirstname(EMPTY));

		// Darf nicht null sein
		assertFalse(PERSON.setFirstname(NULL));
	}

	@Test
	public void setSurname() {
		// Positiv Test
		assertTrue(PERSON.setSurname(NAME));

		assertEquals(NAME, PERSON.getSurname());
		// Negativ Test

		// Setzet leeren String
		assertFalse(PERSON.setSurname(EMPTY));

		// Darf nicht null sein
		assertFalse(PERSON.setSurname(NULL));
	}

	@Test
	public void setMail() {
		// Positiv Test
		assertTrue(PERSON.setMail(EMAIL));

		assertEquals(EMAIL, PERSON.getMail());
		// Negativ Test

		// Setzet leeren String
		assertFalse(PERSON.setMail(EMPTY));

		// Darf nicht null sein
		assertFalse(PERSON.setMail(NULL));

		// Ungueltige EMAIl
		assertFalse(PERSON.setMail(EMAIL_FAIL));

		// Ungueltige EMAIl
		assertFalse(PERSON.setMail(NAME));

	}

	@Test
	public void setMobile() {
		// Positiv Test

		assertTrue(PERSON.setMobile(PHONE));
		assertTrue(PERSON.setMobile(PHONE2));

		assertEquals(PHONE2, PERSON.getMobile());
		// Negativ Test

		// Ungueltige EMAIl
		assertFalse(PERSON.setMobile(PHONE_FAIL));
	}

	@Test
	public void setFax() {
		// Positiv Test

		assertTrue(PERSON.setFax(PHONE));
		assertTrue(PERSON.setFax(PHONE2));

		assertEquals(PHONE2, PERSON.getFax());
		// Negativ Test

		// Ungueltige EMAIl
		assertFalse(PERSON.setFax(PHONE_FAIL));
	}

	@Test
	public void setPhone() {
		// Positiv Test

		assertTrue(PERSON.setPhone(PHONE));
		assertTrue(PERSON.setPhone(PHONE2));

		assertEquals(PHONE2, PERSON.getPhone());
		// Negativ Test

		// Ungueltige EMAIl
		assertFalse(PERSON.setPhone(PHONE_FAIL));
	}

}
