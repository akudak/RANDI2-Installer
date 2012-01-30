package org.randi2.installer.view.steps;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import org.randi2.installer.view.MainPanel;
import org.randi2.installer.controller.Main;

public class WizardStep15 extends MainPanel {

	private static final long serialVersionUID = 4612385484903720852L;
	private JTextField phoneT;
	private JTextField mobileT;
	private JTextField faxT;
	private JPasswordField passwordT1;
	private JPasswordField passwordT2;

	public WizardStep15(Main main) {
		super(main);
		initGUI();
	}

	public void initGUI() {
	
		JLabel titleL = new JLabel(main.getConf().getlProp()
				.getProperty("label.phone"));
		titleL.setLocation(10, 20);
		titleL.setSize(200, 20);

		this.add(titleL);

		JLabel firstnameL = new JLabel(main.getConf().getlProp()
				.getProperty("label.mobile"));
		firstnameL.setLocation(10, 60);
		firstnameL.setSize(200, 20);

		this.add(firstnameL);

		JLabel surnameL = new JLabel(main.getConf().getlProp()
				.getProperty("label.fax"));
		surnameL.setLocation(10, 100);
		surnameL.setSize(200, 20);

		this.add(surnameL);

		

		JLabel contactPersonL = new JLabel(main.getConf().getlProp()
				.getProperty("label.pwAuthentification"));
		contactPersonL.setLocation(10, 130);
		contactPersonL.setSize(600, 20);

		this.add(contactPersonL);
		
		JRadioButton aButton = new JRadioButton(main.getConf().getlProp()
				.getProperty("check.yes"));
		JRadioButton bButton = new JRadioButton(main.getConf().getlProp()
				.getProperty("check.no"));
		

		ButtonGroup myButtonGroup2 = new ButtonGroup();
		myButtonGroup2.add(aButton);
		myButtonGroup2.add(bButton);
	

		aButton.setLocation(100, 160);
		bButton.setLocation(210, 160);
	

		aButton.setSize(100, 20);
		bButton.setSize(100, 20);
	

		aButton.setVisible(true);
		bButton.setVisible(true);
		
		this.add(aButton);
		this.add(bButton);
	

		this.setVisible(true);


		
		JLabel passwordL1 = new JLabel(main.getConf().getlProp()
				.getProperty("label.password"));
		passwordL1.setLocation(10, 200);
		passwordL1.setSize(180, 20);
		
	
		this.add(passwordL1);

		JLabel passwordL2 = new JLabel(main.getConf().getlProp()
				.getProperty("label.repeat"));
		passwordL2.setLocation(10, 240);
		passwordL2.setSize(180, 20);

		this.add(passwordL2);

		phoneT = new JTextField();
		phoneT.setLocation(220, 20);
		phoneT.setSize(200, 20);

		this.add(phoneT);

		mobileT = new JTextField();
		mobileT.setLocation(220, 60);
		mobileT.setSize(200, 20);

		this.add(mobileT);

		faxT = new JTextField();
		faxT.setLocation(220, 100);
		faxT.setSize(200, 20);

		this.add(faxT);

		passwordT1 = new JPasswordField();
		passwordT1.setLocation(220, 200);
		passwordT1.setSize(200, 20);

		this.add(passwordT1);

		passwordT2 = new JPasswordField();
		passwordT2.setLocation(220, 240);
		passwordT2.setSize(200, 20);

		this.add(passwordT2);

		aButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			passwordT1.setEnabled(true);
			passwordT2.setEnabled(true);
			main.getCenter().setSelfRegistration(true);
			}
		});

		bButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				passwordT1.setEnabled(false);
				passwordT2.setEnabled(false);
				main.getCenter().setSelfRegistration(true);
			}
		});
		
		
		JButton insertB = new JButton(main.getConf().getlProp()
				.getProperty("button.save"));
		insertB.setLocation(340, 280);
		insertB.setSize(80, 20);

		this.add(insertB);
		
		JLabel mandatoryL = new JLabel(main.getConf().getlProp()
				.getProperty("label.mandatory"));
		mandatoryL.setSize(150, 20);
		mandatoryL.setLocation(205, 280);

		this.add(mandatoryL);

		insertB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.getStatusService().getAkt().setStatus(1);

				String p1 = "";
				String p2 = "";
				for (int i = 0; i < passwordT1.getPassword().length; i++)
					p1 = p1 + passwordT1.getPassword()[i];

				for (int k = 0; k < passwordT2.getPassword().length; k++)
					p2 = p2 + passwordT2.getPassword()[k];

				if (!main.getCenter().econde(p1, p2))
					main.getStatusService().getAkt().setStatus(-1);

				if (!main.getCenter().getContactPerson().setFax(faxT.getText()))
					main.getStatusService().getAkt().setStatus(-1);

				if (!main.getCenter().getContactPerson()
						.setMobile(mobileT.getText()))
					main.getStatusService().getAkt().setStatus(-1);

				if (!main.getCenter().getContactPerson()
						.setPhone(phoneT.getText()))
					main.getStatusService().getAkt().setStatus(-1);

				if (main.getStatusService().getAkt().getStatus() != -1)
					main.configCenterInfo();
				else
					main.getMainFrame().getStatusText().setText(
							(main.getConf().getlProp()
									.getProperty("error.insert")));

			}
		});
	}
}
