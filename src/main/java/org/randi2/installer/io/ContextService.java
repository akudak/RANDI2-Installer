package org.randi2.installer.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.randi2.installer.controller.Main;
import org.randi2.installer.model.enumerations.StatusEnum;

/**
 * 
 * @author andreas passt die Context.xml im Tomcat Verzeichnis an.
 */
public class ContextService {

	private Main main;

	public ContextService(Main main) {
		this.main = main;
	}

	/**
	 * Bearbeitet die Datei Context.xml
	 */
	public void editContext() {
		try {
			BufferedReader in = new BufferedReader(new FileReader(main
					.getConf().getServerPath() + "conf/context.xml"));
			BufferedWriter out = new BufferedWriter(new FileWriter(main
					.getConf().getServerPath() + "conf/context.xml", true));
			String line;
			String text = "";
			String l;
			// MySQL
			if (main.getDbconf().isMySQL()) {
				l = "<Resource auth=\"Container\" driverClassName=\"com.mysql.jdbc.Driver\" maxActive=\"100\" maxIdle=\"30\" maxWait=\"10000\" type=\"javax.sql.DataSource\" url=\"jdbc:mysql://localhost:3306/"
						+ main.getDbconf().getName()
						+ "?autoReconnect=true\" name=\"jdbc/randi2\" password=\""
						+ main.getDbconf().getPassword()
						+ "\" username=\""
						+ main.getDbconf().getUsername()
						+ "\" /> <Resource auth=\"Container\" name=\"mail/randi2\"  type=\"javax.mail.Session\" mail.smtp.host=\"localhost\" mail.smtp.port=\"25\" mail.smtp.username=\""
						+ main.getMailConf().getUsername()
						+ "\" mail.smtp.password=\""
						+ main.getMailConf().getPassword() + "\" /></Context>";
			}
			// PostgreSQL
			else {
				l = "<Resource name=\"jdbc/randi2\" auth=\"Container\" type=\"org.postgresql.Driverv\" maxActive=\"100\" maxIdle=\"30\" maxWait=\"10000\" driverClassName=\"com.mysql.jdbc.Driver\"  username=\""
						+ main.getDbconf().getUsername()
						+ "\" password=\""
						+ main.getDbconf().getPassword()
						+ "\"   url=\"jdbc:postgresql://localhost:5432/"
						+ main.getDbconf().getName()
						+ "\" />"
						+ "<Resource auth=\"Container\" name=\"mail/randi2\"  type=\"javax.mail.Session\" mail.smtp.host=\"localhost\" mail.smtp.port=\"25\" mail.smtp.username=\""
						+ main.getMailConf().getUsername()
						+ "\" mail.smtp.password=\""
						+ main.getMailConf().getPassword() + "\" /></Context>";
			}
			while ((line = in.readLine()) != null) {
				if (line.equals("</Context>")) {
					text = text + l;
				} else
					text = text + line;
			}

			FileWriter loeschen = new FileWriter(main.getConf().getServerPath()
					+ "conf/context.xml");
			loeschen.close();
			out.write(text);
			out.close();
			in.close();
		} catch (FileNotFoundException e) {
			main.getStatusService().getAkt().setStatus(StatusEnum.FAIL);
			main.getMainFrame()
					.getStatusText()
					.setText(
							main.getConf().getlProp()
									.getProperty("error.context"));
		} catch (IOException e) {
			main.getStatusService().getAkt().setStatus(StatusEnum.FAIL);
			main.getMainFrame()
					.getStatusText()
					.setText(
							main.getConf().getlProp()
									.getProperty("error.context"));
		}
	}
}