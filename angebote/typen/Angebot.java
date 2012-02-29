package angebote.typen;

import java.util.ArrayList;
import java.util.Date;

import buchungen.Buchung;
import graphic.Listable;

import accounts.Anbieter;
import angebote.Kommentar;
import angebote.kriterien.Kriterium;

/**
 * Abstrakte Angebotsklasse mit Typenflags (Flug, Autovermietung, Ausflug, Hotel)
 * 
 * @author osca
 */
public abstract class Angebot implements Listable, Comparable<Angebot> {
	
	//FLAGS
	public static final int FLUG = 1,
							AUTOVERMIETUNG = 2,
							AUSFLUG = 3,
							HOTEL = 4;
	
	private static int anzahl = 0;
	
	private String name,beschreibung;
	private int angebotsNummer;
	private int typ;
	private boolean auffindbar;
	private double preis;
	private Date[] daten;
	private Anbieter anbieter;
	private int kapazitaet;
	
	private Kriterium[] erlaubteKriterien;
	
	private ArrayList<Kommentar> kommentare = new ArrayList<Kommentar>();
	private ArrayList<Buchung> buchungen = new ArrayList<Buchung>();
	
	
	/**
	 * Konstruktor
	 * 
	 * @param pname Name
	 * @param pbeschreibung Beschreibung
	 * @param ptyp Angebotstyp
	 * @param pkapazitaet Kapazitaet
	 * @param ppreis Preis
	 * @param pdaten Daten des Angebots. Es wird erwartet, dass das Array sortiert ist!
	 */
	public Angebot(String pname, String pbeschreibung, int ptyp, int pkapazitaet ,double ppreis, Date[] pdaten) {
		angebotsNummer = anzahl++;
		name = pname;
		beschreibung = pbeschreibung;
		typ = ptyp;
		kapazitaet = pkapazitaet;
		preis = ppreis;
		daten = pdaten;
	}
	
	/**
	 * Get Beschreibung
	 * 
	 * @return Beschreibung
	 */
	public String getBeschreibung() {
		return beschreibung;
	}

	/**
	 * Set Beschreibung
	 * 
	 * @param beschreibung Beschreibung
	 */
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	/**
	 * Get Typ
	 * 
	 * @return Typnummer
	 */
	public int getTyp() {
		return typ;
	}

	/**
	 * Set Typ
	 * 
	 * @param typ Typ
	 */
	public void setTyp(int typ) {
		this.typ = typ;
	}

	/**
	 * Get Angebotsname
	 * 
	 * @return Name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set Angebotsname
	 * 
	 * @param name Name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Ist das Angebot auffindbar?
	 * 
	 * @return Auffindbar oder nicht
	 */
	public boolean isAuffindbar() {
		return auffindbar;
	}

	/**
	 * Set Auffinbar
	 * 
	 * @param auffindbar Auffindbar?
	 */
	public void setAuffindbar(boolean auffindbar) {
		this.auffindbar = auffindbar;
	}

	/**
	 * Get Preis
	 * 
	 * @return Preis
	 */
	public double getPreis() {
		return preis;
	}

	/**
	 * Set Preis
	 * 
	 * @param preis Preis
	 */
	public void setPreis(double preis) {
		this.preis = preis;
	}

	/**
	 * Get Daten
	 * 
	 * @return Array der Daten des Angebots
	 */
	public Date[] getDaten() {
		return daten;
	}

	/**
	 * Set Daten
	 * 
	 * @param daten Datenarray
	 */
	public void setDaten(Date[] daten) {
		this.daten = daten;
	}

	/**
	 * Get Anzahl aller Angebote
	 * 
	 * @return Anzahl
	 */
	public static int getAnzahl() {
		return anzahl;
	}

	/**
	 * Get Angebotsnummer
	 * 
	 * @return Angebotsnummer
	 */
	public int getAngebotsNummer() {
		return angebotsNummer;
	}
	
	/**
	 * Get Kommentarliste
	 * 
	 * @return Kommentar ArrayList
	 */
	public ArrayList<Kommentar> getKommentare() {
		return kommentare;
	}

