package accounts;

import java.util.Vector;

import buchungen.Buchung;

public class Kunde extends Account{
	private Vector<Buchung> Buchungen;
	
	
	public Kunde(String em, String nm, String pw) {
		super(em, nm, pw);
		// TODO Auto-generated constructor stub
	}
	public void addBuchung(Buchung entry){
		Buchungen.add(entry);
	}
	public void delBuchung(Buchung entry){
		Buchungen.remove(entry);
	}
	
	@Override
	public int getTyp(){
		return Account.KUNDE;
	}
	
	
}
