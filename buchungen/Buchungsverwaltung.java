package buchungen;

import graphic.Methods;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import main.Portal;
import accounts.Anbieter;
import accounts.Kunde;
import accounts.LoeschenNichtMoeglichException;
import accounts.Nachricht;
import angebote.typen.Angebot;

/**
 * @author Benjamin
 * @edit Jay, Osca
 */
public class Buchungsverwaltung {
	
	private ArrayList<Buchung> buchungen = new ArrayList<Buchung>();
	
	/**
	 * Erstellt die buchungsverwaltung mit leerer Buchungs-Liste
	 */
	public Buchungsverwaltung() {}
	
	/**
	 * Setzt die uebergebene Buchungs-Liste als die aller zugreifbaren
	 * 
	 * @param buchungen
	 */
	public Buchungsverwaltung(ArrayList<Buchung> buchungen) {
		this.buchungen = buchungen;
	}
	
	/**
	 * Erstellt eine Buchung und weist sie einem Kunden zu.
	 * 
	 * @param kunde			Dem Kunden wird die Buchung zugewiesen.
	 * @param angebot		Gebuchtes Angebot.
	 * @param von			Start (Datum).
	 * @param bis			Ende (Datum).
	 * @throws InvalidDateException 
	 * 
	 * @pre Der Kunde darf nicht null sein muss eingeloggt sein
	 * @pre Das Angebot darf nicht null sein und muss existieren
	 * @pre Enddatum darf nicht vor dem Startdatum sein
	 * @pre Das Startdatum darf nicht nach dem heutigen sein
	 */
	public Buchung createBuchung(Kunde kunde, Angebot angebot, Date von, Date bis) throws InvalidDateException {
		assert kunde != null: "Der Kunde ist null";
		assert Portal.Accountverwaltung().getLoggedIn().equals(kunde): "Der Kunde der bucht ist nicht eingeloggt";
		assert angebot != null: "Das Angebot ist null";
		boolean found = false;
		for(Angebot ang : Portal.Angebotsverwaltung().getAllAngebote())
			if(ang.getAngebotsNummer() == angebot.getAngebotsNummer() )
				found = true;
		assert found: "Das Angebot existiert nicht im System";
		assert !bis.before(von): "Das Enddatum liegt vor dem Startdatum";
		assert !von.before(Methods.getHeuteNullUhr()): "Das Startdatum ist vor dem heutigen Datum";
		
		Buchung buchung = new Buchung(angebot.getAngebotsNummer(), kunde.getName(), von, bis);
		
		buchung.setBis(bis);
		buchung.setVon(von);
		
		kunde.addBuchung(buchung);
		angebot.addBuchung(buchung.getBuchungsNummer());
		buchungen.add(buchung);
		
		Nachricht msg = Portal.Nachrichtenverwaltung().sendeNachricht(kunde, Portal.Angebotsverwaltung().getAnbieter(angebot), "Neue Buchungsanfrage",
				"Der Kunde moechte Ihr Angebot buchen", angebot);
		msg.setBuchungsNummer(buchung.getBuchungsNummer());
		
		return buchung;
	}
	
	/** 
	 * Loescht Entfernt alle Verweise auf das uebergebene Buchungsobjekt.
	 * 
	 * @param b zu loeschende Buchung
	 * 
	 * @pre Die Buchung darf nicht null sein und muss im System sein
	 */
	public void delBuchung(Buchung b) throws LoeschenNichtMoeglichException {
		assert b != null: "Die Buchung ist null";
		boolean found = false;
		for(Buchung buchung: buchungen)
			if(b.getBuchungsNummer() == buchung.getBuchungsNummer())
				found = true;
		assert found: "Die Buchung ist nicht im System vorhanden";
		
		getKunde(b).delBuchung(b);
		getReferringAngebot(b).delBuchung(b.getBuchungsNummer());
		buchungen.remove(b);
		
		int anz = Buchung.getAnzahl();
		anz--;
		Buchung.setAnzahl(anz);
	}
	
