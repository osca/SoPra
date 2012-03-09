package angebote;

import graphic.Methods;

import java.util.ArrayList;
import java.util.Date;
import java.util.Stack;

import main.Portal;
import accounts.Anbieter;
import accounts.Kunde;
import accounts.LoeschenNichtMoeglichException;
import angebote.typen.Angebot;
import angebote.typen.Ausflug;
import angebote.typen.Autovermietung;
import angebote.typen.Flug;
import angebote.typen.Hoteluebernachtung;
import buchungen.Bestaetigung;
import buchungen.Buchung;
import buchungen.InvalidDateException;

/**
 * Angebotsverwaltung
 * 
 * Verwaltet die Angebote mit Standard- und saemtlichen
 * nicht-trivialen Operationen
 * 
 * @author Benjamin, stephan	
 */
public class Angebotsverwaltung {
	
	private ArrayList<Angebot> angebote = new ArrayList<Angebot>();
	
	/**
	 * Konstruiert eine Angebotsverwaltung mit leerer Liste
	 */
	public Angebotsverwaltung(){}
	
	/**
	 * Konstruktor mit einer vorgegebenen Angebotsliste
	 * 
	 * @param angebote ArrayList an Angeboten
	 */
	public Angebotsverwaltung(ArrayList<Angebot> angebote){
		this.angebote = angebote;
	}
	
	/**
	 * Erstellt ein Angebot
	 * 
	 * @param anbieter Anbieter
	 * @param name Name
	 * @param beschr Beschreibung
	 * @param typ Angebotstyp
	 * @param preis Preis
	 * @param kapazitaet Kapazitaet
	 * @param von Startdatum
	 * @param bis Enddatum 
	 * @param krit Array an Kriterien
	 * 
	 * @return Gibt das erstellte Angebot zurueck
	 * 
	 * @throws InvalidDateException falls Daten nicht zumindest teilweise in der Zukunft liegen
	 * 
	 * @pre Preis muss positiv sein
	 * @pre Startdatum darf nicht nach dem Enddatum sein
	 * @pre Das Startdatum darf nicht vor dem heutigen sein
	 */
	public Angebot createAngebot(Anbieter anbieter, String name, String beschr, int typ, double preis, int kapazitaet, 
			Date von, Date bis, String[] krit) throws InvalidDateException {
		assert preis >= 0: "Der Preis ist negativ";
		assert !von.after(bis): "Das Startdatum ist nach dem Enddatum";
		assert !Methods.getHeuteNullUhr().after(von): "Das Startdatum ist vor dem heutigen";
		
		int shift = 0;
		switch(typ) {
			case Angebot.AUTOVERMIETUNG:{
				shift = Autovermietung.LAND;
				return createAutovermietung(anbieter, name, beschr, kapazitaet, preis, von, bis, krit[Autovermietung.LAND-shift], krit[Autovermietung.ORT-shift]);
			}
			case Angebot.AUSFLUG:{
				shift = Ausflug.LAND;
				return createAusflug(anbieter, name, beschr, kapazitaet, preis, von, bis, krit[Ausflug.LAND-shift], krit[Ausflug.ORT-shift], krit[Ausflug.BIERPREIS-shift]);
			}
			case Angebot.HOTEL:{
				shift = Hoteluebernachtung.LAND;
				return createHoteluebernachtung(anbieter, name, beschr, kapazitaet, preis, von, bis, 
						krit[Hoteluebernachtung.LAND-shift], krit[Hoteluebernachtung.ORT-shift], krit[Hoteluebernachtung.KLIMA-shift], krit[Hoteluebernachtung.STERNE-shift], 
						krit[Hoteluebernachtung.VERPFLEGUNGSART-shift], krit[Hoteluebernachtung.BIERPREIS-shift]);
			}
			case Angebot.FLUG:{
				shift = Flug.STARTLAND;
				return createFlug(anbieter, name, beschr, kapazitaet, preis, von, bis, 
						krit[Flug.STARTLAND-shift], krit[Flug.STARTORT-shift], krit[Flug.ZIELLAND-shift], krit[Flug.ZIELORT-shift], 
						krit[Flug.KLASSE-shift], krit[Flug.BIERPREIS-shift]);
			}
			default : 
				return null;
		}
	}
	
	/**
	 * Legt ein Autovermietungs-Angebot an
	 * 
	 * @param anbieter zustaendiger Anbieter
	 * @param name Name der Autovermietung
	 * @param beschr textuelle benutzerdefinierte Beschreibung
	 * @param kapaz Anzahl gleichzeitig zu verleihender Autos
	 * @param preis Preis pro Tag / Buchung
	 * @param dates Array von Daten an denen das Angebot stattfindet
	 * @param ort Ort an dem Das Auto abgeholt werden kann
	 * 
	 * @return Gibt das erstellte Angebot zurueck
	 * 
	 * @throws InvalidDateException 
	 * 
	 * @see Autovermietung
	 */
	public Autovermietung createAutovermietung(Anbieter anbieter, String name, String beschr, int kapaz, double preis, Date von, Date bis, String land, String ort) throws InvalidDateException{
		Autovermietung av = new Autovermietung(anbieter, name, beschr, kapaz, preis, von, bis, land, ort);
		anbieter.addAngebot(av);
		angebote.add(av);
		return av;
	}
	
