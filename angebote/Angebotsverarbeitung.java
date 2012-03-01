package angebote;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import main.Portal;

import accounts.Anbieter;
import angebote.kriterien.Kriterium;
import angebote.typen.Angebot;

public class Angebotsverarbeitung {
	public final static int KEINTYP 		= 0;
	public final static int KEINEKAPAZITAET	= 0;
	public final static double KEINPREIS 	= 0;
	public final static Date[] KEINEDATEN	= null;
	public final static Kriterium KEINKRITERIUM = null;
	/**
	 * Die Methode geht alle übergebenen Parameter durch für alle Angebote und wenn ein Angebot dabei alle Parameter erfüllen kann wird es der
	 * Liste der Suchergebnisse angefügt. Es gibt auch immer die Möglichkeit für ein Parameter eine Nichtgewählt-Flag zu setzen, diese wird immer
	 * als erfüllt angesehen (Näheres dazu in den Erklärungen zu den Parametern).
	 * 
	 * @param typ Der Typ des Angebots in Form eines Integerflags, wenn kein Angebotstyp gewählt worden ist, lautet die Flag KEINETYP
	 * @param kapazitaet Die Anzahl der gewünschten Plätze als Integer, wenn kein Angebotstyp gewählt worden ist, lautet die Flag KEINEKAPAZITAET
	 * @param vonPreis Der minimale Preis, der gezahlt werden soll in Form eines Doubles, 
	 * 			wenn kein Preis gewählt worden ist, lautet die Flag KEINPREIS 
	 * @param bisPreis Der maximale Preis, der gezahlt werden soll in Form eines Doubles, 
	 * 			wenn kein Preis gewählt worden ist, lautet die Flag KEINPREIS
	 * @param daten Das Array an Daten an denen der Kunde ein Angebot buchen möchte, wenn kein Datum gewählt wurde, lautet die Flag KEINEDATEN
	 * @param kriterien [Start/Ort, Ziel, Bierpreis, Klasse, Klima, Sterne, Verpflegungsart]
	 * 			Das Array an Kriterien das vom Kunden spezifiziert wurde. Hat der Kunde ein Kriterium nicht gesetzt oder existiert dies nicht für
	 * 			dieses Angebot, lautet die Flag KEINKRITERIUM (dieses wird innerhalb des Arrays gesetzt). Die Methode erwartet 6 Eintraege im 
	 * 			Array (im Zweifel die Flag setzen), sonst wirft sie eine Exception.
	 * @return	Die ArrayList an Angeboten, die die genannten Kriterien erfüllen.
	 * @throws falscheAnzahlKriterienException Es wird eine Exception geworfen, wenn weniger oder mehr als 6 Kriterien geworfen werden
	 */
	public ArrayList<Angebot> sucheAngebote(int typ, int kapazitaet ,double vonPreis, double bisPreis, Date[] daten, Kriterium[] kriterien)
			throws FalscheAnzahlKriterienException{
		int anzKrit = 7;
		int alleTreffer = 11;
		int treffer=0;
		ArrayList<Angebot> suchErgebnisse = new ArrayList<Angebot>();
		ArrayList<Angebot> erstellteAngebote = getAktuelleAngebote();
		if(kriterien.length!=anzKrit) throw new FalscheAnzahlKriterienException();

		for(Angebot a:erstellteAngebote){
			if(a.getTyp()==typ||typ==KEINTYP) treffer++;
			if(a.getKapazitaet()==kapazitaet||kapazitaet==KEINEKAPAZITAET) treffer++;
			if((a.getPreis()>vonPreis&&a.getPreis()<bisPreis)||
					(vonPreis==KEINPREIS&&bisPreis==KEINPREIS)) treffer++;
			if(daten==KEINEDATEN) treffer++;
			else {
				for(int i=0;i<daten.length;i++){
					if(daten[i].compareTo(a.getDaten()[1])>=0 &&
							daten[i].compareTo(a.getDaten()[a.getDaten().length-1])<=0){
						treffer ++;
						i=daten.length;
					}
				}
			}
			ArrayList<Kriterium> kritContainer = a.getKriterien();
			for(int i=0;i<anzKrit;i++){
				if(kriterien[i]== KEINKRITERIUM) treffer++;
				else if(kritContainer.get(i).getWert()==kriterien[i].getWert()) treffer++;
			}
			if(treffer==alleTreffer) suchErgebnisse.add(a);
		}
		
		return suchErgebnisse;
	}
	/**
	 * Die Methode geht alle Angebote durch, das Aktuelle wird jeweils der Liste angefügt, wenn die Liste mehr als 10 Angebot enthält wird sie
	 * sortiert (aufsteigend) und das erste Element entfernt. Die Sortierung wird auf Grund von Angebotsinternen Kriterien vorgenommen.
	 * 
	 * @return Es wird eine ArrayList von 10 Angeboten zurückgegeben und zwar aufsteigend sortiert. Die Kriterien für die Vergleiche sind
	 * in den Angeboten spezifiziert.
	 */
	public ArrayList<Angebot> getTopAngebote(){
		ArrayList<Angebot> aktAngebote = getAktuelleAngebote();
		ArrayList<Angebot> topAngebote = new ArrayList<Angebot>();
		final int numberOfEntries=10;
		
		for(Angebot a:aktAngebote){
			int curBuchungen = a.getBuchungen().size();
			topAngebote.add(a);
			Collections.sort(topAngebote);
			if(curBuchungen>numberOfEntries) {
				topAngebote.remove(0);
			}
			
		}
		Collections.reverse(topAngebote); 		//absteigende Ordnung
		return topAngebote;
	}
	
	public ArrayList<Angebot> getAbgelaufeneAngebote() {
		ArrayList<Angebot> result = new ArrayList<Angebot>();
		Date now = new Date();
		ArrayList<Angebot> erstellteAngebote = getAllAngebote();
		
		for(Angebot ang:erstellteAngebote) {
			if(ang.getDaten()[ang.getDaten().length-1].before(now)) {
				result.add(ang);
			}
		}
		
		return result;
	}
	public ArrayList<Angebot> getAktuelleAngebote() {
		ArrayList<Angebot> result = new ArrayList<Angebot>();
		Date now = new Date();
		ArrayList<Angebot> erstellteAngebote = getAllAngebote();
		
		for(Angebot ang:erstellteAngebote) {
			if(!(ang.getDaten()[ang.getDaten().length-1].before(now))) {
				result.add(ang);
			}
		}
		
		return result;
	}
	public ArrayList<Angebot> getAllAngebote(){
		ArrayList<Anbieter> anbieterListe = Portal.getSingletonObject().getAccountverwaltung().getAnbieter();
		ArrayList<Angebot> alleAngebote = new ArrayList<Angebot>();
		for(Anbieter a:anbieterListe){
			alleAngebote.addAll(a.getAngebote());
		}
		return alleAngebote;
	}
	//TODO überflüssig?
	public ArrayList<Angebot> getAngebote(Anbieter anbieter){
		ArrayList<Anbieter> anbieterListe = Portal.getSingletonObject().getAccountverwaltung().getAnbieter();
		ArrayList<Angebot> anbieterAngebote=new ArrayList<Angebot>();
		for(Anbieter a:anbieterListe){
			if(a==anbieter){
				anbieterAngebote.addAll(a.getAngebote());
				return anbieterAngebote;
			}
		}
		return anbieterAngebote;
	}
}
