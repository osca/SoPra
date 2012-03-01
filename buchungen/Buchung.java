package buchungen;

import graphic.Listable;

import java.util.Date;

import accounts.Kunde;
import angebote.typen.Angebot;
import buchungen.Bestaetigung;

/**
 * Entitaetsklasse Buchung mit einem Anzahlcounter, einer Buchungsnummer,
 * Bestaetigung und einem Datum von wann bis wann die Buchung dauert
 * 
 * @author osca
 */
public class Buchung implements Listable {
	
	private static int anzahl = 0;
	private int buchungsNummer;
	private Bestaetigung bestaetigt;
	private Date von,bis;
	private Angebot angebot;
	private Kunde kunde;
	/**
	 * Legt ein Buchungsobjekt an
	 * @param pangebot Angebot zur Referenz
	 * @param pkunde Kunde der die Buchung anfragt
	 * @param pvon Startdatum der Buchung
	 * @param pbis Enddatum der Buchung
	 */
	public Buchung(Angebot pangebot, Kunde pkunde, Date pvon, Date pbis) {
		buchungsNummer = anzahl++;
		angebot = pangebot;
		kunde = pkunde;
		von = pvon;
		bis = pbis;
		bestaetigt = Bestaetigung.UNBEARBEITET;
	}

	/**
	 * @return Anzahl der insgesamt getätigten Buchungen
	 */
	public static int getAnzahl() {
		return anzahl;
	}

	/**
	 * @return Buchungsnummer für diese Buchung
	 */
	public int getBuchungsnummer() {
		return buchungsNummer;
	}

	/**
	 * @return gibt zurueck welchen Status die Buchung hat
	 */
	public Bestaetigung getBestaetigt() {
		return bestaetigt;
	}

	/**
	 * @param bestaetigt setzt die Bestaetigung für die Buchung
	 * @pre es muss vorher sichergestellt sein, dass der Aufrufer berechtigt ist die Buchung zu bestaetigen
	 */
	public void setBestaetigt(Bestaetigung bestaetigt) {
		this.bestaetigt = bestaetigt;
	}

	/**
	 * @return Startdatum
	 */
	public Date getVon() {
		return von;
	}

	/**
	 * @param von neues Startdatum
	 * @pre Berechtigung vorher prüfen
	 */
	public void setVon(Date von) {
		this.von = von;
	}

	/**
	 * @return Enddatum
	 */
	public Date getBis() {
		return bis;
	}

	/**
	 * @param bis neues Enddatum
	 * @pre Berechtigung muss vorher geprüft werden
	 */
	public void setBis(Date bis) {
		this.bis = bis;
	}

	/**
	 * @return referenziertes Angebotsobjekt
	 */
	public Angebot getAngebot() {
		return angebot;
	}
	
	/**
	 * @return buchender Kunde
	 */
	public Kunde getKunde() {
		return kunde;
	}

	@Override
	public String getIdetifier() {
		return Integer.toString(buchungsNummer);
	}

	@Override
	public String getAdditionalInfo() {
		return ""+angebot.getAnbieter().getName()+", "+von+" - "+bis+", "+angebot.getAdditionalInfo();
	}

	@Override
	public String getFullInfo() {
		return angebot.getFullInfo();
	}

	@Override
	public String getStatus() {
		String result = "";
		switch(bestaetigt) {
		case JA : result = "Bestätigt";
		case NEIN : result = "Abgelehnt";
		case UNBEARBEITET : result = "Unbearbeitet";
		}
		return result;
	}

	@Override
	public int getListableTyp() {
		return BUCHUNG;
	}
}
