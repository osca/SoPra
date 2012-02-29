package accounts;
//@author stephan
//@edit R

import java.util.ArrayList;

import angebote.typen.Angebot;

public class Anbieter extends Account{
	
	private ArrayList<Angebot> angebote = new ArrayList<Angebot>(); 
	private String agb;
	
	public Anbieter(String em, String nm, String pw) {
		super(em, nm, pw);
		gesperrt = true;
	}
	
	public void addAngebot(Angebot offer){
		angebote.add(offer);
	}
	
	public void delAngebot(Angebot offer){
		angebote.remove(offer);
	}
	
	public ArrayList<Angebot> getAngebote(){
		return angebote;
	}
	
	public double getWertung() {
		double result = 0.00;
		
		if(angebote.size() == 0)
			return result;
		
		for(Angebot a:angebote) {
			result+=a.getWertung();
		}
		
		return result/angebote.size();
	}
	
	@Override
	public int getTyp(){
		return Account.ANBIETER;
	}
	
	//---------------------------------------
	//Triviale GETTER und SETTER
	public String getAgb() {
		return agb;
	}
	
	public void setAgb(String agb) {
		this.agb = agb;
	}
	//-----------------------------------------

}
