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
	private final static String USERNAME = "USERNAME";
	private final static String USERNAME2 = "USERNAME2";
	private final static String USERNAME_TOSHORT = " ";
	private final static String USERNAME_TOLONG = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";

	@BeforeClass
	public static void setUpBeforeClass() {
		ADMIN = new Administrator();
	}

	@Test
	public void setUsername() {

		assertTrue(ADMIN.setUsername(USERNAME));
		assertEquals(ADMIN.getUsername(), USERNAME);
		assertNotSame(ADMIN.getUsername(), USERNAME2);
		assertFalse(ADMIN.setUsername(USERNAME_TOSHORT));
		assertFalse(ADMIN.setUsername(USERNAME_TOLONG));
	}
}
