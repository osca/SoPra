package accounts;

import java.util.ArrayList;
import java.util.Collections;

import main.Portal;
import angebote.typen.Angebot;

/**
 * 
 * @author Jay
 * 
 */
public class Nachrichtenverwaltung {

	private ArrayList<Nachricht> alleNachrichten = new ArrayList<Nachricht>();

	/**
	 * Konstruktor mit leerer Nachrichtenliste
	 */
	public Nachrichtenverwaltung() {
	}

	/**
	 * konstruiert ein Nachrichtenverwaltungsobjekt mit vorgegebener
	 * Nachrichten-Liste
	 * 
	 * @param nachrichten
	 */
	public Nachrichtenverwaltung(ArrayList<Nachricht> nachrichten) {
		alleNachrichten = nachrichten;
	}

	/**
	 * @return Alle Nachrichten die momentan gespeichert sind
	 */
	public ArrayList<Nachricht> getAlleNachrichten() {
		return alleNachrichten;
	}

	/**
	 * Sendet Nachricht mit 'text' und 'betreff' von 'absender' an 'empfaenger'
	 * und verweist auf das 'angebot'
	 * 
	 * @param absender Absender
	 * @param empfaenger Empfaenger
	 * @param betreff Betreff
	 * @param text text
	 * @param angebot Verweisendes Angebot
	 * 
	 * @pre Absender ist nicht null und muss im System sein
	 * @pre Empfaenger ist nicht null und muss im System sein
	 * @pre Betreff darf nicht leer oder null sein
	 * @pre Verweisendes Angebot darf nicht leer sein und muss im System sein
	 */
	public void sendeNachricht(Account absender, Account empfaenger, String betreff, String text, Angebot angebot) {
		assert absender != null: "Der Absender ist null";
		assert Portal.Accountverwaltung().getLoggedIn().equals(absender): "Der Absender ist nicht eingeloggt";
		assert empfaenger != null: "Der Empfaenger ist null";
		assert Portal.Accountverwaltung().getAccounts().contains(empfaenger): "Der Empfaenger ist nicht im System";
		assert !betreff.equals("") || betreff != null: "Der Betreff wurde nicht gesetzt";
		assert angebot != null: "Das Angebot ist null";
		assert Portal.Angebotsverwaltung().getAllAngebote().contains(angebot): "Das Angebot ist nicht im System";
		
		if(text.equals(""))
			throw new IllegalArgumentException("Es wurde kein Text eingegeben");
			
		Nachricht msg = new Nachricht(betreff, text, absender, empfaenger, angebot);
		alleNachrichten.add(msg);
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
	 * @param acc
	 *            ausgewaehlter Account
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
	 * @param acc
	 *            ausgewaehlter Account
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
	 * @param msg
	 *            zu loeschende Nachricht
	 *            
	 * @pre Die Nachricht existiert
	 */
	public void delNachricht(Nachricht msg) {
		assert alleNachrichten.contains(msg): "Die Nachricht existiert nicht";
		
		alleNachrichten.remove(msg);
	}

	/**
	 * Loescht alle Nachrichten, die auf das spezifizierte Angebot verweisen
	 * 
	 * @param ang
	 *            Angebot dessen Verweise hinfaellig sind
	 *            
	 * @pre Das Angebot darf nicht null sein und muss im System sein
	 */
	public void delAllNachrichten(Angebot ang) {
		assert ang != null: "Das Angebot ist null";
		assert Portal.Angebotsverwaltung().getAllAngebote().contains(ang): "Das Angebot existiert nicht im System";
		
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
	 * gibt das Angebotsobjekt zu einer Nachricht aus
	 * @param msg entsprechende Nachricht
	 * @return Angebot
	 * 
	 * @pre Uebergebene Nachricht darf nicht null sein und muss im System vorhanden sein
	 */
	public Angebot getReferringAngebot(Nachricht msg) {
		assert msg != null: "Nachricht ist null";
		assert alleNachrichten.contains(msg): "Die Nachricht existiert nicht im System";
		
		return Portal.Angebotsverwaltung().getAngebotByNummer(msg.getAngebotsNummer());
	}

	/**
	 * gibt den Absender der Nachricht zurueck
	 * 
	 * @param msg
	 * @return Absender
	 * 
	 * @pre Uebergebene Nachricht darf nicht null sein und muss im System vorhanden sein
	 */
	public Account getAbsender(Nachricht msg) {
		assert msg != null: "Nachricht ist null";
		assert alleNachrichten.contains(msg): "Die Nachricht existiert nicht im System";
		
		return Portal.Accountverwaltung().getAccountByName(msg.getAbsender());
	}

	/**
	 * gibt den Empfaenger der Nachricht zurueck
	 * 
	 * @param msg Nachricht
	 * @return Empfaenger
	 * 
	 * @pre Uebergebene Nachricht darf nicht null sein und muss im System vorhanden sein
	 */
	public Account getEmpfaenger(Nachricht msg) {
		assert msg != null: "Nachricht ist null";
		assert alleNachrichten.contains(msg): "Die Nachricht existiert nicht im System";
		
		return Portal.Accountverwaltung().getAccountByName(msg.getEmpfaenger());
	}

	/**
	 * Loescht alle Nachrichten, die der spezifizierte Account in Postein- oder
	 * -ausgang hat
	 * 
	 * @param acc
	 *            spezielles Accountobjekt
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

	/**Ist die Nachricht gelesen
	 * @param n Nachricht
	 * @return Boolean
	 * 
	 * @pre Die Nachricht darf nicht null sein und muss im System existieren
	 */
	public boolean isGelesen(Nachricht n) {
		assert n != null: "Die Nachricht ist null";
		assert alleNachrichten.contains(n): "Die Nachricht exisitert nicht im System";
		
		return n.isGelesen();
	}

	/**Set Gelesen oder Ungelesen
	 * @param n Nachricht
	 * @param gelesen Boolean
	 */
	public void setGelesen(Nachricht n, boolean gelesen) {
		n.setGelesen(gelesen);
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
		assert (acc.getTyp()==Account.BETREIBER || Portal.Accountverwaltung().getAccounts().contains(acc))
				: "Der Account existiert nicht im System";
		
		int result = 0;
		for (Nachricht n : alleNachrichten) {
			if (!n.isGelesen() && n.getEmpfaenger() .equals(acc.getName()))
				result++;
		}
		return result;
	}

}
