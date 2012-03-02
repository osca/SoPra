package angebote.typen;

import graphic.Listable;

import java.util.ArrayList;
import java.util.Date;

import main.Portal;

import accounts.Anbieter;
import angebote.Kommentar;
import angebote.kriterien.Kriterium;
import buchungen.Buchung;

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
	private String anbieterName;
	private int kapazitaet;
	
	private ArrayList<Kommentar> kommentare = new ArrayList<Kommentar>();
	private ArrayList<Integer> buchungsNummern = new ArrayList<Integer>();
	
	
	/**
	 * Konstruktor
	 * @param panbieter Anbieter, der für das Angebot zustaendig ist
	 * @param pname Name
	 * @param pbeschreibung Beschreibung
	 * @param ptyp Angebotstyp (siehe Flags)
	 * @param pkapazitaet Kapazitaet
	 * @param ppreis Preis
	 * @param pdaten Daten des Angebots. 
	 * @pre Es wird erwartet, dass das Array sortiert ist!
	 */
	public Angebot(Anbieter panbieter, String pname, String pbeschreibung, int ptyp, int pkapazitaet, double ppreis, Date[] pdaten) {
		anbieterName = panbieter.getName();
		angebotsNummer = anzahl++;
		name = pname;
		beschreibung = pbeschreibung;
		typ = ptyp;
		auffindbar = true;
		preis = ppreis;
		kapazitaet = pkapazitaet;
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
	 * Get Typ
	 * 
	 * @return Typnummer
	 */
	public int getTyp() {
		return typ;
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
	 * Ist das Angebot auffindbar?
	 * 
	 * @return Auffindbar oder nicht
	 */
	public boolean isAuffindbar() {
		return auffindbar;
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
	 * Get Daten
	 * 
	 * @return Array der Daten des Angebots
	 */
	public Date[] getDaten() {
		return daten;
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
	public ArrayList<Integer> getBuchungsNummern() {
		return buchungsNummern;
	}
	
	/**
	 * Get Anbieter
	 * 
	 * @return Anbieter
	 */
	public String getAnbieterName() {
		return anbieterName;
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
	public void addBuchung(int buchungsNummer) {
		buchungsNummern.add(buchungsNummer);
	}
	
	/**
	 * Loesche Buchung
	 * 
	 * @param buchung Buchung
	 */
	public void delBuchung(int buchungsNummer) {
		buchungsNummern.remove((Integer) buchungsNummer);
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
	public abstract String[] getErlaubteKriterien();
	
	/**
	 * Get Kriterien eines Angebots
	 * 
	 * @return Kriterien ArrayList
	 */
	public abstract ArrayList<Kriterium> getKriterien();
	
	/**
	 * Konvertiere Typnummer in Typnamen
	 * 
	 * @param type Typnummer
	 * @return Typname
	 */
	public static String convertTypToName(int type) {
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
	private static final String _flug = "Flug",
								_autoverm = "Autovermietung",
								_ausflug = "Ausflug",
								_hotel = "Hotel";
	public static int convertNameToTyp(String name){
		if(name.equals(_flug))
			return FLUG;
		else if(name.equals(_autoverm))
			return AUTOVERMIETUNG;
		else if(name.equals(_ausflug))
			return AUSFLUG;
		else if(name.equals(_hotel))
			return HOTEL;
		return -23;	//this should never ever happen :O
	}
	
	/**
	 * Listablemethode
	 */
	@Override
	public String getIdentifier() {
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

	//TODO Gewichtungen muessen ggf spaeter noch angepasst werden
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
		ArrayList<Buchung> buchungenThis = Portal.Buchungsverwaltung().getBuchungen(this),
							buchungenP = Portal.Buchungsverwaltung().getBuchungen(pangebot);
		double fillRatioThis = buchungenThis.size()/(this.getKapazitaet()*this.getDaten().length);
		double fillRatioP = buchungenP.size()/(pangebot.getKapazitaet()*pangebot.getDaten().length);
		double abThis = Portal.Angebotsverwaltung().getAnbieter(this).getWertung();
		double abP = Portal.Angebotsverwaltung().getAnbieter(pangebot).getWertung();
		
		result += (fillRatioThis-fillRatioP)*fillGewichtung;
		result += (abThis-abP)*anbieterGewichtung;
		result += (this.getWertung()-pangebot.getWertung())*angebotsGewichtung;
		
		return (int) Math.round(result);
	}
	
	public int getListableTyp(){
		return ANGEBOT;
	}
}
