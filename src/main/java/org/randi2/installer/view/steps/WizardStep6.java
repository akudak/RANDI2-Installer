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

public class WizardStep6 extends MainPanel {

	private static final long serialVersionUID = -4292214336317860926L;
	private JLabel initDBL;
	private JTextField downloadPathT;
	private boolean start;

	public WizardStep6(final Main main) {
		super(main);
		initGUI();
	}

	public void initGUI() {

		JLabel restartTomcatL = new JLabel(main.getConf().getlProp()
				.getProperty("label.restartTomcat"));
		restartTomcatL.setLocation(10, 10);
		restartTomcatL.setSize(600, 20);

		this.add(restartTomcatL);

		JButton startTomcatMacB = new JButton(main.getConf().getlProp()
				.getProperty("button.goMac"));
		startTomcatMacB.setLocation(10, 40);
		startTomcatMacB.setSize(90, 20);

		this.add(startTomcatMacB);

		JButton startTomcatWinB = new JButton(main.getConf().getlProp()
				.getProperty("button.goWin"));
		startTomcatWinB.setLocation(120, 40);
		startTomcatWinB.setSize(90, 20);

		this.add(startTomcatWinB);

		initDBL = new JLabel(main.getConf().getlProp()
				.getProperty("label.initDBMySQL"));

		initDBL.setLocation(10, 120);
		initDBL.setSize(600, 20);

		this.add(initDBL);

		downloadPathT = new JTextField();
		downloadPathT.setSize(350, 20);
		downloadPathT.setLocation(100, 150);
		downloadPathT.setEditable(false);

		this.add(downloadPathT);

		JButton initDBPfadB = new JButton(main.getConf().getlProp()
				.getProperty("button.open"));
		initDBPfadB.setLocation(10, 150);
		initDBPfadB.setSize(70, 20);

		this.add(initDBPfadB);
		start = false;
		startTomcatMacB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				start = true;
				main.startTomcatMac();
			}
		});

		startTomcatWinB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				start = true;
				main.startTomcatWin();
			}
		});

		initDBPfadB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (start) {
					main.getStatusService().getAkt()
							.setStatus(StatusEnum.SUCCESS);
					Chooser fileOpen = new Chooser(main);
					if (!main.getDbconf()
							.setInitDBPath(fileOpen.getFile("sql"))) {
						main.getStatusService().getAkt()
								.setStatus(StatusEnum.FAIL);
						System.out.println("fehler");
						main.getMainFrame()
								.getStatusText()
								.setText(
										(main.getConf().getlProp()
												.getProperty("error.loadSQL")));
					}
					downloadPathT.setText(main.getDbconf().getInitDBPath());
					main.initDatabase();
				} else
					main.getMainFrame()
							.getStatusText()
							.setText(
									(main.getConf().getlProp()
											.getProperty("error.startTomcatFirst")));

			}
		});
	}
}
