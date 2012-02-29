package angebote.typen;

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

	/*public static final int ORT = 1,
			 				KLIMA = 2,
			 				STERNE = 3,
			 				VERPFLEGUNGSART = 4,
			 				BIERPREIS = 5;*/
	
	private Ort ort = null;
	private Klima klima = null;
	private Sterne sterne = null;
	private Verpflegungsart verpflegungsart = null;
	private Bierpreis bierpreis = null;
	
	private Kriterium[] erlaubteKriterien = {ort,klima,sterne,verpflegungsart,bierpreis};
	
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
	}
	
	public void setOrt(String wert) {
		ort.setWert(wert);
	}
	
	public void setKlima(String wert) {
		klima.setWert(wert);
	}
	
	public void setSterne(String wert) {
		sterne.setWert(wert);
	}
	
	public void setVerpflegungsart(String wert) {
		verpflegungsart.setWert(wert);
	}
	
	public void setBierpreis(String wert) {
		bierpreis.setWert(wert);
	}

	public Ort getOrt() {
		return ort;
	}

	public Klima getKlima() {
		return klima;
	}

	public Sterne getSterne() {
		return sterne;
	}

	public Verpflegungsart getVerpflegungsart() {
		return verpflegungsart;
	}

	public Bierpreis getBierpreis() {
		return bierpreis;
	}

	public Kriterium[] getErlaubteKriterien() {
		return erlaubteKriterien;
	}
}
