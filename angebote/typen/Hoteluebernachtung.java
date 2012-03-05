package angebote.typen;

import java.util.ArrayList;
import java.util.Date;

import accounts.Anbieter;
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

	public static final int ORT = 40,
			 				KLIMA = 41,
			 				STERNE = 42,
			 				VERPFLEGUNGSART = 43,
			 				BIERPREIS = 44;
	
	private Ort ort = null;
	private Klima klima = null;
	private Sterne sterne = null;
	private Verpflegungsart verpflegungsart = null;
	private Bierpreis bierpreis = null;
	
	public static final String[] erlaubteKriterien = {Ort.name,Klima.name,Sterne.name,Verpflegungsart.name,Bierpreis.name};
	private ArrayList<Kriterium> kriterien = new ArrayList<Kriterium>();
	
	/**
	 * Konstruktor erzeugt die abstrakte Klasse und zu dem noch die erlaubten Kriterien mit
	 * den entsprechenden Werten
	 * 
	 * @param panbieter Hotelbesitzer
	 * @param pname Name
	 * @param pbeschreibung Beschreibung
	 * @param pkapazitaet Kapazitaet
	 * @param ppreis Preis
	 * @param pdaten Daten
	 * @param port Ort des Hotels
	 * @param pklima Klima an dem Ort des Hotels
	 * @param psterne Sterne des Hotels
	 * @param pverpflegungsart Verpflegungsart
	 * @param pbierpreis Bierpreis
	 */
	public Hoteluebernachtung(Anbieter panbieter, String pname, String pbeschreibung, int pkapazitaet, double ppreis, 
			Date pvon, Date pbis, String port, String pklima, String psterne, String pverpflegungsart, String pbierpreis) {
		super(panbieter, pname, pbeschreibung, Angebot.HOTEL, pkapazitaet, ppreis, pvon, pbis);
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
