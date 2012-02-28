package angebote.typen;

import java.util.ArrayList;
import java.util.Date;

import angebote.Kommentar;
import angebote.kriterien.Kriterium;

/**
 * Abstrakte Angebotsklasse mit Typenflags (Flug, Autovermietung, Ausflug, Hotel)
 * 
 * @author osca
 */
public abstract class Angebot implements Listable {
	
	//FLAGS
	public static final int FLUG = 1,
							AUTOVERMIETUNG = 2,
							AUSFLUG = 3,
							HOTEL = 4;
	
	private static int anzahl = 0;
	
	private String name,beschreibung;
	private int angebotsNummer,typ;
	private boolean auffindbar;
	private double preis;
	private Date[] daten;
	Kriterium[] erlaubteKriterien;
	
	ArrayList<Kommentar> kommentare;
	
	/**
	 * Konstruktor
	 * 
	 * @param pname Name
	 * @param pbeschreibung Beschreibung
	 * @param ptyp Angebotstyp
	 * @param ppreis Preis
	 * @param pdaten Daten des Angebots. Es wird erwartet, dass das Array sortiert ist!
	 */
	public Angebot(String pname, String pbeschreibung, int ptyp, double ppreis, Date[] pdaten) {
		angebotsNummer = anzahl++;
		name = pname;
		beschreibung = pbeschreibung;
		typ = ptyp;
		preis = ppreis;
		daten = pdaten;
	}
	
	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	public int getTyp() {
		return typ;
	}

	public void setTyp(int typ) {
		this.typ = typ;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isAuffindbar() {
		return auffindbar;
	}

	public void setAuffindbar(boolean auffindbar) {
		this.auffindbar = auffindbar;
	}

	public double getPreis() {
		return preis;
	}

	public void setPreis(double preis) {
		this.preis = preis;
	}

	public Date[] getDaten() {
		return daten;
	}

	public void setDaten(Date[] daten) {
		this.daten = daten;
	}

	public static int getAnzahl() {
		return anzahl;
	}

	public int getAngebotsNummer() {
		return angebotsNummer;
	}
	
	public abstract Kriterium[] getErlaubteKriterien();
	
}
