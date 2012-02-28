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
		empfaenger.addNachricht(msg);
		//absender.addNachricht(msg);	//TODO Postausgang??
	}
	
	/**
	 * @param acc Account mit zu löschender Nachricht
	 * @param msg zu löschende Nachricht
	 */
	public void delNachricht(Account acc, Nachricht msg){
		acc.delNachricht(msg);
	}
	
	/**
	 * @param acc ausgewählter Account
	 * @return ArrayList von Nachrichten des Accounts
	 */
	public ArrayList<Nachricht> getNachrichten(Account acc){
		return acc.getPosteingang();
	}
}
