package angebote.typen;

import java.util.ArrayList;
import java.util.Date;

import angebote.Kommentar;
import angebote.kriterien.Kriterium;

public abstract class Angebot {
	
	//FLAGS
	public static final int FLUG = 1,
							AUTOVERMIETUNG = 2,
							AUSFLUG = 3,
							HOTEL = 4;
	
	private static int anzahl = 0;
	
	private String name;
	private int angebotsNummer,typ;
	private boolean auffindbar;
	private double preis;
	private Date[] daten;
	Kriterium[] erlaubteKriterien;
	
	ArrayList<Kommentar> kommentare;
	
	public Angebot(String pname, int ptyp, double ppreis, Date[] pdaten) {
		angebotsNummer = 1000+anzahl++;
		name = pname;
		typ = ptyp;
		preis = ppreis;
		daten = pdaten;
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
	
}
