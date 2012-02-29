package angebote.typen;

import java.util.Date;

import angebote.kriterien.Kriterium;
import angebote.kriterien.Ort;
import angebote.kriterien.Plaetze;

/**
 * Autovermietung erbt von Angebot. Enthaelt Ort und Plaetze.
 * 
 * @author osca
 */
public class Autovermietung extends Angebot {
	
	/*public static final int ORT = 1,
							PLAETZE = 2;*/
	
	private Ort ort = null;
	private Plaetze plaetze = null;
	
	private Kriterium[] erlaubteKriterien = {ort,plaetze};

	/**
	 * Konstruktor erzeugt die abstrakte Klasse und zu dem noch die erlaubten Kriterien mit
	 * den entsprechenden Werten
	 * 
	 * @param pname Name
	 * @param pbeschreibung Beschreibung
	 * @param ppreis Preis
	 * @param pdaten Von wann, bis wann laeuft das Angebot
	 * @param port Von wo wird das Auto abgeholt?
	 * @param pplaetze Wie viele Plaetze soll das Auto haben?
	 */
	public Autovermietung(String pname, String pbeschreibung, int pkapazitaet, double ppreis, Date[] pdaten, String port, String pplaetze) {
		super(pname, pbeschreibung, Angebot.AUTOVERMIETUNG, pkapazitaet, ppreis, pdaten);
		ort = new Ort(port);
		plaetze = new Plaetze(pplaetze);
	}
	
	public void setOrt(String wert) {
		ort.setWert(wert);
	}
	
	public void setPlaetze(String wert) {
		plaetze.setWert(wert);
	}

	public Ort getOrt() {
		return ort;
	}

	public Plaetze getPlaetze() {
		return plaetze;
	}

	public Kriterium[] getErlaubteKriterien() {
		return erlaubteKriterien;
	}
}
