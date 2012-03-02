package buchungen;

import graphic.Listable;

import java.util.Date;

import main.Portal;

import accounts.Anbieter;
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
	private int angebotsNummer;
	private String kundenName;
	/**
	 * Legt ein Buchungsobjekt an
	 * @param pangebot Angebot zur Referenz
	 * @param pkunde Kunde der die Buchung anfragt
	 * @param pvon Startdatum der Buchung
	 * @param pbis Enddatum der Buchung
	 */
	public Buchung(int pangebotsnummer, String pkundenname, Date pvon, Date pbis) {
		buchungsNummer = anzahl++;
		angebotsNummer = pangebotsnummer;
		kundenName = pkundenname;
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
	public int getAngebotsNummer() {
		return angebotsNummer;
	}
	
	/**
	 * @return buchender Kunde
	 */
	public String getKundenName() {
		return kundenName;
	}

	@Override
	public String getIdentifier() {
		return Integer.toString(buchungsNummer);
	}

	@Override
	public String getAdditionalInfo() {
		Angebot ang = Portal.getSingletonObject().getBuchungsverwaltung().getReferringAngebot(this);
		Anbieter anbieter = Portal.getSingletonObject().getAngebotsverwaltung().getAnbieter(ang);
		return anbieter.getName()+", "+von+" - "+bis+", "+ang.getAdditionalInfo();
	}

	@Override
	public String getFullInfo() {
		return Portal.getSingletonObject().getBuchungsverwaltung().getReferringAngebot(this).getFullInfo();
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
