package accounts;

import java.util.ArrayList;
import java.util.Date;

import buchungen.Bestaetigung;
import buchungen.Buchung;
import buchungen.Buchungsverwaltung;

import angebote.Angebotsverwaltung;
import angebote.typen.Angebot;

import main.Portal;

/**
 * Verwaltet alle drei Accounttypen. Fuehrt saemtliche nicht-triviale Methoden 
 * fuer Accounts aus.
 * 
 * @author jay, stephan, osca
 */
public class Accountverwaltung {

	private ArrayList<Anbieter> anbieter = new ArrayList<Anbieter>();
	private ArrayList<Betreiber> betreiber = new ArrayList<Betreiber>();
	private ArrayList<Kunde> kunden = new ArrayList<Kunde>();
	private Account loggedIn = new Default();
	/**
	 * Konstruktor
	 * 
	 * @param anb Anbieter
	 * @param betr Betreiber
	 * @param kund Kunde
	 */
	public Accountverwaltung(ArrayList<Anbieter> anb, ArrayList<Betreiber> betr, ArrayList<Kunde> kund){
		anbieter = anb;
		betreiber = betr;
		kunden = kund;
	}
	
	/**
	 * Erstelle Kunde
	 * 
	 * @param email E-Mail Adresse
	 * @param name Username
	 * @param password Password
	 * @throws AlreadyInUseException Account E-Mail oder Username schon vergeben
	 */
	public Kunde createKunde(String email, String name, String password) throws AlreadyInUseException{
		if (!isFreeEmail(email) || !isFreeName(name))
			throw new AlreadyInUseException();
		Kunde k = new Kunde(email, name, password);
		kunden.add(k);
		return k;
	}
		/**
	 * Erstelle Anbieter
	 * 
	 * @param email E-Mail Adresse
	 * @param name Username
	 * @param password Password
	 * @throws AlreadyInUseException Account E-Mail oder Username schon vergeben
	 */
	public Anbieter createAnbieter(String email, String name, String password) throws AlreadyInUseException{
		if (!isFreeEmail(email) || !isFreeName(name))
			throw new AlreadyInUseException();
		Anbieter a = new Anbieter(email, name, password);
		anbieter.add(a);
		return a;
	}
	
	/**
	 * Erstelle Betreiber
	 * 
	 * @param email E-Mail Adresse
	 * @param name Username
	 * @param password Password
	 * @throws AlreadyInUseException Account E-Mail oder Username schon vergeben
	 */
	public Betreiber createBetreiber(String email, String name, String password) throws AlreadyInUseException{
		if (!isFreeEmail(email) || !isFreeName(name))
			throw new AlreadyInUseException();
		Betreiber b = new Betreiber(email, name, password);
		betreiber.add(b);
		return b;
	}
	
	/**
	 * Loggt einen Account an der Verwaltung als aktiv ein.
	 * @param identifier entweder E-Mail oder Nick
	 * @param password passwort
	 * @throws LoginException Falls Passwort nicht passend oder Account nicht gefunden (mit entspr. Nachricht)
	 */
	public void logIn(String identifier, String password) throws LoginException{
		Account acc = getAccountByIdentifier(identifier);
		if(acc==null)
			throw new LoginException("Account wurde nicht gefunden");
		if(! acc.getPassword().equals(password))
			throw new LoginException("Passwort war falsch");
		loggedIn = acc;
	}
	
	/**
	 * @return der aktuell eingeloggte Account
	 */
	public Account getLoggedIn(){
		return loggedIn;
	}
	
	public void logOut(){
		//TODO Exceptions oder Warnungen
		loggedIn = new Default();
	}
	/**
	 * Account (de)aktivieren
	 * 
	 * @param acc Freizuschaltender Account
	 * @param enable Aktiv oder nicht
	 */
	public void setEnableAccount(Account acc, boolean enable){
		acc.setGesperrt(enable);
	}
	
