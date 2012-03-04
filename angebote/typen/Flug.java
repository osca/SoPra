package angebote.typen;

import java.util.ArrayList;
import java.util.Date;

import accounts.Anbieter;
import angebote.kriterien.Bierpreis;
import angebote.kriterien.Klasse;
import angebote.kriterien.Kriterium;
import angebote.kriterien.Ort;

/**
 * Flug erbt von Angebot. Enthaelt Start, Ziel, Klasse und Bierpreis.
 * 
 * @author osca
 */
public class Flug extends Angebot {

	public static final int START = 0,
			 				ZIEL = 1,
			 				KLASSE = 2,
			 				BIERPREIS = 3;

	private Ort start,ziel = null;
	private Klasse klasse = null;
	private Bierpreis bierpreis = null;
	
	public static final String[] erlaubteKriterien = {Ort.name, Ort.name, Klasse.name, Bierpreis.name};
	private ArrayList<Kriterium> kriterien = new ArrayList<Kriterium>();
	
	/**
	 * Konstruktor erzeugt die abstrakte Klasse und zu dem noch die erlaubten Kriterien mit
	 * den entsprechenden Werten
	 * 
	 * @param panb zustaendiger Anbieter
	 * @param pname Name
	 * @param pbeschreibung Beschreibung
	 * @param pkapazitaet Kapazitaet
	 * @param ppreis Preis
	 * @param pdaten Daten
	 * @param pstart Abflugort
	 * @param pziel Zielort
	 * @param pklasse Welche Klasse?
	 * @param pbierpreis Bierpreis
	 */
	public Flug(Anbieter panb, String pname, String pbeschreibung, int pkapazitaet, double ppreis, Date pvon, Date pbis, String pstart, String pziel, String pklasse, String pbierpreis) {
		super(panb, pname, pbeschreibung, Angebot.FLUG, pkapazitaet, ppreis, pvon, pbis);
		start = new Ort(pstart);
		ziel = new Ort(pziel);
		klasse = new Klasse(pklasse);
		bierpreis = new Bierpreis(pbierpreis);
		kriterien.add(start);
		kriterien.add(ziel);
		kriterien.add(klasse);
		kriterien.add(bierpreis);
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
	
	/**
	 * Get Kriterien (ArrayList)
	 */
	public ArrayList<Kriterium> getKriterien() {
		return kriterien;
	}
}
