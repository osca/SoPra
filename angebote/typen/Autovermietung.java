package angebote.typen;

import java.util.ArrayList;
import java.util.Date;

import angebote.kriterien.Kriterium;
import angebote.kriterien.Ort;

/**
 * Autovermietung erbt von Angebot. Enthaelt Ort.
 * 
 * @author osca
 */
public class Autovermietung extends Angebot {
	
	/*public static final int ORT = 0;*/
	
	private Ort ort = null;
	
	private String[] erlaubteKriterien = {Ort.name};
	private ArrayList<Kriterium> kriterien = new ArrayList<Kriterium>();

	/**
	 * Konstruktor erzeugt die abstrakte Klasse und zu dem noch die erlaubten Kriterien mit
	 * den entsprechenden Werten
	 * 
	 * @param pname Name
	 * @param pbeschreibung Beschreibung
	 * @param ppreis Preis
	 * @param pdaten Von wann, bis wann laeuft das Angebot
	 * @param port Von wo wird das Auto abgeholt?
	 */
	public Autovermietung(String pname, String pbeschreibung, int pkapazitaet, double ppreis, Date[] pdaten, String port) {
		super(pname, pbeschreibung, Angebot.AUTOVERMIETUNG, pkapazitaet, ppreis, pdaten);
		ort = new Ort(port);
		kriterien.add(ort);
	}
	
	/**
	 * Set Ort
	 * 
	 * @param wert Wert fuer den Ort
	 */
	public void setOrt(String wert) {
		ort.setWert(wert);
	}

	/**
	 * Get Ort
	 * 
	 * @return Ort
	 */
	public Ort getOrt() {
		return ort;
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
