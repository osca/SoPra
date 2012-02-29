package accounts;

import java.util.ArrayList;

import buchungen.Buchung;

public class Kunde extends Account {
	
	private ArrayList<Buchung> buchungen = new ArrayList<Buchung>();
	
	
	public Kunde(String em, String nm, String pw) {
		super(em, nm, pw);
	}
	
	public void addBuchung(Buchung entry){
		buchungen.add(entry);
	}
	
	public void delBuchung(Buchung entry){
		buchungen.remove(entry);
	}
	
	public ArrayList<Buchung> getBuchungen() {
		return buchungen;
	}
	
	@Override
	public int getTyp(){
		return Account.KUNDE;
	}	
}
