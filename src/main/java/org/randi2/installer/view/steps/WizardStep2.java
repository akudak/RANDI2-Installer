package org.randi2.installer.view.steps;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.randi2.installer.view.MainPanel;
import org.randi2.installer.controller.Main;

public class WizardStep2 extends MainPanel {

	private static final long serialVersionUID = 8467361081329740076L;
	private JTextField adminT;
	private JPasswordField passwordT1;
	private JPasswordField passwordT2;
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

		JLabel adminL = new JLabel(main.getConf().getlProp()
				.getProperty("label.username"));
		adminL.setSize(200, 20);
		adminL.setLocation(10, 130);

		this.add(adminL);

		JLabel passwordL1 = new JLabel(main.getConf().getlProp()
				.getProperty("label.password"));
		passwordL1.setSize(200, 20);
		passwordL1.setLocation(10, 170);

		this.add(passwordL1);

		JLabel passwordL2 = new JLabel(main.getConf().getlProp()
				.getProperty("label.repeat"));
		passwordL2.setSize(200, 20);
		passwordL2.setLocation(10, 210);

		this.add(passwordL2);

		adminT = new JTextField();
		adminT.setSize(200, 20);
		adminT.setLocation(200, 130);
		adminT.setEnabled(false);
		this.add(adminT);

		passwordT1 = new JPasswordField();
		passwordT1.setSize(200, 20);
		passwordT1.setLocation(200, 170);
		passwordT1.setEnabled(false);
		this.add(passwordT1);

		passwordT2 = new JPasswordField();
		passwordT2.setSize(200, 20);
		passwordT2.setLocation(200, 210);
		passwordT2.setEnabled(false);
		this.add(passwordT2);

		insertB = new JButton(main.getConf().getlProp()
				.getProperty("button.save"));
		insertB.setLocation(315, 250);
		insertB.setSize(80, 20);
		insertB.setEnabled(false);
		this.add(insertB);

		cButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.getDbconf().setMySQL(true);
				main.getDbconf().setPostgre(false);
				main.getDbconf().setDatabase(true);
				adminT.setEnabled(true);
				passwordT1.setEnabled(true);
				passwordT2.setEnabled(true);
				insertB.setEnabled(true);
				main.getStatusService().getAkt().setStatus(0);
			}
		});

		dButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.getDbconf().setMySQL(false);
				main.getDbconf().setPostgre(true);
				main.getDbconf().setDatabase(true);
				adminT.setEnabled(true);
				passwordT1.setEnabled(true);
				passwordT2.setEnabled(true);
				insertB.setEnabled(true);
				main.getStatusService().getAkt().setStatus(0);
			}
		});

		eButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.getDbconf().setMySQL(true);
				adminT.setEnabled(false);
				main.getDbconf().setDatabase(false);
				passwordT1.setEnabled(false);
				passwordT2.setEnabled(false);
				main.getDbconf().setPostgre(false);
				main.getDbconf().setUsernameCon("root");
				main.getDbconf().setPasswordCon("", "");
				insertB.setEnabled(false);
				main.getStatusService().getAkt().setStatus(1);
			}
		});

		insertB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.getStatusService().getAkt().setStatus(1);
				final JFrame jFrame = new JFrame();
				jFrame.setSize(300, 150);

				String p1 = "";
				String p2 = "";

				for (int i = 0; i < passwordT1.getPassword().length; i++)
					p1 = p1 + passwordT1.getPassword()[i];

				for (int k = 0; k < passwordT2.getPassword().length; k++)
					p2 = p2 + passwordT2.getPassword()[k];

				if (main.getDbconf().isDatabase()) {
					if (!main.getDbconf().setPasswordCon(p1, p2))
						main.getStatusService().getAkt().setStatus(-1);

					if (!main.getDbconf().setUsernameCon(adminT.getText()))
						main.getStatusService().getAkt().setStatus(-1);
				}

			}
		});

	}
}
