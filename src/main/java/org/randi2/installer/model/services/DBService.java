package org.randi2.installer.model.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.jdbc.Statement;
import org.randi2.installer.controller.Main;
import org.randi2.installer.controller.configuration.DBConfiguration;

/**
 * 
 * @author andreas Verwaltet die Datenbank Connection. Ertellt eine neue DB und
 *         legt Benutzer an
 */
public class DBService {

	private Connection con;
	private DBConfiguration dbconf;
	private String url;
	private Main main;

	public DBService(DBConfiguration dbconf, Main main) {
		this.main = main;
		this.dbconf = dbconf;
	}

	public Connection getConnection() {
		try {
			if (dbconf.isMySQL())
				Class.forName("com.mysql.jdbc.Driver");
			else
				Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			main.getMainFrame()
					.aktStatusPanel(
							(main.getConf().getlProp()
									.getProperty("error.driverLoad")));
			main.getStatusService().getAkt().setStatus(-1);;
			e.printStackTrace();
		}
		try {
			if (dbconf.isMySQL())
				url = "jdbc:mysql://" + dbconf.getServer() + ":3306/randi2DB";
			else
				url = "jdbc:postgresql://" + dbconf.getServer()
						+ ":3306/randi2DB";
			con = DriverManager.getConnection(url, "root", "");
		} catch (SQLException e) {
			main.getMainFrame()
					.aktStatusPanel(
							(main.getConf().getlProp()
									.getProperty("error.driverLoad")));
			main.getStatusService().getAkt().setStatus(-1);
			e.printStackTrace();
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
	 *             Fuerht das Init Skript für MySQL aus.
	 */
	public boolean executeMySQLDBScript(String aSQLScriptFilePath)
			throws IOException, SQLException {
		if (dbconf.isMySQL()) {
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
						main.getMainFrame().aktStatusPanel(
								(main.getConf().getlProp()
										.getProperty("error.dbSkript")));
					}
				}
				in.close();
				isScriptExecuted = true;
			} catch (Exception e) {
				System.out.println(e);
				main.getMainFrame().aktStatusPanel(
						(main.getConf().getlProp()
								.getProperty("error.dbSkript")));
				main.getStatusService().getAkt().setStatus(-1);
			}
			return isScriptExecuted;
		} else
			return false;
	}

	public Connection getFirstConnection() {
		String url;
		// Erste Verbindung aufbauen
		try {
			if (dbconf.isMySQL())
				Class.forName("com.mysql.jdbc.Driver");
			else
				Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			main.getStatusService().getAkt().setStatus(-1);
			main.getMainFrame()
					.aktStatusPanel(
							(main.getConf().getlProp()
									.getProperty("error.driverLoad")));
		}
		try {
			if (dbconf.isMySQL())
				url = "jdbc:mysql://" + dbconf.getServer() + "/";
			else
				url = "jdbc:postgresql://" + dbconf.getServer() + "/";

			con = DriverManager.getConnection(url, "root", "");
		} catch (SQLException e) {
			main.getMainFrame().aktStatusPanel(
					(main.getConf().getlProp()
							.getProperty("error.dbConnection")));
			main.getStatusService().getAkt().setStatus(-1);
			e.printStackTrace();
		}
		return con;
	}

	public void createDatabase(DBConfiguration dbconf) {
		// Datenbank randi2DB erstellen MySql
		if (dbconf.isMySQL()) {
			try {
				Statement st = (Statement) getFirstConnection()
						.createStatement();
				st.executeUpdate("CREATE DATABASE IF NOT EXISTS randi2DB");
			} catch (SQLException e) {
				main.getStatusService().getAkt().setStatus(-1);
				main.getMainFrame().aktStatusPanel(
						(main.getConf().getlProp()
								.getProperty("error.createDB")));
				e.printStackTrace();
			}
			// Testen, ob Datenbank bereits exisitiert, wenn nicht anlegen

		} else {
			// fuer Postgre muss es noch programmiert werden
		}
	}

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
				main.getMainFrame().aktStatusPanel(
						(main.getConf().getlProp()
								.getProperty("error.dbCreateUser")));
				e.printStackTrace();
			}
		}
	}
}
