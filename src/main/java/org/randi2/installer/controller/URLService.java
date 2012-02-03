package org.randi2.installer.controller;

import java.awt.Desktop;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

import org.randi2.installer.model.enumerations.StatusEnum;

/**
 * 
 * @author andreas Oeffnet eine URL, damit der Benutzer die noetige Datei runter
 *         laden kann.
 */
public class URLService {

	private Main main;
	private Properties link;

	public URLService(Main main) {
		this.main = main;
		loadLink();
	}

	public void openURL(String urlText) {
		URL url = null;
		try {
			if (urlText.equalsIgnoreCase("Tomcat"))
				url = new URL(link.getProperty("url.tomcat"));
			else if (urlText.equalsIgnoreCase("Xampp"))
				url = new URL(link.getProperty("url.xampp"));
			else if (urlText.equalsIgnoreCase("jdbc_mysql"))
				url = new URL(link.getProperty("url.jdbcMySQL"));
			else if (urlText.equalsIgnoreCase("jdbc_postgre"))
				url = new URL(link.getProperty("url.jdbcPostgreSQL"));
			else if (urlText.equalsIgnoreCase("java_mail"))
				url = new URL(link.getProperty("url.jma"));
			else if (urlText.equalsIgnoreCase("randi2"))
				url = new URL(link.getProperty("url.randi2"));
			else if (urlText.equalsIgnoreCase("jaf"))
				url = new URL(link.getProperty("url.jaf"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		if (Desktop.isDesktopSupported()) {
			try {
				Desktop.getDesktop().browse(url.toURI());
			} catch (IOException e) {
				main.getStatusService().getAkt().setStatus(StatusEnum.FAIL);
				main.getMainFrame()
						.getStatusText()
						.setText(
								main.getConf().getlProp()
										.getProperty("error.url"));
			} catch (URISyntaxException e) {
				main.getStatusService().getAkt().setStatus(StatusEnum.FAIL);
				main.getMainFrame()
						.getStatusText()
						.setText(
								main.getConf().getlProp()
										.getProperty("error.url"));
			}
		}

	}

	public void loadLink() {
		link = main.getProp().loadProperties(
				ClassLoader.getSystemResource("installer.properties"));
	}
}