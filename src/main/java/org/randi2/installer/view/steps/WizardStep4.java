package org.randi2.installer.view.steps;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.randi2.installer.model.enumerations.StatusEnum;
import org.randi2.installer.view.MainPanel;
import org.randi2.installer.controller.Main;

public class WizardStep4 extends MainPanel {

	private static final long serialVersionUID = 7599687124907186290L;
	private JLabel serverL;
	private JTextField serverDBT;
	private JTextField usernameT;
	private JTextField nameDBT;
	private JPasswordField passwordT1;
	private JPasswordField passwordT2;

	public WizardStep4(Main main) {
		super(main);
		initGUI();
	}

	public void initGUI() {
		serverL = new JLabel(main.getConf().getlProp()
				.getProperty("label.serverDBAcc"));
		serverL.setLocation(10, 0);
		serverL.setSize(400, 20);

		this.add(serverL);

		JLabel serverDBL = new JLabel(main.getConf().getlProp()
				.getProperty("label.serverDB"));
		serverDBL.setLocation(10, 40);
		serverDBL.setSize(200, 20);

		this.add(serverDBL);

		JLabel nameDBL = new JLabel(main.getConf().getlProp()
				.getProperty("label.nameDB"));
		nameDBL.setLocation(10, 80);
		nameDBL.setSize(200, 20);

		this.add(nameDBL);

		JLabel usernameL = new JLabel(main.getConf().getlProp()
				.getProperty("label.username"));
		usernameL.setLocation(10, 120);
		usernameL.setSize(200, 20);

		this.add(usernameL);

		JLabel passwordL1 = new JLabel(main.getConf().getlProp()
				.getProperty("label.password"));
		passwordL1.setLocation(10, 160);
		passwordL1.setSize(200, 20);

		this.add(passwordL1);

		JLabel passwordL2 = new JLabel(main.getConf().getlProp()
				.getProperty("label.repeat"));
		passwordL2.setLocation(10, 200);
		passwordL2.setSize(200, 20);

		this.add(passwordL2);

		serverDBT = new JTextField("127.0.0.1");
		serverDBT.setLocation(220, 40);
		serverDBT.setSize(200, 20);

		this.add(serverDBT);

		nameDBT = new JTextField("randi2DB");
		nameDBT.setLocation(220, 80);
		nameDBT.setSize(200, 20);

		this.add(nameDBT);

		usernameT = new JTextField();
		usernameT.setLocation(220, 120);
		usernameT.setSize(200, 20);

		this.add(usernameT);

		passwordT1 = new JPasswordField();
		passwordT1.setLocation(220, 160);
		passwordT1.setSize(200, 20);

		this.add(passwordT1);

		passwordT2 = new JPasswordField();
		passwordT2.setLocation(220, 200);
		passwordT2.setSize(200, 20);

		this.add(passwordT2);

		JButton insertB = new JButton(main.getConf().getlProp()
				.getProperty("button.save"));
		insertB.setLocation(335, 240);
		insertB.setSize(80, 20);

		this.add(insertB);

		JLabel mandatoryL = new JLabel(main.getConf().getlProp()
				.getProperty("label.mandatory"));
		mandatoryL.setSize(150, 20);
		mandatoryL.setLocation(200, 240);

		this.add(mandatoryL);

		insertB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.getStatusService().getAkt().setStatus(StatusEnum.SUCCESS);

				if (!main.getDbconf().setServer(serverDBT.getText()))
					main.getStatusService().getAkt().setStatus(StatusEnum.FAIL);

				if (!main.getDbconf().setUsername(usernameT.getText()))
					main.getStatusService().getAkt().setStatus(StatusEnum.FAIL);

				if (!main.getDbconf().setName(nameDBT.getText()))
					main.getStatusService().getAkt().setStatus(StatusEnum.FAIL);

				String p1 = "";
				String p2 = "";
				for (int i = 0; i < passwordT1.getPassword().length; i++)
					p1 = p1 + passwordT1.getPassword()[i];

				for (int k = 0; k < passwordT2.getPassword().length; k++)
					p2 = p2 + passwordT2.getPassword()[k];

				if (!main.getDbconf().setPassword(p1, p2))
					main.getStatusService().getAkt().setStatus(StatusEnum.FAIL);

				if (main.getStatusService().getAkt().getStatus()
						.equals(StatusEnum.FAIL))
					main.getMainFrame()
							.getStatusText()
							.setText(
									(main.getConf().getlProp()
											.getProperty("error.insert")));
				else
					main.createDatabase();
			}
		});

	}
}
