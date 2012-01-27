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

public class WizardStep12 extends MainPanel {

	private static final long serialVersionUID = 7191819218453076401L;
	private JTextField adminEMailT;
	private JTextField adminFaxT;
	private JTextField adminMobileT;
	private JTextField adminPhoneT;
	private JPasswordField adminPasswordT1;
	private JPasswordField adminPasswordT2;

	public WizardStep12(final Main main) {
		super(main);
		initGUI();
	}

	public void initGUI() {
		JLabel adminL = new JLabel(main.getConf().getlProp()
				.getProperty("label.adminInfo"));
		adminL.setLocation(10, 5);
		adminL.setSize(400, 20);

		this.add(adminL);

		JLabel emailL = new JLabel(main.getConf().getlProp()
				.getProperty("label.email"));
		emailL.setLocation(10, 40);
		emailL.setSize(200, 20);

		this.add(emailL);

		JLabel faxL = new JLabel(main.getConf().getlProp()
				.getProperty("label.fax"));
		faxL.setLocation(10, 80);
		faxL.setSize(200, 20);

		this.add(faxL);

		JLabel mobileL = new JLabel(main.getConf().getlProp()
				.getProperty("label.mobile"));
		mobileL.setLocation(10, 120);
		mobileL.setSize(200, 20);

		this.add(mobileL);

		JLabel phoneL = new JLabel(main.getConf().getlProp()
				.getProperty("label.phone"));
		phoneL.setLocation(10, 160);
		phoneL.setSize(200, 20);

		this.add(phoneL);

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

		adminEMailT = new JTextField();
		adminEMailT.setLocation(220, 40);
		adminEMailT.setSize(200, 20);

		this.add(adminEMailT);

		adminFaxT = new JTextField();
		adminFaxT.setLocation(220, 80);
		adminFaxT.setSize(200, 20);

		this.add(adminFaxT);

		adminMobileT = new JTextField();
		adminMobileT.setLocation(220, 120);
		adminMobileT.setSize(200, 20);

		this.add(adminMobileT);

		adminPhoneT = new JTextField();
		adminPhoneT.setLocation(220, 160);
		adminPhoneT.setSize(200, 20);

		this.add(adminPhoneT);

		adminPasswordT1 = new JPasswordField();
		adminPasswordT1.setLocation(220, 200);
		adminPasswordT1.setSize(200, 20);

		this.add(adminPasswordT1);

		adminPasswordT2 = new JPasswordField();
		adminPasswordT2.setLocation(220, 240);
		adminPasswordT2.setSize(200, 20);

		this.add(adminPasswordT2);

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
				for (int i = 0; i < adminPasswordT1.getPassword().length; i++)
					p1 = p1 + adminPasswordT1.getPassword()[i];

				for (int k = 0; k < adminPasswordT2.getPassword().length; k++)
					p2 = p2 + adminPasswordT2.getPassword()[k];

				if (!main.getAdmin().setPassword(p1, p2))
					main.getStatusService().getAkt().setStatus(-1);

				if (!main.getAdmin().setMail(adminEMailT.getText()))
					main.getStatusService().getAkt().setStatus(-1);

				if (!main.getAdmin().setFax(adminFaxT.getText()))
					main.getStatusService().getAkt().setStatus(-1);

				if (!main.getAdmin().setMobile(adminMobileT.getText()))
					main.getStatusService().getAkt().setStatus(-1);

				if (!main.getAdmin().setPhone(adminPhoneT.getText()))
					main.getStatusService().getAkt().setStatus(-1);

				if (main.getStatusService().getAkt().getStatus() != -1)
					main.editAdmin();
				else
					main.getMainFrame().aktStatusPanel(
							(main.getConf().getlProp()
									.getProperty("error.insert")));
			}
		});
	}
}
