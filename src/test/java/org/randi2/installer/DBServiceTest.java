package org.randi2.installer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.randi2.installer.model.services.DBService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.mysql.jdbc.Statement;
import org.randi2.installer.controller.Main;
import org.randi2.installer.controller.configuration.DBConfiguration;

/**
 * 
 * @author andreas Die MySQL Datenbank muss laufen.
 */
public class DBServiceTest {

	private static DBService DBSERVICE;
	private static DBConfiguration DBCONF;
	private static Main MAIN;
	private static final String USERNAME_FAIL = "lola";

	@BeforeClass
	public static void seUpBeforeClass() {
		DBCONF = new DBConfiguration();
		DBCONF.setServer("127.0.0.1");
		DBCONF.setMySQL(true);
		DBCONF.setUsername("admin");
		DBCONF.setPassword("www", "www");
		DBSERVICE = new DBService(DBCONF, MAIN);
	}

	/**
	 * Loescht alle User und test, ob alle geloescht sind.
	 * 
	 * @throws SQLException
	 */

	@Before
	public void SetUPBefore() throws SQLException {

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
		// Lege User und DB an
		DBSERVICE.createUser(DBCONF);
		DBSERVICE.createDatabase(DBCONF);
		// Hole User
		PreparedStatement ps = DBSERVICE.getFirstConnection().prepareStatement(
				"SELECT user FROM mysql.user WHERE user='"
						+ DBCONF.getUsername() + "'");
		ResultSet rs = ps.executeQuery();
		String user = "";
		while (rs.next()) {
			user = user + rs.getString(1);
		}
		rs.close();

		// Testet, ob User korrekt angelegt wurde
		assertEquals(DBCONF.getUsername(), user);

		// Negativ Test, ob richtiger User angelegt wurde
		assertNotSame(DBCONF.getUsername(), USERNAME_FAIL);
	}
}
