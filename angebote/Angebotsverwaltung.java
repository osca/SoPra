package angebote;

import java.util.ArrayList;
import java.util.Date;

import main.Portal;
import accounts.Anbieter;
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
 * @author Benjamin, stephan	
 */
public class Angebotsverwaltung {
	
	private ArrayList<Angebot> angebote = new ArrayList<Angebot>();
	
	public Angebotsverwaltung(){}
	public Angebotsverwaltung(ArrayList<Angebot> angebote){
		this.angebote = angebote;
	}
	
	
	/**
	 * Erstellt ein Angebot
	 * @param anbieter
	 * @param name
	 * @param beschr
	 * @param typ
	 * @param preis
	 * @param kapazitaet
	 * @param daten
	 * @param krit
	 * @return
	 * @throws InvalidDateException falls Daten nicht zumindest teilweise in der Zukunft liegen
	 */
	public Angebot createAngebot(Anbieter anbieter, String name, String beschr, int typ, double preis, int kapazitaet, 
			Date von, Date bis, String[] krit) throws InvalidDateException {
		switch(typ) {
		case Angebot.AUTOVERMIETUNG:
			return createAutovermietung(anbieter, name, beschr, kapazitaet, preis, von, bis, krit[Autovermietung.ORT]);
		case Angebot.AUSFLUG:
			return createAusflug(anbieter, name, beschr, kapazitaet, preis, von, bis, krit[Ausflug.ORT], krit[Ausflug.BIERPREIS]);
		case Angebot.HOTEL:
			return createHoteluebernachtung(anbieter, name, beschr, kapazitaet, preis, von, bis, 
					krit[Hoteluebernachtung.ORT], krit[Hoteluebernachtung.KLIMA], krit[Hoteluebernachtung.STERNE], 
					krit[Hoteluebernachtung.VERPFLEGUNGSART], krit[Hoteluebernachtung.BIERPREIS]);
		case Angebot.FLUG:
			return createFlug(anbieter, name, beschr, kapazitaet, preis, von, bis, krit[Flug.START], krit[Flug.ZIEL], 
						krit[Flug.KLASSE], krit[Flug.BIERPREIS]);
		default : 
			return null;
		}
		
	}
	
	/**
	 * Legt ein Autovermietungs-Angebot an
	 * @param anbieter zustaendiger Anbieter
	 * @param name Name der Autovermietung
	 * @param beschr textuelle benutzerdefinierte Beschreibung
	 * @param kapaz Anzahl gleichzeitig zu verleihender Autos
	 * @param preis Preis pro Tag / Buchung
	 * @param dates Array von Daten an denen das Angebot stattfindet
	 * @param ort Ort an dem Das Auto abgeholt werden kann
	 * @throws InvalidDateException 
	 * @see Autovermietung
	 */
	public Autovermietung createAutovermietung(Anbieter anbieter, String name, String beschr, int kapaz, double preis, Date von, Date bis, String ort) throws InvalidDateException{
		Autovermietung av = new Autovermietung(anbieter, name, beschr, kapaz, preis, von, bis, ort);
		if(bis.before(von) || von.before(new Date()))
			throw new InvalidDateException("Ablaufdatum des Angebots bereits ueberschritten");
		
		anbieter.addAngebot(av);
		angebote.add(av);
		return av;
	}
	
	/**
	 * @see Ausfluege
	 * @param panb
	 * @param pname
	 * @param pbeschreibung
	 * @param pkapazitaet
	 * @param ppreis
	 * @param pdaten
	 * @param port
	 * @param pbierpreis
	 * @return
	 * @throws InvalidDateException 
	 */
	public Ausflug createAusflug(Anbieter panb, String pname, String pbeschreibung, int pkapazitaet, double ppreis, 
			Date pvon, Date pbis, String port, String pbierpreis) throws InvalidDateException{
		if(pbis.before(pvon) || pvon.before(new Date()))
				throw new InvalidDateException("Ablaufdatum des Angebots bereits ueberschritten");
			
		Ausflug af = new Ausflug(panb, pname, pbeschreibung, pkapazitaet, ppreis, pvon, pbis, port, pbierpreis);
		panb.addAngebot(af);
		angebote.add(af);
		return af;
	}
	
