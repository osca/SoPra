package accounts;
//@author stephan
//@edit R

import java.util.ArrayList;

import angebote.typen.Angebot;

public class Anbieter extends Account{
	
	private ArrayList<Angebot> Angebote = new ArrayList<Angebot>(); 
	private int anzahlWertungen;
	private double wertung;
	private String agb;
	
	public Anbieter(String em, String nm, String pw) {
		super(em, nm, pw);
		gesperrt = true;
		anzahlWertungen=0;
	}
	
	public void addAngebot(Angebot offer){
		Angebote.add(offer);
	}
	
	public void delAngebot(Angebot offer){
		Angebote.remove(offer);
	}
	
	public ArrayList<Angebot> getAngebote(){
		return Angebote;
	}
	
	public void addWertung(double wertung) {
		this.wertung = (wertung + (this.wertung*anzahlWertungen)) / ++anzahlWertungen;
	}
	
	@Override
	public int getTyp(){
		return Account.ANBIETER;
	}
	
	//---------------------------------------
	//Triviale GETTER und SETTER
	public double getWertung() {
		return wertung;
	}
	
	public String getAgb() {
		return agb;
	}
	
	public void setAgb(String agb) {
		this.agb = agb;
	}
	//-----------------------------------------

}
