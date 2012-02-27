package accounts;

import java.util.Vector;

public abstract class Account {
	private String email, name, password;
	protected boolean gesperrt = false;
	private Vector<Nachricht> Posteingang;
	

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
