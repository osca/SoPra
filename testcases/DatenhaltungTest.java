package testcases;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import junit.framework.Assert;
import main.Datenhaltung;
import main.Portal;

import org.junit.Test;

import buchungen.Bestaetigung;
import buchungen.Buchung;
import buchungen.Buchungsverwaltung;

import accounts.Accountverwaltung;
import accounts.Anbieter;
import accounts.Kunde;
import angebote.Angebotsverarbeitung;
import angebote.Angebotsverwaltung;
import angebote.Kommentar;
import angebote.typen.Angebot;
import angebote.typen.Ausflug;
import angebote.typen.Autovermietung;

public class DatenhaltungTest {

	Angebotsverwaltung av = Portal.Angebotsverwaltung();
	Angebotsverarbeitung ava = Portal.Angebotsverarbeitung();
	Accountverwaltung accv = Portal.Accountverwaltung();
	Buchungsverwaltung bv = Portal.Buchungsverwaltung();

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
	public void testSave() throws Exception {
		//Accounts erstellen
		Anbieter anbieter1 = accv.createAnbieter("X@Y.Z", "TUI", "abcxyz"),
				 anbieter2 = accv.createAnbieter("Edgar@Wallace.de", "LTUR", "hallo123");
		Kunde kunde1 = accv.createKunde("E@Mail.de", "HansWurst", "xyzabc"),
			  kunde2 = accv.createKunde("mail@gmail.com", "Dieter", "abcdef");

		//Angebote erstellen
		Autovermietung ang1 = av.createAutovermietung(anbieter1, "Auto Auto", "Hier gibts Autos", 2, 10.00, new Date(1430609911421L),new Date(1430610011421L), "Muenster");
		Ausflug	ang2 = av.createAusflug(anbieter1, "Bierausflug", "Hier gibts BIER!!", 10, 5.00, new Date(1430609911421L),new Date(1430610011421L), "Muenster", "Guenstig"),
				ang3 = av.createAusflug(anbieter1, "Kirchensaufen", "Kirchensaufen yeah!", 30, 3.00, new Date(1430609911421L),new Date(1430610011421L), "Muenster", "Guenstig");
		av.createAusflug(anbieter2, "Klettern", "Klettern mit Bier!", 20, 3.00, new Date(1430609911421L),new Date(1430610011421L), "Muenster", "Guenstig");
		
		//Kommentare zu Angeboten erstellen
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
		bv.createBuchung(kunde2, ang2, new Date(1430609911421L), new Date(1430610011421L));
		bv.createBuchung(kunde2, ang3, new Date(1430609911421L), new Date(1430610011421L));
		Datenhaltung.saveAllData();
	}
	
	@Test
	public void testLoad(){	
		//Hier handelt es sich hauptsächlich um einen Exceptiontest: 
		//Fliegt keine Nullpointer-/Classcast-Exception ist das schon Grund zum Feiern
		Datenhaltung.recoverSavedState();
		Anbieter anb1 = (Anbieter) accv.getAccountByName("TUI");
		ArrayList<Angebot> pa = av.getAngebote(anb1);
		Angebot ang3 = pa.get(0);
		
		Kunde kunde1 = (Kunde) accv.getAccountByName("HansWurst");
		
		//Set und Get Bestaetigung einer Buchung
		for(Buchung b : bv.getBuchungen(ang3))
			bv.setBestaetigt(b, Bestaetigung.JA);
		
		Assert.assertEquals(Bestaetigung.JA, bv.getBuchungen(kunde1).get(0).getBestaetigt());
	}
	
	@Test
	public void testLoad2(){
		Datenhaltung.recoverSavedState();
		
	}
}
