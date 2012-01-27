package org.randi2.installer.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import org.randi2.installer.model.enumerations.Language;
import org.randi2.installer.controller.Main;

/**
 * 
 * @author andreas Erzeugt eine Languagebar.
 */
public class Languagebar extends JPanel {

	private static final long serialVersionUID = 596908881967415838L;
	private Main main;

	public Languagebar(Main main) {
		super();
		this.main = main;
		initGUI();
	}

	private void initGUI() {
		this.setSize(140, 60);
		this.setLocation(630, 30);
		this.setBackground(Color.WHITE);

		ImageIcon ger = new ImageIcon(
				ClassLoader.getSystemResource("ger_40x40.gif"));
		JButton bGer = new JButton(ger);
		bGer.setSize(50, 50);
		bGer.setLocation(10, 5);
		bGer.setVisible(true);
		this.add(bGer);

		ImageIcon us = new ImageIcon(
				ClassLoader.getSystemResource("us_40x40.gif"));
		JButton bUS = new JButton(us);
		bUS.setSize(50, 50);
		bUS.setLocation(70, 5);
		bUS.setVisible(true);
		this.add(bUS);

		this.setVisible(true);
		bGer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.getConf().loadProperties(Language.GER,
						main.getStatusService());
				main.repaint();
			}
		});

		bUS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.getConf().loadProperties(Language.US,
						main.getStatusService());
				main.repaint();
			}
		});
	}
}
