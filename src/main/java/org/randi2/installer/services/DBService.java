package org.randi2.installer.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



import org.randi2.installer.controller.Main;
import org.randi2.installer.controller.configuration.DBConfiguration;
import org.randi2.installer.model.enumerations.StatusEnum;

/**
 * 
 * @author andreas Verwaltet die Datenbank Connection. Ertellt eine neue DB und
 *         legt Benutzer an
 */
public class DBService {

	private Connection con;
	private String url;
	private Main main;

	public DBService(Main main) {
		this.main = main;
	}

	/** 
	 * Gibt eine Connection zur Datenbank randi2DB zurueck
	 * @return
	 */
	public Connection getConnection() {
		try {
			if (main.getDbconf().isMySQL())
				Class.forName("com.mysql.jdbc.Driver");
			else
				Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			main.getMainFrame()
					.getStatusText()
					.setText(
							(main.getConf().getlProp()
									.getProperty("error.driverLoad")));
			main.getStatusService().getAkt().setStatus(StatusEnum.FAIL);
		}
	
		try {
			if (main.getDbconf().isMySQL())
				url = "jdbc:mysql://" + main.getDbconf().getServer() + ":3306/"+main.getDbconf().getName();
			else
				url = "jdbc:postgresql://" + main.getDbconf().getServer()+ ":5432/"+main.getDbconf().getName();
			con = DriverManager.getConnection(url, main.getDbconf().getUsernameCon(),main.getDbconf().getPasswordCon());
		} catch (SQLException e) {
			main.getMainFrame()
					.getStatusText()
					.setText(
							(main.getConf().getlProp()
									.getProperty("error.DBConnection")));
			main.getStatusService().getAkt().setStatus(StatusEnum.FAIL);
			System.out.println(e.getMessage());
	
		}
		return con;
	}

	/**
	 * Beendet die aktuelle Verbindung zur DB.
	 * 
	 * @throws SQLException
	 */
	public void close() throws SQLException {
		if (con != null) {
			con.close();
			con = null;
		}
	}

	/**
	 * 
	 * @param aSQLScriptFilePath
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 * 
	 *  Fuerht das Init Skript fuer MySQL aus.
	 */
	public boolean executeSQLDBScript(String aSQLScriptFilePath)
			throws IOException, SQLException {
		
			boolean isScriptExecuted = false;
			Statement stmt = (Statement) getConnection().createStatement();
			try {
				BufferedReader in = new BufferedReader(new FileReader(
						aSQLScriptFilePath));
				String str;
				String sql = "";
				int lng = 0;
				while ((str = in.readLine()) != null) {
					try {
						// loescht leere Zeilen und Kommentare. Sucht nach ; und
						// fuegt Zeilen zusammen
						if (!str.isEmpty() && !str.substring(0, 2).equals("--")) {
							lng = str.length();
							if (!str.substring(lng - 1, lng).equals(";"))
								sql = sql + str;
							else {
								sql = sql + str;
								stmt.executeUpdate(sql);
								sql = "";
							}
						}
					} catch (SQLException error) {
						main.getStatusService().getAkt().setStatus(StatusEnum.FAIL);
						main.getMainFrame()
								.getStatusText()
								.setText(
										(main.getConf().getlProp()
												.getProperty("error.SQL")));
						System.out.println(error);
					}
				}
				in.close();
				isScriptExecuted = true;
			} catch (Exception e) {
				main.getMainFrame()
						.getStatusText()
						.setText(
								(main.getConf().getlProp()
										.getProperty("error.SQL")));
				main.getStatusService().getAkt().setStatus(StatusEnum.FAIL);
				
			}
			return isScriptExecuted;
		} 

	

	
	/**
	 * Erstellt eine erste Connection ohne die DB randi2DB
	 * @return
	 */
	public Connection getFirstConnection() {
		String url;
		//Erste Verbindung aufbauen
		try {
			if (main.getDbconf().isMySQL())
				Class.forName("com.mysql.jdbc.Driver");
			else
				Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			main.getStatusService().getAkt().setStatus(StatusEnum.FAIL);
			main.getMainFrame()
					.getStatusText()
					.setText(
							(main.getConf().getlProp()
									.getProperty("error.DBConnection")));
		};
		try {
			if (main.getDbconf().isMySQL())
				url = "jdbc:mysql://" + main.getDbconf().getServer() + ":3306/";
			else
				url = "jdbc:postgresql://" + main.getDbconf().getServer() + ":5432/";
		
	con = DriverManager.getConnection(url, main.getDbconf().getUsernameCon(),main.getDbconf().getPasswordCon());

		} catch (SQLException e) {
			main.getMainFrame()
					.getStatusText()
					.setText(
							(main.getConf().getlProp()
									.getProperty("error.DBConnection")));
			main.getStatusService().getAkt().setStatus(StatusEnum.FAIL);
		}
		return con;
		
	}

