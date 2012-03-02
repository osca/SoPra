package testcases;

import junit.framework.Assert;
import main.Portal;

import org.junit.Before;
import org.junit.Test;

import accounts.Accountverwaltung;
import accounts.Anbieter;
import accounts.Kunde;
import accounts.Nachricht;
import accounts.Nachrichtenverwaltung;
import angebote.Angebotsverwaltung;
import angebote.typen.Ausflug;
import angebote.typen.Autovermietung;

public class NachrichtenVerwaltungTest {

	Accountverwaltung accv = Portal.Accountverwaltung();
	Nachrichtenverwaltung nv = Portal.Nachrichtenverwaltung();
	Angebotsverwaltung av = Portal.Angebotsverwaltung();
	Anbieter anbieter;
	Kunde kunde;
	Autovermietung ang1;
	Ausflug ang2;
	
	@Before
	public void setUp() throws Exception {
		accv.createAnbieter("anbietermail", "anbieter1", "pw");
		accv.createKunde("kundeemail", "kunde1", "pw");
		
		anbieter = accv.getAnbieter().get(0);
		kunde = accv.getKunden().get(0);

		ang1 = av.createAutovermietung(anbieter, "Auto Auto", "Hier gibts Autos", 2, 10.00, null, "Muenster");
		ang2 = av.createAusflug(anbieter, "Bierausflug", "Hier gibts BIER!!", 10, 5.00, null, "Muenster", "Guenstig");
		
		nv.sendeNachricht(kunde, anbieter, "Hallo Mr.Anbieter", "Cooles Angebot haben sie da...", ang1);
		nv.sendeNachricht(anbieter, kunde, "Re: Hallo Mr.Anbieter", "Danke, dann buch es doch!", ang1);
		nv.sendeNachricht(kunde, anbieter, "Am Strand?", "Ist das Hotel am Strand?", ang2);
		nv.sendeNachricht(anbieter, kunde, "Re: Am Strand?", "Ja, steht aber auch in der Beschreibung!", ang2);
		nv.sendeNachricht(kunde, anbieter, "Danke", "Danke vielmals!", ang1);
		nv.sendeNachricht(anbieter, kunde, "Re: Danke", "Bitte!", ang1);
	}

	@Test
	public void test() {
		Nachricht ausgang1kunde = nv.getGesendeteNachrichten(kunde).get(0);
		Nachricht eingang1anbieter = nv.getErhalteneNachrichten(anbieter).get(0);
		Nachricht ausgang1anbieter = nv.getGesendeteNachrichten(anbieter).get(0);
		Nachricht eingang1kunde = nv.getErhalteneNachrichten(kunde).get(0);
		
		//Kunde: Postausgang
		Assert.assertEquals(kunde, Portal.Nachrichtenverwaltung().getAbsender(ausgang1kunde));
		Assert.assertEquals(anbieter, Portal.Nachrichtenverwaltung().getEmpfaenger(ausgang1kunde));
		Assert.assertEquals("Hallo Mr.Anbieter", ausgang1kunde.getBetreff());
		Assert.assertEquals("Cooles Angebot haben sie da...", ausgang1kunde.getText());
		
		//Anbieter: Posteingang
		Assert.assertEquals(kunde, Portal.Nachrichtenverwaltung().getAbsender(eingang1anbieter));
		Assert.assertEquals(anbieter, Portal.Nachrichtenverwaltung().getEmpfaenger(eingang1anbieter));
		Assert.assertEquals("Hallo Mr.Anbieter", eingang1anbieter.getBetreff());
		Assert.assertEquals("Cooles Angebot haben sie da...", eingang1anbieter.getText());
		
		//Kunde: Postausgang
		Assert.assertEquals(anbieter, Portal.Nachrichtenverwaltung().getAbsender(ausgang1anbieter));
		Assert.assertEquals(kunde, Portal.Nachrichtenverwaltung().getEmpfaenger(ausgang1anbieter));
		Assert.assertEquals("Re: Hallo Mr.Anbieter", ausgang1anbieter.getBetreff());
		Assert.assertEquals("Danke, dann buch es doch!", ausgang1anbieter.getText());
				
		//Kunde: Postausgang
		Assert.assertEquals(anbieter, Portal.Nachrichtenverwaltung().getAbsender(eingang1kunde));
		Assert.assertEquals(kunde, Portal.Nachrichtenverwaltung().getEmpfaenger(eingang1kunde));
		Assert.assertEquals("Re: Hallo Mr.Anbieter", eingang1kunde.getBetreff());
		Assert.assertEquals("Danke, dann buch es doch!", eingang1kunde.getText());
		
		//Gelesen und ungelesene Nachrichten
		Assert.assertEquals(6, nv.getAnzahlUngelesenerNachrichten());
		nv.setGelesen(ausgang1kunde, true);
		Assert.assertEquals(5, nv.getAnzahlUngelesenerNachrichten());
		
		//Groesse der Postkaesten
		Assert.assertEquals(nv.getGesendeteNachrichten(kunde).size(),3);
		Assert.assertEquals(nv.getErhalteneNachrichten(anbieter).size(),3);
		Assert.assertEquals(nv.getGesendeteNachrichten(anbieter).size(),3);
		Assert.assertEquals(nv.getErhalteneNachrichten(kunde).size(),3);
		
		//Loeschen einer Nachricht
		nv.delNachricht(nv.getGesendeteNachrichten(kunde).get(1));
		
		Assert.assertEquals(nv.getGesendeteNachrichten(kunde).size(),2);
		Assert.assertEquals(nv.getErhalteneNachrichten(anbieter).size(),2);
		Assert.assertEquals(nv.getGesendeteNachrichten(anbieter).size(),3);
		Assert.assertEquals(nv.getErhalteneNachrichten(kunde).size(),3);

		//Loeschen nach Angebot
		nv.delAllNachrichten(ang1);
		
		Assert.assertEquals(nv.getGesendeteNachrichten(kunde).size(),0);
		Assert.assertEquals(nv.getErhalteneNachrichten(anbieter).size(),0);
		Assert.assertEquals(nv.getGesendeteNachrichten(anbieter).size(),1);
		Assert.assertEquals(nv.getErhalteneNachrichten(kunde).size(),1);
		
		//Get Angebot By Id
		Assert.assertEquals(nv.getAlleNachrichten().get(0), nv.getNachrichtById(3));

		//Loeschen nach Account
		nv.delAllNachrichten(kunde);

		Assert.assertEquals(nv.getGesendeteNachrichten(kunde).size(),0);
		Assert.assertEquals(nv.getErhalteneNachrichten(anbieter).size(),0);
		Assert.assertEquals(nv.getGesendeteNachrichten(anbieter).size(),0);
		Assert.assertEquals(nv.getErhalteneNachrichten(kunde).size(),0);
	}

}
