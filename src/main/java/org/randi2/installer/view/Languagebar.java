package org.randi2.installer.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import org.randi2.installer.controller.Main;

/**
 * 
 * @author andreas Erzeugt eine Languagebar.
 * 
 *         Bie Bilder fuer die Flaggen stammen von Nordic Factory -
 *         www.nordicfactory.com und d�rfen frei verwendet werden.
 */
public class Languagebar extends JPanel {

	private static final long serialVersionUID = 596908881967415838L;
	private Main main;

	public Languagebar(Main main) {
		super();
		this.main = main;
		initGUI();
	}

	/**
	 * Erstelle Objekte
	 * 
	 */
	private void initGUI() {
		this.setSize(140, 60);
		this.setLocation(630, 30);
		this.setBackground(Color.WHITE);

		ImageIcon ger = new ImageIcon(
				ClassLoader.getSystemResource("DE_40x40.png"));
		JButton bGer = new JButton(ger);
		bGer.setSize(50, 50);
		bGer.setLocation(10, 5);
		bGer.setVisible(true);
		this.add(bGer);

		ImageIcon us = new ImageIcon(
				ClassLoader.getSystemResource("US_40x40.png"));
		JButton bUS = new JButton(us);
		bUS.setSize(50, 50);
		bUS.setLocation(70, 5);
		bUS.setVisible(true);
		this.add(bUS);

		this.setVisible(true);
		bGer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.getConf().loadLanguageProperties(Locale.GERMANY, main);
				main.repaint();
			}
		});

		bUS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.getConf().loadLanguageProperties(Locale.US, main);
				main.repaint();
			}
		});
	}
}
