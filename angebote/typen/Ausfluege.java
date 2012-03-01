package angebote.typen;

import java.util.ArrayList;
import java.util.Date;

import accounts.Anbieter;
import angebote.kriterien.Bierpreis;
import angebote.kriterien.Ort;
import angebote.kriterien.Kriterium;

/**
 * Ausfluege erbt von Angebot. Enthaelt Ort und Bierpreis.
 * 
 * @author osca
 */
public class Ausfluege extends Angebot {
	
	public static final int ORT = 0,
							BIERPREIS = 1;
	private Ort ort = null;
	private Bierpreis bierpreis = null;

	private String[] erlaubteKriterien = {Ort.name,Bierpreis.name};
	private ArrayList<Kriterium> kriterien = new ArrayList<Kriterium>();
	
	/**
	 * Konstruktor erzeugt die abstrakte Klasse und zu dem noch die erlaubten Kriterien mit
	 * den entsprechenden Werten
	 * 
	 * @param panb Anbieter des Ausflugs
	 * @param pname Name
	 * @param pbeschreibung Beschreibung
	 * @param pkapazitaet Anzahl der buchbaren Plaetze pro Agebotsausfuehrung
	 * @param ppreis Preis
	 * @param pdaten Daten
	 * @param port Ort des Ausflugs
	 * @param pbierpreis Bierpreis
	 */
	public Ausfluege(Anbieter panb, String pname, String pbeschreibung, int pkapazitaet, double ppreis, Date[] pdaten, String port, String pbierpreis) {
		super(panb, pname, pbeschreibung, Angebot.AUSFLUG, pkapazitaet, ppreis, pdaten);
		ort = new Ort(port); 
		kriterien.add(ort);
		bierpreis = new Bierpreis(pbierpreis);
		kriterien.add(bierpreis);
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
