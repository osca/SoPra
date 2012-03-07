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
	public static final int FLUG = 11,
							AUTOVERMIETUNG = 12,
							AUSFLUG = 13,
							HOTEL = 14;

	private static final String _flug = "Flug",
								_autoverm = "Autovermietung",
								_ausflug = "Ausflug",
								_hotel = "Hoteluebernachtung";
	
	private static int anzahl = 0;
	
	private String name,beschreibung;
	private int angebotsNummer;
	private int typ;
	private boolean auffindbar;
	private double preis;
	private Date von,bis,zeitstempel;
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
	 * @param pvon Startdatum des Angebots.
	 * @param pbis Enddatum des Angebots.
	 */
	public Angebot(Anbieter panbieter, String pname, String pbeschreibung, int ptyp, int pkapazitaet, double ppreis, Date pvon, Date pbis) {
		anbieterName = panbieter.getName();
		angebotsNummer = anzahl++;
		name = pname;
		beschreibung = pbeschreibung;
		typ = ptyp;
		auffindbar = true;
		preis = ppreis;
		kapazitaet = pkapazitaet;
		von = pvon;
		bis = pbis;
		zeitstempel = new Date();
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
	 * Setzt den Status auf nicht auffindbar, falls Parameter false, oder auf auffindbar falls true
	 * @param auffindbar
	 */
	public void setAuffindbar(boolean auffindbar){
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
	 * Get Startdatum
	 * 
	 * @return Startdatum
	 */
	public Date getStartdatum() {
		return von;
	}
	
	/**
	 * Get Enddatum
	 * 
	 * @return Enddatum
	 */
	public Date getEnddatum() {
		return bis;
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
	 * Setzt den Angebotszaehler
	 * @param anz
	 */
	public static void setAnzahl(int anz){
		anzahl = anz;
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
		if(kommentare.size() == 0) 
			return result;
		for(Kommentar k:kommentare)
			if(k.getBewertung() != Kommentar.KEINEWERTUNG)
				result+=k.getBewertung();
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
	
	public Date getZeitstempel() {
		return zeitstempel;
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
			case FLUG:
				return _flug;
			case AUTOVERMIETUNG:
				return _autoverm;
			case AUSFLUG:
				return _ausflug;
			case HOTEL:
				return _hotel;
		}
		return "Not a Type";
	}
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
		return "["+Integer.toString(angebotsNummer)+"] "+name;
	}

	/**
	 * Listablemethode
	 */
	@Override
	public String getAdditionalInfo() {
		return convertTypToName(typ)+ " - "+getAnbieterName();
	}

	/**
	 * Listablemethode
	 */
	@Override
	public String getStatus() {
		return (auffindbar)? "(+)": "(-)";
	}
	
	/**
	 * Listablemethode
	 */
	@Override
	public String getFullInfo() {
		return beschreibung;
	}
	
	public int getListableTyp(){
		return Listable.ANGEBOT;
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
		long days = 1;
		if(pangebot.getStartdatum().getTime() != pangebot.getEnddatum().getTime())
			days = (this.getEnddatum().getTime()-this.getStartdatum().getTime())/1000/60/60/24;
		if(days == 0)
			days = 1;
			
		final int fillGewichtung = 100,
				  anbieterGewichtung = 20,
				  angebotsGewichtung = 40;
		double result = 0.00;
		ArrayList<Buchung> buchungenThis = Portal.Buchungsverwaltung().getBuchungen(this),
							buchungenP = Portal.Buchungsverwaltung().getBuchungen(pangebot);
		double fillRatioThis = buchungenThis.size()/(this.getKapazitaet()*days);
		double fillRatioP = buchungenP.size()/(pangebot.getKapazitaet()*days);
		double abThis = Portal.Angebotsverwaltung().getAnbieter(this).getWertung();
		double abP = Portal.Angebotsverwaltung().getAnbieter(pangebot).getWertung();
		
		result += (fillRatioThis-fillRatioP)*fillGewichtung;
		result += (abThis-abP)*anbieterGewichtung;
		result += (this.getWertung()-pangebot.getWertung())*angebotsGewichtung;
		
		return (int) Math.round(result);
	}
	
	/**
	 * Gibt eine Liste aller Flags derAngebotstypen als Integer-ArrayList aus
	 * @return
	 */
	public static ArrayList<Integer> getFlagList(){
		ArrayList<Integer> reslist = new ArrayList<Integer>();
		reslist.add(FLUG);
		reslist.add(AUTOVERMIETUNG);
		reslist.add(AUSFLUG);
		reslist.add(HOTEL);
		return reslist;
	}
}
