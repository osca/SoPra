package angebote.typen;

import java.util.Date;

import angebote.kriterien.Bierpreis;
import angebote.kriterien.Kriterium;
import angebote.kriterien.Ort;

/**
 * Ausfluege erbt von Angebot. Enthaelt Ort und Bierpreis.
 * 
 * @author osca
 */
public class Ausfluege extends Angebot {
	
	/*public static final int ORT = 0,
							BIERPREIS = 1;*/
	
	private Ort ort = null;
	private Bierpreis bierpreis = null;

	private Kriterium[] erlaubteKriterien = {ort,bierpreis};
	
	/**
	 * Konstruktor erzeugt die abstrakte Klasse und zu dem noch die erlaubten Kriterien mit
	 * den entsprechenden Werten
	 * 
	 * @param pname Name
	 * @param pbeschreibung Beschreibung
	 * @param ppreis Preis
	 * @param pdaten Daten
	 * @param port Ort des Ausflugs
	 * @param pplaetze Anzahl der Plaetze
	 * @param pbierpreis Bierpreis
	 */
	public Ausfluege(String pname, String pbeschreibung, int pkapazitaet, double ppreis, Date[] pdaten, String port, String pbierpreis) {
		super(pname, pbeschreibung, Angebot.AUSFLUG, pkapazitaet, ppreis, pdaten);
		ort = new Ort(port); 
		bierpreis = new Bierpreis(pbierpreis);
	}
	
	public void setOrt(String wert) {
		ort.setWert(wert);
	}
	
	public void setBierpreis(String wert) {
		bierpreis.setWert(wert);
	}
	
	public Ort getOrt() {
		return ort;
	}

	public Bierpreis getBierpreis() {
		return bierpreis;
	}
	
	public Kriterium[] getErlaubteKriterien() {
		return erlaubteKriterien;
	}
}
