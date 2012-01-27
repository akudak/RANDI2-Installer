package org.randi2.installer.model.services;

public interface FileServiceInterface {

	public void rename(String Path, String name, String newName);

	public void copy(String name, String oldPath, String newPath);
}
