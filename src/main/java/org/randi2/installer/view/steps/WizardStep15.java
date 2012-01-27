package org.randi2.installer.view.steps;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
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
		JLabel contactPersonL = new JLabel(main.getConf().getlProp()
				.getProperty("label.contactPersonInfo2"));
		contactPersonL.setLocation(10, 20);
		contactPersonL.setSize(600, 20);

		this.add(contactPersonL);

		JLabel titleL = new JLabel(main.getConf().getlProp()
				.getProperty("label.phone"));
		titleL.setLocation(10, 80);
		titleL.setSize(200, 20);

		this.add(titleL);

		JLabel firstnameL = new JLabel(main.getConf().getlProp()
				.getProperty("label.mobile"));
		firstnameL.setLocation(10, 120);
		firstnameL.setSize(200, 20);

		this.add(firstnameL);

		JLabel surnameL = new JLabel(main.getConf().getlProp()
				.getProperty("label.fax"));
		surnameL.setLocation(10, 160);
		surnameL.setSize(200, 20);

		this.add(surnameL);

		JLabel passwordL1 = new JLabel(main.getConf().getlProp()
				.getProperty("label.password"));
		passwordL1.setLocation(10, 200);
		passwordL1.setSize(200, 20);

		this.add(passwordL1);

		JLabel passwordL2 = new JLabel(main.getConf().getlProp()
				.getProperty("label.repeat"));
		passwordL2.setLocation(10, 240);
		passwordL2.setSize(200, 20);

		this.add(passwordL2);

		phoneT = new JTextField();
		phoneT.setLocation(220, 80);
		phoneT.setSize(200, 20);

		this.add(phoneT);

		mobileT = new JTextField();
		mobileT.setLocation(220, 120);
		mobileT.setSize(200, 20);

		this.add(mobileT);

		faxT = new JTextField();
		faxT.setLocation(220, 160);
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

		JButton insertB = new JButton(main.getConf().getlProp()
				.getProperty("button.save"));
		insertB.setLocation(340, 280);
		insertB.setSize(80, 20);

		this.add(insertB);

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

				if (!main.getCenter().setPassword(p1, p2))
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
					main.getMainFrame().aktStatusPanel(
							(main.getConf().getlProp()
									.getProperty("error.insert")));

			}
		});
	}
}
