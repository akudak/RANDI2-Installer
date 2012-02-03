package org.randi2.installer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.junit.BeforeClass;
import org.junit.Test;
import org.randi2.installer.controller.StatusService;
import org.randi2.installer.io.FileService;

public class FileServiceTest {

	private static FileService FILESERVICE;
	private static final String PATH = ClassLoader.getSystemResource("")
			.getFile();
	private static final String NEWPATH = PATH + "/webapps/";
	private static final String NAME = "TestDatei.txt";
	private static final String NAME2 = "TestDatei2.txt";
	private static String TEXT;

	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		FILESERVICE = new FileService(new StatusService());
		FileReader fr = new FileReader(PATH + NAME);
		BufferedReader br = new BufferedReader(fr);
		TEXT = br.readLine();
		br.close();
	}

	@Test
	public void rename() throws IOException {
		FILESERVICE.rename(PATH, NAME, NAME2);
		assertTrue(new File(PATH + NAME2).isFile());
		// Testen, ob der Inhalt identisch geblieben ist
		FILESERVICE = new FileService(new StatusService());
		FileReader fr = new FileReader(PATH + NAME2);
		BufferedReader br = new BufferedReader(fr);
		assertEquals(br.readLine(), TEXT);
		br.close();

		// Wieder rueckgaengig machen
		FILESERVICE.rename(PATH, NAME2, NAME);
	}

	@Test
	public void copy() throws IOException {
		FILESERVICE.copy(NAME, PATH, NEWPATH);
		assertTrue(new File(NEWPATH + NAME).isFile());

		// Testen, ob der Inhalt identisch geblieben ist
		FILESERVICE = new FileService(new StatusService());
		FileReader fr = new FileReader(NEWPATH + NAME);
		BufferedReader br = new BufferedReader(fr);
		assertEquals(br.readLine(), TEXT);
		br.close();

		// Wieder rueckgaengig machen
		FILESERVICE.copy(NAME, PATH + "/webapps/", PATH);
	}
}
