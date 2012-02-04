package org.randi2.installer;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import org.randi2.installer.model.Center;
import org.randi2.installer.model.ContactPerson;
import org.randi2.installer.model.enumerations.Gender;
import org.randi2.installer.services.CenterService;
import org.randi2.installer.services.DBService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.randi2.installer.controller.Main;
import org.randi2.installer.controller.configuration.DBConfiguration;

public class CenterServiceTest {

	/**
	 * 
	 * Es muss eine MySQL Datengesartet sein, damit die Tests funktionieren.
	 */

	// Werte muessen der DB angepass werden
	private static final String DBUSER = "root";
	private static final String DBSERVER = "127.0.0.1";
	private static final String DBPASSWORT = "";
	private static final String DBNAME = "randi2DB";

	private static Center CENTER;
	private static CenterService CENTERSERVICE;
	private static ContactPerson CONTACTPERSON;
	private static DBService DBSERVICE;
	private static DBConfiguration DBCONF;
	private static Main MAIN;
	private static final String TITLE = "Dr,";
	private static final long ID1 = 1;
	private static final long ID2 = 2;
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
	private static final String POSTCODE = "33100";
	private static final String STREET = "Kamp";

	@BeforeClass
	public static void setUpBeforeClass() throws IOException, SQLException {
		MAIN = new Main();
		DBCONF = new DBConfiguration();
		DBCONF.setServer(DBSERVER);
		DBCONF.setMySQL(true);
		DBCONF.setUsername(DBUSER);
		DBCONF.setPassword(PASSWORD, PASSWORD);
		DBCONF.setUsernameCon(DBUSER);
		DBCONF.setPasswordCon(DBPASSWORT, DBPASSWORT);
		DBCONF.setName(DBNAME);
		MAIN.setDbconf(DBCONF);
		DBSERVICE = new DBService(MAIN);
		DBSERVICE.createDatabase(DBCONF);
		DBSERVICE.createUser(DBCONF);
		MAIN.setDbService(DBSERVICE);

		String url = ClassLoader.getSystemResource("").getFile()
				+ "randi2_073_InitData.sql";
		DBSERVICE.executeSQLDBScript(url);
		CENTER = new Center();
		CENTERSERVICE = new CenterService(MAIN);
		CONTACTPERSON = new ContactPerson();
		CONTACTPERSON.setAcademicTitle(TITLE);
		CONTACTPERSON.setMail(EMAIL);
		CONTACTPERSON.setFax(FAX);
		CONTACTPERSON.setFirstname(FIRSTNAME);
		CONTACTPERSON.setSex(SEX);
		CONTACTPERSON.setSurname(SURNAME);
		CONTACTPERSON.setMobile(MOBILE);
		CONTACTPERSON.setPhone(PHONE);
		CONTACTPERSON.setId(ID2);
		CENTER.econde(PASSWORD, PASSWORD);
		CENTER.setCity(CITY);
		CENTER.setCountry(COUNTRY);
		CENTER.setName(NAME);
		CENTER.setPostcode(POSTCODE);
		CENTER.setStreet(STREET);
		CENTER.setId(ID1);
		CENTER.setContactPerson(CONTACTPERSON);
	}

	@Test
	public void update() throws SQLException {

		// Hole Timestamp Login
		Timestamp tsTrialSite = null;
		PreparedStatement ps = DBSERVICE.getConnection().prepareStatement(
				"SELECT updatedAt FROM TrialSite WHERE id= '" + CENTER.getId()
						+ " ' ");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			tsTrialSite = rs.getTimestamp(1);
		}
		rs.close();

		// Hole Timestamp Person
		Timestamp tsPerson = null;
		ps = DBSERVICE.getConnection().prepareStatement(
				"SELECT updatedAt FROM PERSON WHERE id='"
						+ CONTACTPERSON.getId() + "'");
		rs = ps.executeQuery();
		while (rs.next()) {
			tsPerson = rs.getTimestamp(1);
		}
		rs.close();

		CENTERSERVICE.update(CENTER);

		// Positv Test

		// Testet, ob die Daten korrekt in die Tabelle TrialSite eingetragen
		// werden
		ps = DBSERVICE
				.getConnection()
				.prepareStatement(
						"SELECT city, country, name, postcode, street, contactPerson_id, updatedAt FROM TrialSite WHERE id='"
								+ CENTER.getId() + "'");
		rs = ps.executeQuery();
		while (rs.next()) {
			assertEquals(rs.getString(1), CITY);
			assertEquals(rs.getString(2), COUNTRY);
			assertEquals(rs.getString(3), NAME);
			assertEquals(rs.getString(4), POSTCODE);
			assertEquals(rs.getString(5), STREET);
			CONTACTPERSON.setId(rs.getLong(6));
			assertEquals(rs.getTimestamp(7).compareTo(tsTrialSite), 1);
		}
		rs.close();

		// Testet, ob die Daten korrekt in die Tabelle Person eingetragen werden
		ps = DBSERVICE
				.getConnection()
				.prepareStatement(
						"SELECT email, fax, firstname, mobile, phone, sex, surname, title, updatedAt FROM Person WHERE id='"
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
			assertEquals(rs.getTimestamp(9).compareTo(tsPerson), 1);
		}
		rs.close();

		// Negativ Test
		// Ungueltige Eingaben nicht moeglich, da sie in der Klasse Administrtor
		// beim Setzten ueberprueft werden.
	}
}
