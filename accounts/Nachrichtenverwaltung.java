package accounts;

import java.util.ArrayList;

import angebote.typen.Angebot;
/**
 * 
 * @author Jay
 *
 */
public class Nachrichtenverwaltung {
	
	private ArrayList<Nachricht> alleNachrichten = new ArrayList<Nachricht>();
	
	public Nachrichtenverwaltung(ArrayList<Nachricht> msgList){
		alleNachrichten = msgList;
	}
	
	public ArrayList<Nachricht> getAlleNachrichten(){
		return alleNachrichten;
	}
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
		alleNachrichten.add(msg);
	}
	
	/** Gibt den Posteingang eines Accounts aus
	 * @param acc ausgewählter Account
	 * @return ArrayList von Nachrichten des Accounts
	 */
	public ArrayList<Nachricht> getErhalteneNachrichten(Account acc){
		ArrayList<Nachricht> posteingang = new ArrayList<Nachricht>();
		for(Nachricht msg : alleNachrichten)
			if(msg.getEmpfaenger().equals(acc))
				posteingang.add(msg);
		return posteingang;
	}
	
	/** Gibt den Postausgang eines Accounts aus
	 * @param acc ausgewählter Account
	 * @return ArrayList von Nachrichten des Accounts
	 */
	public ArrayList<Nachricht> getGesendeteNachrichten(Account acc){
		ArrayList<Nachricht> postausgang = new ArrayList<Nachricht>();
		for(Nachricht msg : alleNachrichten)
			if(msg.getAbsender().equals(acc))
				postausgang.add(msg);
		return postausgang;
	}
	
	/** Entfernt eine Nachricht aus dem Postausgang des Absenders und aus dem Posteingang des Empfaengers
	 * @param msg zu löschende Nachricht
	 */
	public void delNachricht(Nachricht msg){
		alleNachrichten.remove(msg);
	}
	
	/**
	 * Loescht alle Nachrichten, die auf das spezifizierte Angebot verweisen
	 * @param ang Angebot dessen Verweise hinfaellig sind
	 */
	public void delAllNachrichten(Angebot ang){
		int n = alleNachrichten.size();
		for(int i=0; i<n; i++){		//For-Each-Schleife funktioniert nicht
			Nachricht current = alleNachrichten.get(i);
			if(current.getAngebot().equals(ang)){
				delNachricht(current);
				i--;	//Liste rueckt auf
				n--;	//Liste verkuerzt sich
			}
		}
	}
	
	/**
	 * Loescht alle Nachrichten, die der spezifizierte Account in Postein- oder -ausgang hat
	 * @param acc spezielles Accountobjekt
	 */
	public void delAllNachrichten(Account acc){
		// Mit For-Each-Schleife funktioniert Loeschen nicht wie gewünscht, da Elemente nachrutschen
		int n = alleNachrichten.size();
		for(int i=0; i<n; i++){
			Nachricht current = alleNachrichten.get(i);
			if(current.getAbsender().equals(acc) || current.getEmpfaenger().equals(acc)){
				delNachricht(current);
				i--;	//Liste rueckt auf
				n--;	//Liste verkuerzt sich
			}
		}
	}
	
}
