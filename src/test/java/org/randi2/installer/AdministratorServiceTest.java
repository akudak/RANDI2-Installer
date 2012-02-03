package org.randi2.installer;

import static org.junit.Assert.assertEquals;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import org.randi2.installer.model.Administrator;
import org.randi2.installer.model.enumerations.Gender;
import org.randi2.installer.services.AdministratorService;
import org.randi2.installer.services.DBService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.randi2.installer.controller.Main;
import org.randi2.installer.controller.configuration.DBConfiguration;

public class AdministratorServiceTest {

	private static DBService DBSERVICE;
	private static DBConfiguration DBCONF;
	private static Main MAIN;
	private static Administrator ADMIN;
	private static final String NAME = "admin";
	private static final String ROOT = "root";
	private static final String PASSWORD = "www";
	private static final String SERVER = "127.0.0.1";
	private static AdministratorService ADMINSERVICE = new AdministratorService(
			MAIN);

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
	public static void setUpBeforeClass() throws IOException, SQLException {
		MAIN = new Main();
		DBCONF = new DBConfiguration();
		DBCONF.setServer(SERVER);
		DBCONF.setMySQL(true);
		DBCONF.setUsername(NAME);
		DBCONF.setPassword(PASSWORD, PASSWORD);
		DBCONF.setUsernameCon(ROOT);
		DBCONF.setPasswordCon("", "");
		DBCONF.setName("randi2DB");
		MAIN.setDbconf(DBCONF);
		ADMINSERVICE = new AdministratorService(MAIN);
		DBSERVICE = new DBService(MAIN);
		DBSERVICE.createDatabase(DBCONF);
		DBSERVICE.createUser(DBCONF);
		MAIN.setDbService(DBSERVICE);

		String url = ClassLoader.getSystemResource("").getFile()
				+ "randi2_073_InitData.sql";
		DBSERVICE.executeSQLDBScript(url);
		ADMIN = new Administrator();
		ADMIN.setFax(FAX);
		ADMIN.econde(PASSWORD1, PASSWORD1);
		ADMIN.setUsername(USERNAME);
		ADMIN.setId(ID);
		ADMIN.setMail(EMAIL);
		ADMIN.setFirstname(FIRSTNAME);
		ADMIN.setMobile(MOBILE);
		ADMIN.setPhone(PHONE);
		ADMIN.setSex(SEX);
		ADMIN.setSurname(SURNAME);
		ADMIN.setAcademicTitle(TITLE);
	}

	@Test
	public void update() throws SQLException {

		// Hole Timestamp Login
		Timestamp tsLogin = null;
		PreparedStatement ps = DBSERVICE.getConnection().prepareStatement(
				"SELECT updatedAt FROM Login WHERE person_id='" + ADMIN.getId()
						+ "'");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			tsLogin = rs.getTimestamp(1);
		}
		rs.close();

		// Hole Timestamp Person
		Timestamp tsPerson = null;
		ps = DBSERVICE.getConnection()
				.prepareStatement(
						"SELECT updatedAt FROM PERSON WHERE id='"
								+ ADMIN.getId() + "'");
		rs = ps.executeQuery();
		while (rs.next()) {
			tsPerson = rs.getTimestamp(1);
		}
		rs.close();
		// Admin mit eingetragenden Werten Updaten
		ADMINSERVICE.update(ADMIN);

		// Positv Test

		// Testet, ob die Daten korrekt in die Tabelle Login eingetragen werden
		// Testet zudem, ob der neue Timestamp nach dem alten liegt.
		ps = DBSERVICE.getConnection().prepareStatement(
				"SELECT prefLocale, username, updatedAt FROM Login WHERE person_id='"
						+ ADMIN.getId() + "'");
		rs = ps.executeQuery();
		while (rs.next()) {
			assertEquals(rs.getString(1), PRELOCALE);
			assertEquals(rs.getString(2), USERNAME);
			assertEquals(rs.getTimestamp(3).compareTo(tsLogin), 1);
		}
		rs.close();

		// Testet, ob die Daten korrekt in die Tabelle Person eingetragen werden
		ps = DBSERVICE
				.getConnection()
				.prepareStatement(
						"SELECT email, fax, firstname, mobile, phone, sex, surname, title, updatedAt FROM Person WHERE id='"
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
			assertEquals(rs.getTimestamp(9).compareTo(tsPerson), 1);
		}
		rs.close();

		// Negativ Test
		// Ungueltige Eingaben nicht moeglich, da sie in der Klasse Administrtor
		// beim Setzten ueberprueft werden.

	}

}
