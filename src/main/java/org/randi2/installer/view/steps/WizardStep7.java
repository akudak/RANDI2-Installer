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

public class WizardStep7 extends MainPanel {

	private static final long serialVersionUID = 4572893521134618163L;
	private JTextField downloadPathT;
	
	public WizardStep7(final Main main) {
		super(main);
		initGUI();
	}

	public void initGUI() {
		JLabel downloadJDBC = new JLabel(main.getConf().getlProp()
				.getProperty("label.JDBCDownload"));
		downloadJDBC.setLocation(10, 10);
		downloadJDBC.setSize(600, 20);

		this.add(downloadJDBC);

		JButton downloadButton = new JButton(main.getConf().getlProp()
				.getProperty("button.download"));
		downloadButton.setLocation(10, 40);
		downloadButton.setSize(70, 20);

		this.add(downloadButton);

		downloadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (main.getDbconf().isMySQL())
					main.getUrlService().openURL("JDBC_MYSQL");
				else
					main.getUrlService().openURL("JDBC_POSTGRE");
			}
		});

		JLabel downloadPfadL = new JLabel(main.getConf().getlProp()
				.getProperty("label.jarJDBCPath"));
		downloadPfadL.setLocation(10, 90);
		downloadPfadL.setSize(400, 20);

		this.add(downloadPfadL);

		JButton downloadPfadB = new JButton(main.getConf().getlProp()
				.getProperty("button.open"));
		downloadPfadB.setLocation(10, 120);
		downloadPfadB.setSize(70, 20);

		this.add(downloadPfadB);

		downloadPathT = new JTextField();
		downloadPathT.setSize(350, 20);
		downloadPathT.setLocation(100, 120);
		downloadPathT.setEditable(false);

		this.add(downloadPathT);
		
		downloadPfadB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.getStatusService().getAkt().setStatus(StatusEnum.SUCCESS);
				Chooser fileOpen = new Chooser(main);
				if (!main.getConf().setJDBCPath(fileOpen.getFile("jar"))) {
					main.getStatusService().getAkt().setStatus(StatusEnum.FAIL);
						main.getMainFrame().getStatusText().setText(
									(main.getConf().getlProp()
											.getProperty("error.jar")));
				}
				downloadPathT.setText(main.getConf().getJDBCPath());
				main.copyJDBC();
			
			}
		});
	}
}
