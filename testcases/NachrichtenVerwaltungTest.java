package testcases;

import java.util.ArrayList;

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

	Accountverwaltung accv = Portal.getSingletonObject().getAccountverwaltung();
	Nachrichtenverwaltung nv = Portal.getSingletonObject().getNachrichtenverwaltung();
	Angebotsverwaltung av = Portal.getSingletonObject().getAngebotsverwaltung();
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
		ArrayList<Nachricht> postausgangKun = nv.getGesendeteNachrichten(kunde);
		ArrayList<Nachricht> posteingangAnb = nv.getErhalteneNachrichten(anbieter);
		ArrayList<Nachricht> postausgangAnb = nv.getGesendeteNachrichten(anbieter);
		ArrayList<Nachricht> posteingangKun = nv.getErhalteneNachrichten(kunde);
		
		Nachricht ausgang1kunde = postausgangKun.get(0);
		Nachricht eingang1anbieter = posteingangAnb.get(0);
		Nachricht ausgang1anbieter = postausgangAnb.get(0);
		Nachricht eingang1kunde = posteingangKun.get(0);
		
		//Kunde: Postausgang
		Assert.assertEquals(kunde, ausgang1kunde.getAbsender());
		Assert.assertEquals(anbieter, ausgang1kunde.getEmpfaenger());
		Assert.assertEquals("Hallo Mr.Anbieter", ausgang1kunde.getBetreff());
		Assert.assertEquals("Cooles Angebot haben sie da...", ausgang1kunde.getText());
		
		//Anbieter: Posteingang
		Assert.assertEquals(kunde, eingang1anbieter.getAbsender());
		Assert.assertEquals(anbieter, eingang1anbieter.getEmpfaenger());
		Assert.assertEquals("Hallo Mr.Anbieter", eingang1anbieter.getBetreff());
		Assert.assertEquals("Cooles Angebot haben sie da...", eingang1anbieter.getText());
		
		//Kunde: Postausgang
		Assert.assertEquals(anbieter, ausgang1anbieter.getAbsender());
		Assert.assertEquals(kunde, ausgang1anbieter.getEmpfaenger());
		Assert.assertEquals("Re: Hallo Mr.Anbieter", ausgang1anbieter.getBetreff());
		Assert.assertEquals("Danke, dann buch es doch!", ausgang1anbieter.getText());
				
		//Kunde: Postausgang
		Assert.assertEquals(anbieter, eingang1kunde.getAbsender());
		Assert.assertEquals(kunde, eingang1kunde.getEmpfaenger());
		Assert.assertEquals("Re: Hallo Mr.Anbieter", eingang1kunde.getBetreff());
		Assert.assertEquals("Danke, dann buch es doch!", eingang1kunde.getText());
		
		//Groesse der Postkaesten
		Assert.assertEquals(postausgangKun.size(),3);
		Assert.assertEquals(posteingangAnb.size(),3);
		Assert.assertEquals(postausgangAnb.size(),3);
		Assert.assertEquals(posteingangKun.size(),3);
		
		//Loeschen einer Nachricht
		nv.delNachricht(postausgangKun.get(1));
		
		Assert.assertEquals(postausgangKun.size(),2);
		Assert.assertEquals(posteingangAnb.size(),2);
		Assert.assertEquals(postausgangAnb.size(),3);
		Assert.assertEquals(posteingangKun.size(),3);

		//Loeschen nach Angebot
		nv.delAllNachrichten(ang1);
		
		Assert.assertEquals(postausgangKun.size(),1);
		Assert.assertEquals(posteingangAnb.size(),1);
		Assert.assertEquals(postausgangAnb.size(),1);
		Assert.assertEquals(posteingangKun.size(),1);

		//Loeschen nach Account
		nv.delAllNachrichten(kunde);
		//nv.delAllNachrichten(anbieter);

		Assert.assertEquals(postausgangKun.size(),0);
		Assert.assertEquals(posteingangAnb.size(),0);
		Assert.assertEquals(postausgangAnb.size(),0);
		Assert.assertEquals(posteingangKun.size(),0);
	}

}
