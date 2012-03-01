package testcases;

import main.*;
import accounts.*;

import org.junit.*;
import java.util.ArrayList;
import junit.framework.Assert;

public class AccountverwaltungTest {
	private static Accountverwaltung acv;
	
	@BeforeClass
	public static void setup() {
		acv = Portal.getSingletonObject().getAccountverwaltung();
	}
	
	@Test
	public void testCreateKunde() {
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
		
		try {
			acv.createBetreiber("betreiber@host.de", "Horst", "ichkannalles");
			
			ArrayList<Betreiber> betreiber = acv.getBetreiber();
			Assert.assertEquals(1, betreiber.size());								// habe ich auch wirklich nur einen erstellt, wenn nicht fehler im setup?7
			
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
		try {
			acv.createKunde("nixda@dasda.de", "Ninja", "wurstloch");
			acv.setEnableAccount(acv.getKunden().get(0), false);
			
			Assert.assertEquals(acv.getKunden().get(0).isGesperrt(), false);
		} catch (AlreadyInUseException e) {
			e.printStackTrace();
		}
		
	}
	
	 @Test
	 public void deleteTest() {
		 for(int i = 0; i < acv.getAnbieter().size(); i++) {
			 try {
				acv.delAccount(acv.getAnbieter().get(i));
			} catch (LoeschenNichtMoeglichException e) {
				e.printStackTrace();
			}
		 }
		 for(int i = 0; i < acv.getKunden().size(); i++) {
			 try {
				acv.delAccount(acv.getKunden().get(i));
			} catch (LoeschenNichtMoeglichException e) {
				e.printStackTrace();
			}
		 }
		 for(int i = 0; i < acv.getBetreiber().size(); i++) {
			 try {
				acv.delAccount(acv.getBetreiber().get(i));
			} catch (LoeschenNichtMoeglichException e) {
				e.printStackTrace();
			}
		 }
		 
		 
		 Assert.assertEquals(acv.getAccounts().size(), 0);
	 }
}
