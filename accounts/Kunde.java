package accounts;

import java.util.ArrayList;

import buchungen.Buchung;

/**
 * Kunde, erbende Klasse von Account
 * 
 * @edit osca (schrieb javadoc)
 */
public class Kunde extends Account {
	
	private boolean firstLogin = true;
	
	// private ArrayList<Buchung> buchungen = new ArrayList<Buchung>();
	private ArrayList<Integer> buchungen = new ArrayList<Integer>();
	
	/**
	 * Konstruktor
	 * 
	 * @param em E-Mail Adresse
	 * @param nm Username
	 * @param pw Passwort
	 */
	public Kunde(String em, String nm, String pw) {
		super(em, nm, pw);
	}
	
	/**
	 * Fuege Buchung in die Buchungsliste hinzu
	 * 
	 * @param entry Buchung
	 */
	public void addBuchung(Buchung entry) {
		buchungen.add(entry.getBuchungsnummer());
	}
	
	/**
	 * Loesche Buchung aus Buchungsliste
	 * 
	 * @param entry Buchung
	 * @throws LoeschenNichtMoeglichException Buchung ist null oder wurde nicht gefunden
	 */
	public void delBuchung(Buchung entry) throws LoeschenNichtMoeglichException {
		if(entry == null || !(buchungen.contains(entry.getBuchungsnummer())))
			throw new LoeschenNichtMoeglichException("Buchung null oder nicht gefunden!");
		buchungen.remove((Integer) entry.getBuchungsnummer());
	}
	
	public ArrayList<Integer> getBuchungsNummern(){
		return buchungen;
	}
	
	public boolean getFirstLogin() {
		return firstLogin;
	}
	
	public void setFirstLogin() {
		firstLogin = false;
	}
	
	/**
	 * Get Accounttyp
	 * 
	 * @return Accounttypnummer
	 */
	@Override
	public int getTyp() {
		return Account.KUNDE;
	}	
}
