package accounts;

import graphic.Listable;

import java.util.ArrayList;

import main.Portal;

/**
 * Repräsentiert einen Benutzer
 * @author stephan
 * @edit 	Jay
 */

public abstract class Account implements Listable{
	
	private String email, name, password;
	protected boolean gesperrt = false;
	private ArrayList<Nachricht> Posteingang = new ArrayList<Nachricht>();
	private ArrayList<Nachricht> Postausgang = new ArrayList<Nachricht>();
	
	public static final int NONE = 0,
							KUNDE = 1,
							ANBIETER = 2,
							BETREIBER = 3;
	
	/**
	 * Legt einen Account mit allen erforderlichen Daten an
	 * @param em E-Mail-Adresse
	 * @param nm Nick- bzw Unternehmens-Name
	 * @param pw Passwort
	 * @pre Es muss vorher nachgeprueft werden ob die Daten (insb. E-Mail und Name) zulaessig sind
	 */
	public Account(String em, String nm, String pw){
		email=em;
		name =nm;
		password=pw;
	}

	/** Fuegt eine Nachricht in den Posteingang ein
	 * @param msg Nachrichtenobjekt
	 */
	public void addErhalteneNachricht(Nachricht msg){
		Posteingang.add(msg);
	}
	
	/** Entfernt eine Nachricht aus dem Posteingang
	 * @param msg Nachrichtenobjekt
	 */
	public void delErhalteneNachricht(Nachricht msg){
		Posteingang.remove(msg);
	}
	
	/** Fuegt eine Nachricht in den Postausgang ein
	 * @param msg Nachrichtenobjekt
	 */
	public void addGeschriebeneNachricht(Nachricht msg){
		Postausgang.add(msg);
	}
	
	/** Entfernt eine Nachricht aus dem Postausgang
	 * @param msg Nachrichtenobjekt
	 */
	public void delGeschriebeneNachricht(Nachricht msg){
		Postausgang.remove(msg);
	}
	
	/** gibt den kompletten Posteingang zurueck
	 * @return Posteingang
	 */
	public ArrayList<Nachricht> getPosteingang() {
		return Posteingang;
	}
	
	/** gibt den kompletten Postausgang zurueck
	 * @return
	 */
	public ArrayList<Nachricht> getPostausgang() {
		return Postausgang;
	}
	
	/** Gibt in Flag aus von welchem Typ der jeweilige Account ist
	 * @return Flag des AccountTyps
	 */
	public abstract int getTyp();

	//-----------------------------------------------------------------------------
	// GETTER und SETTER
	/** Gibt die E-Mail-Adresse zurueck
	 * @return E-Mail-Adresse
	 */
	public String getEmail() {
		return email;
	}

	/** Gibt den Nicknamen bzw den Unternehmensnamen zurueck
	 * @return NamenString
	 */
	public String getName() {
		return name;
	}

	/** Gibt das Passwort zurück
	 * @return PasswortString
	 * @pre Der Aufrufer muss selbst sicherstellen, dass er authorisiert ist
	 */
	public String getPassword() {
		//TODO Sollte noch ersetzt werden durch vernuenftige Sicherheitsabfragen
		return password;
	}
	//Password-getter-und Setter sind doof!!!
	
	/**
	 * @return gibt an ob der Account gesperrt ist
	 */
	public boolean isGesperrt() {
		return gesperrt;
	}

	/**Setzt den Account auf gesperrt bzw. nicht gesperrt
	 * @param gesperrt : true - sperrt account, false - entsperrt Account
	 */
	public void setGesperrt(boolean gesperrt) {	
		this.gesperrt = gesperrt;
	}
	//-----------------------------------------------------------------------------

	//Listable-Methods
	public String getIdetifier(){
		return name+" / "+email;
	}
	
	public String getAdditionalInfo(){
		return ""+getTyp();
	}
	
	public String getFullInfo(){
		return "";
	}
	
	public String getStatus(){ // vllt. 
		return ""+(Portal.getSingletonObject().getAccountverwaltung().getLoggedIn()).equals(this);
	}
	
	public int getListableTyp(){
		return ACCOUNT;
	}
	
}
