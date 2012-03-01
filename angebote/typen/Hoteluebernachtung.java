package angebote.typen;

import java.util.ArrayList;
import java.util.Date;

import angebote.kriterien.Bierpreis;
import angebote.kriterien.Klima;
import angebote.kriterien.Kriterium;
import angebote.kriterien.Ort;
import angebote.kriterien.Sterne;
import angebote.kriterien.Verpflegungsart;

/**
 * Hoteluebernachtung erbt von Angebot. Enthaelt Ort, Klima, 
 * Sterne, Verpflegungsart und Bierpreis.
 * 
 * @author osca
 */
public class Hoteluebernachtung extends Angebot {

	/*public static final int ORT = 0,
			 				KLIMA = 1,
			 				STERNE = 2,
			 				VERPFLEGUNGSART = 3,
			 				BIERPREIS = 4;*/
	
	private Ort ort = null;
	private Klima klima = null;
	private Sterne sterne = null;
	private Verpflegungsart verpflegungsart = null;
	private Bierpreis bierpreis = null;
	
	private String[] erlaubteKriterien = {Ort.name,Klima.name,Sterne.name,Verpflegungsart.name,Bierpreis.name};
	private ArrayList<Kriterium> kriterien = new ArrayList<Kriterium>();
	
	/**
	 * Konstruktor erzeugt die abstrakte Klasse und zu dem noch die erlaubten Kriterien mit
	 * den entsprechenden Werten
	 * 
	 * @param pname Name
	 * @param pbeschreibung Beschreibung
	 * @param ppreis Preis
	 * @param pdaten Daten
	 * @param port Ort des Hotels
	 * @param pklima Klima an dem Ort des Hotels
	 * @param psterne Sterne des Hotels
	 * @param pverpflegungsart Verpflegungsart
	 * @param pbierpreis Bierpreis
	 */
	public Hoteluebernachtung(String pname, String pbeschreibung, int pkapazitaet, double ppreis, Date[] pdaten, String port, String pklima, String psterne, String pverpflegungsart, String pbierpreis) {
		super(pname, pbeschreibung, Angebot.HOTEL, pkapazitaet, ppreis, pdaten);
		ort = new Ort(port);
		klima = new Klima(pklima);
		sterne = new Sterne(psterne);
		verpflegungsart = new Verpflegungsart(pverpflegungsart);
		bierpreis = new Bierpreis(pbierpreis);
		kriterien.add(ort);
		kriterien.add(klima);
		kriterien.add(sterne);
		kriterien.add(verpflegungsart);
		kriterien.add(bierpreis);
	}
	
	/**
	 * Set Ort
	 * 
	 * @param wert Wert fuer Ort
	 */
	public void setOrt(String wert) {
		ort.setWert(wert);
	}
	
	/**
	 * Set Klima
	 * 
	 * @param wert Wert fuer Klima
	 */
	public void setKlima(String wert) {
		klima.setWert(wert);
	}
	
	/**
	 * Set Sterne
	 * 
	 * @param wert Wert fuer Sterne
	 */
	public void setSterne(String wert) {
		sterne.setWert(wert);
	}
	
	/**
	 * Set Verpflegungsart
	 * 
	 * @param wert Wert fuer Verpflegungsart
	 */
	public void setVerpflegungsart(String wert) {
		verpflegungsart.setWert(wert);
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
	 * Get Ort
	 * 
	 * @return Ort
	 */
	public Ort getOrt() {
		return ort;
	}

	/**
	 * Get Klima
	 * 
	 * @return Klima
	 */
	public Klima getKlima() {
		return klima;
	}

	/**
	 * Get Sterne
	 * 
	 * @return Sterne
	 */
	public Sterne getSterne() {
		return sterne;
	}

	/**
	 * Get Verpflegungsart
	 * 
	 * @return Verpflegungsart
	 */
	public Verpflegungsart getVerpflegungsart() {
		return verpflegungsart;
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
	 * 
	 * @return Erlaubte Kriterienarray
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
