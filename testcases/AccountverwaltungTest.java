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
		try {
			acv.createKunde("hier@dasda.de", "Polska", "wurstloch");
			acv.setEnableAccount(acv.getKunden().get(0), false);
			
			Assert.assertEquals(acv.getKunden().get(0).isGesperrt(), false);
		} catch (AlreadyInUseException e) {
			e.printStackTrace();
		}
		
	}
	
	 @Test
	 public void deleteTest() {
		 Assert.assertEquals(1, acv.getAnbieter().size());							// insgesamt oben nur einen erstellt, also sollte hier auch nur einer sein.
		 Assert.assertEquals(1, acv.getBetreiber().size());							// insgesamt oben nur einen erstellt, also sollte hier auch nur einen sein.
		 Assert.assertEquals(2, acv.getKunden().size());							// insgesamt oben zwei erstellt, also sollten hier auch zwei sein.
		 
		 for(int i = 0; i < acv.getAccounts().size(); i++) {
			 try {
				 acv.delAccount(acv.getAccounts().get(i));
			} catch (LoeschenNichtMoeglichException e) {
				e.printStackTrace();
			}
		 }
	 }
}
