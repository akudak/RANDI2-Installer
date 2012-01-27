package org.randi2.installer.controller;

import java.awt.Desktop;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * 
 * @author andreas Oeffnet eine URL, damit der Benutzer die noetige Datei runter
 *         laden kann.
 */
public class URLService {

	private final static String TOMCAT = "http://tomcat.apache.org/";
	private final static String XAMPP = "http://www.apachefriends.org/de/xampp.html";
	private final static String JDBC_MYSQL = "http://www.mysql.com/products/connector/";
	private final static String JDBC_POSTGRE = "http://jdbc.postgresql.org/download.html";
	private final static String JAVA_MAIL = "http://www.oracle.com/technetwork/java/javaee/index-138643.html";
	private final static String RANDI2 = "http://www.randi2.org";
	private final static String JAF = "http://www.oracle.com/technetwork/java/javase/downloads/index-135046.html";
	private Main main;

	public URLService(Main main) {
		this.main = main;
	}

	public void openURL(String urlText) {
		URL url = null;
		try {
			if (urlText.equalsIgnoreCase("Tomcat"))
				url = new URL(TOMCAT);
			else if (urlText.equalsIgnoreCase("Xampp"))
				url = new URL(XAMPP);
			else if (urlText.equalsIgnoreCase("jdbc_mysql"))
				url = new URL(JDBC_MYSQL);
			else if (urlText.equalsIgnoreCase("jdbc_postgre"))
				url = new URL(JDBC_POSTGRE);
			else if (urlText.equalsIgnoreCase("java_mail"))
				url = new URL(JAVA_MAIL);
			else if (urlText.equalsIgnoreCase("randi2"))
				url = new URL(RANDI2);
			else if (urlText.equalsIgnoreCase("jaf"))
				url = new URL(JAF);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		if (Desktop.isDesktopSupported()) {
			try {
				Desktop.getDesktop().browse(url.toURI());
			} catch (IOException e) {
				main.getStatusService().getAkt().setStatus(-1);
				e.printStackTrace();
			} catch (URISyntaxException e) {
				main.getStatusService().getAkt().setStatus(-1);
				e.printStackTrace();
			}
		}
	}
}