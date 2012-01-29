package org.randi2.installer.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;

import org.randi2.installer.controller.configuration.Configuration;
import org.randi2.installer.controller.configuration.DBConfiguration;
import org.randi2.installer.controller.configuration.MailConfiguration;
import org.randi2.installer.io.ContextService;
import org.randi2.installer.io.IOProperties;
import org.randi2.installer.model.Administrator;
import org.randi2.installer.model.Center;
import org.randi2.installer.model.ContactPerson;
import org.randi2.installer.model.enumerations.Language;
import org.randi2.installer.services.AdministratorService;
import org.randi2.installer.services.CenterService;
import org.randi2.installer.services.DBService;
import org.randi2.installer.view.MainFrame;
import org.randi2.installer.view.Statusbar;
import org.randi2.installer.view.steps.WizardStep1;
import org.randi2.installer.view.steps.WizardStep10;
import org.randi2.installer.view.steps.WizardStep11;
import org.randi2.installer.view.steps.WizardStep12;
import org.randi2.installer.view.steps.WizardStep13;
import org.randi2.installer.view.steps.WizardStep14;
import org.randi2.installer.view.steps.WizardStep15;
import org.randi2.installer.view.steps.WizardStep16;
import org.randi2.installer.view.steps.WizardStep17;
import org.randi2.installer.view.steps.WizardStep18;
import org.randi2.installer.view.steps.WizardStep19;
import org.randi2.installer.view.steps.WizardStep2;
import org.randi2.installer.view.steps.WizardStep20;
import org.randi2.installer.view.steps.WizardStep3;
import org.randi2.installer.view.steps.WizardStep4;
import org.randi2.installer.view.steps.WizardStep5;
import org.randi2.installer.view.steps.WizardStep6;
import org.randi2.installer.view.steps.WizardStep7;
import org.randi2.installer.view.steps.WizardStep8;
import org.randi2.installer.view.steps.WizardStep9;


public class Main {
	private Configuration conf;
	private MailConfiguration mailConf;
	private DBConfiguration dbconf;

	private MainFrame mainFrame;
	private Statusbar statusbar;
	private WizardStep1 ws1;
	private WizardStep2 ws2;
	private WizardStep3 ws3;
	private WizardStep4 ws4;
	private WizardStep5 ws5;
	private WizardStep6 ws6;
	private WizardStep7 ws7;
	private WizardStep8 ws8;
	private WizardStep9 ws9;
	private WizardStep10 ws10;
	private WizardStep11 ws11;
	private WizardStep12 ws12;
	private WizardStep13 ws13;
	private WizardStep14 ws14;
	private WizardStep15 ws15;
	private WizardStep16 ws16;
	private WizardStep17 ws17;
	private WizardStep18 ws18;
	private WizardStep19 ws19;
	private WizardStep20 ws20;

	private AdministratorService adminService;
	private CenterService centerService;
	private StatusService statusService;
	private URLService urlService;
	private DBService dbService;

	private Administrator admin;
	private Center center;
	private ContactPerson contactPerson;

	private IOProperties prop;
	private int actStatus;
	private FileService fileService;
	private Iterator<Status> iterator;

	public Main()
	{
		
	}
	/**
	 * Erstelle Objekte
	 */
	public void init() {
		statusService = new StatusService();
		conf = new Configuration();
		prop = new IOProperties(this);
		dbconf = new DBConfiguration();
		dbService = new DBService(this);
		fileService = new FileService(statusService);
		actStatus = 0;
		mailConf = new MailConfiguration();
		admin = new Administrator();
		adminService = new AdministratorService(this);
		centerService = new CenterService(this);
		statusbar = new Statusbar(statusService);
		contactPerson = new ContactPerson();
		center = new Center();
		center.setId(1);
		center.setContactPerson(contactPerson);
		urlService = new URLService(this);
		mainFrame = new MainFrame(this);
		if(System.getProperty("user.language").equals("de"))
		{
			conf.loadLanguageProperties(Language.GER, this);
		}
		else
		{
			conf.loadLanguageProperties(Language.US, this);
		}
		start();
		mainFrame.repaint();
		}

	public void configCenterInfo() {
		centerService.update(center);
	}

	/** 
	 * Ruft die Methoden zur Datenbankerstellung auf
	 */
	public void createDatabase() {
		dbService.createDatabase(dbconf);
		dbService.createUser(dbconf);
	}

