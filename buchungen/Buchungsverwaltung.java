package buchungen;

import java.util.Date;
import accounts.Kunde;
import angebote.typen.Angebot;

/**
 * @author Benjamin
 */
public class Buchungsverwaltung {
	/**
	 * 
	 * @param kunde			Dem Kunden wird die Buchung zugewiesen.
	 * @param angebot		Gebuchtes Angebot.
	 * @param von			Start (Datum).
	 * @param bis			Ende (Datum).
	 */
	public void createBuchung(Kunde kunde, Angebot angebot, Date von, Date bis) {
		Buchung buchung = new Buchung();
		
		if (bis.after(von)) return; //TODO: Exception??
		
		buchung.setBis(bis);
		buchung.setVon(von);
		
		//TODO: angebot verarbeiten, evtl. in buchung als parameter einfügen, da buchung auf angebot verweist
		
		kunde.addBuchung(buchung);
	}
	
	/**
	 * 
	 * @param buchung 		zu beartbeitende Buchung.
	 */
	public void cancelBuchung(Buchung buchung) {
		buchung.setBestaetigt(Bestaetigung.NEIN);					// ?????
	}
	
	/**
	 * 
	 * @param kunde			DU KUNDE JUNGE!!!!!
	 * @return				Liste seiner Buchungen.
	 */
	public Buchung[] getBuchungen(Kunde kunde) {
		Buchung[] buchungen = null;
		
		// TODO: Kunde fehlt buchung-auslesen-methode (sinn??)
		return null;
	}
	
	/**
	 * 
	 * @param buchung		zu bestätigenede Buchung.
	 * @param bestaetigt	Art der Bestätigung.
	 */
	public void setBestaetigt(Buchung buchung, Bestaetigung bestaetigt) {
		buchung.setBestaetigt(bestaetigt);							// ?????
	}
	
	/**
	 * 
	 * @param buchung		zu bestätigenede Buchung.
	 * @return				Art der Bestaetigung wird ausgegeben.
	 */
	public Bestaetigung getBestaetigt(Buchung buchung) {
		return buchung.getBestaetigt();								// ?????
	}
}
