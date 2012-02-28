package angebote.typen;

import java.util.Date;

import angebote.kriterien.Bierpreis;
import angebote.kriterien.Klima;
import angebote.kriterien.Kriterium;
import angebote.kriterien.Ort;
import angebote.kriterien.Plaetze;
import angebote.kriterien.Sterne;
import angebote.kriterien.Verpflegungsart;

/**
 * Hoteluebernachtung erbt von Angebot. Enthaelt Ort, Klima, Betten, 
 * Sterne Verpflegungsart und Bierpreis.
 * 
 * @author osca
 */
public class Hoteluebernachtung extends Angebot {

	/*public static final int ORT = 1,
			 				KLIMA = 2,
			 				BETTEN = 3,
			 				STERNE = 4,
			 				VERPFLEGUNGSART = 5,
			 				BIERPREIS = 6;*/
	
	private Ort ort = null;
	private Klima klima = null;
	private Plaetze betten = null;
	private Sterne sterne = null;
	private Verpflegungsart verpflegungsart = null;
	private Bierpreis bierpreis = null;
	
	Kriterium[] erlaubteKriterien = {ort,klima,betten,sterne,verpflegungsart,bierpreis};
	
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
	 * @param pbetten Buchbare Betten
	 * @param psterne Sterne des Hotels
	 * @param pverpflegungsart Verpflegungsart
	 * @param pbierpreis Bierpreis
	 */
	public Hoteluebernachtung(String pname, String pbeschreibung, double ppreis, Date[] pdaten, String port, String pklima, String pbetten, String psterne, String pverpflegungsart, String pbierpreis) {
		super(pname, pbeschreibung, Angebot.HOTEL, ppreis, pdaten);
		ort = new Ort(port);
		klima = new Klima(pklima);
		betten = new Plaetze(pbetten);
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
	
	public void setBetten(String wert) {
		betten.setWert(wert);
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

	public Plaetze getBetten() {
		return betten;
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
