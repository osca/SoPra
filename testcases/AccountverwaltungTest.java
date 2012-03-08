package testcases;

import java.io.IOException;
import java.util.ArrayList;

import junit.framework.Assert;
import main.Portal;

import org.junit.Test;

import accounts.Account;
import accounts.Accountverwaltung;
import accounts.AlreadyInUseException;
import accounts.Anbieter;
import accounts.Betreiber;
import accounts.Gesperrt;
import accounts.Kunde;
import accounts.LoeschenNichtMoeglichException;
import accounts.LoginException;

public class AccountverwaltungTest {
	private Accountverwaltung acv;
	
	@Test
	public void testCreateKunde() throws AlreadyInUseException, IOException {
		acv = Portal.Accountverwaltung();
		acv.createKunde("Kunde1@kunde.de", "Kunde", "KundenPasswort");
		
		ArrayList<Kunde> kunden = acv.getKunden();
		Assert.assertEquals(1, kunden.size());									// habe ich auch wirklich nur einen erstellt, wenn nicht fehler im setup?Assert.assertEquals(1, kunden.size());									// habe ich auch wirklich nur einen erstellt, wenn nicht fehler im setup?
		
		Kunde kunde = kunden.get(0);
		Assert.assertEquals("Kunde", kunde.getName());
		Assert.assertEquals("Kunde1@kunde.de", kunde.getEmail());
		Assert.assertEquals(Account.hashPassword("KundenPasswort"), kunde.getPassword());
	}
	
	@Test
	public void testCreateAnbieter() throws AlreadyInUseException, IOException {
		acv = Portal.Accountverwaltung();
		acv.createAnbieter("Anbieter1@anbieter.de", "Anbieter", "AnbieterPasswort");
		
		ArrayList<Anbieter> anbieter = acv.getAnbieter();
		Assert.assertEquals(1, anbieter.size());								// habe ich auch wirklich nur einen erstellt, wenn nicht fehler im setup?
		
		Anbieter bieter = anbieter.get(0);
		
		Assert.assertEquals("Anbieter", bieter.getName());
		Assert.assertEquals("Anbieter1@anbieter.de", bieter.getEmail());
		Assert.assertEquals(Account.hashPassword("AnbieterPasswort"), bieter.getPassword());
	}
	
	@Test
	public void testeCreateBetreiber() throws AlreadyInUseException, IOException {
		acv = Portal.Accountverwaltung();
		acv.createBetreiber("Betreiber1@betreiber.de", "Betreiber", "BetreiberPasswort");
		
		ArrayList<Betreiber> betreiber = acv.getBetreiber();
		Assert.assertEquals(1, betreiber.size());								// habe ich auch wirklich nur einen erstellt, wenn nicht fehler im setup?
		
		Betreiber treiber = betreiber.get(0);

		Assert.assertEquals("Betreiber", treiber.getName());
		Assert.assertEquals("Betreiber1@betreiber.de", treiber.getEmail());
		Assert.assertEquals(Account.hashPassword("BetreiberPasswort"), treiber.getPassword());
	}
	
	//@Test
	public void testEnableAccount() throws Exception {
		acv = Portal.Accountverwaltung();
		acv.setAccountGesperrt(acv.getKunden().get(0), Gesperrt.NEIN);
			
		Assert.assertEquals(acv.getKunden().get(0).isGesperrt(), true);
		
	}
	
	@Test
	public void testDeleteKunden() throws LoeschenNichtMoeglichException {
		acv = Portal.Accountverwaltung();
		acv.delAccount(acv.getKunden().get(0));

		Assert.assertEquals(true, acv.getKunden().isEmpty());
	}

	@Test
	public void testDeleteAnbieter() throws LoeschenNichtMoeglichException {
		acv = Portal.Accountverwaltung();
		acv.delAccount(acv.getAnbieter().get(0));
		
		Assert.assertEquals(true, acv.getAnbieter().isEmpty());
	}
	
	@Test
	public void testDeleteBetreiber() throws AlreadyInUseException, IOException, LoeschenNichtMoeglichException {
		acv = Portal.Accountverwaltung();
		acv.createBetreiber("x@y.z", "betr", "pw");
		acv.delAccount(acv.getBetreiber().get(0));
		Assert.assertEquals(false, acv.getBetreiber().isEmpty());		// sollte false sein, da dies der einzige Betreiber ist und der nicht gel�scht werden kann; Benjamin
	}
	
	@Test
	public void testLogIn() throws AlreadyInUseException, IOException, LoginException, LoeschenNichtMoeglichException {
		acv = Portal.Accountverwaltung();
		acv.createKunde("kunde@kunden.de", "Kundenspasst", "passwort");
		
		Kunde kunde = (Kunde)acv.getAccountByIdentifier("Kundenspasst");
		Kunde test = null;
		
		acv.logIn(kunde.getEmail(), "passwort");
		
		test = (Kunde) acv.getLoggedIn();
		Assert.assertEquals(kunde.getName(), test.getName());
		
		acv.delAccount(kunde);
	}
}
