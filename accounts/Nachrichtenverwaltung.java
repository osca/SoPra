package accounts;

import java.util.ArrayList;

import main.Portal;

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
	
	/** Gibt den Posteingang eines Accounts aus
	 * @param acc ausgewählter Account
	 * @return ArrayList von Nachrichten des Accounts
	 */
	public ArrayList<Nachricht> getErhalteneNachrichten(Account acc){
		return acc.getPosteingang();
	}
	
	/** Entfernt eine Nachricht aus dem Postausgang des Absenders und aus dem Posteingang des Empfaengers
	 * @param msg zu löschende Nachricht
	 */
	public void delNachricht(Nachricht msg){
		msg.getAbsender().delGeschriebeneNachricht(msg);
		msg.getEmpfaenger().delErhalteneNachricht(msg);
	}
	
	/**
	 * Loescht alle Nachrichten, die auf das spezifizierte Angebot verweisen
	 * @param ang Angebot dessen Verweise hinfaellig sind
	 */
	public void delAllNachrichten(Angebot ang){
		Accountverwaltung av = Portal.getSingletonObject().getAccountverwaltung();
		for(Account acc : av.getAccounts()){
			for(Nachricht n : getErhalteneNachrichten(acc))
				delNachricht(n);
			for(Nachricht n : getGesendeteNachrichten(acc))
				delNachricht(n);
		}
	}
	
	/**
	 * Loescht alle Nachrichten, die der spezifizierte Account in Postein- oder -ausgang hat
	 * @param acc spezielles Accountobjekt
	 */
	public void delAllNachrichten(Account acc){
		for(Nachricht n : getErhalteneNachrichten(acc))
			delNachricht(n);
		for(Nachricht n : getGesendeteNachrichten(acc))
			delNachricht(n);
	}
	
	/** Gibt den Postausgang eines Accounts aus
	 * @param acc ausgewählter Account
	 * @return ArrayList von Nachrichten des Accounts
	 */
	public ArrayList<Nachricht> getGesendeteNachrichten(Account acc){
		return acc.getPostausgang();
	}
}
