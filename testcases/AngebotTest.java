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

public class AngebotTest {

	Angebotsverwaltung av = Portal.getSingletonObject().getAngebotsverwaltung();
	Angebotsverarbeitung ava = Portal.getSingletonObject().getAngebotsverarbeitung();
	Accountverwaltung accv = Portal.getSingletonObject().getAccountverwaltung();
	Buchungsverwaltung bv = Portal.getSingletonObject().getBuchungsverwaltung();
	
	Anbieter anbieter;
	Kunde kunde1,kunde2;
	
	Autovermietung ang1;
	Ausflug ang2,ang3;
	
	Buchung b1,b2,b3,b4,b5;
	
	@Before
	public void setUp() throws Exception {	
		
	}

	@Test
	public void test() {
		try {
		accv.createAnbieter("X@Y.Z", "TUI", "abcxyz");
		accv.createKunde("E@Mail.de", "HansWurst", "xyzabc");
		accv.createKunde("mail@gmail.com", "Dieter", "abcdef");
		
		anbieter = accv.getAnbieter().get(0);
		kunde1 = accv.getKunden().get(0);
		kunde2 = accv.getKunden().get(1);

		ang1 = av.createAutovermietung(anbieter, "Auto Auto", "Hier gibts Autos", 2, 10.00, new Date[]{new Date(1430609911421L),new Date(1430610011421L)}, "Muenster");
		ang2 = av.createAusflug(anbieter, "Bierausflug", "Hier gibts BIER!!", 10, 5.00, new Date[]{new Date(1430609911421L),new Date(1430610011421L)}, "Muenster", "Guenstig");
		ang3 = av.createAusflug(anbieter, "Kirchensaufen", "Kirchensaufen yeah!", 30, 3.00, new Date[]{new Date(1430609911421L),new Date(1430610011421L)}, "Muenster", "Guenstig");
		
		av.addKommentar(ang1, new Kommentar(kunde1.getName(), "Super Duper Urlaub", 5));
		av.addKommentar(ang1, new Kommentar(kunde2.getName(), "Guter Urlaub", 4));
		av.addKommentar(ang1, new Kommentar(kunde1.getName(), "Ultra Urlaub", 5));
		av.addKommentar(ang2, new Kommentar(kunde1.getName(), "Echt mies!", 1));
		av.addKommentar(ang2, new Kommentar(kunde2.getName(), "Ganz doll dreckig!", 3));
		av.addKommentar(ang3, new Kommentar(kunde1.getName(), "Echt mies!", 1));
		av.addKommentar(ang3, new Kommentar(kunde2.getName(), "Ganz doll dreckig!", 1));
	
		bv.createBuchung(kunde1, ang1, new Date(1430609911421L), new Date(1430610011421L));
		bv.createBuchung(kunde1, ang2, new Date(1430609911421L), new Date(1430610011421L));
		bv.createBuchung(kunde1, ang3, new Date(1430609911421L), new Date(1430610011421L));
		bv.createBuchung(kunde2, ang1, new Date(1430609911421L), new Date(1430610011421L));
		bv.createBuchung(kunde2, ang2, new Date(1430609911421L), new Date(1430610011421L));
		//Bewertungstest fuer Topangebote
		ArrayList<Angebot> topangebote = ava.getTopAngebote();

		Assert.assertEquals(ang1, topangebote.get(0));
		Assert.assertEquals(ang2, topangebote.get(1));
		Assert.assertEquals(ang3, topangebote.get(2));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
