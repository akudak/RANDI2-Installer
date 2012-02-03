package org.randi2.installer.view.steps;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.randi2.installer.model.enumerations.StatusEnum;
import org.randi2.installer.view.MainPanel;
import org.randi2.installer.controller.Main;

public class WizardStep2 extends MainPanel {

	private static final long serialVersionUID = 8467361081329740076L;
	private JTextField adminT;
	private JTextField serverT;
	private JTextField nameT;
	private JPasswordField passwordT1;
	private JButton insertB;

	public WizardStep2(final Main main) {
		super(main);
		initGUI();
	}

	public void initGUI() {
		JLabel DBInst = new JLabel(main.getConf().getlProp()
				.getProperty("label.DBInst"));
		DBInst.setLocation(10, 10);
		DBInst.setSize(350, 20);

		this.add(DBInst);

		JRadioButton cButton = new JRadioButton(main.getConf().getlProp()
				.getProperty("check.mySQL"));
		JRadioButton dButton = new JRadioButton(main.getConf().getlProp()
				.getProperty("check.postgre"));
		JRadioButton eButton = new JRadioButton(main.getConf().getlProp()
				.getProperty("check.no"));

		ButtonGroup myButtonGroup2 = new ButtonGroup();
		myButtonGroup2.add(cButton);
		myButtonGroup2.add(dButton);
		myButtonGroup2.add(eButton);

		cButton.setLocation(380, 10);
		dButton.setLocation(380, 30);
		eButton.setLocation(380, 50);

		cButton.setSize(300, 20);
		dButton.setSize(300, 20);
		eButton.setSize(300, 20);

		cButton.setVisible(true);
		dButton.setVisible(true);
		eButton.setVisible(true);

		this.add(cButton);
		this.add(dButton);
		this.add(eButton);

		this.setVisible(true);

		JLabel datenL = new JLabel(main.getConf().getlProp()
				.getProperty("label.userInsert"));
		datenL.setSize(600, 20);
		datenL.setLocation(10, 90);

		this.add(datenL);

		JLabel serverL = new JLabel(main.getConf().getlProp()
				.getProperty("label.serverDB"));
		serverL.setSize(600, 20);
		serverL.setLocation(10, 130);

		this.add(serverL);

		
		JLabel nameL = new JLabel(main.getConf().getlProp()
				.getProperty("label.nameDB"));
		nameL.setSize(200, 20);
		nameL.setLocation(10, 170);

		this.add(nameL);
		
		JLabel adminL = new JLabel(main.getConf().getlProp()
				.getProperty("label.username"));
		adminL.setSize(200, 20);
		adminL.setLocation(10, 210);

		this.add(adminL);

		JLabel passwordL1 = new JLabel(main.getConf().getlProp()
				.getProperty("label.password"));
		passwordL1.setSize(200, 20);
		passwordL1.setLocation(10, 250);

		this.add(passwordL1);

		serverT = new JTextField("127.0.0.1");
		serverT.setSize(200, 20);
		serverT.setLocation(200, 130);
		serverT.setEnabled(false);
		this.add(serverT);

		nameT = new JTextField("randi2DB");
		nameT.setSize(200, 20);
		nameT.setLocation(200, 170);
		nameT.setEnabled(false);
		this.add(nameT);

		adminT = new JTextField("randi2");
		adminT.setSize(200, 20);
		adminT.setLocation(200, 210);
		adminT.setEnabled(false);
		this.add(adminT);
		
		passwordT1 = new JPasswordField("www");
		passwordT1.setSize(200, 20);
		passwordT1.setLocation(200, 250);
		passwordT1.setEnabled(false);
		this.add(passwordT1);

		insertB = new JButton(main.getConf().getlProp()
				.getProperty("button.save"));
		insertB.setLocation(315, 290);
		insertB.setSize(80, 20);
		insertB.setEnabled(false);
		this.add(insertB);

		JLabel mandatoryL = new JLabel(main.getConf().getlProp()
				.getProperty("label.mandatory"));
		mandatoryL.setSize(150, 20);
		mandatoryL.setLocation(180, 290);

		this.add(mandatoryL);
		
		cButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.getDbconf().setMySQL(true);
				main.getDbconf().setPostgre(false);
				main.getDbconf().setDatabase(true);
				adminT.setEnabled(true);
				nameT.setEnabled(true);
				serverT.setEnabled(true);
				passwordT1.setEnabled(true);
				insertB.setEnabled(true);
				
				main.getStatusService().getAkt().setStatus(StatusEnum.UNMACHINED);
			}
		});

		dButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.getDbconf().setMySQL(false);
				main.getDbconf().setPostgre(true);
				main.getDbconf().setDatabase(true);
				adminT.setEnabled(true);
				passwordT1.setEnabled(true);
				nameT.setEnabled(true);
				serverT.setEnabled(true);
				insertB.setEnabled(true);

				main.getStatusService().getAkt().setStatus(StatusEnum.UNMACHINED);
			}
		});

		eButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.getDbconf().setMySQL(true);
				adminT.setEnabled(false);
				main.getDbconf().setDatabase(false);
				passwordT1.setEnabled(false);
				nameT.setEnabled(false);
				serverT.setEnabled(false);
				main.getDbconf().setPostgre(false);
				main.getDbconf().setUsernameCon("root");
				main.getDbconf().setPasswordCon("", "");
				insertB.setEnabled(false);

				main.getStatusService().getAkt().setStatus(StatusEnum.SUCCESS);
			}
		});

		insertB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.getStatusService().getAkt().setStatus(StatusEnum.SUCCESS);

				String p1 = "";


				for (int i = 0; i < passwordT1.getPassword().length; i++)
					p1 = p1 + passwordT1.getPassword()[i];

				if (main.getDbconf().isDatabase()) {
					if (!main.getDbconf().setPasswordCon(p1, p1))
						main.getStatusService().getAkt().setStatus(StatusEnum.FAIL);

					if (!main.getDbconf().setUsernameCon(adminT.getText()))
					{
						main.getStatusService().getAkt().setStatus(StatusEnum.FAIL);
						main.getMainFrame().getStatusText().setText(main.getConf().getlProp().getProperty("error.insert"));
					}
					if (!main.getDbconf().setName(nameT.getText()))
					{
						main.getStatusService().getAkt().setStatus(StatusEnum.FAIL);
						main.getMainFrame().getStatusText().setText(main.getConf().getlProp().getProperty("error.insert"));
					}
					if (!main.getDbconf().setServer(serverT.getText()))
					{
						main.getStatusService().getAkt().setStatus(StatusEnum.FAIL);
						main.getMainFrame().getStatusText().setText(main.getConf().getlProp().getProperty("error.insert"));
					}
				}

			}
		});

	}
}
