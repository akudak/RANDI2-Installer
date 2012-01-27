package org.randi2.installer.view;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * @author andreas Zeigt das RANDI2 Logo an.
 */
public class Logo extends JPanel {

	private static final long serialVersionUID = 593847208534027948L;

	public Logo() {
		super();
		init();
	}

	public void init() {
		this.setSize(351, 100);
		this.setLocation(150, 20);
		this.setBackground(Color.WHITE);

		ImageIcon icon = new ImageIcon(
				ClassLoader
						.getSystemResource("randi2withSlogan_351x100.png"));

		JLabel label = new JLabel(icon);
		label.setSize(351, 100);
		label.setLocation(20, 5);
		label.setVisible(true);
		this.add(label);
		this.setVisible(true);
	}
}