	/**
	 * Legt einen Ausflug an
	 * 
	 * @param panb Anbieter
	 * @param pname Name
	 * @param pbeschreibung Beschreibung
	 * @param pkapazitaet Kapazitaet
	 * @param ppreis Preis
	 * @param pvon Startdatum
	 * @param pbis Enddatum
	 * @param pland Land
	 * @param port Stadt
	 * @param pbierpreis Bierpreis
	 * 
	 * @return Gibt das erstellte Angebot zurueck
	 * 
	 * @throws InvalidDateException 
	 *
	 * @see Ausfluege
	 */
	public Ausflug createAusflug(Anbieter panb, String pname, String pbeschreibung, int pkapazitaet, double ppreis, 
			Date pvon, Date pbis, String pland, String port, String pbierpreis) throws InvalidDateException {
		Ausflug af = new Ausflug(panb, pname, pbeschreibung, pkapazitaet, ppreis, pvon, pbis, pland, port, pbierpreis);
		panb.addAngebot(af);
		angebote.add(af);
		return af;
	}
	
	/**
	 * Erstellt einen Flug
	 * 
	 * @param panb Anbieter
	 * @param pname Name
	 * @param pbeschreibung Beschreibung
	 * @param pkapazitaet Kapazitaet
	 * @param ppreis Preis
	 * @param pvon Startdatum
	 * @param pbis Enddatum
	 * @param pstartLand Startland
	 * @param pstart Startstadt
	 * @param pzielLand Zielland
	 * @param pziel Zielstadt
	 * @param pklasse Klasse
	 * @param pbierpreis Bierpreis
	 * 
	 * @return Gibt das erstellte Angebot zurueck
	 * 
	 * @throws InvalidDateException
	 */
	public Flug createFlug(Anbieter panb, String pname, String pbeschreibung, int pkapazitaet, double ppreis, 
			Date pvon, Date pbis, String pstartLand, String pstart, String pzielLand, String pziel, String pklasse, String pbierpreis) throws InvalidDateException{
		Flug f = new Flug(panb, pname, pbeschreibung, pkapazitaet, ppreis, pvon, pbis, pstartLand, pstart, pzielLand, pziel, pklasse, pbierpreis);
		panb.addAngebot(f);
		angebote.add(f);
		return f;
	}
	
	/**
	 * Legt eine Hoteluebernachtung an
	 * 
	 * @param anb Anbieter
	 * @param name Name
	 * @param beschr Beschreibung
	 * @param kapa Kapazitaet
	 * @param preis Preis
	 * @param pvon Startdatum
	 * @param pbis Enddatum
	 * @param land Land
	 * @param ort Stadt
	 * @param klima Klima
	 * @param sterne Sterne
	 * @param verpf Verpflegung
	 * @param bierpr Bierpreis
	 * 
	 * @return Gibt das erstellte Angebot zurueck
	 * 
	 * @throws InvalidDateException
	 */
	public Hoteluebernachtung createHoteluebernachtung(Anbieter anb, String name, String beschr, int kapa, double preis, Date pvon, Date pbis, 
			String land, String ort, String klima, String sterne, String verpf, String bierpr) throws InvalidDateException{
		Hoteluebernachtung hu = new Hoteluebernachtung(anb, name, beschr, kapa, preis, pvon, pbis, land, ort, klima, sterne, verpf, bierpr);
		anb.addAngebot(hu);
		angebote.add(hu);
		return hu;
	}
	
	/**
	 * Loescht ein Angebot eines Anbieters.
	 * 
	 * @param angebot Das zu loeschende Angebot
	 */
	public void delAngebot(Angebot angebot) throws LoeschenNichtMoeglichException {
		ArrayList<Buchung> buchungen = Portal.Buchungsverwaltung().getBuchungen(angebot);
		
		// Erstmal checken, ob offene Buchungen vorhanden sind. Loeschen geht an dieser Stelle noch nicht, da wir erst wissen muessen, ob loeschen erlaubt ist.
		for(int i = 0; i < buchungen.size(); i++) {
			if (buchungen.get(i).getBestaetigt() == Bestaetigung.JA && buchungen.get(i).getBis().after(new Date())) 
				throw new LoeschenNichtMoeglichException("Es existieren noch zu erfuellende Buchungen auf dem Angebot.");
		}
		
		// Loeschen ist erlaubt, wir entfernen die Buchungen aus dem Angebot
		for(int i = 0; i < buchungen.size(); i++) 
			Portal.Buchungsverwaltung().delBuchung(buchungen.get(i));
		Portal.Nachrichtenverwaltung().delAllNachrichten(angebot);
		// zugriff auf Nachrichten is nicht moeglich
		// das loeschen in den Dateien uebernimmt XStream durch das Streamen der Entititaetsklassen
		
		// Loeschen ist erlaubt, wir entfernen das Angebot vom Anbieter
		getAnbieter(angebot).delAngebot(angebot);
		// und aus der gesamten Liste
		angebote.remove(angebot);
	}
	
