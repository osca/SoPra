package main;

import java.io.File;

import junit.framework.Assert;

import org.junit.Test;

public class DatenhaltungTest {

	@Test
	public void test() {
		File f = new File("/media/Acer/Users/Jay/workspace/ReisePortal/laender.txt");
		System.out.println(f.getAbsolutePath());
		if(!f.exists()){
			System.out.println("FOOO");
			return;
		}
		Object[] o = Datenhaltung.getStringArrayFromFile(f);
		String[] s = new String[o.length];
		// s = Datenhaltung.getStringArrayFromFile(f);
		// Assert.assertEquals(s[151], "São Tomé und Príncipe");
		// Assert.assertEquals(s[100], "Algerien");
	}
}
