package accounts;
//@author stephan
//@edit R

import java.util.ArrayList;

import main.Portal;

import angebote.typen.Angebot;

public class Anbieter extends Account{
	
	
	//private ArrayList<Angebot> angebote = new ArrayList<Angebot>(); 
	private ArrayList<Integer> angebote = new ArrayList<Integer>();
	private String agb;
	
	public Anbieter(String em, String nm, String pw) {
		super(em, nm, pw);
		gesperrt = true;
	}
	
	public void addAngebot(Angebot offer){
		angebote.add(offer.getAngebotsNummer());
	}
	
	public void addAngebot(int angebotsNummer){
		angebote.add(angebotsNummer);
	}

	public void delAngebot(Angebot offer){
		angebote.remove(offer.getAngebotsNummer());
	}
	
	public void delAngebot(int angebotsNummer){
		angebote.remove(angebotsNummer);
	}
	
	public double getWertung() {
		double result = 0.00;
		
		if(angebote.size() == 0)
			return result;
		
		for(Angebot a: Portal.getSingletonObject().getAngebotsverwaltung().getAngebote(this)) {
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
