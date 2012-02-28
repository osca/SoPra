package accounts;
/**
 * @author stephan
 * @edit 	Jay
 */

import java.util.ArrayList;

public abstract class Account {
	private String email, name, password;
	protected boolean gesperrt = false;
	private ArrayList<Nachricht> Posteingang;
	public static final int //NONE = 0,
							KUNDE = 1,
							ANBIETER = 2,
							BETREIBER = 3;
	

	public Account(String em, String nm, String pw){
		email=em;
		name =nm;
		password=pw;
	}
	
	public void addNachricht(Nachricht msg){
		Posteingang.add(msg);
	}
	public void delNachricht(Nachricht msg){
		Posteingang.remove(msg);
	}
	
	public ArrayList<Nachricht> getPosteingang() {
		return Posteingang;
	}
	
	/** Gibt in Flag aus von welchem Typ der jeweilige Account ist
	 * @return Flag des AccountTyps
	 */
	public abstract int getTyp();

	//-----------------------------------------------------------------------------
	// GETTER und SETTER
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;	
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	//Sollte noch ersetzt werden durch vernünftige Sicherheitsabfragen
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	//Password-getter-und Setter sind doof!!!
	
	
	public boolean isGesperrt() {
		return gesperrt;
	}

	public void setGesperrt(boolean gesperrt) {	
		this.gesperrt = gesperrt;
	}
	//-----------------------------------------------------------------------------

	
}
