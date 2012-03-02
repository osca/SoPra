package testcases;

import java.io.File;

import junit.framework.Assert;
import main.Datenhaltung;
import main.Portal;

import org.junit.Test;

import accounts.Anbieter;
import accounts.Kunde;
import accounts.Nachrichtenverwaltung;

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
	
	
	/*@Test
	public void testStruktur(){
		Anbieter anb;
		Kunde kun;
		try {
			anb = 	Portal.Accountverwaltung().createAnbieter("Email", "anb", "password");
			kun = 	Portal.Accountverwaltung().createKunde("em", "kunde", "password");
			Angebot ang = Portal.Angebotsverwaltung().createAusflug(anb, "Ausf", "toller Ausflug"
					, 20, 25.5, new Date[]{new Date(99999999999999999L)}, "Duisburg", "Cheap");
			Portal.Nachrichtenverwaltung().sendeNachricht(kun, anb, "Betr", "txtblabla", ang);
			Datenhaltung.saveAllAccounts(Portal.Accountverwaltung());
			Datenhaltung.saveAllMessages(Portal.Nachrichtenverwaltung());
			//Weiter in Teil 2
		} catch (AlreadyInUseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}*/
	
	@Test
	public void testStruktur2(){	
		Nachrichtenverwaltung nv = Portal.Nachrichtenverwaltung();
		Kunde kun = Portal.Accountverwaltung().getKunden().get(0);
		Anbieter anb = Portal.Accountverwaltung().getAnbieter().get(0);
		//Assert.assertEquals(kun.getPostausgang().get(0), anb.getPosteingang().get(0));
		Assert.assertEquals(kun, nv.getAlleNachrichten().get(0).getAbsender());
	}
}
