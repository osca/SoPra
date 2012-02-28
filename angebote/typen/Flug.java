package angebote.typen;

import java.util.Date;

import angebote.kriterien.Bierpreis;
import angebote.kriterien.Klasse;
import angebote.kriterien.Kriterium;
import angebote.kriterien.Ort;
import angebote.kriterien.Plaetze;

public class Flug extends Angebot {

	/*public static final int START = 0,
			 				ZIEL = 1,
			 				PLAETZE = 2,
			 				KLASSE = 3,
			 				BIERPREIS = 4;*/

	private Ort start = null;
	private Ort ziel = null;
	private Plaetze plaetze = null;
	private Klasse klasse = null;
	private Bierpreis bierpreis = null;
	
	Kriterium[] erlaubteKriterien = {start,ziel,plaetze,klasse,bierpreis};
	
	public Flug(String pname, String beschreibung, double ppreis, Date[] pdaten, String pstart, String pziel, String pplaetze, String pklasse, String pbierpreis) {
		super(pname, beschreibung, Angebot.FLUG, ppreis, pdaten);
		start = new Ort(pstart);
		ziel = new Ort(pziel);
		plaetze = new Plaetze(pplaetze);
		klasse = new Klasse(pklasse);
		bierpreis = new Bierpreis(pbierpreis);
	}
	
	public void setStart(String wert) {
		start.setWert(wert);
	}
	
	public void setZiel(String wert) {
		ziel.setWert(wert);
	}
	
	public void setPlaetze(String wert) {
		plaetze.setWert(wert);
	}
	
	public void setKlasse(String wert) {
		klasse.setWert(wert);
	}
	
	public void setBierpreis(String wert) {
		bierpreis.setWert(wert);
	}
	
}
