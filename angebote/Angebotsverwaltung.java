package angebote;

import main.Portal;
import accounts.Anbieter;
import angebote.kriterien.Kriterium;
import angebote.typen.*;

/**
 * @author Benjamin
 */
public class Angebotsverwaltung {
	/**
	 * 
	 * @param anbieter			Dem Anbieter wird das Angebot zugeordnet.
	 * @param name				Name, den das Angebot haben wird.
	 * @param typ				Typ, entweder: Ausflug, Autovermietung, Hotelübernachtung, Ausflug
	 * @param preis				Preis des Angebots.
	 * @param werte				erlaubte Werte für die Suche
	 * @param kriterien			Kriterien.
	 */
	public void createAngebot(Anbieter anbieter, String name, int typ, double preis, String[] werte, Kriterium[] kriterien) {
		Angebot offer = null;
		
		switch(typ) {
		case Angebot.AUTOVERMIETUNG:
			offer = new Autovermietung(name, null, preis, null, null, null);
		case Angebot.AUSFLUG:
			offer = new Ausfluege(name, null, preis, null, null, null, null);
		case Angebot.HOTEL:
			offer = new Hoteluebernachtung(name, null, preis, null, null, null, null, null, null, null);		// hauptsache alles ist null!
		case Angebot.FLUG:
			offer = new Flug(name, null, preis, null, null, null, null, null, null);
		}
		
		anbieter.addAngebot(offer);
	}
	
	/**
	 * Löscht ein Angebot.
	 * 
	 * @param angebot			das zu löschende Angebot
	 */
	public void delAngebot(Angebot angebot) {
		
	}
	
	/**
	 * Editiert ein Angebot.
	 * 
	 * @param angebot			?
	 */
	public void editAngebot(Angebot angebot) {
		
	}
}
