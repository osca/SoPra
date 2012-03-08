package accounts;

/**
 * Betreiber - erbt von Account
 * 
 * @author osca
 */
public class Betreiber extends Account {

	/**
	 * Konstruktor
	 * 
	 * @param em E-Mail Adresse
	 * @param nm Username
	 * @param pw Passwort
	 */
	public Betreiber(String em, String nm, String pw) {
		super(em, nm, pw);
	}

	/**
	 * Get Accounttyp
	 * 
	 * @return Accounttypnummer
	 */
	@Override
	public int getTyp(){
		return Account.BETREIBER;
	}

}
