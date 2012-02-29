package accounts;

import java.util.ArrayList;
import java.util.Date;

import buchungen.Bestaetigung;
import buchungen.Buchung;
import buchungen.Buchungsverwaltung;

import angebote.Angebotsverwaltung;
import angebote.typen.Angebot;

import main.Portal;

public class Accountverwaltung {

	private ArrayList<Anbieter> anbieter = new ArrayList<Anbieter>();
	private ArrayList<Betreiber> betreiber = new ArrayList<Betreiber>();
	private ArrayList<Kunde> kunden = new ArrayList<Kunde>();

	public Accountverwaltung(ArrayList<Anbieter> anb, ArrayList<Betreiber> betr, ArrayList<Kunde> kund){
		anbieter = anb;
		betreiber = betr;
		kunden = kund;
	}
	
	public void createKunde(String email, String name, String password) throws AlreadyInUseException{
		if (!isFreeEmail(email) || !isFreeName(name))
			throw new AlreadyInUseException();
		kunden.add(new Kunde(email, name, password));
	}

	public void createAnbieter(String email, String name, String password) throws AlreadyInUseException{
		if (!isFreeEmail(email) || !isFreeName(name))
			throw new AlreadyInUseException();
		anbieter.add(new Anbieter(email, name, password));
	}
	
	public void createBetreiber(String email, String name, String password) throws AlreadyInUseException{
		if (!isFreeEmail(email) || !isFreeName(name))
			throw new AlreadyInUseException();
		betreiber.add(new Betreiber(email, name, password));
	}
	
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
			}
			
			case(Account.BETREIBER):{
				//Ist er der letzte Betreiber? Hoffentlich nicht...
				if(getBetreiber().size() < 2)
					throw new LoeschenNichtMoeglichException("You're the last unicorn!");
			}
		}
		
		//Loesche saemtliche mit dem Account verbundenen Nachrichten
		Nachrichtenverwaltung nachrichtenVerwaltung = Portal.getSingletonObject().getNachrichtenverwaltung();
		
		for(Nachricht n:nachrichtenVerwaltung.getErhalteneNachrichten(acc))
			nachrichtenVerwaltung.delNachricht(n);

		for(Nachricht n:nachrichtenVerwaltung.getGesendeteNachrichten(acc))
			nachrichtenVerwaltung.delNachricht(n);
		
		boolean success = anbieter.remove((Anbieter) acc) || betreiber.remove((Betreiber) acc) || kunden.remove((Kunde) acc);
		//Ist der Account sicher aus der Liste geloescht?
		if(!success)
			throw new LoeschenNichtMoeglichException("Der Account wurde nicht gefunden!");
	}

	public ArrayList<Anbieter> getAnbieter(){
		return anbieter;
	}
	public ArrayList<Betreiber> getBetreiber(){
		return betreiber;
	}
	public ArrayList<Kunde> getKunden(){
		return kunden;
	}
	public ArrayList<Account> getAccounts(){
		ArrayList<Account> result = new ArrayList<Account>();
		result.addAll(getAnbieter());
		result.addAll(getBetreiber());
		result.addAll(getKunden());
		return result;
	}
	
	private boolean isFreeEmail(String email) {
		boolean result = true;
		for(Account a : getAccounts())
			if(a.getEmail().equals(email))
				result = false;
		return result;
	}

	private boolean isFreeName(String name) {
		boolean result = true;
		for(Account a : getAccounts())
			if(a.getName().equals(name))
				result = false;
		return result;
	}
}
