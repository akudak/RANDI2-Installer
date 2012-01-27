package org.randi2.installer;

import static org.junit.Assert.assertTrue;
import java.io.File;
import org.junit.BeforeClass;
import org.junit.Test;
import org.randi2.installer.controller.StatusService;
import org.randi2.installer.model.services.FileService;

public class FileServiceTest {

	private static FileService FILESERVICE;
	private static final String PATH = ClassLoader.getSystemResource(
			"").getFile();
	private static final String NAME = "TestDatei.txt";
	private static final String NAME2 = "TestDatei2.txt";

	@BeforeClass
	public static void setUpBeforeClass() {
		FILESERVICE = new FileService(new StatusService());

	}

	@Test
	public void rename() {
		FILESERVICE.rename(PATH, NAME, NAME2);
		assertTrue(new File(PATH + NAME2).isFile());
		// Wieder rueckgaengig machen
		FILESERVICE.rename(PATH, NAME2, NAME);
	}

	@Test
	public void copy() {
		FILESERVICE.copy(NAME, PATH, PATH + "/webapps/");
		assertTrue(new File(PATH + "/webapps/" + NAME).isFile());
		// Wieder rueckgaengig machen
		FILESERVICE.copy(NAME, PATH + "/webapps/", PATH);
	}
}
