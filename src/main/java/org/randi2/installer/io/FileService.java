package org.randi2.installer.io;

import java.io.File;

import org.randi2.installer.controller.StatusService;
import org.randi2.installer.model.enumerations.StatusEnum;

/**
 * 
 * @author andreas Kopiert Datein. Benennt Datein um.
 */

public class FileService implements FileServiceInterface {

	private StatusService statusService;

	public FileService(StatusService statusService) {
		this.statusService = statusService;
	}

	/**
	 * Benennt eine Datei um
	 */
	@Override
	public void rename(String path, String name, String newName) {
		File source = new File(path + name);
		File destination = new File(path + newName);
		if (!source.renameTo(destination)) {
			statusService.getAkt().setStatus(StatusEnum.FAIL);
		}
	}

	/**
	 * Korpiert eine Datei in ein neues Verzeichnis
	 */
	@Override
	public void copy(String name, String oldPath, String newPath) {
		File source = new File(oldPath + name);
		File destination = new File(newPath + name);
		if (!source.renameTo(destination)) {
			statusService.getAkt().setStatus(StatusEnum.FAIL);
		}
	}
}
