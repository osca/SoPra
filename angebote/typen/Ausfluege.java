package angebote.typen;

import java.util.Date;

import angebote.kriterien.Bierpreis;
import angebote.kriterien.Kriterium;
import angebote.kriterien.Ort;
import angebote.kriterien.Plaetze;

/**
 * Ausfluege erbt von Angebot. Enthaelt Ort, Plaetze und Bierpreis.
 * 
 * @author osca
 */
public class Ausfluege extends Angebot {
	
	/*public static final int ORT = 0,
							PLAETZE = 1,
							BIERPREIS = 2;*/
	
	private Ort ort = null;
	private Plaetze plaetze = null;
	private Bierpreis bierpreis = null;

	private Kriterium[] erlaubteKriterien = {ort,plaetze,bierpreis};
	
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
	public Ausfluege(String pname, String pbeschreibung, double ppreis, Date[] pdaten, String port, String pplaetze, String pbierpreis) {
		super(pname, pbeschreibung, Angebot.AUSFLUG, ppreis, pdaten);
		ort = new Ort(port); 
		plaetze = new Plaetze(pplaetze); 
		bierpreis = new Bierpreis(pbierpreis);
	}
	
	public void setOrt(String wert) {
		ort.setWert(wert);
	}
	
	public void setPlaetze(String wert) {
		plaetze.setWert(wert);
	}
	
	public void setBierpreis(String wert) {
		bierpreis.setWert(wert);
	}
	
	public Ort getOrt() {
		return ort;
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
