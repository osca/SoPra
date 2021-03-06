package accounts;

import java.util.ArrayList;
import java.util.Collections;

import main.Portal;
import angebote.typen.Angebot;

/**
 * Nachrichtenverwaltung
 * 
 * Verwaltet die Nachrichten mit Standard- und saemtlichen
 * nicht-trivialen Operationen
 * 
 * @author Jay
 * @edit osca
 */
public class Nachrichtenverwaltung {

	private ArrayList<Nachricht> alleNachrichten = new ArrayList<Nachricht>();

	/**
	 * Konstruktor mit leerer Nachrichtenliste
	 */
	public Nachrichtenverwaltung() {
	}

	/**
	 * Konstruiert ein Nachrichtenverwaltungsobjekt mit vorgegebener
	 * Nachrichten-Liste
	 * 
	 * @param nachrichten ArrayList an Nachrichten
	 */
	public Nachrichtenverwaltung(ArrayList<Nachricht> nachrichten) {
		alleNachrichten = nachrichten;
	}

	/**
	 * Sendet Nachricht mit 'text' und 'betreff' von 'absender' an 'empfaenger'
	 * und verweist auf das 'angebot'
	 * 
	 * @param absender Absender
	 * @param empfaenger Empfaenger
	 * @param betreff Betreff
	 * @param text Text
	 * @param angebot Verweisendes Angebot
	 * 
	 * @pre Absender ist nicht null und muss im System sein
	 * @pre Empfaenger ist nicht null und muss im System sein
	 * @pre Betreff darf nicht leer oder null sein
	 * @pre Verweisendes Angebot darf nicht leer sein und muss im System sein
	 */
	public Nachricht sendeNachricht(Account absender, Account empfaenger, String betreff, String text, Angebot angebot) {
		assert absender != null: "Der Absender ist null";
		assert Portal.Accountverwaltung().getLoggedIn().equals(absender): "Der Absender ist nicht eingeloggt";
		assert empfaenger != null: "Der Empfaenger ist null";
		boolean found = false;
		for(Account acc : Portal.Accountverwaltung().getAccounts())
			if(acc.getName().equals(empfaenger.getName()))
				found = true;
		assert found: "Der Empfaenger ist nicht im System";
		assert !betreff.equals("") || betreff != null: "Der Betreff wurde nicht gesetzt";
		assert angebot != null: "Das Angebot ist null";
		found = false;
		for(Angebot ang : Portal.Angebotsverwaltung().getAllAngebote())
			if(ang.getAngebotsNummer() == angebot.getAngebotsNummer())
				found = true;
		assert found : "Das Angebot ist nicht im System";
		
		if(text.equals(""))
			throw new IllegalArgumentException("Es wurde kein Text eingegeben");
			
		Nachricht msg = new Nachricht(betreff, text, absender, empfaenger, angebot);
		alleNachrichten.add(msg);
		return msg;
	}
	
	/**
	 * Sende Meldung an alle Betreiber (Meldung eines Angebots)
	 * 
	 * @param absender Absender (Meist Kunde)
	 * @param betreff Betreff
	 * @param text Text
	 * @param angebot Zu meldendes Angebot
	 */
	public void sendeMeldungAnAlleBetreiber(Account absender, String betreff, String text, Angebot angebot) {
		ArrayList<Betreiber> betreiberListe = Portal.Accountverwaltung().getBetreiber();
		for(Betreiber betreiber:betreiberListe) {
			sendeNachricht(absender, betreiber, betreff, text, angebot);
		}
	}

	/**
	 * Gibt den Posteingang eines Accounts aus
	 * 
	 * @param acc Ausgewaehlter Account
	 * @return ArrayList von Nachrichten des Accounts
	 */
	public ArrayList<Nachricht> getErhalteneNachrichten(Account acc) {
		ArrayList<Nachricht> posteingang = new ArrayList<Nachricht>();
		for (Nachricht msg : alleNachrichten)
			if(acc.getName() .equals(msg.getEmpfaenger()))
				posteingang.add(msg);
		Collections.reverse(posteingang);
		return posteingang;
	}

