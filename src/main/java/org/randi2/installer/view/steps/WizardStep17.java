package org.randi2.installer.view.steps;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.randi2.installer.model.enumerations.StatusEnum;
import org.randi2.installer.view.MainPanel;

import org.randi2.installer.controller.Main;

public class WizardStep17 extends MainPanel {

	private static final long serialVersionUID = 5226022527665435772L;
	private JTextField infoHosterT;
	private JTextField mailFromT;
	private JTextField infoServerT;

	public WizardStep17(final Main main) {
		super(main);
		initGUI();

	}

	public void initGUI() {
		JLabel infoMail = new JLabel(main.getConf().getlProp()
				.getProperty("label.infoMail"));
		infoMail.setLocation(10, 20);
		infoMail.setSize(400, 20);

		this.add(infoMail);

		JLabel mailFrom = new JLabel(main.getConf().getlProp()
				.getProperty("label.mailFrom"));
		mailFrom.setLocation(10, 80);
		mailFrom.setSize(200, 20);

		this.add(mailFrom);

		JLabel infoServer = new JLabel(main.getConf().getlProp()
				.getProperty("label.website"));
		infoServer.setLocation(10, 120);
		infoServer.setSize(200, 20);

		this.add(infoServer);

		JLabel infoHoster = new JLabel(main.getConf().getlProp()
				.getProperty("label.infoHoster"));
		infoHoster.setLocation(10, 160);
		infoHoster.setSize(200, 20);

		this.add(infoHoster);

		mailFromT = new JTextField("randi2@randi2.de");
		mailFromT.setLocation(220, 80);
		mailFromT.setSize(200, 20);

		this.add(mailFromT);

		infoServerT = new JTextField("www.randi2.org");
		infoServerT.setLocation(220, 120);
		infoServerT.setSize(200, 20);

		this.add(infoServerT);

		infoHosterT = new JTextField("RANDI2 Team");
		infoHosterT.setLocation(220, 160);
		infoHosterT.setSize(200, 20);

		this.add(infoHosterT);

		JButton insertB = new JButton(main.getConf().getlProp()
				.getProperty("button.save"));
		insertB.setLocation(340, 200);
		insertB.setSize(80, 20);

		this.add(insertB);

		JLabel mandatoryL = new JLabel(main.getConf().getlProp()
				.getProperty("label.mandatory"));
		mandatoryL.setSize(150, 20);
		mandatoryL.setLocation(205, 200);

		this.add(mandatoryL);

		insertB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				main.getStatusService().getAkt().setStatus(StatusEnum.SUCCESS);
				if (!main.getConf().setMail_from(mailFromT.getText()))
					main.getStatusService().getAkt().setStatus(StatusEnum.FAIL);

				if (!main.getConf().setInfo_server(infoServerT.getText()))
					main.getStatusService().getAkt().setStatus(StatusEnum.FAIL);

				if (!main.getConf().setInfo_hoster(infoHosterT.getText()))
					main.getStatusService().getAkt().setStatus(StatusEnum.FAIL);

				if (!main.getStatusService().getAkt().getStatus()
						.equals(StatusEnum.FAIL))
					main.editInfoData();
				else
					main.getMainFrame()
							.getStatusText()
							.setText(
									(main.getConf().getlProp()
											.getProperty("error.insert")));

			}
		});
	}
}