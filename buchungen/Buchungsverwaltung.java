package buchungen;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import main.Portal;
import accounts.Anbieter;
import accounts.Kunde;
import accounts.LoeschenNichtMoeglichException;
import angebote.typen.Angebot;

/**
 * @author Benjamin
 * @edit Jay, Osca
 */
public class Buchungsverwaltung {
	
	private ArrayList<Buchung> buchungen = new ArrayList<Buchung>();
	
	/**
	 * erstellt die buchungsverwaltung mit leerer Buchungs-Liste
	 */
	public Buchungsverwaltung(){}
	
	/**
	 * setzt die uebergebene Buchungs-Liste als die aller zugreifbaren
	 * @param buchungen
	 */
	public Buchungsverwaltung(ArrayList<Buchung> buchungen){
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
	 */
	public Buchung createBuchung(Kunde kunde, Angebot angebot, Date von, Date bis) throws InvalidDateException {
		Buchung buchung = new Buchung(angebot.getAngebotsNummer(), kunde.getName(), von, bis);
		
		Date now = new Date();
		Calendar cal = new GregorianCalendar();
		cal.setTime(now);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		now = cal.getTime();
		
		if (bis.before(von)) 
			throw new InvalidDateException("Das Enddatum ist vor dem Startdatum");
		if (von.before(now))
			throw new InvalidDateException("Das Startdatum ist vor dem heutigen Datum");
		
		buchung.setBis(bis);
		buchung.setVon(von);
		
		kunde.addBuchung(buchung);
		angebot.addBuchung(buchung.getBuchungsnummer());
		buchungen.add(buchung);
		
		Portal.Nachrichtenverwaltung().sendeNachricht(kunde, Portal.Angebotsverwaltung().getAnbieter(angebot), "Neue Buchungsanfrage",
				"Der Kunde moechte Ihr Angebot buchen", angebot);
		
		return buchung;
	}
	
	/** Loescht Entfernt alle Verweise auf das uebergebene Buchungsobjekt.
	 * 
	 * @param b zu loeschende Buchung
	 */
	public void delBuchung(Buchung b) throws LoeschenNichtMoeglichException {
		getKunde(b).delBuchung(b);
		getReferringAngebot(b).delBuchung(b.getBuchungsnummer());
		buchungen.remove(b);
	}
	
	/**
	 * Gibt eine Liste an Buchungen ueber ein Angebot aus
	 * 
	 * @param angebot Angebot
	 * @return ArrayList an Buchungen zu dem Angebot
	 */
	public ArrayList<Buchung> getBuchungen(Angebot angebot){
		ArrayList<Buchung> reslist = new ArrayList<Buchung>();
		for (Buchung b : buchungen)
			if(b.getAngebotsNummer() == angebot.getAngebotsNummer())
				reslist.add(b);
		return reslist;
	}
	
	/**
	 * Gibt alle Buchungen eines Kunden aus.
	 * 
	 * @param kunde			Kunde
	 * @return				Liste seiner Buchungen
	 */
	public ArrayList<Buchung> getBuchungen(Kunde kunde) {
		ArrayList<Buchung> reslist = new ArrayList<Buchung>();
		for(Buchung b : buchungen)
			if(kunde.getBuchungsNummern().contains(b.getBuchungsnummer()))
				reslist.add(b);
		return reslist;
	}

	/**
	 * Gibt alle Buchungen eines Anbieters aus.
	 * 
	 * @param anbieter Anbieter
	 * @return ArrayList seiner Buchungen
	 */
	public ArrayList<Buchung> getBuchungen(Anbieter anbieter){
		ArrayList<Buchung> reslist = new ArrayList<Buchung>();
		for(Buchung b : buchungen)
			if(anbieter.getAngebotsNummern().contains(b.getAngebotsNummer()))
				reslist.add(b);
		return reslist;
	}
	
	/**
	 * Gibt eine Buchung ueber die Buchungsnummer aus
	 * 
	 * @param id Buchungsnummer
	 * @return Buchung mit der gegebenen Buchungsnummer
	 */
	public Buchung getBuchungByBuchungsnummer(int id){
		for(Buchung b : getAllBuchungen())
			if(b.getBuchungsnummer() == id)
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
	 * Gibt den Kunden zu einer Buchung aus
	 * 
	 * @param buchung Buchung
	 * @return Kunde zu der jeweiligen Buchung
	 */
	public Kunde getKunde(Buchung buchung){
		for (Buchung b : buchungen)
			if(b.getBuchungsnummer() == buchung.getBuchungsnummer())
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
	 * Setter.
	 * 
	 * @param buchung		zu bestaetigenede Buchung.
	 * @param bestaetigt	Art der Bestaetigung.
	 */
	public void setBestaetigt(Buchung buchung, Bestaetigung bestaetigt) {
		buchung.setBestaetigt(bestaetigt);
		
		Portal.Nachrichtenverwaltung().sendeNachricht(Portal.Angebotsverwaltung().getAnbieter(getReferringAngebot(buchung)), 
				getKunde(buchung), 
				"Buchung wurde bearbeitet", "Ihre Buchung "+buchung.getBuchungsnummer()+" hat nun den Status "+buchung.getStatus(), 
				getReferringAngebot(buchung));
	}
	
	/**
	 * Wurde das Angebot von einem Kunden gebucht?
	 * 
	 * @param angebot Angebot
	 * @param kunde Kunde
	 * @return Ja oder nein
	 */
	public boolean isBookedByKunde(Angebot angebot, Kunde kunde) {
		for(Buchung b:getBuchungen(kunde)) {
			if(b.getKundenName().equals(kunde.getName()))
				return true;
		}
		
		return false;
	}
}
