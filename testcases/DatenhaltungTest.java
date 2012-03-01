package testcases;

import java.io.File;

import junit.framework.Assert;

import main.Datenhaltung;
import main.Portal;

import org.junit.Test;

import accounts.AlreadyInUseException;
import accounts.Anbieter;
import accounts.Kunde;

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
	
	@Test
	public void testStruktur(){
		Anbieter anb;
		Kunde kun;
		try {
			anb = 	Portal.getSingletonObject().getAccountverwaltung().createAnbieter("Email", "anb", "password");
			kun = 	Portal.getSingletonObject().getAccountverwaltung().createKunde("em", "kunde", "password");
		} catch (AlreadyInUseException e) {
			e.printStackTrace();
		}
		
	}
}
