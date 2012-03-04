package testcases;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import junit.framework.Assert;
import main.Portal;

import org.junit.Before;
import org.junit.Test;

import accounts.Account;
import accounts.Accountverwaltung;
import accounts.AlreadyInUseException;
import accounts.Anbieter;
import accounts.Kunde;
import angebote.Angebotsverarbeitung;
import angebote.Angebotsverwaltung;
import angebote.Kommentar;
import angebote.kriterien.Bierpreis;
import angebote.kriterien.Klasse;
import angebote.kriterien.Klima;
import angebote.kriterien.Sterne;
import angebote.kriterien.Verpflegungsart;
import angebote.typen.Angebot;
import angebote.typen.Ausflug;
import angebote.typen.Autovermietung;
import angebote.typen.Flug;
import buchungen.Buchung;
import buchungen.Buchungsverwaltung;
import buchungen.InvalidDateException;

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
		try{		accv.createAnbieter("X@Y.Z", "TUI", "abcxyz");
		} catch(AlreadyInUseException aiu){}		//Falls Datenhaltung daten bereits geladen hat, ist nichts zu tun
		try{		accv.createAnbieter("Edgar Wallace", "LTUR", "hallo123");
		} catch(AlreadyInUseException aiu){}
		try{		accv.createKunde("E@Mail.de", "HansWurst", "xyzabc");
		} catch(AlreadyInUseException aiu){}
		try{		accv.createKunde("mail@gmail.com", "Dieter", "abcdef");
		} catch(AlreadyInUseException aiu){}
		
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
	
	@SuppressWarnings("deprecation")
	@Test 
	public void testFlug() throws AlreadyInUseException, java.text.ParseException, InvalidDateException{
		Anbieter anb = (Anbieter) Portal.Accountverwaltung().createAccount(Account.ANBIETER, "E@Ma.il", "Unternehmen", "safe");
		Kunde kunde = (Kunde) Portal.Accountverwaltung().createAccount(Account.KUNDE, "E@Mail.com", "Nah Meh", "blabla");
		Flug flug = (Flug) Portal.Angebotsverwaltung().createAngebot(anb, "Superabsturz", "s.o.", Angebot.FLUG, 150.99, 125, 
				new Date[]{new Date(78943216748967489L)}, new String[]{"Bremen", "Barcelona", Klasse.wertebereich[1], Bierpreis.wertebereich[2]});
		Ausflug ausflug = (Ausflug) Portal.Angebotsverwaltung().createAngebot(anb, "Strandtest", "von Strand zu Stand ziehen und einfach nur rumliegen",
				Angebot.AUSFLUG, 20.00, 35, graphic.Methods.dater("12/12/2012", "14/12/2012", 1), new String[]{"Malediven",Bierpreis.wertebereich[3]});
		Buchung buchungAusflug = Portal.Buchungsverwaltung().createBuchung(kunde, ausflug, new Date("12/12/2012"), new Date("13/12/2012"));
		
		Assert.assertEquals(Portal.Angebotsverwaltung().getAnbieter(flug) .getName(), anb.getName());
		Assert.assertEquals(Portal.Buchungsverwaltung().getBuchungen(ausflug).get(0).getBuchungsnummer(), 
				Portal.Buchungsverwaltung().getBuchungen(kunde).get(0).getBuchungsnummer());
		Assert.assertEquals(Portal.Buchungsverwaltung().getBuchungen(ausflug).get(0).getBuchungsnummer(), 
				buchungAusflug.getBuchungsnummer());
		ArrayList<Angebot> alleAngebote = Portal.Angebotsverwaltung().getAllAngebote();
		for(Angebot angeb : Portal.Angebotsverwaltung().getAngebote(anb))
			Assert.assertTrue(alleAngebote.contains(angeb));
	}
	
	@Test
	public void testException() throws AlreadyInUseException, ParseException{
		try{
			Anbieter anb = (Anbieter) Portal.Accountverwaltung().createAccount(Account.ANBIETER, "email@provider.land", "DaFuq", "unsafe");
			Portal.Angebotsverwaltung().createAngebot(anb, "Schlafplatz", "", Angebot.HOTEL, 39.99, 30, 
					graphic.Methods.dater("01/01/2012", "01/02/2012", 1), 
					new String[]{"Turin", Klima.wertebereich[3], Sterne.wertebereich[9], Verpflegungsart.wertebereich[1], Bierpreis.wertebereich[0]});
			Assert.fail("Wegen abgelaufenem Datum sollte Exception fliegen!");
		} catch(Exception exc){
			//Do Nothing. Everything's fine.
		}
	}

}
