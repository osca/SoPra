package buchungen;

import java.util.ArrayList;
import java.util.Date;

import main.Portal;
import accounts.Kunde;
import accounts.LoeschenNichtMoeglichException;
import angebote.typen.Angebot;

/**
 * @author Benjamin
 * @edit Jay
 */
public class Buchungsverwaltung {
	
	private ArrayList<Buchung> buchungen = new ArrayList<Buchung>();
	
	/**
	 * Erstellt eine Buchung und weist sie einem Kunden zu.
	 * 
	 * @param kunde			Dem Kunden wird die Buchung zugewiesen.
	 * @param angebot		Gebuchtes Angebot.
	 * @param von			Start (Datum).
	 * @param bis			Ende (Datum).
	 * @throws InvalidDateException 
	 */
	public void createBuchung(Kunde kunde, Angebot angebot, Date von, Date bis) throws InvalidDateException {
		Buchung buchung = new Buchung(angebot.getAngebotsNummer(), kunde.getName(), von, bis);
		
		if (bis.before(von) || von.before(new Date()))
			throw new InvalidDateException();
		
		buchung.setBis(bis);
		buchung.setVon(von);
		
		kunde.addBuchung(buchung);
		angebot.addBuchung(buchung.getBuchungsnummer());
		buchungen.add(buchung);
	}
	
	/** Loescht Entfernt alle Verweise auf das uebergebene Buchungsobjekt.
	 * @param b zu loeschende Buchung
	 */
	public void delBuchung(Buchung b) throws LoeschenNichtMoeglichException {
		getKunde(b).delBuchung(b);
		getReferringAngebot(b).delBuchung(b.getBuchungsnummer());
		buchungen.remove(b);
	}
	
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
			if(getKunde(b).equals(kunde))
				reslist.add(b);
		return reslist;
	}
	
	public Buchung getBuchungByBuchungsnummer(int id){
		for(Buchung b : getAllBuchungen())
			if(b.getBuchungsnummer() == id)
				return b;
		return null;
	}
	
	
	public ArrayList<Buchung> getAllBuchungen(){
		return buchungen;
	}
	/**
	 * Setter.
	 * 
	 * @param buchung		zu bestaetigenede Buchung.
	 * @param bestaetigt	Art der Bestaetigung.
	 */
	public void setBestaetigt(Buchung buchung, Bestaetigung bestaetigt) {
		buchung.setBestaetigt(bestaetigt);
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
	
	public Kunde getKunde(Buchung buchung){
		for (Buchung b : buchungen)
			if(b.getBuchungsnummer() == buchung.getBuchungsnummer())
				return (Kunde) Portal.getSingletonObject().getAccountverwaltung().getAccountByName(buchung.getKundenName());
		return null;
	}
	
	public Angebot getReferringAngebot(Buchung buchung){
		return Portal.getSingletonObject().getAngebotsverwaltung().getAngebotByAngebotsNummer(buchung.getAngebotsNummer());
	}
}
