package org.randi2.installer.view;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.randi2.installer.controller.StatusService;

/**
 * 
 * @author andreas Erzeugt die Statusbar
 */
public class Statusbar extends JPanel {

	private static final long serialVersionUID = -6083229991703208570L;
	private ImageIcon iconGreen;
	private ImageIcon iconYellow;
	private ImageIcon iconRed;
	private ImageIcon iconGreenG;
	private ImageIcon iconYellowG;
	private ImageIcon iconRedG;
	private StatusService statusService;

	public Statusbar(StatusService statusService) {
		this.statusService = statusService;
		init();
	}

	/**
	 * Laed die Bilder
	 */
	public void init() {
		this.setSize(800, 100);
		this.setLocation(0, 120);
		this.setBackground(Color.WHITE);
		iconGreen = new ImageIcon(
				ClassLoader.getSystemResource("gruen_30x30.png"));
		iconYellow = new ImageIcon(
				ClassLoader.getSystemResource("gelb_30x30.png"));
		iconRed = new ImageIcon(
				ClassLoader.getSystemResource("rot_30x30.png"));
		iconGreenG = new ImageIcon(
				ClassLoader.getSystemResource("gruen.png"));
		iconYellowG = new ImageIcon(
				ClassLoader.getSystemResource("gelb.png"));
		iconRedG = new ImageIcon(ClassLoader.getSystemResource("rot.png"));

		initBar();
	}

	public void initBar() {
		JLabel[] l = new JLabel[statusService.getStatusList().size()];
		for (int i = 0; i < statusService.getStatusList().size() - 1; i++) {
			if (statusService.getStatusList().get(i).isAktive()) {
				if (statusService.getStatusList().get(i).getStatus() == 1)
					l[i] = new JLabel(iconGreenG);
				if (statusService.getStatusList().get(i).getStatus() == -1)
					l[i] = new JLabel(iconRedG);
				if (statusService.getStatusList().get(i).getStatus() == 0)
					l[i] = new JLabel(iconYellowG);
				l[i].setSize(40, 40);
			} else {
				if (statusService.getStatusList().get(i).getStatus() == 1)
					l[i] = new JLabel(iconGreen);
				if (statusService.getStatusList().get(i).getStatus() == -1)
					l[i] = new JLabel(iconRed);
				if (statusService.getStatusList().get(i).getStatus() == 0)
					l[i] = new JLabel(iconYellow);
				l[i].setSize(40, 40);
			}
			l[i].setLocation(i * 37 + 40, 20);
			l[i].setVisible(true);
			this.add(l[i]);
		}
		this.repaint();
	}
}