	/**
	 * Gibt den Postausgang eines Accounts aus
	 * 
	 * @param acc Ausgewaehlter Account
	 * @return ArrayList von Nachrichten des Accounts
	 */
	public ArrayList<Nachricht> getGesendeteNachrichten(Account acc) {
		ArrayList<Nachricht> postausgang = new ArrayList<Nachricht>();
		for (Nachricht msg : alleNachrichten)
			if (acc.getName() .equals(msg.getAbsender()))
				postausgang.add(msg);
		Collections.reverse(postausgang);
		return postausgang;
	}

	/**
	 * Entfernt eine Nachricht aus dem Postausgang des Absenders und aus dem
	 * Posteingang des Empfaengers
	 * 
	 * @param msg Zu loeschende Nachricht
	 *            
	 * @pre Die Nachricht existiert
	 */
	public void delNachricht(Nachricht msg) {
		boolean found = false;
		for(Nachricht n : alleNachrichten)
			if(n.getId() == msg.getId())
				found = true;
		assert found : "Die Nachricht existiert nicht";
		
		alleNachrichten.remove(msg);
	}

	/**
	 * Loescht alle Nachrichten, die der spezifizierte Account in Postein- oder
	 * -ausgang hat
	 * 
	 * @param acc Spezielles Accountobjekt
	 *            
	 * @pre Uebergebener Account darf nicht null sein und muss im System existieren
	 */
	public void delAllNachrichten(Account acc) {
		assert acc != null: "Account ist null";
		assert Portal.Accountverwaltung().getLoggedIn().equals(acc): "Der Account ist nicht eingeloggt";
		
		// Mit For-Each-Schleife funktioniert Loeschen nicht wie gewünscht, da
		// Elemente nachrutschen
		int n = alleNachrichten.size();
		for (int i = 0; i < n; i++) {
			Nachricht current = alleNachrichten.get(i);
			if (current.getAbsender().equals(acc.getName())
					|| current.getEmpfaenger().equals(acc.getName())) {
				delNachricht(current);
				i--; // Liste rueckt auf
				n--; // Liste verkuerzt sich
			}
		}
	}

	/**
	 * Loescht alle Nachrichten, die auf das spezifizierte Angebot verweisen
	 * 
	 * @param ang Angebot dessen Verweise hinfaellig sind
	 *            
	 * @pre Das Angebot darf nicht null sein und muss im System sein
	 */
	public void delAllNachrichten(Angebot ang) {
		assert ang != null: "Das Angebot ist null";		
		boolean found = false;
		for(Angebot a : Portal.Angebotsverwaltung().getAllAngebote())
			if(a.getAngebotsNummer() == ang.getAngebotsNummer())
				found = true;
		assert found : "Das Angebot ist nicht im System";
		
		int n = alleNachrichten.size();
		for (int i = 0; i < n; i++) { // For-Each-Schleife funktioniert nicht
			Nachricht current = alleNachrichten.get(i);
			if (getReferringAngebot(current).equals(ang)) {
				delNachricht(current);
				i--; // Liste rueckt auf
				n--; // Liste verkuerzt sich
			}
		}
	}

	/**
	 * Gibt das Angebotsobjekt zu einer Nachricht aus
	 * 
	 * @param msg Entsprechende Nachricht
	 * @return Angebot
	 * 
	 * @pre Uebergebene Nachricht darf nicht null sein und muss im System vorhanden sein
	 */
	public Angebot getReferringAngebot(Nachricht msg) {
		assert msg != null: "Nachricht ist null";
		boolean found = false;
		for(Nachricht n : alleNachrichten)
			if(n.getId() == msg.getId())
				found = true;
		assert found : "Die Nachricht existiert nicht";
		
		return Portal.Angebotsverwaltung().getAngebotByNummer(msg.getAngebotsNummer());
	}

