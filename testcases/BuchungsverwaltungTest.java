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

public class BuchungsverwaltungTest {
	
	Angebotsverwaltung av = Portal.getSingletonObject().getAngebotsverwaltung();
	Accountverwaltung accv = Portal.getSingletonObject().getAccountverwaltung();
	Buchungsverwaltung bv = Portal.getSingletonObject().getBuchungsverwaltung();
	
	Anbieter anbieter;
	Kunde kunde1,kunde2;
	
	Autovermietung ang1;
	Ausflug ang2,ang3;
	
	Buchung b1,b2,b3,b4,b5;
	
	@Before
	public void setUp() throws Exception {
		
		accv.createAnbieter("X@Y.Z", "TUI", "abcxyz");
		accv.createKunde("E@Mail.de", "HansWurst", "xyzabc");
		accv.createKunde("mail@gmail.com", "Dieter", "abcdef");
		
		anbieter = accv.getAnbieter().get(0);
		kunde1 = accv.getKunden().get(0);
		kunde2 = accv.getKunden().get(1);
		
		ang1 = av.createAutovermietung(anbieter, "Auto Auto", "Hier gibts Autos", 2, 10.00, new Date[]{new Date(1430609911421L),new Date(1430610011421L)}, "Muenster");
		ang2 = av.createAusflug(anbieter, "Bierausflug", "Hier gibts BIER!!", 10, 5.00, new Date[]{new Date(1430609911421L),new Date(1430610011421L)}, "Muenster", "Guenstig");
		ang3 = av.createAusflug(anbieter, "Kirchensaufen", "Kirchensaufen yeah!", 30, 3.00, new Date[]{new Date(1430609911421L),new Date(1430610011421L)}, "Muenster", "Guenstig");
		
		bv.createBuchung(kunde1, ang1, new Date(1430609911421L), new Date(1430610011421L));
		bv.createBuchung(kunde1, ang2, new Date(1430609911421L), new Date(1430610011421L));
		bv.createBuchung(kunde1, ang3, new Date(1430609911421L), new Date(1430610011421L));
		bv.createBuchung(kunde2, ang2, new Date(1430609911421L), new Date(1430610011421L));
		bv.createBuchung(kunde2, ang3, new Date(1430609911421L), new Date(1430610011421L));
		
		b1 = bv.getBuchungen(kunde1).get(0);
		b2 = bv.getBuchungen(kunde1).get(1);
		b3 = bv.getBuchungen(kunde1).get(2);
		b4 = bv.getBuchungen(kunde2).get(0);
		b5 = bv.getBuchungen(kunde2).get(1);
	}
	
	@Test
	public void test() {
		//Set und Get Bestaetigung einer Buchung
		bv.setBestaetigt(b3, Bestaetigung.JA);
		
		Assert.assertEquals(Bestaetigung.JA, bv.getBuchungen(kunde1).get(2).getBestaetigt());
		
		//Loeschen einer Buchung
		bv.delBuchung(b3);
		bv.delBuchung(b4);
		
		Assert.assertEquals(true, bv.getBuchungen(kunde1).contains(b1));
		Assert.assertEquals(true, bv.getBuchungen(kunde1).contains(b2));
		Assert.assertEquals(false, bv.getBuchungen(kunde1).contains(b3));
		Assert.assertEquals(false, bv.getBuchungen(kunde2).contains(b4));
		Assert.assertEquals(true, bv.getBuchungen(kunde2).contains(b5));

	}

}
