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
			acv.createKunde("nixda@dasda.de", "Ninja", "wurstloch");
			
			ArrayList<Kunde> kunden = acv.getKunden();
			Assert.assertEquals(1, kunden.size());									// habe ich auch wirklich nur einen erstellt, wenn nicht fehler im setup?Assert.assertEquals(1, kunden.size());									// habe ich auch wirklich nur einen erstellt, wenn nicht fehler im setup?
			
			Kunde kunde = kunden.get(0);
			Assert.assertEquals("Ninja", kunde.getName());
			Assert.assertEquals("nixda@dasda.de", kunde.getEmail());
			Assert.assertEquals("wurstloch", kunde.getPassword());
		} catch (AlreadyInUseException e) {
			e.printStackTrace();
		}
		
		
	}
	
	@Test
	public void testCreateAnbieter() {
		acv = Portal.getSingletonObject().getAccountverwaltung();
		try {
			acv.createAnbieter("tui@flug.de", "TUI", "supadupa");
			
			ArrayList<Anbieter> anbieter = acv.getAnbieter();
			Assert.assertEquals(1, anbieter.size());								// habe ich auch wirklich nur einen erstellt, wenn nicht fehler im setup?
			
			Anbieter bieter = anbieter.get(0);
			
			Assert.assertEquals("TUI", bieter.getName());
			Assert.assertEquals("tui@flug.de", bieter.getEmail());
			Assert.assertEquals("supadupa", bieter.getPassword());
		} catch (AlreadyInUseException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testeCreateBetreiber() {
		acv = Portal.getSingletonObject().getAccountverwaltung();
		try {
			acv.createBetreiber("betreiber@host.de", "Horst", "ichkannalles");
			
			ArrayList<Betreiber> betreiber = acv.getBetreiber();
			Assert.assertEquals(1, betreiber.size());								// habe ich auch wirklich nur einen erstellt, wenn nicht fehler im setup?
			
			Betreiber treiber = betreiber.get(0);

			Assert.assertEquals("Horst", treiber.getName());
			Assert.assertEquals("betreiber@host.de", treiber.getEmail());
			Assert.assertEquals("ichkannalles", treiber.getPassword());
		} catch (AlreadyInUseException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testEnableAccount() {
		acv = Portal.getSingletonObject().getAccountverwaltung();
		try {
			acv.createKunde("hier@dasda.de", "Polska", "wurstloch");
			acv.setEnableAccount(acv.getKunden().get(0), false);
			
			Assert.assertEquals(acv.getKunden().get(0).isGesperrt(), false);
		} catch (AlreadyInUseException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testDeleteKunden() {
		acv = Portal.getSingletonObject().getAccountverwaltung();
		
		try {
			acv.createKunde("hier@nix.de", "Dieter", "hahahha");
			
			acv.delAccount(acv.getKunden().get(0));
			
			Assert.assertEquals(1, acv.getKunden().size());
		} catch (AlreadyInUseException | LoeschenNichtMoeglichException e) {
			e.printStackTrace();
		}
	}
	
	public void testDeleteAnbieter() {
		acv = Portal.getSingletonObject().getAccountverwaltung();
		
		try {
			acv.createAnbieter("pfui@tui,de", "PFUI", "supiiii");
			
			acv.delAccount(acv.getKunden().get(0));
			
			Assert.assertEquals(1, acv.getAnbieter().size());
		} catch (AlreadyInUseException | LoeschenNichtMoeglichException e) {
			e.printStackTrace();
		}
	}
}
