package org.randi2.installer.view.steps;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import org.randi2.installer.view.MainPanel;
import org.randi2.installer.io.Chooser;
import org.randi2.installer.model.enumerations.StatusEnum;
import org.randi2.installer.controller.Main;

public class WizardStep16 extends MainPanel {

	private static final long serialVersionUID = -7646853990013034406L;
	private JTextField contactWebsiteT;
	private JButton logoPathB;
	private JTextField downloadPathT;
	private boolean logo;

	public WizardStep16(final Main main) {
		super(main);
		initGUI();
	}

	public void initGUI() {
		JLabel logoL = new JLabel(main.getConf().getlProp()
				.getProperty("label.logo"));
		logoL.setLocation(10, 20);
		logoL.setSize(400, 20);
		this.add(logoL);

		logoPathB = new JButton(main.getConf().getlProp()
				.getProperty("button.open"));
		logoPathB.setLocation(10, 60);
		logoPathB.setSize(70, 20);

		this.add(logoPathB);

		downloadPathT = new JTextField();
		downloadPathT.setSize(350, 20);
		downloadPathT.setLocation(100, 60);
		downloadPathT.setEditable(false);

		this.add(downloadPathT);

		JLabel contactWebsite = new JLabel(main.getConf().getlProp()
				.getProperty("label.contactWebsite"));
		contactWebsite.setLocation(10, 120);
		contactWebsite.setSize(400, 20);

		this.add(contactWebsite);

		JLabel contactWebsiteL = new JLabel(main.getConf().getlProp()
				.getProperty("label.website"));
		contactWebsiteL.setLocation(10, 160);
		contactWebsiteL.setSize(200, 20);

		this.add(contactWebsiteL);

		contactWebsiteT = new JTextField();
		contactWebsiteT.setLocation(220, 160);
		contactWebsiteT.setSize(200, 20);

		this.add(contactWebsiteT);
		logo = false;
		logoPathB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Chooser fileOpen = new Chooser(main);
				if (main.getConf().setLogoPath(fileOpen.getFile(""))) {
					downloadPathT.setText(main.getConf().getLogoPath());
					logo = true;
				} else {
					main.getStatusService().getAkt().setStatus(StatusEnum.FAIL);
					downloadPathT.setText("");
					main.getMainFrame()
							.getStatusText()
							.setText(
									(main.getConf().getlProp()
											.getProperty("error.logo")));
				}

			}
		});

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
				if (logo) {
					main.getStatusService().getAkt()
							.setStatus(StatusEnum.SUCCESS);

					if (!main.getConf().setWebsite(contactWebsiteT.getText()))
						main.getStatusService().getAkt()
								.setStatus(StatusEnum.FAIL);
					if (!main.getStatusService().getAkt().getStatus()
							.equals(StatusEnum.FAIL)) {
						main.copyLogo();
						main.editWebsite();
					} else
						main.getMainFrame()
								.getStatusText()
								.setText(
										(main.getConf().getlProp()
												.getProperty("error.insert")));
				} else
					main.getMainFrame()
							.getStatusText()
							.setText(
									(main.getConf().getlProp()
											.getProperty("error.logoFirst")));
			}

		});
	}
}