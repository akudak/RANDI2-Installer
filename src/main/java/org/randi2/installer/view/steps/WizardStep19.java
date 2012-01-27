package org.randi2.installer.view.steps;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.randi2.installer.view.MainPanel;
import org.randi2.installer.controller.Main;

public class WizardStep19 extends MainPanel {
	private static final long serialVersionUID = -8925606432927260647L;
	private JTextField hostingInstGERT;
	private JTextField hostingInstUST;

	public WizardStep19(final Main main) {
		super(main);
		initGUI();
	}

	public void initGUI() {
		JLabel disclaimerL = new JLabel(main.getConf().getlProp()
				.getProperty("label.infoInst"));
		disclaimerL.setLocation(10, 20);
		disclaimerL.setSize(600, 20);

		this.add(disclaimerL);

		hostingInstGERT = new JTextField(
				"Diese RANDI2 Installation wird von XYZ verwaltet.");
		hostingInstGERT.setLocation(10, 80);
		hostingInstGERT.setSize(600, 20);
		this.add(hostingInstGERT);

		hostingInstUST = new JTextField(
				"This RANDI2 installation is hosted and maintained by XYZ");
		hostingInstUST.setLocation(10, 140);
		hostingInstUST.setSize(600, 20);

		this.add(hostingInstUST);

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
				if (!main.getConf()
						.setHostingInstGER(hostingInstGERT.getText()))
					main.getStatusService().getAkt().setStatus(-1);

				if (!main.getConf().setHostingInstUS(hostingInstUST.getText()))
					main.getStatusService().getAkt().setStatus(-1);

				if (main.getStatusService().getAkt().getStatus() != -1)
					main.editLabel();
				else
					main.getMainFrame().aktStatusPanel(
							(main.getConf().getlProp()
									.getProperty("error.insert")));
			}
		});
	}
}
