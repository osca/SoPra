package angebote;

import main.Portal;
import accounts.Anbieter;
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
	//NOTE: String beschr geaddet
	//		int kapazitaet geaddet
	//		Kriterium[] kriterien entfernt
	public void createAngebot(Anbieter anbieter, String name, String beschr, int typ, double preis, int kapazitaet, String[] werte) {
		Angebot offer = null;
		
		// werte array.. was da los? ich geh jetzt einfach mal davon aus, dass die Reihenfolge der Kriterien-Werte im werte[] in der Reihenfolge das Konstruktor stehen.
		switch(typ) {
		case Angebot.AUTOVERMIETUNG:
			offer = new Autovermietung(name, beschr, kapazitaet, preis, null, werte[0], werte[1]);
		case Angebot.AUSFLUG:
			offer = new Ausfluege(name, beschr, kapazitaet, preis, null, werte[0], werte[1], werte[2]);
		case Angebot.HOTEL:
			offer = new Hoteluebernachtung(name, beschr, kapazitaet, preis, null, werte[0], werte[1], werte[2], werte[3], werte[4], werte[5]);
		case Angebot.FLUG:
			offer = new Flug(name, beschr, kapazitaet, preis, null, werte[0], werte[1], werte[2], werte[3], werte[4]);
		}
		
		anbieter.addAngebot(offer);
	}
	
	/**
	 * Löscht ein Angebot eines Anbieters.
	 * 
	 * @param angebot			das zu löschende Angebot
	 */
	public void delAngebot(Angebot angebot) {		
		int nAnbieter = Portal.getSingletonObject().getAccountverwaltung().getAnbieter().size();
		Anbieter[] anbieter = (Anbieter[])Portal.getSingletonObject().getAccountverwaltung().getAnbieter().toArray();
		
		for(int i = 0; i < nAnbieter; i++) {
			if (anbieter[i].getAngebote().contains(angebot)) anbieter[i].delAngebot(angebot);
		}
		// das löschen in den Dateien übernimmt XStream durch das Streamen der Entititätsklassen
	}
	
	/**
	 * Editiert ein Angebot eines Anbieters.
	 * 
	 * @param angebot			?
	 */
	public void editAngebot(Angebot neues, Anbieter anbieter) {
		// TODO: angebot suchen (ist übergebene Angebot 100% anders als das Ursprungsangebot????)???
		//		 neues Angebot erstellen und durch edited ersetzen => .replace(...)?????
		
		anbieter.addAngebot(neues);
	}
}
