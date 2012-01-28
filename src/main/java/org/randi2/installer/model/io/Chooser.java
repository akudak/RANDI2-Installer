package org.randi2.installer.model.io;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;

import org.randi2.installer.controller.Main;
import org.randi2.installer.controller.configuration.Configuration;

/**
 * 
 * @author andreas Oeffnet einen Dialog mit dem Datein oder Ordner ausgewaehlt
 *         werden koennen.
 */
public class Chooser {

	private Main main;

	public Chooser(Main main) {
		this.main = main;
	}

	/**
	 * Oeffnet Verzeichnis um eiene Datei auszuwaehlen
	 * 
	 * @param conf
	 * @param start
	 * @return Pfad zur Datei
	 */
	public String getDirectorie(Configuration conf, String start) {
		String path = "fail";
		final JFileChooser chooser = new JFileChooser(main.getConf().getlProp()
				.getProperty("label.directorie"));
		chooser.setDialogType(JFileChooser.OPEN_DIALOG);
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		final File file = new File(start);
		chooser.setCurrentDirectory(file);

		chooser.setVisible(true);
		final int result = chooser.showOpenDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			File inputVerzFile = chooser.getSelectedFile();
			path = inputVerzFile.getPath();
			path = path + "/";
		}
		chooser.setVisible(false);
		return path;
	}

	/**
	 * 
	 * @param format
	 * @return Gibt Pfad der ausgewaehlten Datei zurueck
	 */
	public String getFile(final String format) {
		String path = "";
		JFileChooser chooser = new JFileChooser();
		if (!format.equals(""))
			chooser.addChoosableFileFilter(new FileFilter() {
				public boolean accept(File f) {
					if (f.isDirectory())
						return true;
					return f.getName().toLowerCase().endsWith("." + format);
				}

				public String getDescription() {
					return format;
				}
			});
		chooser.setMultiSelectionEnabled(false);
		JFrame frame = new JFrame();
		if (chooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION)
			path = chooser.getSelectedFile().getPath();
		return path;
	}
}