	public Flug createFlug(Anbieter panb, String pname, String pbeschreibung, int pkapazitaet, double ppreis, 
			Date pvon, Date pbis, String pstart, String pziel, String pklasse, String pbierpreis) throws InvalidDateException{
		if(pbis.before(pvon) || pvon.before(new Date()))
			throw new InvalidDateException("Ablaufdatum des Angebots bereits ueberschritten");
		Flug f = new Flug(panb, pname, pbeschreibung, pkapazitaet, ppreis, pvon, pbis, pstart, pziel, pklasse, pbierpreis);
		panb.addAngebot(f);
		angebote.add(f);
		return f;
	}
	
	public Hoteluebernachtung createHoteluebernachtung(Anbieter anb, String name, String beschr, int kapa, double preis, Date pvon, Date pbis, 
			String ort, String klima, String sterne, String verpf, String bierpr) throws InvalidDateException{
		if(pbis.before(pvon) || pvon.before(new Date()))
			throw new InvalidDateException("Ablaufdatum des Angebots bereits ueberschritten");
		Hoteluebernachtung hu = new Hoteluebernachtung(anb, name, beschr, kapa, preis, pvon, pbis, ort, klima, sterne, verpf, bierpr);
		anb.addAngebot(hu);
		angebote.add(hu);
		return hu;
	}
	
	/**
	 * Loescht ein Angebot eines Anbieters.
	 * 
	 * @param angebot			das zu l�schende Angebot
	 */
	public void delAngebot(Angebot angebot) throws LoeschenNichtMoeglichException{
//		ArrayList<Kommentar> kommentare = angebot.getKommentare();
		ArrayList<Buchung> buchungen = Portal.Buchungsverwaltung().getBuchungen(angebot);
		
		// Erstmal checken, ob offene Buchungen vorhanden sind. Loeschen geht an dieser Stelle noch nicht, da wir erst wissen muessen, ob loeschen erlaubt ist.
		for(int i = 0; i < buchungen.size(); i++) {
			if (buchungen.get(i).getBestaetigt() == Bestaetigung.JA && buchungen.get(i).getBis().after(new Date())) 
				throw new LoeschenNichtMoeglichException("Es existieren noch zu erfuellende Buchungen auf dem Angebot.");
		}
		
		
		// Loeschen ist erlaubt, wir entfernen die Kommentare aus dem Angebot
//		for(int i = 0; i < kommentare.size(); i++) {
//			angebot.delKommentar(kommentare.get(i));
//		}
		
		// Loeschen ist erlaubt, wir entfernen die Buchungen aus dem Angebot
		for(int i = 0; i < buchungen.size(); i++) {
			Portal.Buchungsverwaltung().delBuchung(buchungen.get(i));
		}
		Portal.Nachrichtenverwaltung().delAllNachrichten(angebot);
		// zugriff auf Nachrichten is nicht moeglich
		// das loeschen in den Dateien uebernimmt XStream durch das Streamen der Entititaetsklassen
		
		// Loeschen ist erlaubt, wir entfernen das Angebot vom Anbieter
		getAnbieter(angebot).delAngebot(angebot);
		// und aus der gesamten Liste
		angebote.remove(angebot);
	}
	
	
	/**
	 * Editiert ein Angebot eines Anbieters.
	 * 
	 * @param altes Zu aenderndes Angebot (wird geloescht)
	 * @param neues Neues Angebot mit neuen Daten
	 * @param anbieter Anbieter des Angebots
	 */
	public void editAngebot(Angebot altes, Angebot neues, Anbieter anbieter) {
		try {
			delAngebot(altes);
			angebote.remove(altes);
		} catch (LoeschenNichtMoeglichException e) {
			e.printStackTrace();
		}
		anbieter.addAngebot(neues);
		angebote.add(neues);
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
	
	public Anbieter getAnbieter(Angebot angebot){
		return (Anbieter) Portal.Accountverwaltung().getAccountByName(angebot.getAnbieterName());
	}
	
	public ArrayList<Angebot> getAngebote(Anbieter anb){
		ArrayList<Angebot> result = new ArrayList<Angebot>();
		for(Angebot ang : angebote)
			if(anb.getAngebotsNummern().contains(ang.getAngebotsNummer()))
				result.add(ang);
		return result;
	}

	/**
	 * suche Angebot nach Angebotsnummer (eindeutig)
	 * @param id
	 * @return passendes Angebot oder null, falls nicht gefunden/vorhanden
	 */
	public Angebot getAngebotByNummer(int id){
		for(Angebot a : angebote)
			if(id == a.getAngebotsNummer())
				return a;
		return null;
	}
	
	/**
	 * gibt alle im System befindlichen Angebote zurueck
	 * @return
	 */
	public ArrayList<Angebot> getAllAngebote(){
		return angebote;
	}
	
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
}
