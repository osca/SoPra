package angebote;

import java.util.Date;

import main.Portal;

import buchungen.Bestaetigung;
import buchungen.Buchung;
import accounts.Anbieter;
import accounts.LoeschenNichtMoeglichException;
import angebote.typen.*;

/**
 * @author Benjamin, stephan	
 */
public class Angebotsverwaltung {
	/**
	 * Es wird ein spezifisches Angebot erstellt und einem Anbieter zugeordnet. 
	 * 
	 * @param anbieter			Dem Anbieter wird das Angebot zugeordnet.
	 * @param name				Name, den das Angebot haben wird.
	 * @param typ				Typ, entweder: Ausflug, Autovermietung, Hotel�bernachtung, Ausflug
	 * @param preis				Preis des Angebots.
	 * @param werte				erlaubte Werte f�r die Suche
	 * @param kriterien			Kriterien.
	 */
	// NOTE: String beschr geaddet
	//		 int kapazitaet geaddet
	//		 Kriterium[] kriterien entfernt
	public void createAngebot(Anbieter anbieter, String name, String beschr, int typ, double preis, int kapazitaet, String[] werte) {
		Angebot offer = null;
		
		// werte array.. was da los? ich geh jetzt einfach mal davon aus, dass die Reihenfolge der Kriterien-Werte im werte[] in der Reihenfolge das Konstruktor stehen.
		// streng genommen waere es jetzt noch noetig zu checken ob werte.length() = die Laenge der maximialen Anzanl an erlaubten Kriterien entspricht
		switch(typ) {
		case Angebot.AUTOVERMIETUNG:
			offer = new Autovermietung(name, beschr, kapazitaet, preis, null, werte[0]);
		case Angebot.AUSFLUG:
			offer = new Ausfluege(name, beschr, kapazitaet, preis, null, werte[0], werte[1]);
		case Angebot.HOTEL:
			offer = new Hoteluebernachtung(name, beschr, kapazitaet, preis, null, werte[0], werte[1], werte[2], werte[3], werte[4]);
		case Angebot.FLUG:
			offer = new Flug(name, beschr, kapazitaet, preis, null, werte[0], werte[1], werte[2], werte[3]);
		}
		
		anbieter.addAngebot(offer);
	}
	
	/**
	 * Loescht ein Angebot eines Anbieters.
	 * 
	 * @param angebot			das zu l�schende Angebot
	 */
	public void delAngebot(Angebot angebot) throws LoeschenNichtMoeglichException{
		Kommentar[] kommentare = (Kommentar[])angebot.getKommentare().toArray();
		Buchung[] buchungen = (Buchung[])angebot.getBuchungen().toArray();
		
		// Erstmal checken, ob offene buchungen vorhanden sind. Loeschen geht an dieser Stelle noch nicht, da wir erst wissen muessen, ob loeschen erlaubt ist.
		for(int i = 0; i < buchungen.length; i++) {
			if (buchungen[i].getBestaetigt() == Bestaetigung.JA && buchungen[i].getBis().after(new Date())) 
				throw new LoeschenNichtMoeglichException("Es existieren noch zu erfuellende Buchungen auf dem Angebot.");
		}
		
		// Loeschen ist erlaubt, wir entfernen das Angebot vom Anbieter
		angebot.getAnbieter().delAngebot(angebot);
		
		// Loeschen ist erlaubt, wir entfernen die Kommentare aus dem Angebot
		for(int i = 0; i < kommentare.length; i++) {
			angebot.delKommentar(kommentare[i]);
		}
		
		// Loeschen ist erlaubt, wir entfernen die Buchungen aus dem Angebot
		for(int i = 0; i < buchungen.length; i++) {
			angebot.delBuchung(buchungen[i]);
		}
		Portal.getSingletonObject().getNachrichtenverwaltung().delAllNachrichten(angebot);
		// zugriff auf Nachrichten is nicht m�glich
		// das loeschen in den Dateien �bernimmt XStream durch das Streamen der Entititaetsklassen
	}
	
	/**
	 * Editiert ein Angebot eines Anbieters.
	 * 
	 * @param angebot			?
	 */
	// NOTE: Anbieter anbieter geaddet
	public void editAngebot(Angebot neues, Anbieter anbieter) {
		// TODO: angebot suchen (ist �bergebene Angebot 100% anders als das Ursprungsangebot????)???
		//		 neues Angebot erstellen und durch edited ersetzen => .replace(...)?????
		
		anbieter.addAngebot(neues);
	}
}
