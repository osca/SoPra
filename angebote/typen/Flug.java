package angebote.typen;

import java.util.ArrayList;
import java.util.Date;

import angebote.kriterien.Beschreibung;
import angebote.kriterien.Bierpreis;
import angebote.kriterien.Klasse;
import angebote.kriterien.Kriterium;
import angebote.kriterien.Ort;
import angebote.kriterien.Plaetze;

public class Flug extends Angebot {
	
	private static final int BESCHREIBUNG = 0,
			 				 START = 1,
			 				 ZIEL = 2,
			 				 PLAETZE = 2,
			 				 KLASSE = 3,
			 				 BIERPREIS = 4;

	Kriterium[] erlaubteKriterien = {new Beschreibung(null),new Ort(null),new Ort(null),new Plaetze(null),new Klasse(null),new Bierpreis(null)};
	
	public Flug(String pname, double ppreis, Date[] pdaten) {
		super(pname, Angebot.FLUG, ppreis, pdaten);
	}
	
	public void setBeschreibung(ArrayList<String> werte) {
		erlaubteKriterien[BESCHREIBUNG].setWerte(werte);
	}
	
	public void setStart(ArrayList<String> werte) {
		erlaubteKriterien[START].setWerte(werte);
	}
	
	public void setZiel(ArrayList<String> werte) {
		erlaubteKriterien[ZIEL].setWerte(werte);
	}
	
	public void setPlaetze(ArrayList<String> werte) {
		erlaubteKriterien[PLAETZE].setWerte(werte);
	}
	
	public void setKlasse(ArrayList<String> werte) {
		erlaubteKriterien[KLASSE].setWerte(werte);
	}
	
	public void setBierpreis(ArrayList<String> werte) {
		erlaubteKriterien[BIERPREIS].setWerte(werte);
	}
	
}
