package org.randi2.installer;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.randi2.installer.model.Administrator;
import org.randi2.installer.model.enumerations.Gender;
import org.randi2.installer.model.services.AdministratorService;
import org.randi2.installer.model.services.DBService;
import org.junit.BeforeClass;
import org.junit.Test;

import org.randi2.installer.controller.Main;
import org.randi2.installer.controller.StatusService;
import org.randi2.installer.controller.configuration.DBConfiguration;

public class AdministratorServiceTest {

	private static Administrator ADMIN;
	private static AdministratorService ADMINSERVICE;
	private static DBService DBSERVICE;
	private static DBConfiguration DBCONF;
	private static StatusService STATUSSERVICE;
	private static Main MAIN;
	private static final String TITLE = "Dr,";
	private static final String PRELOCALE = "de";
	private static final String USERNAME = "lolaUser";
	private static final long ID = 1;
	private static final String PASSWORD1 = "1$password";
	private static final String EMAIL = "mail@test.de";
	private static final String FAX = "012334243";
	private static final String FIRSTNAME = "Hans";
	private static final String SURNAME = "Meyer";
	private static final String MOBILE = "0123123123";
	private static final String PHONE = "0123213123";
	private static final Gender SEX = Gender.MALE;

	@BeforeClass
	public static void setUpBeforeClass() {
		STATUSSERVICE = new StatusService();
		DBCONF = new DBConfiguration();
		DBCONF.setServer("127.0.0.1");
		DBCONF.setMySQL(true);
		DBCONF.setUsername("admin");
		DBCONF.setPassword("www", "www");
		DBSERVICE = new DBService(DBCONF, MAIN);
		ADMIN = new Administrator();
		ADMINSERVICE = new AdministratorService(DBSERVICE, STATUSSERVICE);
		ADMIN.setAcademicTitle(TITLE);
		ADMIN.setPrefLocale(PRELOCALE);
		ADMIN.setUsername(USERNAME);
		ADMIN.setPassword(PASSWORD1, PASSWORD1);
		ADMIN.setMail(EMAIL);
		ADMIN.setId(ID);
		ADMIN.setFax(FAX);
		ADMIN.setFirstname(FIRSTNAME);
		ADMIN.setSex(SEX);
		ADMIN.setPrefLocale(PRELOCALE);
		ADMIN.setSurname(SURNAME);
		ADMIN.setMobile(MOBILE);
		ADMIN.setPhone(PHONE);
	}

	@Test
	public void update() throws SQLException, IOException {
		// Datenbank mit Tabellen erstellen
		DBSERVICE.createDatabase(DBCONF);
		DBSERVICE.createUser(DBCONF);
		String url = ClassLoader.getSystemResource("").getFile()
				+ "randi2_073_InitData.sql";
		DBSERVICE.executeMySQLDBScript(url);
		ADMINSERVICE.update(ADMIN);

		// Positv Test

		// Testet, ob die Daten korrekt in die Tabelle Login eingetragen werden
		PreparedStatement ps = DBSERVICE.getConnection().prepareStatement(
				"SELECT prefLocale, username FROM Login WHERE person_id='"
						+ ADMIN.getId() + "'");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			assertEquals(rs.getString(1), PRELOCALE);
			assertEquals(rs.getString(2), USERNAME);
		}
		rs.close();

		// Testet, ob die Daten korrekt in die Tabelle Person eingetragen werden
		ps = DBSERVICE
				.getConnection()
				.prepareStatement(
						"SELECT email, fax, firstname, mobile, phone, sex, surname, title FROM Person WHERE id='"
								+ ADMIN.getId() + "'");
		rs = ps.executeQuery();
		while (rs.next()) {
			assertEquals(rs.getString(1), EMAIL);
			assertEquals(rs.getString(2), FAX);
			assertEquals(rs.getString(3), FIRSTNAME);
			assertEquals(rs.getString(4), MOBILE);
			assertEquals(rs.getString(5), PHONE);
			assertEquals(rs.getString(6), SEX.toString());
			assertEquals(rs.getString(7), SURNAME);
			assertEquals(rs.getString(8), TITLE);
		}
		rs.close();

		// Negativ Test
		// Ungueltige Eingaben nicht moeglich, da sie in der Klasse Administrtor
		// beim setzten ueberprueft werden.
	}
}
