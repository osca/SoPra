package testcases;

import java.io.File;

import junit.framework.Assert;

import main.Datenhaltung;

import org.junit.Test;

public class DatenhaltungTest {

	@Test
	public void testReadLaender() {
		File f = new File("laender.txt");
		if(!f.exists())
			return;
		String[] s = Datenhaltung.getStringArrayFromFile(f);
		Assert.assertEquals("Luxemburg", s[100]);
		Assert.assertEquals("Sao Tome und Principe", s[150]);
	}
}
