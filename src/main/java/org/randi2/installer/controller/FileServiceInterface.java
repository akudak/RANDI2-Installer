package org.randi2.installer.controller;

public interface FileServiceInterface {


	/**
	 * Benennt eine Datei um
	 */
	public void rename(String Path, String name, String newName);
	
	/**
	 * Korpiert eine Datei in ein neues Verzeichnis
	 */
	public void copy(String name, String oldPath, String newPath);
}
