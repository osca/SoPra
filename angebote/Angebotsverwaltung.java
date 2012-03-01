package angebote;

import java.util.ArrayList;
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
	
	public void createAngebot(Anbieter anbieter, String name, String beschr, int typ, double preis, int kapazitaet, 
			Date[] daten, String[] krit) {
		switch(typ) {
		case Angebot.AUTOVERMIETUNG:
			createAutovermietung(anbieter, name, beschr, kapazitaet, preis, daten, krit[Autovermietung.ORT]);
			break;
		case Angebot.AUSFLUG:
			createAusflug(anbieter, name, beschr, kapazitaet, preis, daten, krit[Ausflug.ORT], krit[Ausflug.BIERPREIS]);
			break;
		case Angebot.HOTEL:
			createHoteluebernachtung(anbieter, name, beschr, kapazitaet, preis, daten, 
					krit[Hoteluebernachtung.ORT], krit[Hoteluebernachtung.KLIMA], krit[Hoteluebernachtung.STERNE], 
					krit[Hoteluebernachtung.VERPFLEGUNGSART], krit[Hoteluebernachtung.VERPFLEGUNGSART]);
			break;
		case Angebot.FLUG:
			createFlug(anbieter, name, beschr, kapazitaet, preis, daten, krit[Flug.START], krit[Flug.ZIEL], 
						krit[Flug.KLASSE], krit[Flug.BIERPREIS]);
			break;
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
	 * @see Autovermietung
	 */
	public Autovermietung createAutovermietung(Anbieter anbieter, String name, String beschr, int kapaz, double preis, Date[] dates, String ort){
		Autovermietung av = new Autovermietung(anbieter, name, beschr, kapaz, preis, dates, ort);
		anbieter.addAngebot(av);
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
	 */
	public Ausflug createAusflug(Anbieter panb, String pname, String pbeschreibung, int pkapazitaet, double ppreis, 
			Date[] pdaten, String port, String pbierpreis){
		Ausflug af = new Ausflug(panb, pname, pbeschreibung, pkapazitaet, ppreis, pdaten, port, pbierpreis);
		panb.addAngebot(af);
		return af;
	}
	
	public Flug createFlug(Anbieter panb, String pname, String pbeschreibung, int pkapazitaet, double ppreis, 
			Date[] pdaten, String pstart, String pziel, String pklasse, String pbierpreis){
		Flug f = new Flug(panb, pname, pbeschreibung, pkapazitaet, ppreis, pdaten, pstart, pziel, pklasse, pbierpreis);
		panb.addAngebot(f);
		return f;
	}
	
	public Hoteluebernachtung createHoteluebernachtung(Anbieter anb, String name, String beschr, int kapa, double preis, Date[] daten, 
			String ort, String klima, String sterne, String verpf, String bierpr){
		Hoteluebernachtung hu = new Hoteluebernachtung(anb, name, beschr, kapa, preis, daten, ort, klima, sterne, verpf, bierpr);
		anb.addAngebot(hu);
		return hu;
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
}