	/**
	 * Loesche einen Account und saemtliche Abhaengigkeiten
	 * 
	 * @param acc Der zu loeschende Account
	 * @throws LoeschenNichtMoeglichException Loeschen ist nicht moeglich
	 */
	public void delAccount(Account acc) throws LoeschenNichtMoeglichException {
		if(acc == null)
			throw new LoeschenNichtMoeglichException("Der Account wurde nicht gefunden!");
		
		Date heute = new Date();
		
		switch(acc.getTyp()){
			case(Account.ANBIETER):{
				Anbieter anbieteracc = (Anbieter) acc;
				ArrayList<Angebot> zuLoeschendeAngebote=anbieteracc.getAngebote();
				
				//Gibt es noch offene Buchungen Schleife
				for(Angebot a:zuLoeschendeAngebote){
					if(a.getDaten()[a.getDaten().length-1].compareTo(heute)>0){
						ArrayList<Buchung> buchungen = a.getBuchungen();
						
						for(Buchung b:buchungen){
							if(b.getBis().compareTo(heute) > 0 && (Bestaetigung.JA == b.getBestaetigt())) 
								throw new LoeschenNichtMoeglichException("Sie haben noch offene Buchungen");
						}
					}
				}
				
				//Angebote und deren Buchungen loeschen
				Buchungsverwaltung buchungsVerwaltung=Portal.getSingletonObject().getBuchungsverwaltung();
				Angebotsverwaltung angebotsVerwaltung=Portal.getSingletonObject().getAngebotsverwaltung();
				
				for(Angebot a:zuLoeschendeAngebote){
					ArrayList<Buchung> buchungen = a.getBuchungen();
					
					for(Buchung b:buchungen){
						buchungsVerwaltung.delBuchung(b);
						a.delBuchung(b);
					}
					
					angebotsVerwaltung.delAngebot(a);
				}
				break;
			}
			
			case(Account.KUNDE):{
				Kunde kundenacc = (Kunde) acc;
				ArrayList<Buchung> kundenbuchungen = kundenacc.getBuchungen();
				
				//Hat der Kunde noch anstehende bestaetigte Buchungen?
				for(Buchung b:kundenbuchungen) {
					if(b.getBestaetigt() == Bestaetigung.JA && b.getBis().compareTo(heute) > 0) {
						throw new LoeschenNichtMoeglichException("Sie haben noch anstehende bestaetigte Buchungen");
					}
				}
				
				//Loesche saemtliche mit dem Kunden verbundene Buchungen
				Buchungsverwaltung buchungsVerwaltung = Portal.getSingletonObject().getBuchungsverwaltung();
					
				for(Buchung b:buchungsVerwaltung.getBuchungen(kundenacc))
					buchungsVerwaltung.delBuchung(b);
				
				break;
			}
			
			case(Account.BETREIBER):{
				//Ist er der letzte Betreiber? Hoffentlich nicht...
				if(getBetreiber().size() < 2)
					throw new LoeschenNichtMoeglichException("You're the last unicorn!");
				
				break;
			}
		}
		
		//Loesche saemtliche mit dem Account verbundenen Nachrichten
		Nachrichtenverwaltung nachrichtenVerwaltung = Portal.getSingletonObject().getNachrichtenverwaltung();
		
		nachrichtenVerwaltung.delAllNachrichten(acc);
		
		boolean success = anbieter.remove(acc) || betreiber.remove(acc) || kunden.remove(acc);
		//Ist der Account sicher aus der Liste geloescht?
		if(!success)
			throw new LoeschenNichtMoeglichException("Der Account wurde nicht gefunden!");
	}

	/**
	 * Get Anbieterliste
	 * 
	 * @return Anbieterliste
	 */
	public ArrayList<Anbieter> getAnbieter(){
		return anbieter;
	}
	
	/**
	 * Get Betreiberliste
	 * 
	 * @return Betreiberliste
	 */
	public ArrayList<Betreiber> getBetreiber(){
		return betreiber;
	}
	
	/**
	 * Get Kundenliste
	 * 
	 * @return Kundenliste
	 */
	public ArrayList<Kunde> getKunden(){
		return kunden;
	}
	
	/**
	 * Get Accountliste
	 * 
	 * @return Accountliste
	 */
	public ArrayList<Account> getAccounts(){
		ArrayList<Account> result = new ArrayList<Account>();
		result.addAll(getAnbieter());
		result.addAll(getBetreiber());
		result.addAll(getKunden());
		return result;
	}
	

	/**
	 * Sucht einen Account über seinen Nick-/Unternehmensnamen
	 * @param name
	 * @return passender Account oder null falls nicht gefunden
	 */
	public Account getAccountByName(String name){
		for(Account acc : getAccounts())
			if (acc.getName().equals(name))
				return acc;
		return null;
	}
	
	/** Sucht einen Account über seine E-Mail-Adresse
	 * @param email
	 * @return passender Account oder null falls nicht gefunden
	 */
	public Account getAccountByEmail(String email){
		for(Account acc : getAccounts())
			if (acc.getEmail().equals(email))
				return acc;
		return null;
	}

	/**
	 * Sucht einen Account über seine "Identifikation" (= Name oder email, siehe Pflichtenheft)
	 * @param ident
	 * @return passender Account oder null falls nicht gefunden
	 */
	public Account getAccountByIdentifier(String ident){
		Account acc1 = getAccountByEmail(ident),
				acc2 = getAccountByName(ident);
		if (acc1 != null)
			return acc1;
		return acc2;
	}

	
	/**
	 * Ist diese E-Mail Adresse schon vergeben?
	 * 
	 * @param email E-Mail Adresse
	 * @return Vergeben oder nicht
	 */
	private boolean isFreeEmail(String email) {
		boolean result = true;
		for(Account a : getAccounts())
			if(a.getEmail().equals(email))
				result = false;
		return result;
	}

	/**
	 * Ist dieser Username schon vergeben?
	 * 
	 * @param name Username
	 * @return Vergeben oder nicht
	 */
	private boolean isFreeName(String name) {
		boolean result = true;
		for(Account a : getAccounts())
			if(a.getName().equals(name))
				result = false;
		return result;
	}
}
