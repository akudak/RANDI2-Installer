package org.randi2.installer.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.randi2.installer.controller.Main;

/**
 * 
 * @author andreas Erzeugt ein MainFrame auf dem die Panels abgelegt werden.
 */
public class MainFrame extends JFrame {

	private static final long serialVersionUID = 5428707011904387779L;
	private JPanel statusbar;
	private Main main;
	private JPanel mainPanel;
	private JButton bPrevious;
	private JButton bNext;
	private JTextField statusText;
	private Logo logo;
	private Languagebar languagebar;

	public MainFrame(Main main) {
		this.main = main;
		this.setTitle("RANDI2 - Installer");
		this.setResizable(false);
		this.setBackground(Color.WHITE);
		this.setSize(800, 600);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void initGUI() {
		this.statusbar = main.getStatusbar();
		languagebar = new Languagebar(this.main);
		logo = new Logo();
		statusText = new JTextField();
		statusText.setSize(500, 28);
		statusText.setLocation(60, 520);
		statusText.setVisible(true);
		statusText.setEditable(false);
		this.add(statusText);
		this.add(logo);
		this.add(statusbar);
		this.add(languagebar);
		initButton();
	}

	public void repaintAll() {
		this.remove(statusbar);
		this.remove(languagebar);
		this.remove(bNext);
		this.remove(bPrevious);
		this.remove(logo);
		this.remove(statusText);
		initGUI();
		this.remove(mainPanel);
		repaint();
	}

	public void setMainPanel(JPanel mainPanel) {
		if (this.mainPanel != null)
			this.remove(this.mainPanel);
		this.mainPanel = mainPanel;
		this.add(this.mainPanel);
		this.repaint();
	}

	public void initButton() {
		bNext = new JButton(main.getConf().getlProp()
				.getProperty("button.next"));
		bNext.setSize(70, 28);
		bNext.setLocation(700, 520);
		this.add(bNext);
		bNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.setStatusNext();
			}
		});

		bPrevious = new JButton(main.getConf().getlProp()
				.getProperty("button.previous"));
		bPrevious.setSize(90, 28);
		bPrevious.setLocation(620, 520);
		this.add(bPrevious);

		bPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.setStatusPrevious();
			}
		});
	}


	/**
	 * @return the bPrevious
	 */
	public JButton getbPrevious() {
		return bPrevious;
	}

	/**
	 * @param bPrevious
	 *            the bPrevious to set
	 */
	public void setbPrevious(JButton bPrevious) {
		this.bPrevious = bPrevious;
	}

	/**
	 * @return the bNext
	 */
	public JButton getbNext() {
		return bNext;
	}

	/**
	 * @param bNext
	 *            the bNext to set
	 */
	public void setbNext(JButton bNext) {
		this.bNext = bNext;
	}

	/**
	 * @return the statusbar
	 */
	public JPanel getStatusbar() {
		return statusbar;
	}

	/**
	 * @param statusbar
	 *            the statusbar to set
	 */
	public void setStatusbar(JPanel statusbar) {
		this.statusbar = statusbar;
	}

	/**
	 * @return the statusText
	 */
	public JTextField getStatusText() {
		return statusText;
	}

	/**
	 * @param statusText
	 *            the statusText to set
	 */
	public void setStatusText(JTextField statusText) {
		this.statusText = statusText;
		this.add(statusText);
		this.repaint();
	}

}
