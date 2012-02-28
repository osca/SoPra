package angebote.typen;

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
			 				 PLAETZE = 3,
			 				 KLASSE = 4,
			 				 BIERPREIS = 5;

	Kriterium[] erlaubteKriterien = new Kriterium[6];
	
	public Flug(String pname, double ppreis, Date[] pdaten, String beschreibung, String start, String ziel, String plaetze, String klasse, String bierpreis) {
		super(pname, Angebot.FLUG, ppreis, pdaten);
		erlaubteKriterien[BESCHREIBUNG] = new Beschreibung(beschreibung);
		erlaubteKriterien[START] = new Ort(start);
		erlaubteKriterien[ZIEL] = new Ort(ziel);
		erlaubteKriterien[PLAETZE] = new Plaetze(plaetze);
		erlaubteKriterien[KLASSE] = new Klasse(klasse);
		erlaubteKriterien[BIERPREIS] = new Bierpreis(bierpreis);
	}
	
	public void setBeschreibung(String wert) {
		erlaubteKriterien[BESCHREIBUNG].setWert(wert);
	}
	
	public void setStart(String wert) {
		erlaubteKriterien[START].setWert(wert);
	}
	
	public void setZiel(String wert) {
		erlaubteKriterien[ZIEL].setWert(wert);
	}
	
	public void setPlaetze(String wert) {
		erlaubteKriterien[PLAETZE].setWert(wert);
	}
	
	public void setKlasse(String wert) {
		erlaubteKriterien[KLASSE].setWert(wert);
	}
	
	public void setBierpreis(String wert) {
		erlaubteKriterien[BIERPREIS].setWert(wert);
	}
	
}
