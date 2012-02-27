package accounts;
//@author stephan
//@edit R
import java.util.Vector;

import angebote.typen.Angebot;

public class Anbieter extends Account{
	private Vector<Angebot> Angebote; 
	private int anzahlWertungen;
	private double wertung;
	private String agb;
	public Anbieter(String em, String nm, String pw) {
		super(em, nm, pw);
		gesperrt = true;
		anzahlWertungen=0;
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
		if(anzahlWertungen>0){
		this.wertung = (wertung+this.wertung)/anzahlWertungen;
		}
		else return;
	}
	public String getAgb() {
		return agb;
	}
	public void setAgb(String agb) {
		this.agb = agb;
	}
	//-----------------------------------------

}
