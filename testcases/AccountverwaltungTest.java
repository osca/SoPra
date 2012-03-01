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
		
		try {
			acv.createAnbieter("tui@flug.de", "TUI", "supadupa");
			acv.createBetreiber("betreiber@host.de", "Horst", "ichkannalles");
			acv.createKunde("nixda@dasda.de", "Ninja", "wurstloch");
		} catch (AlreadyInUseException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testCreateKunde() {
		ArrayList<Kunde> kunden = acv.getKunden();
		Kunde kunde = kunden.get(0);
			
		Assert.assertEquals(1, kunden.size());									// habe ich auch wirklich nur einen erstellt?
		Assert.assertEquals("Ninja", kunde.getName());
		Assert.assertEquals("nixda@dasda.de", kunde.getEmail());
		Assert.assertEquals("wurstloch", kunde.getPassword());
	}
	
	@Test
	public void testCreateAnbieter() {
		ArrayList<Anbieter> anbieter = acv.getAnbieter();
		Anbieter bieter = anbieter.get(0);
		
		Assert.assertEquals(1, anbieter.size());								// habe ich auch wirklich nur einen erstellt?
		Assert.assertEquals("TUI", bieter.getName());
		Assert.assertEquals("tui@flug.de", bieter.getEmail());
		Assert.assertEquals("supadupa", bieter.getPassword());
	}
	
	@Test
	public void testeCreateBetreiber() {
		ArrayList<Betreiber> betreiber = acv.getBetreiber();
		Betreiber treiber = betreiber.get(0);

		Assert.assertEquals(1, betreiber.size());								// habe ich auch wirklich nur einen erstellt?
		Assert.assertEquals("Horst", treiber.getName());
		Assert.assertEquals("betreiber@host.de", treiber.getEmail());
		Assert.assertEquals("ichkannalles", treiber.getPassword());
	}
	
	@Test
	public void testEnableAccount() {
		acv.setEnableAccount(acv.getKunden().get(0), false);
		
		Assert.assertEquals(acv.getKunden().get(0).isGesperrt(), false);
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
