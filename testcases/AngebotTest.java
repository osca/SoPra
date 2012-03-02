package testcases;

import java.util.ArrayList;
import java.util.Date;

import junit.framework.Assert;
import main.Portal;

import org.junit.Before;
import org.junit.Test;

import accounts.Accountverwaltung;
import accounts.Anbieter;
import accounts.Kunde;
import angebote.Angebotsverarbeitung;
import angebote.Angebotsverwaltung;
import angebote.Kommentar;
import angebote.typen.Angebot;
import angebote.typen.Ausflug;
import angebote.typen.Autovermietung;
import buchungen.Buchung;
import buchungen.Buchungsverwaltung;

/**
 * Testcase fuer Angebote/Angebotsverarbeitung/Angebotsverwaltung
 * 
 * @author osca
 *
 */
public class AngebotTest {

	Angebotsverwaltung av = Portal.Angebotsverwaltung();
	Angebotsverarbeitung ava = Portal.Angebotsverarbeitung();
	Accountverwaltung accv = Portal.Accountverwaltung();
	Buchungsverwaltung bv = Portal.Buchungsverwaltung();
	
	Anbieter anbieter1;
	Anbieter anbieter2;
	Kunde kunde1,kunde2;
	
	Autovermietung ang1;
	Ausflug ang2,ang3,ang4;
	
	Buchung b1,b2,b3,b4,b5;
	
	@Before
	public void setUp() throws Exception {
		//Accounts erstellen
		accv.createAnbieter("X@Y.Z", "TUI", "abcxyz");
		accv.createAnbieter("Edgar Wallace", "LTUR", "hallo123");
		accv.createKunde("E@Mail.de", "HansWurst", "xyzabc");
		accv.createKunde("mail@gmail.com", "Dieter", "abcdef");
		
		//Acounts aufnehmen
		anbieter1 = accv.getAnbieter().get(0);
		anbieter2 = accv.getAnbieter().get(1);
		kunde1 = accv.getKunden().get(0);
		kunde2 = accv.getKunden().get(1);

		//Angebote erstellen
		ang1 = av.createAutovermietung(anbieter1, "Auto Auto", "Hier gibts Autos", 2, 10.00, new Date[]{new Date(1430609911421L),new Date(1430610011421L)}, "Muenster");
		ang2 = av.createAusflug(anbieter1, "Bierausflug", "Hier gibts BIER!!", 10, 5.00, new Date[]{new Date(1430609911421L),new Date(1430610011421L)}, "Muenster", "Guenstig");
		ang3 = av.createAusflug(anbieter1, "Kirchensaufen", "Kirchensaufen yeah!", 30, 3.00, new Date[]{new Date(1430609911421L),new Date(1430610011421L)}, "Muenster", "Guenstig");
		ang4 = av.createAusflug(anbieter2, "Klettern", "Klettern mit Bier!", 20, 3.00, new Date[]{new Date(1430609911421L),new Date(1430610011421L)}, "Muenster", "Guenstig");
		
		//Kommentare zu Angeboten erstellen
		av.addKommentar(ang1, new Kommentar(kunde1.getName(), "Super Duper Urlaub", 5));
		av.addKommentar(ang1, new Kommentar(kunde2.getName(), "Guter Urlaub", 4));
		av.addKommentar(ang1, new Kommentar(kunde1.getName(), "Ultra Urlaub", 5));
		av.addKommentar(ang2, new Kommentar(kunde1.getName(), "Echt mies!", 1));
		av.addKommentar(ang2, new Kommentar(kunde2.getName(), "Ganz doll dreckig!", 3));
		av.addKommentar(ang3, new Kommentar(kunde1.getName(), "Echt mies!", 1));
		av.addKommentar(ang3, new Kommentar(kunde2.getName(), "Ganz doll dreckig!", 1));
	
		//Buchungen von Kunden zu Angeboten erstellen
		bv.createBuchung(kunde1, ang1, new Date(1430609911421L), new Date(1430610011421L));
		bv.createBuchung(kunde1, ang2, new Date(1430609911421L), new Date(1430610011421L));
		bv.createBuchung(kunde1, ang3, new Date(1430609911421L), new Date(1430610011421L));
		bv.createBuchung(kunde2, ang1, new Date(1430609911421L), new Date(1430610011421L));
		bv.createBuchung(kunde2, ang2, new Date(1430609911421L), new Date(1430610011421L));
	}

	@Test
	public void test() throws Exception {
		//Bewertungstest fuer Topangebote (auch Angebots compareTo enthalten)
		ArrayList<Angebot> topangebote = ava.getTopAngebote();
		
		Assert.assertEquals(ang1, topangebote.get(0));
		Assert.assertEquals(ang2, topangebote.get(1));
		Assert.assertEquals(ang3, topangebote.get(2));
		Assert.assertEquals(ang4, topangebote.get(3));
		
		//Loeschen eines bestimmten Angebots
		av.delAngebot(ang2);
		
		Assert.assertTrue(av.getAllAngebote().contains(ang1));
		Assert.assertFalse(av.getAllAngebote().contains(ang2));
		Assert.assertTrue(av.getAllAngebote().contains(ang3));
		
		//Aendern eines bestimmten Angebots
		av.editAngebot(ang1, ang2, anbieter1);
		
		Assert.assertFalse(av.getAllAngebote().contains(ang1));
		Assert.assertTrue(av.getAllAngebote().contains(ang2));
		Assert.assertTrue(av.getAllAngebote().contains(ang3));
		
		//Get Alle Angebote
		Assert.assertEquals(3, av.getAllAngebote().size());
		
		//Get abgelaufene Angebote
		Assert.assertEquals(0, ava.getAbgelaufeneAngebote().size());
		
		//Get aktuelle Angebote
		Assert.assertEquals(3, ava.getAktuelleAngebote().size());
		
		//Get Angebote eines Anbieters
		Assert.assertEquals(ang4, av.getAngebote(anbieter2).get(0));
		
		//Suche Angebot
		ArrayList<Angebot> suche = ava.sucheAngebote("Klettern", Angebot.AUSFLUG, 1, 0.00, 200.00, Angebotsverarbeitung.KEINEDATEN, new String[]{"Muenster","Guenstig"});
		
		Assert.assertEquals(ang4, suche.get(0));
		
		//Typconverter
		Assert.assertEquals("Ausflug", Angebot.convertTypToName(Angebot.AUSFLUG));
		Assert.assertEquals("Autovermietung", Angebot.convertTypToName(Angebot.AUTOVERMIETUNG));
		Assert.assertEquals("Flug", Angebot.convertTypToName(Angebot.FLUG));
		Assert.assertEquals("Hotel", Angebot.convertTypToName(Angebot.HOTEL));
		
	}

}
