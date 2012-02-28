package buchungen;

import java.util.Date;

import buchungen.Bestaetigung;

/**
 * Entitaetsklasse Buchung mit einem Anzahlcounter, einer Buchungsnummer,
 * Bestaetigung und einem Datum von wann bis wann die Buchung dauert
 * 
 * @author osca
 */
public class Buchung {
	
	private static int anzahl = 0;
	private int buchungsNummer;
	private Bestaetigung bestaetigt;
	private Date von,bis;
	
	public Buchung() {
		buchungsNummer = anzahl++;
	}

	public static int getAnzahl() {
		return anzahl;
	}

	public int getBuchungsnummer() {
		return buchungsNummer;
	}


	public Bestaetigung getBestaetigt() {
		return bestaetigt;
	}

	public void setBestaetigt(Bestaetigung bestaetigt) {
		this.bestaetigt = bestaetigt;
	}

	public Date getVon() {
		return von;
	}

	public void setVon(Date von) {
		this.von = von;
	}

	public Date getBis() {
		return bis;
	}

	public void setBis(Date bis) {
		this.bis = bis;
	}
}