	/**
	 * Erstellt die Datenbank randi2DB, wenn sie noch nicht vorhanden ist
	 * @param dbconf
	 */
	public void createDatabase(DBConfiguration dbconf) {
		
		// Datenbank randi2DB erstellen MySql
		
			try {
				Statement st = (Statement) getFirstConnection()
						.createStatement();
		st.executeUpdate("CREATE DATABASE IF NOT EXISTS "+main.getDbconf().getName());
			} catch (SQLException e) {
				main.getStatusService().getAkt().setStatus(StatusEnum.FAIL);
				main.getMainFrame()
						.getStatusText()
						.setText(
								(main.getConf().getlProp()
										.getProperty("error.createDB")));
			}	
		}
	
	

	/**
	 * Erstellt einen DB Benutzer mit dem von Anwender angegebenen Daten
	 */
	public void createUser(DBConfiguration dbconf) {
		if (dbconf.isMySQL()) {
			try {
				Statement st = (Statement) getFirstConnection()
						.createStatement();
				// Holt alle Benutzer, die wie der neue heissen.
				PreparedStatement ps = getFirstConnection().prepareStatement(
						"SELECT user FROM mysql.user WHERE user='"
								+ dbconf.getUsername() + "'");
				ResultSet rs = ps.executeQuery();
				String user = "";
				while (rs.next()) {
					user = user + rs.getString(1);
				}
				rs.close();
				// Wenne es noch kein gibt, lege einen an
				// Wenn es einen gibt, gib ihm die rechte fuer die DB
				if (user.isEmpty()) {
					st.executeUpdate("CREATE USER '" + dbconf.getUsername()
							+ "'@'%' IDENTIFIED BY  '" + dbconf.getPassword()
							+ "'");
					st.executeUpdate("GRANT ALL PRIVILEGES ON *.* TO  '"
							+ dbconf.getUsername()
							+ "'@'%' IDENTIFIED BY  '"
							+ dbconf.getPassword()
							+ "' WITH GRANT OPTION MAX_QUERIES_PER_HOUR 0 MAX_CONNECTIONS_PER_HOUR 0 MAX_UPDATES_PER_HOUR 0 MAX_USER_CONNECTIONS 0");
					st.executeUpdate("GRANT ALL PRIVILEGES ON  `randi2DB`. * TO  '"
							+ dbconf.getUsername() + "'@'%'");
				} else {
					st.executeUpdate("GRANT ALL PRIVILEGES ON *.* TO  '"
							+ dbconf.getUsername()
							+ "'@'%' IDENTIFIED BY  '"
							+ dbconf.getPassword()
							+ "' WITH GRANT OPTION MAX_QUERIES_PER_HOUR 0 MAX_CONNECTIONS_PER_HOUR 0 MAX_UPDATES_PER_HOUR 0 MAX_USER_CONNECTIONS 0");
					st.executeUpdate("GRANT ALL PRIVILEGES ON  `randi2DB`. * TO  '"
							+ dbconf.getUsername() + "'@'%'");
				}
			} catch (SQLException e) {
				main.getStatusService().getAkt().setStatus(StatusEnum.FAIL);
				main.getMainFrame()
						.getStatusText()
						.setText(
								(main.getConf().getlProp()
										.getProperty("error.createDBUser")));
			}
		}
	}
}