	/**
	 * Ruft die Methoden zur Datenbank Konfiguration auf
	 */
	public void initDatabase() {
		try {
			dbService.executeMySQLDBScript(dbconf.getInitDBPath());
		} catch (IOException e) {
			statusService.getAkt().setStatus(-1);
			e.printStackTrace();
		} catch (SQLException e) {
			statusService.getAkt().setStatus(-1);
			e.printStackTrace();
		}
	}

	/**
	 * @return Administrator
	 */
	public Administrator getAdmin() {
		return admin;
	}

	/**
	 * Ruft die Methoden zur Aenderung des Amdinistrators auf
	 */
	public void editAdmin() {
		admin.setId(1);
		adminService.update(admin);
	}

	/**
	 * Ruft Methode auf um die JDBC Treiber zu kopieren
	 */
	public void copyJDBC() {
		String JDBCPath = "";
		String JDBCName = "";
		if (conf.getJDBCPath() != null && !conf.getJDBCPath().isEmpty()) {
			for (int i = conf.getJDBCPath().length() - 1; i >= 0; i--) {
				if (conf.getJDBCPath().charAt(i) == '/') {
					JDBCPath = conf.getJDBCPath().substring(0, i + 1);
					JDBCName = conf.getJDBCPath().substring(i + 1,
							conf.getJDBCPath().length());
					i = 0;
				}
			}
			fileService.copy(JDBCName, JDBCPath, conf.getServerPath() + "lib/");
		} else
			getMainFrame().getStatusText().setText(
					(getConf().getlProp().getProperty("error.jar")));
	}

/**
 * Ruft Methode auf um die JMA zu kopieren
 */
	public void copyMail() {
		String mailPath = "";
		String mailName = "";
		if (mailConf.getJarPath() != null && !mailConf.getJarPath().isEmpty()) {
			for (int i = mailConf.getJarPath().length() - 1; i >= 0; i--) {
				if (mailConf.getJarPath().charAt(i) == '/') {
					mailPath = mailConf.getJarPath().substring(0, i + 1);
					mailName = mailConf.getJarPath().substring(i + 1,
							mailConf.getJarPath().length());
					i = 0;
				}
			}
			fileService.copy(mailName, mailPath, conf.getServerPath() + "lib/");
			mailConf.setJarPath(mailPath);
		} else
			getMainFrame().getStatusText().setText(
					(getConf().getlProp().getProperty("error.jar")));
	}

	/**
	 * Ruft Methode auf um die RANDI2 Datei zu kopieren
	 */
	public void copyRandi2() {
		String randi2Path = "";
		String randi2Name = "";
		if (conf.getRandi2Path() != null && !conf.getRandi2Path().isEmpty()) {
			for (int i = conf.getRandi2Path().length() - 1; i >= 0; i--) {
				if (conf.getRandi2Path().charAt(i) == '/') {
					randi2Path = conf.getRandi2Path().substring(0, i + 1);
					randi2Name = conf.getRandi2Path().substring(i + 1,
							conf.getRandi2Path().length());
					i = 0;
				}
			}
			fileService.rename(randi2Path, randi2Name, "RANDI2.war");
			fileService.copy("RANDI2.war", randi2Path, conf.getServerPath()
					+ "webapps/");
			conf.setRandi2Path(randi2Path);
		} else
			getMainFrame().getStatusText().setText(
					(getConf().getlProp().getProperty("error.jar")));
	}

	/**
	 * Ruft Methode auf um das Logo zu kopieren
	 */
	public void copyLogo() {
		String logoPath = "";
		String logoName = "";
		if (conf.getLogoPath() == null || conf.getLogoPath().isEmpty()) {
			statusService.getAkt().setStatus(-1);
			getMainFrame().getStatusText().setText(
					(getConf().getlProp().getProperty("error.logo")));
		} else {
			for (int i = conf.getLogoPath().length() - 1; i >= 0; i--) {
				if (conf.getLogoPath().charAt(i) == '/') {
					logoPath = conf.getLogoPath().substring(0, i + 1);
					logoName = conf.getLogoPath().substring(i + 1,
							conf.getLogoPath().length());
					i = 0;
				}
			}
			fileService.rename(logoPath, logoName, "hostingInstLogo.png");
			fileService.copy("hostingInstLogo.png", logoPath,
					conf.getServerPath() + "webapps/RANDI2/resources/");
			conf.setLogoPath(logoPath);
		}
	}

