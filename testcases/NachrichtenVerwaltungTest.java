package testcases;

import java.util.Date;

import junit.framework.Assert;
import main.Portal;

import org.junit.Before;
import org.junit.Test;

import accounts.Accountverwaltung;
import accounts.Anbieter;
import accounts.Gesperrt;
import accounts.Kunde;
import accounts.LoginException;
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
		accv.createAnbieter("anbieter@ma.il", "anbieter1", "pw");
		accv.createKunde("kunde@email.de", "kunde1", "pw");
		accv.createBetreiber("a@dm.in","admin", "pw");
		
		anbieter = accv.getAnbieter().get(0);
		accv.logIn("admin","pw");
		accv.setAccountGesperrt(anbieter, Gesperrt.NEIN);
		kunde = accv.getKunden().get(0);

		ang1 = av.createAutovermietung(anbieter, "Auto Auto", "Hier gibts Autos", 2, 10.00, new Date(78943216748967489L), new Date(78943316748967489L),"Germany",  "Muenster");
		ang2 = av.createAusflug(anbieter, "Bierausflug", "Hier gibts BIER!!", 10, 5.00, new Date(78943216748967489L), new Date(78943316748967489L), "Germany", "Muenster", "Guenstig");
		
		accv.logIn("kunde1", "pw");
		nv.sendeNachricht(kunde, anbieter, "Hallo Mr.Anbieter", "Cooles Angebot haben sie da...", ang1);
		accv.logIn("anbieter1", "pw");
		nv.sendeNachricht(anbieter, kunde, "Re: Hallo Mr.Anbieter", "Danke, dann buch es doch!", ang1);
		accv.logIn("kunde1", "pw");
		nv.sendeNachricht(kunde, anbieter, "Am Strand?", "Ist das Hotel am Strand?", ang2);
		accv.logIn("anbieter1", "pw");
		nv.sendeNachricht(anbieter, kunde, "Re: Am Strand?", "Ja, steht aber auch in der Beschreibung!", ang2);
		accv.logIn("kunde1", "pw");
		nv.sendeNachricht(kunde, anbieter, "Danke", "Danke vielmals!", ang1);
		accv.logIn("anbieter1", "pw");
		nv.sendeNachricht(anbieter, kunde, "Re: Danke", "Bitte!", ang1);
	}

	@Test
	public void test() throws LoginException {
		Nachricht ausgang1kunde = nv.getGesendeteNachrichten(kunde).get(0);
		Nachricht eingang1anbieter = nv.getErhalteneNachrichten(anbieter).get(0);
		Nachricht ausgang1anbieter = nv.getGesendeteNachrichten(anbieter).get(0);
		Nachricht eingang1kunde = nv.getErhalteneNachrichten(kunde).get(0);
		
		//Kunde: Postausgang
		Assert.assertEquals(kunde, Portal.Nachrichtenverwaltung().getAbsender(ausgang1kunde));
		Assert.assertEquals(anbieter, Portal.Nachrichtenverwaltung().getEmpfaenger(ausgang1kunde));
		Assert.assertEquals("Danke", ausgang1kunde.getBetreff());
		Assert.assertEquals("Danke vielmals!", ausgang1kunde.getText());
		
		//Anbieter: Posteingang
		Assert.assertEquals(kunde, Portal.Nachrichtenverwaltung().getAbsender(eingang1anbieter));
		Assert.assertEquals(anbieter, Portal.Nachrichtenverwaltung().getEmpfaenger(eingang1anbieter));
		Assert.assertEquals("Danke", eingang1anbieter.getBetreff());
		Assert.assertEquals("Danke vielmals!", eingang1anbieter.getText());
		
		//Kunde: Postausgang
		Assert.assertEquals(anbieter, Portal.Nachrichtenverwaltung().getAbsender(ausgang1anbieter));
		Assert.assertEquals(kunde, Portal.Nachrichtenverwaltung().getEmpfaenger(ausgang1anbieter));
		Assert.assertEquals("Re: Danke", ausgang1anbieter.getBetreff());
		Assert.assertEquals("Bitte!", ausgang1anbieter.getText());
				
		//Kunde: Postausgang
		Assert.assertEquals(anbieter, Portal.Nachrichtenverwaltung().getAbsender(eingang1kunde));
		Assert.assertEquals(kunde, Portal.Nachrichtenverwaltung().getEmpfaenger(eingang1kunde));
		Assert.assertEquals("Re: Danke", eingang1kunde.getBetreff());
		Assert.assertEquals("Bitte!", eingang1kunde.getText());
		
		//Gelesen und ungelesene Nachrichten
		Assert.assertEquals(3, nv.getAnzahlUngelesenerNachrichten(kunde));
		
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
		accv.logIn("kunde1", "pw");
		nv.delAllNachrichten(kunde);

		Assert.assertEquals(nv.getGesendeteNachrichten(kunde).size(),0);
		Assert.assertEquals(nv.getErhalteneNachrichten(anbieter).size(),0);
		Assert.assertEquals(nv.getGesendeteNachrichten(anbieter).size(),0);
		Assert.assertEquals(nv.getErhalteneNachrichten(kunde).size(),0);
	}

}
