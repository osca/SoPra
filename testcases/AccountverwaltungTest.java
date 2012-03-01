package testcases;

import main.*;
import accounts.*;

import org.junit.*;
import java.util.ArrayList;
import junit.framework.Assert;

public class AccountverwaltungTest {
	private Accountverwaltung acv;
	
	@Test
	public void testCreateKunde() {
		acv = Portal.getSingletonObject().getAccountverwaltung();
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
		}
		
		
	}
	
	@Test
	public void testCreateAnbieter() {
		acv = Portal.getSingletonObject().getAccountverwaltung();
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
		}
	}
	
	@Test
	public void testeCreateBetreiber() {
		acv = Portal.getSingletonObject().getAccountverwaltung();
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
		}
	}
	
	@Test
	public void testEnableAccount() {
		acv = Portal.getSingletonObject().getAccountverwaltung();
		
		acv.setEnableAccount(acv.getKunden().get(0), false);
	
		Assert.assertEquals(acv.getKunden().get(0).isGesperrt(), false);
		
	}
	
	@Test
	public void testDeleteKunden() {
		acv = Portal.getSingletonObject().getAccountverwaltung();
		
		try {
			acv.delAccount(acv.getKunden().get(0));

			Assert.assertEquals(true, acv.getKunden().isEmpty());
		} catch (LoeschenNichtMoeglichException e1) {
			e1.printStackTrace();
		}
	}

	public void testDeleteAnbieter() {
		acv = Portal.getSingletonObject().getAccountverwaltung();
		
		try {
			acv.createAnbieter("pfui@tui,de", "PFUI", "supiiii");
			
			acv.delAccount(acv.getKunden().get(0));
			
			Assert.assertEquals(true, acv.getAnbieter().isEmpty());
		} catch (AlreadyInUseException e) {
			e.printStackTrace();
		} catch (LoeschenNichtMoeglichException e1) {
			e1.printStackTrace();
		}
	}
	
	public void testDeleteBetreiber() {
		acv = Portal.getSingletonObject().getAccountverwaltung();
		
		try {
			acv.createBetreiber("", "", "");
			
			acv.delAccount(acv.getBetreiber().get(0));
			Assert.assertEquals(false, acv.getBetreiber().isEmpty());
		} catch (AlreadyInUseException e) {
			e.printStackTrace();
		} catch (LoeschenNichtMoeglichException e1) {
			e1.printStackTrace();
		}
	}
}
