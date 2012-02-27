package accounts;

import java.util.Vector;

import angebote.typen.Angebot;

public class Anbieter extends Account{
	private Vector<Angebot> Angebote; 
	private double wertung;
	private String agb;
	public Anbieter(String em, String nm, String pw) {
		super(em, nm, pw);
		gesperrt = true;
		// TODO Auto-generated constructor stub
	}
	public void addAngebot(Angebot offer){
		Angebote.add(offer);
	}
	public void delAngebot(Angebot offer){
		Angebote.remove(offer);
	}
	public Vector<Angebot> getAngebote(){
		return Angebote;
	}
	
	
	//---------------------------------------
	//Triviale GETTER und SETTER
	public double getWertung() {
		return wertung;
	}
	public void setWertung(double wertung) {
		this.wertung = wertung;
	}
	public String getAgb() {
		return agb;
	}
	public void setAgb(String agb) {
		this.agb = agb;
	}
	//-----------------------------------------

}