	public void editWebsite() {
		prop.editWebsite();
	}

	public void editInfoData() {
		prop.infoData();
	}

	public void editLabel() {
		prop.labelsGER();
		prop.labelsUS();
	}

	/**
	 * Ruft Methode auf um die JAF zu kopieren
	 */
	public void copyJAF() {
		String JAFPath = "";
		String JAFName = "";
		if (conf.getJAFPath() != null && !conf.getJAFPath().isEmpty()) {
			for (int i = conf.getJAFPath().length() - 1; i >= 0; i--) {
				if (conf.getJAFPath().charAt(i) == '/') {
					JAFPath = conf.getJAFPath().substring(0, i + 1);
					JAFName = conf.getJAFPath().substring(i + 1,
							conf.getJAFPath().length());
					i = 0;
				}
			}
			fileService.copy(JAFName, JAFPath, conf.getServerPath() + "lib/");
			conf.setJAFPath(JAFPath);
		} else
			getMainFrame().getStatusText().setText(
					(getConf().getlProp().getProperty("error.jar")));
	}

	public void confContext() {
		ContextService contextService = new ContextService(this);
		contextService.editContext();
	}

	/**
	 * Startet den Tomcat Server unter UNIX
	 */
	public void startTomcatMac() {
		try {
			Runtime.getRuntime().exec(
					new String[] { conf.getServerPath() + "bin/startup.sh" });
		} catch (IOException e) {
			getStatusService().getAkt().setStatus(-1);
			getMainFrame().getStatusText().setText(
					(getConf().getlProp().getProperty("error.startTomcat")));
		}

	}

	/**
	 * Startet den Tomcat Server unter Windows
	 */
	
	public void startTomcatWin() {
		try {
			Runtime.getRuntime().exec(
					new String[] { conf.getServerPath() + "bin/startup.bat" });
		} catch (IOException e) {
			getStatusService().getAkt().setStatus(-1);
			getMainFrame().getStatusText().setText(
					(getConf().getlProp().getProperty("error.startTomcat")));
		}

	}

	/**
	 * Starte den Ablauf des Installer
	 */
	public void start() {
		mainFrame.initGUI();

		ws1 = new WizardStep1(this);
		ws2 = new WizardStep2(this);
		ws3 = new WizardStep3(this);
		ws4 = new WizardStep4(this);
		ws5 = new WizardStep5(this);
		ws6 = new WizardStep6(this);
		ws7 = new WizardStep7(this);
		ws8 = new WizardStep8(this);
		ws9 = new WizardStep9(this);
		ws10 = new WizardStep10(this);
		ws11 = new WizardStep11(this);
		ws12 = new WizardStep12(this);
		ws13 = new WizardStep13(this);
		ws14 = new WizardStep14(this);
		ws15 = new WizardStep15(this);
		ws16 = new WizardStep16(this);
		ws17 = new WizardStep17(this);
		ws18 = new WizardStep18(this);
		ws19 = new WizardStep19(this);
		ws20 = new WizardStep20(this);

		statusService.getStatusList().add(ws1.getStatus());
		statusService.getStatusList().add(ws2.getStatus());
		statusService.getStatusList().add(ws3.getStatus());
		statusService.getStatusList().add(ws4.getStatus());
		statusService.getStatusList().add(ws5.getStatus());
		statusService.getStatusList().add(ws6.getStatus());
		statusService.getStatusList().add(ws7.getStatus());
		statusService.getStatusList().add(ws8.getStatus());
		statusService.getStatusList().add(ws9.getStatus());
		statusService.getStatusList().add(ws10.getStatus());
		statusService.getStatusList().add(ws11.getStatus());
		statusService.getStatusList().add(ws12.getStatus());
		statusService.getStatusList().add(ws13.getStatus());
		statusService.getStatusList().add(ws14.getStatus());
		statusService.getStatusList().add(ws15.getStatus());
		statusService.getStatusList().add(ws16.getStatus());
		statusService.getStatusList().add(ws17.getStatus());
		statusService.getStatusList().add(ws18.getStatus());
		statusService.getStatusList().add(ws19.getStatus());
		statusService.getStatusList().add(ws20.getStatus());
		ws1.getStatus().setActive(true);

		mainFrame.setMainPanel(ws1);
		mainFrame.getbPrevious().setEnabled(false);
		statusbar.removeAll();
		statusbar.initBar();
	}