	/**
	 * Get Buchungsliste
	 * 
	 * @return Buchung ArrayList
	 */
	public ArrayList<Buchung> getBuchungen() {
		return buchungen;
	}
	
	/**
	 * Get Anbieter
	 * 
	 * @return Anbieter
	 */
	public Anbieter getAnbieter() {
		return anbieter;
	}
	
	/**
	 * Get Kapazitaet
	 * 
	 * @return Kapazitaet
	 */
	public int getKapazitaet() {
		return kapazitaet;
	}
	
	/**
	 * Get Wertung des Angebots ueber die Wertung der Kommentare
	 * 
	 * @return Wertung des Angebots
	 */
	public double getWertung() {
		double result = 0.00;
		
		if(kommentare.size() == 0) {
			return result;
		}
		
		for(Kommentar k:kommentare) {
			result+=k.getBewertung();
		}
		
		return result/kommentare.size();
	}
	
	/**
	 * Fuege Buchung dem Angebot hinzu
	 * 
	 * @param buchung Buchung
	 */
	public void addBuchung(Buchung buchung) {
		buchungen.add(buchung);
	}
	
	/**
	 * Loesche Buchung
	 * 
	 * @param buchung Buchung
	 */
	public void delBuchung(Buchung buchung) {
		buchungen.remove(buchung);
	}
	
	/**
	 * Fuege Kommentar dem Angebot hinzu
	 * 
	 * @param kommentar Kommentar
	 */
	public void addKommentar(Kommentar kommentar) {
		kommentare.add(kommentar);
	}
	
	/**
	 * Loesche Kommentar
	 * 
	 * @param kommentar Kommentar
	 */
	public void delKommentar(Kommentar kommentar) {
		kommentare.remove(kommentar);
	}

	/**
	 * Get erlaubte Kriterien eines Angebots
	 * 
	 * @return erlaubte Kriterien Array
	 */
	public Kriterium[] getErlaubteKriterien() {
		return erlaubteKriterien;
	}
	
	/**
	 * Konvertiere Typnummer in Typnamen
	 * 
	 * @param type Typnummer
	 * @return Typname
	 */
	public String convertTypToName(int type) {
		switch(type) {
			case 1:
				return "Flug";
			case 2:
				return "Autovermietung";
			case 3:
				return "Ausflug";
			case 4:
				return "Hotel";
		}
		return "Not a Type";
	}
	
	/**
	 * Listablemethode
	 */
	@Override
	public String getIdetifier() {
		return Integer.toString(angebotsNummer);
	}

	/**
	 * Listablemethode
	 */
	@Override
	public String getAdditionalInfo() {
		return convertTypToName(typ);
	}

	/**
	 * Listablemethode
	 */
	@Override
	public String getStatus() {
		//TODO Was kommt hier hin?!
		return "STATUS HERE :)";
	}
	
	/**
	 * Listablemethode
	 */
	@Override
	public String getFullInfo() {
		return beschreibung;
	}

	//TODO Gewichtungen muessen spaeter noch angepasst werden
	/**
	 * Vergleicht diese Buchung mit einer weiteren ueber drei unterschiedliche Gewichtungen:
	 * 	- Kapazitaetenanzahl multipliziert mit der Angebotslaenge
	 * 	- Wertung der Anbieter
	 *  - Wertung der Angebote
	 * 
	 * @param pangebot Das zu vergleichende Angebot
	 */
	@Override
	public int compareTo(Angebot pangebot) {
		final int fillGewichtung = 100,
				  anbieterGewichtung = 20,
				  angebotsGewichtung = 40;
		double result = 0.00;
		double fillRatioThis = this.getBuchungen().size()/(this.getKapazitaet()*this.getDaten().length);
		double fillRatioP = pangebot.getBuchungen().size()/(pangebot.getKapazitaet()*pangebot.getDaten().length);
		double abThis = this.getAnbieter().getWertung();
		double abP = pangebot.getAnbieter().getWertung();
		
		result += (fillRatioThis-fillRatioP)*fillGewichtung;
		result += (abThis-abP)*anbieterGewichtung;
		result += (this.getWertung()-pangebot.getWertung())*angebotsGewichtung;
		
		return (int) Math.round(result);
	}
}
