package testcases;

import java.io.IOException;
import java.util.ArrayList;

import junit.framework.Assert;
import main.Portal;

import org.junit.Test;

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
	public void testCreateKunde() {
		acv = Portal.Accountverwaltung();
		try {
			acv.createKunde("Kunde1@kunde.de", "Kunde", "KundenPasswort");
			
			ArrayList<Kunde> kunden = acv.getKunden();
			Assert.assertEquals(1, kunden.size());									// habe ich auch wirklich nur einen erstellt, wenn nicht fehler im setup?Assert.assertEquals(1, kunden.size());									// habe ich auch wirklich nur einen erstellt, wenn nicht fehler im setup?
			
			Kunde kunde = kunden.get(0);
			Assert.assertEquals("Kunde", kunde.getName());
			Assert.assertEquals("Kunde1@kunde.de", kunde.getEmail());
			Assert.assertEquals("KundenPasswort", kunde.getPassword());
		} catch (AlreadyInUseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@Test
	public void testCreateAnbieter() {
		acv = Portal.Accountverwaltung();
		try {
			acv.createAnbieter("Anbieter1@anbieter.de", "Anbieter", "AnbieterPasswort");
			
			ArrayList<Anbieter> anbieter = acv.getAnbieter();
			Assert.assertEquals(1, anbieter.size());								// habe ich auch wirklich nur einen erstellt, wenn nicht fehler im setup?
			
			Anbieter bieter = anbieter.get(0);
			
			Assert.assertEquals("Anbieter", bieter.getName());
			Assert.assertEquals("Anbieter1@anbieter.de", bieter.getEmail());
			Assert.assertEquals("AnbieterPasswort", bieter.getPassword());
		} catch (AlreadyInUseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testeCreateBetreiber() {
		acv = Portal.Accountverwaltung();
		try {
			acv.createBetreiber("Betreiber1@betreiber.de", "Betreiber", "BetreiberPasswort");
			
			ArrayList<Betreiber> betreiber = acv.getBetreiber();
			Assert.assertEquals(1, betreiber.size());								// habe ich auch wirklich nur einen erstellt, wenn nicht fehler im setup?
			
			Betreiber treiber = betreiber.get(0);

			Assert.assertEquals("Betreiber", treiber.getName());
			Assert.assertEquals("Betreiber1@betreiber.de", treiber.getEmail());
			Assert.assertEquals("BetreiberPasswort", treiber.getPassword());
		} catch (AlreadyInUseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//@Test
	public void testEnableAccount() {
		acv = Portal.Accountverwaltung();
		
		try {
			acv.setAccountGesperrt(acv.getKunden().get(0), Gesperrt.NEIN);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		Assert.assertEquals(acv.getKunden().get(0).isGesperrt(), true);
		
	}
	
	@Test
	public void testDeleteKunden() {
		acv = Portal.Accountverwaltung();
		
		try {
			acv.delAccount(acv.getKunden().get(0));

			Assert.assertEquals(true, acv.getKunden().isEmpty());
		} catch (LoeschenNichtMoeglichException e1) {
			e1.printStackTrace();
		}
	}

	@Test
	public void testDeleteAnbieter() {
		acv = Portal.Accountverwaltung();
		
		try {
			acv.delAccount(acv.getAnbieter().get(0));
			
			Assert.assertEquals(true, acv.getAnbieter().isEmpty());
		} catch (LoeschenNichtMoeglichException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDeleteBetreiber() {
		acv = Portal.Accountverwaltung();
		
		try {
			acv.createBetreiber("x@y.z", "betr", "pw");
			
			acv.delAccount(acv.getBetreiber().get(0));
			Assert.assertEquals(false, acv.getBetreiber().isEmpty());		// sollte false sein, da dies der einzige Betreiber ist und der nicht gel�scht werden kann; Benjamin
		} catch (AlreadyInUseException e) {
			e.printStackTrace();
		} catch (LoeschenNichtMoeglichException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testLogIn() {
		acv = Portal.Accountverwaltung();
		
		try {
			acv.createKunde("kunde@kunden.de", "Kundenspasst", "passwort");
			
			Kunde kunde = (Kunde)acv.getAccountByIdentifier("Kundenspasst");
			Kunde test = null;
			
			acv.logIn(kunde.getEmail(), kunde.getPassword());
			
			test = (Kunde) acv.getLoggedIn();
			Assert.assertEquals(kunde.getName(), test.getName());
			
			acv.delAccount(kunde);
		} catch (AlreadyInUseException e) {
			e.printStackTrace();
		} catch (LoginException e) {
			e.printStackTrace();
		} catch (LoeschenNichtMoeglichException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
