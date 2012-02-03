package org.randi2.installer.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import org.randi2.installer.model.Administrator;
import org.randi2.installer.model.Person;
import org.randi2.installer.model.enumerations.StatusEnum;
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

	/**
	 * Updatet die Tabellen Login und Person mit den Daten des Amdinistrators
	 * 
	 * @param person
	 */
	public void update(Person person) {
		Administrator admin = (Administrator) person;
		PreparedStatement pStatement;

		if (System.getProperty("user.language").equals("de")) {
			admin.setPrefLocale("de");
		} else {
			admin.setPrefLocale("us");
		}
		try {
			/**
			 * Hole zu erst die ID
			 */
			PreparedStatement ps = main.getDbService().getConnection()
					.prepareStatement("SELECT person_id FROM Login");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				admin.setId(rs.getLong(1));
			}
			rs.close();

			/**
			 * Wenn Eintrag vorhanden, aktuallisere den Admin und den Login.
			 */
			if (admin.getId() != -1) {
				Timestamp ts = new Timestamp(new Date().getTime());
				pStatement = main
						.getDbService()
						.getConnection()
						.prepareStatement(
								"Update Login SET updatedAt = ?, password = ?, prefLocale = ?, username = ? where person_id = ? ");
				pStatement.setTimestamp(1, ts);
				pStatement.setString(2, admin.getPassword());
				pStatement.setString(3, admin.getPrefLocale());
				pStatement.setString(4, admin.getUsername());
				pStatement.setLong(5, admin.getId());
				pStatement.executeUpdate();
				pStatement.close();

				pStatement = main
						.getDbService()
						.getConnection()
						.prepareStatement(
								"Update Person SET updatedAt =?, email = ?, fax = ?, firstname = ?, mobile = ?, phone = ?, sex = ?, surname = ?, title = ? where id =?");
				pStatement.setTimestamp(1, ts);
				pStatement.setString(2, admin.getMail());
				pStatement.setString(3, admin.getFax());
				pStatement.setString(4, admin.getFirstname());
				pStatement.setString(5, admin.getMobile());
				pStatement.setString(6, admin.getPhone());
				pStatement.setString(7, "" + admin.getSex());
				pStatement.setString(8, admin.getSurname());
				pStatement.setString(9, admin.getAcademicTitle());
				pStatement.setLong(10, admin.getId());
				pStatement.executeUpdate();
				pStatement.close();
			} else {
				/**
				 * Wenn kein Eintrag vorhanden, Fehler ausgabe. -> Richtige
				 * InitSkript nutzenn.
				 */
				main.getStatusService().getAkt().setStatus(StatusEnum.FAIL);
				main.getMainFrame()
						.getStatusText()
						.setText(
								main.getConf().getlProp()
										.getProperty("error.dbDel"));
			}
		} catch (SQLException e) {
			main.getStatusService().getAkt().setStatus(StatusEnum.FAIL);
			main.getMainFrame()
					.getStatusText()
					.setText(
							main.getConf().getlProp()
									.getProperty("error.databaseUpdate"));
		}
	}
}