	/**
	 * Zeichnet alle Objekte neu
	 */
	public void repaint() {
		mainFrame.remove(mainFrame.getbPrevious());
		mainFrame.remove(mainFrame.getbNext());

		mainFrame.initButton();
		mainFrame.repaint();
		ws1.removeAll();
		ws1.initGUI();
		ws2.removeAll();
		ws2.initGUI();
		ws3.removeAll();
		ws3.initGUI();
		ws4.removeAll();
		ws4.initGUI();
		ws5.removeAll();
		ws5.initGUI();
		ws6.removeAll();
		ws6.initGUI();
		ws7.removeAll();
		ws7.initGUI();
		ws8.removeAll();
		ws8.initGUI();
		ws9.removeAll();
		ws9.initGUI();
		ws10.removeAll();
		ws10.initGUI();
		ws11.removeAll();
		ws11.initGUI();
		ws12.removeAll();
		ws12.initGUI();
		ws13.removeAll();
		ws13.initGUI();
		ws14.removeAll();
		ws14.initGUI();
		ws15.removeAll();
		ws15.initGUI();
		ws16.removeAll();
		ws16.initGUI();
		ws17.removeAll();
		ws17.initGUI();
		ws18.removeAll();
		ws18.initGUI();
		ws19.removeAll();
		ws19.initGUI();
		ws20.removeAll();
		ws20.initGUI();

		ws1.repaint();
		ws2.repaint();
		ws3.repaint();
		ws4.repaint();
		ws5.repaint();
		ws6.repaint();
		ws7.repaint();
		ws8.repaint();
		ws9.repaint();
		ws10.repaint();
		ws11.repaint();
		ws12.repaint();
		ws13.repaint();
		ws14.repaint();
		ws15.repaint();
		ws16.repaint();
		ws17.repaint();
		ws18.repaint();
		ws19.repaint();
		ws20.repaint();
	}

	/**
	 * @param Setzt den aktuellen Status weiter
	 */
	public void setStatusNext() {
mainFrame.getStatusText().setText("Aktueller Status: OK");
		iterator = statusService.getStatusList().iterator();
		boolean end = false;
		Status akt;
		Status next;
		while (iterator.hasNext() && !end) {
			akt = (Status) iterator.next();
			if (akt.isActive()) {
				akt.setActive(false);
				next = (Status) iterator.next();
				next.setActive(true);
				end = true;
			}

		}
		getStatusbar().removeAll();
		getStatusbar().initBar();
		actStatus++;
		if (actStatus == 0) {
			mainFrame.getbPrevious().setEnabled(false);
			mainFrame.setMainPanel(ws1);
		}
		if (actStatus == 1) {
			mainFrame.setMainPanel(ws2);
			mainFrame.getbPrevious().setEnabled(true);
		}
		if (actStatus == 2)
			mainFrame.setMainPanel(ws3);
		if (actStatus == 3)
			mainFrame.setMainPanel(ws4);
		if (actStatus == 4)
			mainFrame.setMainPanel(ws5);
		if (actStatus == 5)
			mainFrame.setMainPanel(ws6);
		if (actStatus == 6)
			mainFrame.setMainPanel(ws7);
		if (actStatus == 7)
			mainFrame.setMainPanel(ws8);
		if (actStatus == 8)
			mainFrame.setMainPanel(ws9);
		if (actStatus == 9)
			mainFrame.setMainPanel(ws10);
		if (actStatus == 10)
			mainFrame.setMainPanel(ws11);
		if (actStatus == 11)
			mainFrame.setMainPanel(ws12);
		if (actStatus == 12)
			mainFrame.setMainPanel(ws13);
		if (actStatus == 13)
			mainFrame.setMainPanel(ws14);
		if (actStatus == 14)
			mainFrame.setMainPanel(ws15);
		if (actStatus == 15)
			mainFrame.setMainPanel(ws16);
		if (actStatus == 16)
			mainFrame.setMainPanel(ws17);
		if (actStatus == 17)
			mainFrame.setMainPanel(ws18);
		if (actStatus == 18)
			mainFrame.setMainPanel(ws19);
		if (actStatus == 19) {
			mainFrame.setMainPanel(ws20);
			mainFrame.getbNext().setEnabled(false);
		}

	}