	/**
	 * Gibt die Liste aller Kommentare eines Angebots
	 * 
	 * @param angebot Angebot
	 * @return ArrayList an Kommentaren des Angebots
	 */
	public ArrayList<Kommentar> getKommentare(Angebot angebot) {
		return angebot.getKommentare();
	}
	
	/**
	 * Fuege einem Angebot ein Kommentar hinzu
	 * 
	 * @param angebot Angebot
	 * @param kommentar Kommentar
	 */
	public void addKommentar(Angebot angebot, Kommentar kommentar) {
		angebot.addKommentar(kommentar);
	}
	
	/**
	 * Loesche ein Kommentar eines Angebots
	 * 
	 * @param angebot Angebot
	 * @param kommentar Kommentar
	 */
	public void delKommentar(Angebot angebot, Kommentar kommentar) {
		angebot.delKommentar(kommentar);
	}
	
	/**
	 * Gibt den Anbieter zu einem Angebot aus
	 * 
	 * @param angebot Angebot
	 * @return Anbieter
	 */
	public Anbieter getAnbieter(Angebot angebot){
		return (Anbieter) Portal.Accountverwaltung().getAccountByName(angebot.getAnbieterName());
	}
	
	/**
	 * Gibt Angebote zu einem Anbieter aus
	 * 
	 * @param anb Anbieter
	 * @return ArrayList an Angeboten
	 */
	public ArrayList<Angebot> getAngebote(Anbieter anb){
		ArrayList<Angebot> result = new ArrayList<Angebot>();
		for(Angebot ang : angebote)
			if(anb.getAngebotsNummern().contains(ang.getAngebotsNummer()))
				result.add(ang);
		return result;
	}

	/**
	 * Suche Angebot nach Angebotsnummer (eindeutig)
	 * 
	 * @param id Angebotsnummer
	 * @return passendes Angebot oder null, falls nicht gefunden/vorhanden
	 */
	public Angebot getAngebotByNummer(int id){
		for(Angebot a : angebote)
			if(id == a.getAngebotsNummer())
				return a;
		return null;
	}
	
	/**
	 * Gibt alle im System befindlichen Angebote zurueck
	 * (chronologisch absteigend sortiert)
	 * 
	 * @return ArrayList an allen Angeboten
	 */
	public ArrayList<Angebot> getAllAngebote() {
		ArrayList<Angebot> result = new ArrayList<Angebot>();
		result.addAll(angebote);
		
		int left = 0;
		int right = result.size()-1;
		
		Stack<Integer> stack = new Stack<Integer>();
		while (true) {
			if (left < right) {
				int pivot = result.get(left).getAngebotsNummer();
				int i = left - 1;
				int j = right + 1;

				while (true) {
					do {
						i = i + 1;
					} while (result.get(i).getAngebotsNummer() < pivot);

					do {
						j = j - 1;
					} while (result.get(j).getAngebotsNummer() > pivot);

					if (i >= j) {
						break;
					}
					
					Angebot temp = result.get(i);
					result.set(i, result.get(j));
					result.set(j, temp);
				}
				stack.push(right);
				right = left > (i - 1) ? left : (i - 1);
			} 
			else {
				if (stack.size() == 0) {
					break;
		    }
		    
			left = right + 1;
		    right = stack.pop();
			}
		}
		
		return result;
	}
	
	/**
	 * Gibt Erlaubte Kriterien zu einem Angebotsnamen aus
	 * 
	 * @param name Angebotsname
	 * @return Stringarray an erlaubten Kriterien
	 */
	public static String[] angebotNameToErlaubteKriterien(String name){
		switch(Angebot.convertNameToTyp(name)){
		case Angebot.AUSFLUG : 
			return Ausflug.erlaubteKriterien;
		case Angebot.AUTOVERMIETUNG : 
			return Autovermietung.erlaubteKriterien;
		case Angebot.FLUG : 
			return Flug.erlaubteKriterien;
		case Angebot.HOTEL : 
			return Hoteluebernachtung.erlaubteKriterien;
		default : return null;
		}
	}
	
	/**
	 * Wurde das Angebot schon bewertet/kommentiert?
	 * 
	 * @param angebot Angebot
	 * @param kunde Kunde
	 * @return Ja oder Nein
	 */
	public boolean isCommentedByKunde(Angebot angebot, Kunde kunde) {
		ArrayList<Kommentar> kommentare = getKommentare(angebot);

		for(Kommentar k:kommentare) {
			if(k.getAbsender().equals(kunde.getName()) && k.getBewertung() != Kommentar.KEINEWERTUNG)
				return true;
		}
		
		return false;
	}
	
	/**
	 * Ein Angebot auffindbar
	 * 
	 * @param angebot Angebot
	 * @param auffindbar Boolean
	 */
	public void setAuffindbar(Angebot angebot, boolean auffindbar) {
		if(angebot.getEnddatum().before(Methods.getHeuteNullUhr()))
			throw new IllegalArgumentException("Das Angebot ist bereits abgelaufen");
			
		angebot.setAuffindbar(auffindbar);
	}
}
