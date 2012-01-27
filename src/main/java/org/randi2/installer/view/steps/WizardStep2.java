package org.randi2.installer.view.steps;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import org.randi2.installer.view.MainPanel;
import org.randi2.installer.controller.Main;

public class WizardStep2 extends MainPanel {

	private static final long serialVersionUID = 8467361081329740076L;

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

		cButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.getDbconf().setMySQL(true);
				main.getStatusService().getAkt().setStatus(1);
			}
		});

		dButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.getDbconf().setPostgre(true);
				main.getStatusService().getAkt().setStatus(1);
			}
		});

		eButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.getDbconf().setMySQL(true);
				main.getDbconf().setPostgre(false);
				main.getStatusService().getAkt().setStatus(1);
			}
		});
	}
}
