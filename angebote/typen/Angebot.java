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
	private int angebotsNummer = 0;
	private int typ = -1;
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
	
	public ArrayList<Kommentar> getKommentare() {
		return kommentare;
	}

	public ArrayList<Buchung> getBuchungen() {
		return buchungen;
	}
	
	public Anbieter getAnbieter() {
		return anbieter;
	}
	
	public int getKapazitaet() {
		return kapazitaet;
	}
	
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
	
	public void addBuchung(Buchung buchung) {
		buchungen.add(buchung);
	}
	
	public void delBuchung(Buchung buchung) {
		buchungen.remove(buchung);
	}
	
	public void addKommentar(Kommentar kommentar) {
		kommentare.add(kommentar);
	}
	
	public void delKommentar(Kommentar kommentar) {
		kommentare.remove(kommentar);
	}

	public Kriterium[] getErlaubteKriterien() {
		return erlaubteKriterien;
	}
	
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
	
	@Override
	public String getIdetifier() {
		return Integer.toString(angebotsNummer);
	}

	@Override
	public String getAdditionalInfo() {
		return convertTypToName(typ);
	}

	@Override
	public String getStatus() {
		//TODO Was kommt hier hin?!
		return "STATUS HERE :)";
	}
	
	@Override
	public String getFullInfo() {
		return beschreibung;
	}
	
	//Prozentuale Plaetzebuchungen
	//Bewertung des Angebots
	//Bewertung des Anbieters
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
