package accounts;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import graphic.Listable;
import main.Portal;

/**
 * Repräsentiert einen Benutzer
 * @author stephan
 * @edit 	Jay
 */

public abstract class Account implements Listable{
	
	private String email, name, password;
	protected Gesperrt gesperrt = Gesperrt.NEIN;
	
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
		password=hashPassword(pw);
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
		return password;
	}
	//Password-getter-und Setter sind doof!!!
	
	/**
	 * @return gibt an ob der Account gesperrt ist
	 */
	public boolean isGesperrt() {
		if(gesperrt == Gesperrt.JA || gesperrt == Gesperrt.UNBERARBEITET)
			return true;
		else
			return false;
	}

	/**Setzt den Account auf gesperrt bzw. nicht gesperrt
	 * @param gesperrt : true - sperrt account, false - entsperrt Account
	 */
	void setGesperrt(Gesperrt gesperrt) {		//package
		this.gesperrt = gesperrt;
	}
	//-----------------------------------------------------------------------------

	//Listable-Methods
	public String getIdentifier(){
		return name;
	}
	
	public String getAdditionalInfo(){
		return Portal.Accountverwaltung().convertFlagToName(getTyp());
	}
	
	public String getFullInfo(){
		return "";
	}
	
	public String getStatus(){ // vllt. 
		switch(gesperrt){
		case JA : return "gesperrt";
		case UNBERARBEITET : return "nicht freigeschaltet";
		case NEIN : return "freigeschaltet";
		default : return "";
		}
	}
	
	public int getListableTyp(){
		return Listable.ACCOUNT;
	}
	
	public static String hashPassword(String password) {
		String hashword = null;
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(password.getBytes());
			BigInteger hash = new BigInteger(1, md5.digest());
			hashword = hash.toString(16);
		} catch (NoSuchAlgorithmException nsae) {
			nsae.printStackTrace();
		}
		return hashword;
	}
}