	/**
	 * Setzt den aktuellen Status zurueck
	 */
	public void setStatusPrevious() {
		mainFrame.getStatusText().setText("Aktueller Status: OK");
		iterator = statusService.getStatusList().iterator();
		boolean end = false;
		Status akt = null;
		Status prev = null;
		while (iterator.hasNext() && !end) {
			akt = (Status) iterator.next();
			if (akt.isActive()) {
				akt.setActive(false);
				prev.setActive(true);
				end = true;
			}
			prev = akt;
		}
		getStatusbar().removeAll();
		getStatusbar().initBar();
		actStatus--;
		if (actStatus == 0) {
			mainFrame.setMainPanel(ws1);
			mainFrame.getbPrevious().setEnabled(false);
		}
		if (actStatus == 1)
			mainFrame.setMainPanel(ws2);
		if (actStatus == 2)
			mainFrame.setMainPanel(ws3);
		if (actStatus == 3)
			mainFrame.setMainPanel(ws4);
		if (actStatus == 4)
			mainFrame.setMainPanel(ws5);
		if (actStatus == 5)
			mainFrame.setMainPanel(ws6);
		if (actStatus == 6)
			mainFrame.setMainPanel(ws7);
		if (actStatus == 7)
			mainFrame.setMainPanel(ws8);
		if (actStatus == 8)
			mainFrame.setMainPanel(ws9);
		if (actStatus == 9)
			mainFrame.setMainPanel(ws10);
		if (actStatus == 10)
			mainFrame.setMainPanel(ws11);
		if (actStatus == 11)
			mainFrame.setMainPanel(ws12);
		if (actStatus == 12)
			mainFrame.setMainPanel(ws13);
		if (actStatus == 13)
			mainFrame.setMainPanel(ws14);
		if (actStatus == 14)
			mainFrame.setMainPanel(ws15);
		if (actStatus == 15)
			mainFrame.setMainPanel(ws16);
		if (actStatus == 16)
			mainFrame.setMainPanel(ws17);
		if (actStatus == 16)
			mainFrame.setMainPanel(ws17);
		if (actStatus == 17)
			mainFrame.setMainPanel(ws18);
		if (actStatus == 18) {
			mainFrame.setMainPanel(ws19);
			mainFrame.getbNext().setEnabled(true);
		}
		if (actStatus == 19)
			mainFrame.setMainPanel(ws20);

	}

	/**
	 * @return Configuration
	 */
	public Configuration getConf() {
		return conf;
	}

	/**
	 * @return IO_properties
	 */
	public IOProperties getProp() {
		return prop;
	}

	/**
	 * @return DBService
	 */
	public DBService getDbService() {
		return dbService;
	}

	public void setDbService(DBService dbService) {
		this.dbService = dbService;
	}
	/**
	 * @return MailConfiguration
	 */
	public MailConfiguration getMailConf() {
		return mailConf;
	}

	/**
	 * @return DBConfiguration
	 */
	public DBConfiguration getDbconf() {
		return dbconf;
	}
	
	/**
	 * @return DBConfiguration
	 */
	public void setDbconf(DBConfiguration dbconf) {
		this.dbconf=dbconf;
	}

	/**
	 * @return Statusbar
	 */
	public Statusbar getStatusbar() {
		return statusbar;
	}

	/**
	 * @return StatusService
	 */
	public StatusService getStatusService() {
		return statusService;
	}

	/**
	 * @return Center
	 */
	public Center getCenter() {
		return center;
	}

	/**
	 * @return URLService
	 */
	public URLService getUrlService() {
		return urlService;
	}

	/**
	 * @return MainFrame
	 */
	public MainFrame getMainFrame() {
		return mainFrame;
	}

	/**
	 * @param Setzte MainFrame
	 */
	public void setMainFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	/**
	 * @param Setze aktuellen Status 
	 */
	public void setactStatus(int actStatus) {
		this.actStatus = actStatus;
	}

	/**
	 * @return aktuellen Status
	 */
	public int getactStatus() {
		return actStatus;
	}

	/**
	 * Setzte Configuration
	 * @param conf
	 */
	public void setConf(Configuration conf) {
		this.conf = conf;
	}

	/**
	 * Setzte StatusService
	 * @param statusService
	 */
	public void setStatusService(StatusService statusService) {
		this.statusService = statusService;
	}

	/**
	 * Setze Center
	 * @param center
	 */
	public void setCenter(Center center) {
		this.center = center;
	}
	/**
	 * @param args
	 * @throws SQLException
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException, SQLException {
		Main main = new Main();
		main.init();
	}

}
