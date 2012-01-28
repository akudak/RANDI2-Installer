package org.randi2.installer.model.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.randi2.installer.model.Administrator;
import org.randi2.installer.model.Person;
import org.randi2.installer.controller.Main;

/**
 * 
 * @author andreas Speichert den Admistrator in die Datenbank
 */
public class AdministratorService {

	private Main main;

	public AdministratorService(Main main) {
		this.main = main;
	}

	public Connection getConnection() {
		return main.getDbService().getConnection();
	}

	/**
	 * Updatet die Tabellen Login und Person mit den Daten des Amdinistrators
	 * 
	 * @param person
	 */
	public void update(Person person) {
		Administrator admin = (Administrator) person;
		PreparedStatement pStatement;
		try {
			pStatement = getConnection()
					.prepareStatement(
							"UPDATE Login SET password = ?, prefLocale = ?, username = ? WHERE person_id = ?");
			pStatement.setString(1, admin.getPassword());
			pStatement.setString(2, admin.getPrefLocale());
			pStatement.setString(3, admin.getUsername());
			pStatement.setLong(4, admin.getId());
			pStatement.executeUpdate();
			pStatement.close();

			pStatement = getConnection()
					.prepareStatement(
							"Update Person SET email = ?, fax = ?, firstname = ?, mobile = ?, phone = ?, sex = ?, surname = ?, title = ? WHERE id = ?");
			pStatement.setString(1, admin.getMail());
			pStatement.setString(2, admin.getFax());
			pStatement.setString(3, admin.getFirstname());
			pStatement.setString(4, admin.getMobile());
			pStatement.setString(5, admin.getPhone());
			pStatement.setString(6, "" + admin.getSex());
			pStatement.setString(7, admin.getSurname());
			pStatement.setString(8, admin.getAcademicTitle());
			pStatement.setLong(9, admin.getId());
			pStatement.executeUpdate();
			pStatement.close();
		} catch (SQLException e) {
			main.getStatusService().getAkt().setStatus(-1);
			main.getMainFrame()
					.getStatusText()
					.setText(
							main.getConf().getlProp()
									.getProperty("error.datenbankUpdate"));
		}
	}
}
