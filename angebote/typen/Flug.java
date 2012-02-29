package angebote.typen;

import java.util.Date;

import angebote.kriterien.Bierpreis;
import angebote.kriterien.Klasse;
import angebote.kriterien.Ort;

/**
 * Flug erbt von Angebot. Enthaelt Start, Ziel, Klasse und Bierpreis.
 * 
 * @author osca
 */
public class Flug extends Angebot {

	/*public static final int START = 0,
			 				ZIEL = 1,
			 				KLASSE = 2,
			 				BIERPREIS = 3;*/

	private Ort start,ziel = null;
	private Klasse klasse = null;
	private Bierpreis bierpreis = null;
	private String[] erlaubteKriterien = {Ort.name, Ort.name, Klasse.name, Bierpreis.name};
	
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
	 * @param pklasse Welche Klasse?
	 * @param pbierpreis Bierpreis
	 */
	public Flug(String pname, String pbeschreibung, int pkapazitaet, double ppreis, Date[] pdaten, String pstart, String pziel, String pklasse, String pbierpreis) {
		super(pname, pbeschreibung, Angebot.FLUG, pkapazitaet, ppreis, pdaten);
		start = new Ort(pstart);
		ziel = new Ort(pziel);
		klasse = new Klasse(pklasse);
		bierpreis = new Bierpreis(pbierpreis);
	}
	
	/**
	 * Set Startort
	 * 
	 * @param wert Wert fuer Startort
	 */
	public void setStart(String wert) {
		start.setWert(wert);
	}
	
	/**
	 * Set Zielort
	 * 
	 * @param wert Wert fuer Zielort
	 */
	public void setZiel(String wert) {
		ziel.setWert(wert);
	}
	
	/**
	 * Set Klasse
	 * 
	 * @param wert Wert fuer Klasse
	 */
	public void setKlasse(String wert) {
		klasse.setWert(wert);
	}
	
	/**
	 * Set Bierpreis
	 * 
	 * @param wert Wert fuer Bierpreis
	 */
	public void setBierpreis(String wert) {
		bierpreis.setWert(wert);
	}
	
	/**
	 * Get Startort
	 * 
	 * @return Startort
	 */
	public Ort getStart() {
		return start;
	}
	
	/**
	 * Get Zielort
	 * 
	 * @return Zielort
	 */
	public Ort getZiel() {
		return ziel;
	}

	/**
	 * Get Bierpreis
	 * 
	 * @return Bierpreis
	 */
	public Bierpreis getBierpreis() {
		return bierpreis;
	}

	/**
	 * Get erlaubte Kriterien (Array)
	 */
	public String[] getErlaubteKriterien() {
		return erlaubteKriterien;
	}
	
}
