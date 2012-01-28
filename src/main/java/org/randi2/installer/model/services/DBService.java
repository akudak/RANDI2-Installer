package org.randi2.installer.model.services;

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
			main.getStatusService().getAkt().setStatus(-1);
		}
		try {
			if (main.getDbconf().isMySQL())
				url = "jdbc:mysql://" + main.getDbconf().getServer() + ":3306/randi2DB";
			else
				url = "jdbc:postgresql://" + main.getDbconf().getServer()
						+ ":3306/randi2DB";
			con = DriverManager.getConnection(url, main.getDbconf().getUsernameCon(),
					main.getDbconf().getPasswordCon());
		} catch (SQLException e) {
			main.getMainFrame()
					.getStatusText()
					.setText(
							(main.getConf().getlProp()
									.getProperty("error.DBConnection")));
			main.getStatusService().getAkt().setStatus(-1);

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
	public boolean executeMySQLDBScript(String aSQLScriptFilePath)
			throws IOException, SQLException {
		if (main.getDbconf().isMySQL()) {
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
						main.getStatusService().getAkt().setStatus(-1);
						main.getMainFrame()
								.getStatusText()
								.setText(
										(main.getConf().getlProp()
												.getProperty("error.SQL")));
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
				main.getStatusService().getAkt().setStatus(-1);
			}
			return isScriptExecuted;
		} else
			return false;
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
			System.out.println("Fehler");
			main.getStatusService().getAkt().setStatus(-1);
			main.getMainFrame()
					.getStatusText()
					.setText(
							(main.getConf().getlProp()
									.getProperty("error.DBConnection")));
		};
		try {
			if (main.getDbconf().isMySQL())
				url = "jdbc:mysql://" + main.getDbconf().getServer() + "/";
			else
				url = "jdbc:postgresql://" + main.getDbconf().getServer() + "/";
		
	con = DriverManager.getConnection(url, main.getDbconf().getUsernameCon(),main.getDbconf().getPasswordCon());
		} catch (SQLException e) {
			main.getMainFrame()
					.getStatusText()
					.setText(
							(main.getConf().getlProp()
									.getProperty("error.DBConnection")));
			main.getStatusService().getAkt().setStatus(-1);

		}
		return con;
		
	}

	/**
	 * Erstellt die Datenbank randi2DB, wenn sie noch nicht vorhanden ist
	 * @param dbconf
	 */
	public void createDatabase(DBConfiguration dbconf) {
		// Datenbank randi2DB erstellen MySql
		if (dbconf.isMySQL()) {
			try {
				Statement st = (Statement) getFirstConnection()
						.createStatement();
				st.executeUpdate("CREATE DATABASE IF NOT EXISTS randi2DB");
			} catch (SQLException e) {
				main.getStatusService().getAkt().setStatus(-1);
				main.getMainFrame()
						.getStatusText()
						.setText(
								(main.getConf().getlProp()
										.getProperty("error.createDB")));
			}
		
		} else {
			// fuer Postgre muss es noch programmiert werden
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
				main.getStatusService().getAkt().setStatus(-1);
				main.getMainFrame()
						.getStatusText()
						.setText(
								(main.getConf().getlProp()
										.getProperty("error.createDBUser")));
			}
		}
	}
}
