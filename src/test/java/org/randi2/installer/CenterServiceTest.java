package org.randi2.installer;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.randi2.installer.model.Center;
import org.randi2.installer.model.ContactPerson;
import org.randi2.installer.model.enumerations.Gender;
import org.randi2.installer.model.services.CenterService;
import org.randi2.installer.model.services.DBService;
import org.junit.BeforeClass;
import org.junit.Test;

import org.randi2.installer.controller.Main;
import org.randi2.installer.controller.StatusService;
import org.randi2.installer.controller.configuration.DBConfiguration;

public class CenterServiceTest {

	private static Center CENTER;
	private static CenterService CENTERSERVICE;
	private static ContactPerson CONTACTPERSON;
	private static Main MAIN;
	private static DBService DBSERVICE;
	private static DBConfiguration DBCONF;
	private static StatusService STATUSSERVICE;
	private static final String TITLE = "Dr,";
	private static final long ID1 = 1;
	private static final String PASSWORD = "1$password";
	private static final String EMAIL = "mail@test.de";
	private static final String FAX = "012334243";
	private static final String FIRSTNAME = "Hans";
	private static final String SURNAME = "Meyer";
	private static final String MOBILE = "0123123123";
	private static final String PHONE = "0123213123";
	private static final Gender SEX = Gender.MALE;
	private static final String CITY = "Padeborn";
	private static final String COUNTRY = "Germany";
	private static final String NAME = "Zentrum";
	private static final String PLZ = "33100";
	private static final String STREET = "Mittelberg";

	@BeforeClass
	public static void setUpBeforeClass() {
		STATUSSERVICE = new StatusService();
		DBCONF = new DBConfiguration();
		DBCONF.setServer("127.0.0.1");
		DBCONF.setMySQL(true);
		DBCONF.setUsername("admin");
		DBCONF.setPassword("www", "www");
		DBSERVICE = new DBService(DBCONF, MAIN);
		CENTER = new Center();
		CENTERSERVICE = new CenterService(DBSERVICE, STATUSSERVICE);
		CONTACTPERSON = new ContactPerson();
		CONTACTPERSON.setAcademicTitle(TITLE);
		CONTACTPERSON.setMail(EMAIL);
		CONTACTPERSON.setFax(FAX);
		CONTACTPERSON.setFirstname(FIRSTNAME);
		CONTACTPERSON.setSex(SEX);
		CONTACTPERSON.setSurname(SURNAME);
		CONTACTPERSON.setMobile(MOBILE);
		CONTACTPERSON.setPhone(PHONE);
		CENTER.setPassword(PASSWORD, PASSWORD);
		CENTER.setCity(CITY);
		CENTER.setCountry(COUNTRY);
		CENTER.setName(NAME);
		CENTER.setPlz(PLZ);
		CENTER.setStreet(STREET);
		CENTER.setId(ID1);
		CENTER.setContactPerson(CONTACTPERSON);
	}

	@Test
	public void update() throws SQLException, IOException {
		// Datenbank mit Tabellen erstellen
		DBSERVICE.createDatabase(DBCONF);
		DBSERVICE.createUser(DBCONF);
		String url = ClassLoader.getSystemResource("").getFile()
				+ "randi2_073_InitData.sql";
		DBSERVICE.executeMySQLDBScript(url);
		CENTERSERVICE.update(CENTER);

		// Positv Test

		// Testet, ob die Daten korrekt in die Tabelle TrialSite eingetragen
		// werden
		PreparedStatement ps = DBSERVICE.getConnection().prepareStatement(
				"SELECT city, country, name, postcode, street, contactPerson_id FROM TrialSite WHERE id='"
						+ CENTER.getId() + "'");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			assertEquals(rs.getString(1), CITY);
			assertEquals(rs.getString(2), COUNTRY);
			assertEquals(rs.getString(3), NAME);
			assertEquals(rs.getString(4), PLZ);
			assertEquals(rs.getString(5), STREET);
			CONTACTPERSON.setId(rs.getLong(6));
		}
		rs.close();

		// Testet, ob die Daten korrekt in die Tabelle Person eingetragen werden
		ps = DBSERVICE
				.getConnection()
				.prepareStatement(
						"SELECT email, fax, firstname, mobile, phone, sex, surname, title FROM Person WHERE id='"
								+ CONTACTPERSON.getId() + "'");
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
