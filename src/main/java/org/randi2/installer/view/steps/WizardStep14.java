package org.randi2.installer.view.steps;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import org.randi2.installer.view.MainPanel;
import org.randi2.installer.model.enumerations.Gender;
import org.randi2.installer.controller.Main;

public class WizardStep14 extends MainPanel {

	private static final long serialVersionUID = 5482507537285196028L;
	private JTextField titleT;
	private JTextField firstnameT;
	private JTextField surnameT;
	private JTextField mailT;

	public WizardStep14(Main main) {
		super(main);
		initGUI();
	}

	public void initGUI() {
		JLabel contactPersonL = new JLabel(main.getConf().getlProp()
				.getProperty("label.contactPersonInfo"));
		contactPersonL.setLocation(10, 20);
		contactPersonL.setSize(600, 20);

		this.add(contactPersonL);

		JLabel titleL = new JLabel(main.getConf().getlProp()
				.getProperty("label.title"));
		titleL.setLocation(10, 80);
		titleL.setSize(200, 20);

		this.add(titleL);

		JLabel firstnameL = new JLabel(main.getConf().getlProp()
				.getProperty("label.firstname"));
		firstnameL.setLocation(10, 120);
		firstnameL.setSize(200, 20);

		this.add(firstnameL);

		JLabel surnameL = new JLabel(main.getConf().getlProp()
				.getProperty("label.surname"));
		surnameL.setLocation(10, 160);
		surnameL.setSize(200, 20);

		this.add(surnameL);

		JLabel usernameL = new JLabel(main.getConf().getlProp()
				.getProperty("label.email"));
		usernameL.setLocation(10, 200);
		usernameL.setSize(200, 20);

		this.add(usernameL);

		JLabel genderL = new JLabel(main.getConf().getlProp()
				.getProperty("label.gender"));
		genderL.setLocation(10, 240);
		genderL.setSize(200, 20);

		this.add(genderL);

		titleT = new JTextField();
		titleT.setLocation(220, 80);
		titleT.setSize(200, 20);

		this.add(titleT);

		firstnameT = new JTextField();
		firstnameT.setLocation(220, 120);
		firstnameT.setSize(200, 20);

		this.add(firstnameT);

		surnameT = new JTextField();
		surnameT.setLocation(220, 160);
		surnameT.setSize(200, 20);

		this.add(surnameT);

		mailT = new JTextField();
		mailT.setLocation(220, 200);
		mailT.setSize(200, 20);

		this.add(mailT);

		JRadioButton aButton = new JRadioButton(main.getConf().getlProp()
				.getProperty("check.female"));
		JRadioButton bButton = new JRadioButton(main.getConf().getlProp()
				.getProperty("check.male"));

		ButtonGroup myButtonGroup = new ButtonGroup();
		myButtonGroup.add(aButton);
		myButtonGroup.add(bButton);

		aButton.setLocation(220, 240);
		bButton.setLocation(340, 240);

		aButton.setSize(100, 20);
		bButton.setSize(100, 20);

		aButton.setVisible(true);
		bButton.setVisible(true);

		this.add(aButton);
		this.add(bButton);

		JButton insertB = new JButton(main.getConf().getlProp()
				.getProperty("button.save"));
		insertB.setLocation(340, 280);
		insertB.setSize(80, 20);

		this.add(insertB);

		aButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.getCenter().getContactPerson().setSex(Gender.FEMALE);
			}
		});

		bButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.getCenter().getContactPerson().setSex(Gender.MALE);
			}
		});

		insertB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JFrame jFrame = new JFrame();
				jFrame.setSize(300, 150);
				main.getStatusService().getAkt().setStatus(1);
				main.getCenter().getContactPerson()
						.setAcademicTitle(titleT.getText());
				if (!main.getCenter().getContactPerson()
						.setFirstname(firstnameT.getText()))
					main.getStatusService().getAkt().setStatus(-1);

				if (!main.getCenter().getContactPerson()
						.setSurname(surnameT.getText()))
					main.getStatusService().getAkt().setStatus(-1);

				if (!main.getCenter().getContactPerson()
						.setMail(mailT.getText()))
					main.getStatusService().getAkt().setStatus(-1);

				if (main.getStatusService().getAkt().getStatus() == -1)
					main.getMainFrame().aktStatusPanel(
							(main.getConf().getlProp()
									.getProperty("error.insert")));
			}
		});
	}
}
