package org.randi2.installer.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import org.randi2.installer.model.enumerations.Language;
import org.randi2.installer.controller.Main;

/**
 * 
 * @author andreas Erzeugt ein MainPanel, indem der Benutzer die Sprache
 *         auswaehlen kann.
 */

public class LanguagePanel extends MainPanel {

	private static final long serialVersionUID = 1243033736166929348L;

	public LanguagePanel(final Main main) {
		super(main);
		JLabel lger = new JLabel("Bitte waehlen Sie eine Sprache aus.");
		lger.setFont(new Font("Dialog", 0, 18));
		lger.setLocation(100, 0);
		lger.setSize(600, 40);

		this.add(lger);

		JLabel lus = new JLabel("Please select a language.");
		lus.setFont(new Font("Dialog", 0, 18));
		lus.setLocation(100, 40);
		lus.setSize(600, 40);
		this.setVisible(true);
		this.add(lus);

		ImageIcon ger = new ImageIcon(
				ClassLoader.getSystemResource("ger.png"));
		JButton bGer = new JButton(ger);
		bGer.setSize(90, 90);
		bGer.setLocation(150, 110);
		bGer.setVisible(true);
		this.add(bGer);

		ImageIcon us = new ImageIcon(
				ClassLoader.getSystemResource("us.png"));
		JButton bUS = new JButton(us);
		bUS.setSize(90, 90);
		bUS.setLocation(280, 110);
		bUS.setVisible(true);
		this.add(bUS);
		this.setVisible(true);

		bGer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.getConf().loadProperties(Language.GER,
						main.getStatusService());
				main.start();
			}
		});

		bUS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.getConf().loadProperties(Language.US,
						main.getStatusService());
				main.start();
			}
		});
	}
}
