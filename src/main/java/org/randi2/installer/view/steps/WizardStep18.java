package org.randi2.installer.view.steps;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.randi2.installer.view.MainPanel;
import org.randi2.installer.controller.Main;

public class WizardStep18 extends MainPanel {

	private static final long serialVersionUID = 8783327356935626274L;
	private JTextField disclaimerGERT;
	private JTextField disclaimerUST;

	public WizardStep18(final Main main) {

		super(main);
		initGUI();
	}

	public void initGUI() {
		JLabel disclaimerL = new JLabel(main.getConf().getlProp()
				.getProperty("label.disclaimer"));
		disclaimerL.setLocation(10, 20);
		disclaimerL.setSize(500, 20);

		this.add(disclaimerL);

		disclaimerGERT = new JTextField("Deutscher Disclaimer");
		disclaimerGERT.setLocation(10, 80);
		disclaimerGERT.setSize(600, 20);
		this.add(disclaimerGERT);

		disclaimerUST = new JTextField("Englischer Disclaimer");
		disclaimerUST.setLocation(10, 140);
		disclaimerUST.setSize(600, 20);

		this.add(disclaimerUST);

		JButton insertB = new JButton(main.getConf().getlProp()
				.getProperty("button.save"));
		insertB.setLocation(530, 200);
		insertB.setSize(80, 20);

		this.add(insertB);

		insertB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JFrame jFrame = new JFrame();
				jFrame.setSize(300, 150);
				main.getStatusService().getAkt().setStatus(1);

				if (!main.getConf().setDisclaimerGER(disclaimerGERT.getText()))
					main.getStatusService().getAkt().setStatus(-1);

				if (!main.getConf().setDisclaimerUS(disclaimerUST.getText()))
					main.getStatusService().getAkt().setStatus(-1);

				if (main.getStatusService().getAkt().getStatus() == -1)
					main.getMainFrame().aktStatusPanel(
							(main.getConf().getlProp()
									.getProperty("error.insert")));
			}
		});
	}
}
