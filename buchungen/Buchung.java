package buchungen;

import graphic.Listable;

import java.text.SimpleDateFormat;
import java.util.Date;

import main.Portal;

import angebote.typen.Angebot;

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
	private boolean stornierungsAnfrage;
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
		stornierungsAnfrage = false;
	}

	/**
	 * @return Anzahl der insgesamt getaetigten Buchungen
	 */
	public static int getAnzahl() {
		return anzahl;
	}

	public static void setAnzahl(int anz){
		anzahl = anz;
	}
	/**
	 * @return Buchungsnummer fuer diese Buchung
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
	 * @param bestaetigt setzt die Bestaetigung fuer die Buchung
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
	 * @pre Berechtigung vorher pruefen
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
	 * @pre Berechtigung muss vorher geprueft werden
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
	
	/**
	 * Hat diese Buchung eine Stornierungsanfrage?
	 * 
	 * @return Ja oder nein
	 */
	public boolean getStornierungsAnfrage() {
		return stornierungsAnfrage;
	}
	
	/**
	 * Setze ob die Buchung eine Stornierungsanfrage hat
	 * 
	 * @param set Ja oder nein
	 */
	public void setStornierungsAnfrage(boolean set) {
		stornierungsAnfrage = set;
	}

	@Override
	public String getIdentifier() {
		return "["+buchungsNummer+"] "+Portal.Buchungsverwaltung().getReferringAngebot(this).getName();
	}

	@Override
	public String getAdditionalInfo() {
		Angebot ang = Portal.Buchungsverwaltung().getReferringAngebot(this);
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		return "("+ang.getAdditionalInfo()+")  :  "+df.format(von)+" - "+df.format(bis);
	}

	@Override
	public String getFullInfo() {
		return Portal.Buchungsverwaltung().getReferringAngebot(this).getFullInfo();
	}

	@Override
	public String getStatus() {
		switch(bestaetigt) {
		case JA : return "		Bestaetigt";
		case NEIN : return "		Abgelehnt";
		case UNBEARBEITET : return "		Unbearbeitet";
		}
		return "";
	}

	@Override
	public int getListableTyp() {
		return Listable.BUCHUNG;
	}

}
