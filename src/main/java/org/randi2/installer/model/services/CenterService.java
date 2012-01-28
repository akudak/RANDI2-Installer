package org.randi2.installer.model.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
							"UPDATE TrialSite SET updatedAt = ?, password = ?, city = ?, country = ?, name=?, postcode=?, street=? WHERE id = ?");
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			String dateS = dateFormat.format(date);
			 
			pStatement.setString(1, dateS);
			pStatement.setString(2, center.getPassword());
			pStatement.setString(3, center.getCity());
			pStatement.setString(4, center.getCountry());
			pStatement.setString(5, center.getName());
			pStatement.setString(6, center.getPostcode());
			pStatement.setString(7, center.getStreet());
			pStatement.setLong(8, 1);
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
							"Update Person SET updatedAt = ?, email = ?, fax = ?, firstname = ?, mobile = ?, phone = ?, sex = ?, surname = ?, title = ? WHERE id = ?");
			pStatement.setString(1, dateS);
			pStatement.setString(2, center.getContactPerson().getMail());
			pStatement.setString(3, center.getContactPerson().getFax());
			pStatement.setString(4, center.getContactPerson().getFirstname());
			pStatement.setString(5, center.getContactPerson().getMobile());
			pStatement.setString(6, center.getContactPerson().getPhone());
			pStatement.setString(7, "" + center.getContactPerson().getSex());
			pStatement.setString(8, center.getContactPerson().getSurname());
			pStatement.setString(9, center.getContactPerson()
					.getAcademicTitle());
			pStatement.setLong(10, center.getContactPerson().getId());
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
