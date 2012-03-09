package accounts;

import java.util.ArrayList;

import main.Portal;

import angebote.typen.Angebot;

/**
 * Anbieter - erbt von Account
 * 
 * @author stephan
 * @edit Rudolf
 */
public class Anbieter extends Account {
	
	private boolean firstLogin = true;
	
	private ArrayList<Integer> angebote = new ArrayList<Integer>();
	private String agb = "";
	
	/**
	 * Konstruktor ohne AGB
	 * 
	 * @param em E-Mail Adresse
	 * @param nm Username
	 * @param pw Passwort
	 */
	public Anbieter(String em, String nm, String pw) {
		super(em, nm, pw);
		gesperrt = Gesperrt.UNBEARBEITET;
	}
	
	/**
	 * Konstruktor mit AGB
	 * 
	 * @param em E-Mail Adresse
	 * @param nm Username
	 * @param pw Passwort
	 * @param pagb AGB
	 */
	public Anbieter(String em, String nm, String pw, String pagb) {
		super(em, nm, pw);
		agb = pagb;
		gesperrt = Gesperrt.UNBEARBEITET;
	}
	
	/**
	 * Ein Angebot (Angebotsnummer) der Angebotsliste hinzufuegen
	 * 
	 * @param angebot Angebot
	 */
	public void addAngebot(Angebot angebot) {
		angebote.add(angebot.getAngebotsNummer());
	}

	/**
	 * Ein Angebot (Angebotsnummer) der Angebotsliste loeschen
	 * 
	 * @param angebot Angebot
	 */
	public void delAngebot(Angebot angebot) {
		angebote.remove((Integer) angebot.getAngebotsNummer());
	}
	
	/**
	 * Get Wertung des Anbieters
	 * 
	 * @return Wertung
	 */
	public double getWertung() {
		double result = 0.00;
		
		if(angebote.size() == 0)
			return result;
		
		for(Angebot a: Portal.Angebotsverwaltung().getAngebote(this)) {
			result += a.getWertung();
		}
		
		return result/angebote.size();
	}
	
	
	//-----------------------------------------------------------------------------//
	//	GETTER UND SETTER														   //
	//-----------------------------------------------------------------------------//
	
	/**
	 * Gibt von allen Angeboten des Anbieters die AngebotsNummern aus
	 * 
	 * @return ArrayList an Angebotsnummern
	 */
	public ArrayList<Integer> getAngebotsNummern() {
		return angebote;
	}
	
	/**
	 * Ist der Anbieter das erste Mal eingeloggt?
	 * 
	 * @return Ja oder Nein
	 */
	public boolean getFirstLogin() {
		return firstLogin;
	}
	
	/**
	 * Wenn das erste Mal eingeloggt, wird diese Methode aufgerufen und
	 * firstLogin auf false gesetzt
	 */
	public void setFirstLogin() {
		firstLogin = false;
	}
	
	/**
	 * Get AGB
	 * 
	 * @return AGB
	 */
	public String getAgb() {
		return agb;
	}
	
	/**
	 * Set AGB
	 * 
	 * @param agb AGB
	 */
	public void setAgb(String agb) {
		this.agb = agb;
	}

	/**
	 * Get Accounttyp
	 * 
	 * @return Accounttypnummer
	 */
	@Override
	public int getTyp(){
		return Account.ANBIETER;
	}
}
