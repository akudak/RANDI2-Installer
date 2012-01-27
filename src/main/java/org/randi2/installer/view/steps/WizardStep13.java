package org.randi2.installer.view.steps;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.randi2.installer.view.MainPanel;
import org.randi2.installer.controller.Main;

public class WizardStep13 extends MainPanel {

	private static final long serialVersionUID = 8796736405920985371L;
	private JTextField nameT;
	private JTextField countryT;
	private JTextField streetT;
	private JTextField plzT;
	private JTextField cityT;

	public WizardStep13(final Main main) {
		super(main);
		initGUI();
	}

	public void initGUI() {
		JLabel centerInfoL = new JLabel(main.getConf().getlProp()
				.getProperty("label.centerInfo"));
		centerInfoL.setLocation(10, 5);
		centerInfoL.setSize(400, 20);

		this.add(centerInfoL);

		JLabel nameL = new JLabel(main.getConf().getlProp()
				.getProperty("label.name"));
		nameL.setLocation(10, 40);
		nameL.setSize(200, 20);

		this.add(nameL);

		JLabel countryL = new JLabel(main.getConf().getlProp()
				.getProperty("label.country"));
		countryL.setLocation(10, 80);
		countryL.setSize(200, 20);

		this.add(countryL);

		JLabel cityL = new JLabel(main.getConf().getlProp()
				.getProperty("label.city"));
		cityL.setLocation(10, 120);
		cityL.setSize(200, 20);

		this.add(cityL);

		JLabel plzL = new JLabel(main.getConf().getlProp()
				.getProperty("label.plz"));
		plzL.setLocation(10, 160);
		plzL.setSize(200, 20);

		this.add(plzL);

		JLabel streetL = new JLabel(main.getConf().getlProp()
				.getProperty("label.street"));
		streetL.setLocation(10, 200);
		streetL.setSize(200, 20);

		this.add(streetL);

		nameT = new JTextField();
		nameT.setLocation(220, 40);
		nameT.setSize(200, 20);

		this.add(nameT);

		countryT = new JTextField();
		countryT.setLocation(220, 80);
		countryT.setSize(200, 20);

		this.add(countryT);

		cityT = new JTextField();
		cityT.setLocation(220, 120);
		cityT.setSize(200, 20);

		this.add(cityT);

		plzT = new JTextField();
		plzT.setLocation(220, 160);
		plzT.setSize(200, 20);

		this.add(plzT);

		streetT = new JTextField();
		streetT.setLocation(220, 200);
		streetT.setSize(200, 20);

		this.add(streetT);

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

				if (!main.getCenter().setCity(cityT.getText()))
					main.getStatusService().getAkt().setStatus(-1);

				if (!main.getCenter().setName(nameT.getText()))
					main.getStatusService().getAkt().setStatus(-1);

				if (!main.getCenter().setCountry(countryT.getText()))
					main.getStatusService().getAkt().setStatus(-1);

				if (!main.getCenter().setPlz(plzT.getText()))
					main.getStatusService().getAkt().setStatus(-1);

				if (!main.getCenter().setStreet(streetT.getText()))
					main.getStatusService().getAkt().setStatus(-1);

				if (main.getStatusService().getAkt().getStatus() == -1)
					main.getMainFrame().aktStatusPanel(
							(main.getConf().getlProp()
									.getProperty("error.insert")));
			}
		});
	}
}