	/**
	 * Gibt eine Liste an Buchungen ueber ein Angebot aus
	 * 
	 * @param angebot Angebot
	 * @return ArrayList an Buchungen zu dem Angebot
	 * 
	 * @pre Das Angebot ist nicht null und ist im System vorhanden
	 */
	public ArrayList<Buchung> getBuchungen(Angebot angebot) {
		assert angebot != null: "Das Angebot ist null";
		boolean found = false;
		for(Angebot ang : Portal.Angebotsverwaltung().getAllAngebote())
			if(ang.getAngebotsNummer() == angebot.getAngebotsNummer())
				found = true;
		assert found: "Das Angebot ist nicht im System vorhanden";
		
		ArrayList<Buchung> reslist = new ArrayList<Buchung>();
		
		for (Buchung b : buchungen)
			if(b.getAngebotsNummer() == angebot.getAngebotsNummer())
				reslist.add(b);
		
		Collections.reverse(reslist);
		return reslist;
	}
	
	/**
	 * Gibt alle Buchungen eines Kunden aus.
	 * 
	 * @param kunde			Kunde
	 * @return				Liste seiner Buchungen
	 * 
	 * @pre Der Kunde ist nicht null und muss im System vorhanden sein
	 */
	public ArrayList<Buchung> getBuchungen(Kunde kunde) {
		assert kunde != null: "Der Kunde ist null";
		boolean found = false;
		for(Kunde k : Portal.Accountverwaltung().getKunden())
			if(k.getName().equals(kunde.getName()))
				found = true;
		assert found: "Der Kunde ist nicht im System";
		ArrayList<Buchung> reslist = new ArrayList<Buchung>();
		
		for(Buchung b : buchungen)
			if(kunde.getBuchungsNummern().contains(b.getBuchungsNummer()))
				reslist.add(b);
		
		Collections.reverse(reslist);
		return reslist;
	}

	/**
	 * Gibt alle Buchungen eines Anbieters aus die noch nicht angetreten wurden
	 * 
	 * @param anbieter Anbieter
	 * @return ArrayList seiner Buchungen
	 * 
	 * @pre Der Anbieter darf nicht null sein und muss im System gelistet sein
	 */
	public ArrayList<Buchung> getBuchungen(Anbieter anbieter) {
		assert anbieter != null: "Der Anbieter ist null";
		boolean found = false;
		for(Anbieter anb : Portal.Accountverwaltung().getAnbieter())
			if(anb.getName().equals(anbieter.getName()))
				found = true;
		assert found: "Der Anbieter ist nicht im System";
		
		ArrayList<Buchung> reslist = new ArrayList<Buchung>();
		
		Date heute = Methods.getHeuteNullUhr();
		
		for(Buchung b : buchungen)
			if(anbieter.getAngebotsNummern().contains(b.getAngebotsNummer()) && !b.getVon().before(heute))
				reslist.add(b);
		
		Collections.reverse(reslist);
		return reslist;
	}
	
	/**
	 * Gibt die Anzahl unbearbeiteter Buchungen eines Anbieters zurueck
	 * 
	 * @param anbieter Anbieter
	 * @return Anzahl unbearbeiteter Buchungen
	 * 
	 * @pre Der Anbieter darf nicht null sein und muss im System gelistet sein
	 */
	public int getAnzahlUnbearbeiteterBuchungen(Anbieter anbieter) {
		assert anbieter != null: "Der Anbieter ist null";
		boolean found = false;
		for(Anbieter anb : Portal.Accountverwaltung().getAnbieter())
			if(anb.getName().equals(anbieter.getName()))
				found = true;
		assert found: "Der Anbieter ist nicht im System";
		
		int r = 0;
		
		for(Buchung b : getBuchungen(anbieter))
			if(b.getBestaetigt() == Bestaetigung.UNBEARBEITET)
				r++;
		
		return r;
	}
	
	/**
	 * Gibt die Anzahl unbearbeiteter Buchungen eines Kunden zurueck
	 * 
	 * @param kunde Kunde
	 * @return Anzahl unbearbeiteter Buchungen
	 * 
	 * @pre Der Kunde ist nicht null und muss im System vorhanden sein
	 */
	public int getAnzahlUnbearbeiteterBuchungen(Kunde kunde){
		assert kunde != null: "Der Kunde ist null";
		boolean found = false;
		for(Kunde k : Portal.Accountverwaltung().getKunden())
			if(k.getName().equals(kunde.getName()))
				found = true;
		assert found: "Der Kunde ist nicht im System";
		
		int r = 0;
		
		for(Buchung b : getBuchungen(kunde))
			if(b.getBestaetigt() == Bestaetigung.UNBEARBEITET)
				r++;
		
		return r;
	}
	
