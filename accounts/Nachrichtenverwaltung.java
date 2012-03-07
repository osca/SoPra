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
	 * @param absender
	 * @param empfaenger
	 * @param betreff
	 * @param text
	 * @param angebot
	 */
	public void sendeNachricht(Account absender, Account empfaenger, String betreff, String text, Angebot angebot) {
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
	 *            ausgewählter Account
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
	 *            ausgewählter Account
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
	 *            zu löschende Nachricht
	 */
	public void delNachricht(Nachricht msg) {
		alleNachrichten.remove(msg);
	}

	/**
	 * Loescht alle Nachrichten, die auf das spezifizierte Angebot verweisen
	 * 
	 * @param ang
	 *            Angebot dessen Verweise hinfaellig sind
	 */
	public void delAllNachrichten(Angebot ang) {
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
	 */
	public Angebot getReferringAngebot(Nachricht msg) {
		return Portal.Angebotsverwaltung().getAngebotByNummer(msg.getAngebotsNummer());
	}

	/**
	 * gibt den Absender der Nachricht zurueck
	 * 
	 * @param msg
	 * @return
	 */
	public Account getAbsender(Nachricht msg) {
		return Portal.Accountverwaltung().getAccountByName(msg.getAbsender());
	}

	/**
	 * gibt den Empfaenger der Nachricht zurueck
	 * 
	 * @param msg
	 * @return
	 */
	public Account getEmpfaenger(Nachricht msg) {
		return Portal.Accountverwaltung().getAccountByName(msg.getEmpfaenger());
	}

	/**
	 * Loescht alle Nachrichten, die der spezifizierte Account in Postein- oder
	 * -ausgang hat
	 * 
	 * @param acc
	 *            spezielles Accountobjekt
	 */
	public void delAllNachrichten(Account acc) {
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
	 */
	public boolean isGelesen(Nachricht n) {
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
	 */
	public int getAnzahlUngelesenerNachrichten(Account acc) {
		int result = 0;
		for (Nachricht n : alleNachrichten) {
			if (!n.isGelesen() && (acc==null || n.getEmpfaenger() .equals(acc.getName())))
				result++;
		}
		return result;
	}

}
