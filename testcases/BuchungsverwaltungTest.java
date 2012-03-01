package testcases;

import static org.junit.Assert.*;

import java.util.Date;

import main.Portal;

import org.junit.Before;
import org.junit.Test;

import accounts.Anbieter;
import accounts.Kunde;
import angebote.kriterien.Ort;
import angebote.typen.Angebot;
import angebote.typen.Flug;
import buchungen.Buchung;

public class BuchungsverwaltungTest {

	Kunde 	k = new Kunde("E@Mail.de", "HansWurst", "xyzabc");
	Anbieter anb = new Anbieter("X@Y.Z", "TUI", "abcxyz");
	
	@Before
	public void setUp(){
		
		Portal.getSingletonObject().getAngebotsverwaltung().createAngebot(anb, "Flug1023", "blablbal", Angebot.FLUG, 120.5, 80, new String[])
		 a = new Flug("Megaflug", "Blablablabla", 120, 120.0, new Date[]{new Date()}, , null, null, null);
	}
	
	@Test
	public void test() {
	}

}
