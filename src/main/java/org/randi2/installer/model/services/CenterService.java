package org.randi2.installer.model.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.randi2.installer.model.Center;
import org.randi2.installer.controller.Main;


/**
 * 
 * @author andreas Speichert das Center in der Datenbank.
 */
public class CenterService {

	private Main main;

	public CenterService(Main main) {
		this.main = main;
	}

	public Connection getConnection() {
		return main.getDbService().getConnection();
	}

	/**
	 * Updatet die Tabellen TrialSite und Person
	 * @param center
	 */
	public void update(Center center) {
		PreparedStatement pStatement;
		try {
			pStatement = getConnection()
					.prepareStatement(
							"UPDATE TrialSite SET password = ?, city = ?, country = ?, name=?, postcode=?, street=? WHERE id = ?");
			pStatement.setString(1, center.getPassword());
			pStatement.setString(2, center.getCity());
			pStatement.setString(3, center.getCountry());
			pStatement.setString(4, center.getName());
			pStatement.setString(5, center.getPostcode());
			pStatement.setString(6, center.getStreet());
			pStatement.setLong(7, 1);
			pStatement.executeUpdate();
			pStatement.close();

			PreparedStatement ps = main
					.getDbService()
					.getConnection()
					.prepareStatement(
							"SELECT contactPerson_id FROM TrialSite WHERE id='"
									+ center.getId() + "'");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				center.getContactPerson().setId(rs.getLong(1));
			}
			rs.close();

			pStatement = getConnection()
					.prepareStatement(
							"Update Person SET email = ?, fax = ?, firstname = ?, mobile = ?, phone = ?, sex = ?, surname = ?, title = ? WHERE id = ?");
			pStatement.setString(1, center.getContactPerson().getMail());
			pStatement.setString(2, center.getContactPerson().getFax());
			pStatement.setString(3, center.getContactPerson().getFirstname());
			pStatement.setString(4, center.getContactPerson().getMobile());
			pStatement.setString(5, center.getContactPerson().getPhone());
			pStatement.setString(6, "" + center.getContactPerson().getSex());
			pStatement.setString(7, center.getContactPerson().getSurname());
			pStatement.setString(8, center.getContactPerson()
					.getAcademicTitle());
			pStatement.setLong(9, center.getContactPerson().getId());
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
