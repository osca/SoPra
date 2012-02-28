package buchungen;

import java.util.*;
import accounts.Kunde;
import angebote.typen.Angebot;

/**
 * @author Benjamin
 */
public class Buchungsverwaltung {
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
		Buchung buchung = new Buchung();
		
		if (bis.after(von))
			throw new InvalidDateException();
		
		buchung.setBis(bis);
		buchung.setVon(von);
		
		//TODO: parameter angebot verarbeiten, evtl. in buchung als attribut einfügen, da buchung auf angebot verweist
		
		kunde.addBuchung(buchung);
	}
	
	/**
	 * Cancelt eine Buchung (im Fall von stornieren etc. relevant)
	 * 
	 * @param buchung 		zu beartbeitende Buchung.
	 */
	public void cancelBuchung(Buchung buchung) {
		buchung.setBestaetigt(Bestaetigung.NEIN);
	}
	
	/**
	 * Gibt alle Buchungen eines Kunden aus.
	 * 
	 * @param kunde			DU KUNDE JUNGE!!!!!
	 * @return				Liste seiner Buchungen.
	 */
	public Buchung[] getBuchungen(Kunde kunde) {
		ArrayList<Buchung> buchungen = new ArrayList<Buchung>();
		buchungen.clear();
		
		// TODO: Kunde fehlt buchung-auslesen-methode (sinn??)
		//		 Nicht nur Kunden haben Buchungen, sondern auch Anbieter? Besprechen?
		return null;
	}
	
	/**
	 * Setter.
	 * 
	 * @param buchung		zu bestätigenede Buchung.
	 * @param bestaetigt	Art der Bestätigung.
	 */
	public void setBestaetigt(Buchung buchung, Bestaetigung bestaetigt) {
		buchung.setBestaetigt(bestaetigt);
	}
	
	/**
	 * Getter.
	 * 
	 * @param buchung		zu bestätigenede Buchung.
	 * @return				Art der Bestaetigung wird ausgegeben.
	 */
	public Bestaetigung getBestaetigt(Buchung buchung) {
		return buchung.getBestaetigt();
	}
}
