package org.randi2.installer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.mysql.jdbc.Statement;
import org.randi2.installer.controller.Main;
import org.randi2.installer.controller.configuration.DBConfiguration;
import org.randi2.installer.services.DBService;

/**
 * 
 * @author andreas Die MySQL Datenbank muss laufen.
 */

public class DBServiceTest {

	private static DBService DBSERVICE;
	private static DBConfiguration DBCONF;
	private static Main MAIN;
	private static final String ADMIN = "admin";
	private static final String ROOT = "root";
	private static final String PASSWORD = "www";
	private static final String SERVER = "127.0.0.1";
	private static final String USERNAME_FAIL = "lola";

	@BeforeClass
	public static void seUpBeforeClass() {
		DBCONF = new DBConfiguration();
		DBCONF.setServer(SERVER);
		DBCONF.setMySQL(true);
		DBCONF.setUsernameCon(ROOT);
		DBCONF.setPasswordCon("", "");
		DBCONF.setUsername(ADMIN);
		DBCONF.setPassword(PASSWORD, PASSWORD);
		DBCONF.setName("randi2DB");
		MAIN = new Main();
		MAIN.setDbconf(DBCONF);
		DBSERVICE = new DBService(MAIN);
	}

	/**
	 * Loescht alle User und test, ob alle geloescht sind.
	 * 
	 * @throws SQLException
	 */

	@Before
	public void SetUpBefore() throws SQLException {
		String user = "user";
		do {
			PreparedStatement ps = DBSERVICE.getFirstConnection()
					.prepareStatement(
							"SELECT user FROM mysql.user WHERE user='"
									+ DBCONF.getUsername() + "'");
			ResultSet rs = ps.executeQuery();
			user = "";
			while (rs.next()) {
				user = user + rs.getString(1);
			}
			rs.close();
			if (!user.equals("")) {
				Statement st = (Statement) DBSERVICE.getFirstConnection()
						.createStatement();
				st.executeUpdate("DROP USER " + DBCONF.getUsername());
			} else
				assertEquals(user, "");
		} while (!user.equals(""));
	}

	@Test
	public void createUser() throws SQLException {
		// Alle User sind geloescht
		// Lege User an
		DBSERVICE.createUser(DBCONF);
		// Hole User
		PreparedStatement ps = DBSERVICE.getFirstConnection().prepareStatement(
				"SELECT user FROM mysql.user WHERE user='"
						+ DBCONF.getUsername() + "'");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			// Testet, ob User korrekt angelegt wurde
			assertEquals(DBCONF.getUsername(), rs.getString(1));

			// Negativ Test, ob richtiger User angelegt wurde
			assertNotSame(DBCONF.getUsername(), USERNAME_FAIL);
		}
		rs.close();

		// Negativ Test, ob richtiger User angelegt wurde
		assertNotSame(DBCONF.getUsername(), USERNAME_FAIL);
	}

	// Datenbank wird geloescht
	@Before
	public void setUpBefore() throws SQLException {
		Statement st = (Statement) DBSERVICE.getFirstConnection()
				.createStatement();
		st.executeUpdate("DROP DATABASE IF EXISTS randi2DB");
	}

	@Test
	public void createDatabse() throws SQLException {

		// Datenbank erstellen
		DBSERVICE.createDatabase(DBCONF);
		Statement st = (Statement) DBSERVICE.getFirstConnection()
				.createStatement();
		// Tabelle erstellen
		st.executeUpdate("CREATE TABLE randi2DB.Test (id int)");
		// Eintrag machen und schauen, ob es eingetragen wurde
		assertEquals(1,
				st.executeUpdate("Insert into randi2DB.Test (id) values (1)"));
	}

	@AfterClass
	public static void afterClassTest() throws SQLException {
		Statement st = (Statement) DBSERVICE.getFirstConnection()
				.createStatement();
		// Loescht Datenbank wieder
		st.executeUpdate("Drop Database randi2DB");
	}
}