	/**
	 * Gibt eine Buchung ueber die Buchungsnummer aus
	 * 
	 * @param id Buchungsnummer
	 * @return Buchung mit der gegebenen Buchungsnummer
	 */
	public Buchung getBuchungByBuchungsNummer(int id) {
		for(Buchung b : getAllBuchungen())
			if(b.getBuchungsNummer() == id)
				return b;
		return null;
	}
	
	/**
	 * Gibt die Liste aller Buchungen aus
	 * 
	 * @return ArrayList aller Buchungen
	 */
	public ArrayList<Buchung> getAllBuchungen(){
		return buchungen;
	}
	
	/**
	 * Getter.
	 * 
	 * @param buchung		zu bestaetigenede Buchung.
	 * @return				Art der Bestaetigung wird ausgegeben.
	 */
	public Bestaetigung getBestaetigt(Buchung buchung) {
		return buchung.getBestaetigt();
	}
	
	/**
	 * Setzt eine Buchung Bestaetigt (enum)
	 * 
	 * @param buchung		zu bestaetigenede Buchung.
	 * @param bestaetigt	Art der Bestaetigung.
	 * 
	 * @pre Die Buchung darf nicht null sein und muss im System sein
	 */
	public void setBestaetigt(Buchung buchung, Bestaetigung bestaetigt) {
		assert buchung != null: "Die Buchung ist null";
		boolean found = false;
		for(Buchung b : getAllBuchungen())
			if(b.getBuchungsNummer() == b.getBuchungsNummer())
				found = true;
		assert found: "Die Buchung ist nicht im System";
		assert buchungen.contains(buchung): "Die Buchung ist im System vorhanden";
		
		buchung.setBestaetigt(bestaetigt);
	}

	/**
	 * Gibt den Kunden zu einer Buchung aus
	 * 
	 * @param buchung Buchung
	 * @return Kunde zu der jeweiligen Buchung
	 */
	public Kunde getKunde(Buchung buchung){
		for (Buchung b : buchungen)
			if(b.getBuchungsNummer() == buchung.getBuchungsNummer())
				return (Kunde) Portal.Accountverwaltung().getAccountByName(buchung.getKundenName());
		return null;
	}
	
	/**
	 * Gibt das Angebot einer Buchung aus
	 * 
	 * @param buchung Buchung
	 * @return Angebot der jeweiligen Buchung
	 */
	public Angebot getReferringAngebot(Buchung buchung){
		return Portal.Angebotsverwaltung().getAngebotByNummer(buchung.getAngebotsNummer());
	}
	
	/**
	 * Wurde das Angebot von einem Kunden gebucht?
	 * 
	 * @param angebot Angebot
	 * @param kunde Kunde
	 * @return Ja oder nein
	 * 
	 * @pre Das Angebot darf nicht null sein und muss existieren
	 * @pre Der Kunde ist nicht null und muss im System vorhanden sein
	 */
	public boolean isBookedByKunde(Angebot angebot, Kunde kunde) {
		assert angebot != null: "Das Angebot ist null";
		boolean found = false;
		for(Angebot ang : Portal.Angebotsverwaltung().getAllAngebote())
			if(ang.getAngebotsNummer() == angebot.getAngebotsNummer())
				found = true;
		assert found: "Das Angebot existiert nicht im System";
		assert kunde != null: "Der Kunde ist null";
		found = false;
		for(Kunde k : Portal.Accountverwaltung().getKunden())
			if(k.getName().equals(kunde.getName()))
				found = true;
		assert found: "Der Kunde ist nicht im System";
		
		for(Buchung b:getBuchungen(angebot)) {
			if(b.getKundenName().equals(kunde.getName()) && b.getBestaetigt().equals(Bestaetigung.JA))
				return true;
		}
		
		return false;
	}
}