	/**
	 * Gibt den Absender der Nachricht zurueck
	 * 
	 * @param msg Nachricht
	 * @return Absender
	 * 
	 * @pre Uebergebene Nachricht darf nicht null sein und muss im System vorhanden sein
	 */
	public Account getAbsender(Nachricht msg) {
		assert msg != null: "Nachricht ist null";
		boolean found = false;
		for(Nachricht n : alleNachrichten)
			if(n.getId() == msg.getId())
				found = true;
		assert found : "Die Nachricht existiert nicht";
		
		return Portal.Accountverwaltung().getAccountByName(msg.getAbsender());
	}

	/**
	 * Gibt den Empfaenger der Nachricht zurueck
	 * 
	 * @param msg Nachricht
	 * @return Empfaenger
	 * 
	 * @pre Uebergebene Nachricht darf nicht null sein und muss im System vorhanden sein
	 */
	public Account getEmpfaenger(Nachricht msg) {
		assert msg != null: "Nachricht ist null";
		boolean found = false;
		for(Nachricht n : alleNachrichten)
			if(n.getId() == msg.getId())
				found = true;
		assert found : "Die Nachricht existiert nicht";
		
		return Portal.Accountverwaltung().getAccountByName(msg.getEmpfaenger());
	}

	/**
	 * Get Nachricht By Id
	 * @param id  Id einer Nachricht
	 * @return Nachricht mit der uebergebenen Id oder null wenn diese nicht existiert
	 */
	public Nachricht getNachrichtById(int id) {
		for (Nachricht n : alleNachrichten) {
			if (n.getId() == id)
				return n;
		}
		return null;
	}

	/**
	 * Get Anzahl an Ungelesene Nachrichten
	 * @param acc Account dessen ungelesene Nachrichten gezählt werden sollen. Bei null werden alle ungelesenen Nachrichten gezählt
	 * @return Anzahl an ungelesenen Nachrichten
	 * 
	 * @pre Uebergebener Account darf nicht null sein und muss im System existieren
	 */
	public int getAnzahlUngelesenerNachrichten(Account acc) {
		assert acc != null: "Account ist null";
		boolean found = false;
		if(acc.getTyp()==Account.BETREIBER )
			found = true;
		else{
			for(Account a : Portal.Accountverwaltung().getAccounts())
				if(a.getName().equals(acc.getName()))
					found = true;
		}
		assert found : "Der Account existiert nicht im System";
		
		int result = 0;
		for (Nachricht n : alleNachrichten) {
			if (!n.isGelesen() && n.getEmpfaenger() .equals(acc.getName()))
				result++;
		}
		return result;
	}
	
	
	//-----------------------------------------------------------------------------//
	//	GETTER UND SETTER														   //
	//-----------------------------------------------------------------------------//
	
	/**
	 * Get Alle Nachrichten
	 * 
	 * @return Alle Nachrichten die momentan gespeichert sind
	 */
	public ArrayList<Nachricht> getAlleNachrichten() {
		return alleNachrichten;
	}
	
	/**
	 * Get Buchungsnummer einer Nachricht
	 * 
	 * @param msg Nachricht
	 * @return Buchungsnummer
	 */
	public int getBuchungsNummer(Nachricht msg) {
		return msg.getBuchungsNummer();
	}
	
	/**
	 * Get Buchungsnummer einer Nachricht
	 * 
	 * @param msg Nachricht
	 * @return Buchungsnummer
	 */
	public void setBuchungsnummer(Nachricht msg, int buchungsnummer) {
		msg.setBuchungsNummer(buchungsnummer);
	}
	
	/**
	 * Ist die Nachricht gelesen
	 * 
	 * @param n Nachricht
	 * @return Boolean
	 * 
	 * @pre Die Nachricht darf nicht null sein und muss im System existieren
	 */
	public boolean isGelesen(Nachricht n) {
		assert n != null: "Die Nachricht ist null";
		boolean found = false;
		for(Nachricht msg : alleNachrichten)
			if(n.getId() == msg.getId())
				found = true;
		assert found : "Die Nachricht existiert nicht";
		
		return n.isGelesen();
	}

	/**
	 * Set Gelesen oder Ungelesen
	 * 
	 * @param n Nachricht
	 * @param gelesen Boolean
	 */
	public void setGelesen(Nachricht n, boolean gelesen) {
		n.setGelesen(gelesen);
	}

}
