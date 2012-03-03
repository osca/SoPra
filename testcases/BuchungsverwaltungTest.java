package testcases;

import java.util.Date;

import junit.framework.Assert;
import main.Portal;

import org.junit.Before;
import org.junit.Test;

import accounts.Accountverwaltung;
import accounts.Anbieter;
import accounts.Kunde;
import angebote.Angebotsverwaltung;
import angebote.typen.Ausflug;
import angebote.typen.Autovermietung;
import buchungen.Bestaetigung;
import buchungen.Buchung;
import buchungen.Buchungsverwaltung;

/**
 * Buchungsverwaltungs Testcase
 * 
 * @author osca
 */
public class BuchungsverwaltungTest {
	
	Angebotsverwaltung av = Portal.Angebotsverwaltung();
	Accountverwaltung accv = Portal.Accountverwaltung();
	Buchungsverwaltung bv = Portal.Buchungsverwaltung();
	
	Anbieter anbieter;
	Kunde kunde1,kunde2;
	
	Autovermietung ang1;
	Ausflug ang2,ang3;
	
	Buchung b1,b2,b3,b4,b5;
	
	@Before
	public void setUp() throws Exception {
		//Accounts erstellen
		accv.createAnbieter("X@Y.Z", "TUI", "abcxyz");
		accv.createKunde("E@Mail.de", "HansWurst", "xyzabc");
		accv.createKunde("mail@gmail.com", "Dieter", "abcdef");
		
		//Accounts abrufen
		anbieter = accv.getAnbieter().get(0);
		kunde1 = accv.getKunden().get(0);
		kunde2 = accv.getKunden().get(1);
		
		//Angebote erstellen
		ang1 = av.createAutovermietung(anbieter, "Auto Auto", "Hier gibts Autos", 2, 10.00, new Date[]{new Date(1430609911421L),new Date(1430610011421L)}, "Muenster");
		ang2 = av.createAusflug(anbieter, "Bierausflug", "Hier gibts BIER!!", 10, 5.00, new Date[]{new Date(1430609911421L),new Date(1430610011421L)}, "Muenster", "Guenstig");
		ang3 = av.createAusflug(anbieter, "Kirchensaufen", "Kirchensaufen yeah!", 30, 3.00, new Date[]{new Date(1430609911421L),new Date(1430610011421L)}, "Muenster", "Guenstig");
		
		//Buchungen erstellen
		b1 = bv.createBuchung(kunde1, ang1, new Date(1430609911421L), new Date(1430610011421L));
		b2 = bv.createBuchung(kunde1, ang2, new Date(1430609911421L), new Date(1430610011421L));
		b3 = bv.createBuchung(kunde1, ang3, new Date(1430609911421L), new Date(1430610011421L));
		b4 = bv.createBuchung(kunde2, ang2, new Date(1430609911421L), new Date(1430610011421L));
		b5 = bv.createBuchung(kunde2, ang3, new Date(1430609911421L), new Date(1430610011421L));
	}
	
	@Test
	public void testSetGetBestaetigung() throws Exception {
		//Set und Get Bestaetigung einer Buchung
		bv.setBestaetigt(b3, Bestaetigung.JA);
		
		Assert.assertEquals(Bestaetigung.JA, bv.getBuchungen(kunde1).get(2).getBestaetigt());

		//Loesche Buchung
		bv.delBuchung(b3);
		bv.delBuchung(b4);
		
		Assert.assertEquals(true, bv.getBuchungen(kunde1).contains(b1));
		Assert.assertEquals(true, bv.getBuchungen(kunde1).contains(b2));
		Assert.assertEquals(false, bv.getBuchungen(kunde1).contains(b3));
		Assert.assertEquals(false, bv.getBuchungen(kunde2).contains(b4));
		Assert.assertEquals(true, bv.getBuchungen(kunde2).contains(b5));
	}

}
