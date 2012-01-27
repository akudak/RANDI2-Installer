package org.randi2.installer.model.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.randi2.installer.controller.Main;

/**
 * 
 * @author andreas passt die Context.xml im Tomcat Verzeichnis an.
 */
public class ContextService {

	private Main main;

	public ContextService(Main main) {
		this.main = main;
	}

	public void editContext() {
		try {
			BufferedReader in = new BufferedReader(new FileReader(main
					.getConf().getServerPath() + "conf/context.xml"));
			BufferedWriter out = new BufferedWriter(new FileWriter(main
					.getConf().getServerPath() + "conf/context.xml", true));
			String line;
			String text = "";
			String l = "<Resource auth=\"Container\" driverClassName=\"com.mysql.jdbc.Driver\" maxActive=\"100\" maxIdle=\"30\" maxWait=\"10000\" type=\"javax.sql.DataSource\" url=\"jdbc:mysql://localhost:3306/randi2DB?autoReconnect=true\" name=\"jdbc/randi2\" password=\""
					+ main.getDbconf().getPassword()
					+ "\" username=\""
					+ main.getDbconf().getUsername()
					+ "\" /> <Resource auth=\"Container\" name=\"mail/randi2\"  type=\"javax.mail.Session\" mail.smtp.host=\"localhost\" mail.smtp.port=\"25\" mail.smtp.username=\""
					+ main.getMailConf().getUsername()
					+ "\" mail.smtp.password=\""
					+ main.getMailConf().getPassword() + "\" /></Context>";
			;
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
			main.getStatusService().getAkt().setStatus(-1);
			e.printStackTrace();
		} catch (IOException e) {
			main.getStatusService().getAkt().setStatus(-1);
			e.printStackTrace();
		}
	}
}