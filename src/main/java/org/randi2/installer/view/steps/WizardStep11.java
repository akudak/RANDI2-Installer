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

public class WizardStep11 extends MainPanel {

	private static final long serialVersionUID = 2369390476171854503L;
	private JTextField adminTitleT;
	private JTextField adminFirstnameT;
	private JTextField adminSurnameT;
	private JTextField adminUsernameT;

	public WizardStep11(final Main main) {
		super(main);
		initGUI();
	}

	public void initGUI() {
		JLabel adminL = new JLabel(main.getConf().getlProp()
				.getProperty("label.adminInfo"));
		adminL.setLocation(10, 20);
		adminL.setSize(400, 20);

		this.add(adminL);

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
				.getProperty("label.username"));
		usernameL.setLocation(10, 200);
		usernameL.setSize(200, 20);

		this.add(usernameL);

		JLabel genderL = new JLabel(main.getConf().getlProp()
				.getProperty("label.gender"));
		genderL.setLocation(10, 240);
		genderL.setSize(200, 20);

		this.add(genderL);

		adminTitleT = new JTextField();
		adminTitleT.setLocation(220, 80);
		adminTitleT.setSize(200, 20);

		this.add(adminTitleT);

		adminFirstnameT = new JTextField();
		adminFirstnameT.setLocation(220, 120);
		adminFirstnameT.setSize(200, 20);

		this.add(adminFirstnameT);

		adminSurnameT = new JTextField();
		adminSurnameT.setLocation(220, 160);
		adminSurnameT.setSize(200, 20);

		this.add(adminSurnameT);

		adminUsernameT = new JTextField();
		adminUsernameT.setLocation(220, 200);
		adminUsernameT.setSize(200, 20);

		this.add(adminUsernameT);

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
				main.getAdmin().setSex(Gender.FEMALE);
			}
		});

		bButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.getAdmin().setSex(Gender.MALE);
			}
		});

		insertB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JFrame jFrame = new JFrame();
				jFrame.setSize(300, 150);
				main.getStatusService().getAkt().setStatus(1);
				main.getAdmin().setAcademicTitle(adminTitleT.getText());

				if (!main.getAdmin().setFirstname(adminFirstnameT.getText()))
					main.getStatusService().getAkt().setStatus(-1);

				if (!main.getAdmin().setSurname(adminSurnameT.getText()))
					main.getStatusService().getAkt().setStatus(-1);

				if (!main.getAdmin().setUsername(adminUsernameT.getText()))
					main.getStatusService().getAkt().setStatus(-1);

				if (main.getConf().getLanguage().equals("GER"))
					main.getAdmin().setPrefLocale("de");
				else
					main.getAdmin().setPrefLocale("us");

				if (main.getStatusService().getAkt().getStatus() == -1)
					main.getMainFrame().aktStatusPanel(
							(main.getConf().getlProp()
									.getProperty("error.insert")));
			}
		});
	}
}
