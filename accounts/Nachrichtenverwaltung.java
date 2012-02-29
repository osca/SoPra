package accounts;

import java.util.ArrayList;

import angebote.typen.Angebot;
/**
 * 
 * @author Jay
 *
 */
public class Nachrichtenverwaltung {
	/**
	 * Sendet Nachricht mit 'text' und 'betreff' von 'absender' an 'empfaenger' und verweist auf das 'angebot'
	 * @param absender
	 * @param empfaenger
	 * @param betreff
	 * @param text
	 * @param angebot
	 */
	public void sendeNachricht(Account absender, Account empfaenger, String betreff, String text, Angebot angebot){
		Nachricht msg = new Nachricht(betreff, text, absender, empfaenger, angebot);
		empfaenger.addErhalteneNachricht(msg);
		absender.addGeschriebeneNachricht(msg);
		//Angebot kennt nicht alle Nachrichten.
	}
	
	/** Entfernt eine Nachricht aus dem Posteingang eines Accounts
	 * @param acc Account mit zu löschender Nachricht
	 * @param msg zu löschende Nachricht
	 */
	public void delErhalteneNachricht(Account acc, Nachricht msg){
		acc.delErhalteneNachricht(msg);
	}
	
	/** Gibt den Posteingang eines Accounts aus
	 * @param acc ausgewählter Account
	 * @return ArrayList von Nachrichten des Accounts
	 */
	public ArrayList<Nachricht> getErhalteneNachrichten(Account acc){
		return acc.getPosteingang();
	}
	
	/** Entfernt eine Nachricht aus dem Postausgang eines Accounts
	 * @param acc Account mit zu löschender Nachricht
	 * @param msg zu löschende Nachricht
	 */
	public void delGeschriebeneNachricht(Account acc, Nachricht msg){
		acc.delGeschriebeneNachricht(msg);
	}
	
	/** Gibt den Postausgang eines Accounts aus
	 * @param acc ausgewählter Account
	 * @return ArrayList von Nachrichten des Accounts
	 */
	public ArrayList<Nachricht> getGesendeteNachrichten(Account acc){
		return acc.getPostausgang();
	}
}
