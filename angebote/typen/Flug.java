package angebote.typen;

import java.util.Date;

import angebote.kriterien.Bierpreis;
import angebote.kriterien.Klasse;
import angebote.kriterien.Kriterium;
import angebote.kriterien.Ort;
import angebote.kriterien.Plaetze;

/**
 * Flug erbt von Angebot. Enthaelt Start, Ziel, Plaetze, Klasse und Bierpreis.
 * 
 * @author osca
 */
public class Flug extends Angebot {

	/*public static final int START = 0,
			 				ZIEL = 1,
			 				PLAETZE = 2,
			 				KLASSE = 3,
			 				BIERPREIS = 4;*/

	private Ort start,ziel = null;
	private Plaetze plaetze = null;
	private Klasse klasse = null;
	private Bierpreis bierpreis = null;
	
	private Kriterium[] erlaubteKriterien = {start,ziel,plaetze,klasse,bierpreis};
	
	/**
	 * Konstruktor erzeugt die abstrakte Klasse und zu dem noch die erlaubten Kriterien mit
	 * den entsprechenden Werten
	 * 
	 * @param pname Name
	 * @param pbeschreibung Beschreibung
	 * @param ppreis Preis
	 * @param pdaten Daten
	 * @param pstart Abflugort
	 * @param pziel Zielort
	 * @param pplaetze Anzahl der Plaetze
	 * @param pklasse Welche Klasse?
	 * @param pbierpreis Bierpreis
	 */
	public Flug(String pname, String pbeschreibung, double ppreis, Date[] pdaten, String pstart, String pziel, String pplaetze, String pklasse, String pbierpreis) {
		super(pname, pbeschreibung, Angebot.FLUG, ppreis, pdaten);
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
	
	public Ort getStart() {
		return start;
	}
	
	public Ort getZiel() {
		return ziel;
	}

	public Plaetze getPlaetze() {
		return plaetze;
	}

	public Bierpreis getBierpreis() {
		return bierpreis;
	}

	public Kriterium[] getErlaubteKriterien() {
		return erlaubteKriterien;
	}
	
}
