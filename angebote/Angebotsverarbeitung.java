package angebote;

import graphic.Methods;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import main.Portal;
import angebote.kriterien.Kriterium;
import angebote.typen.Angebot;

/**
 * Angebotsverarbeitung
 * 
 * Haendelt die Filterfunktionen fuer Angebote
 * 
 * @author stephan, osca
 */
public class Angebotsverarbeitung {
	
	public final String KEINNAME		= "";
	public final int KEINEKAPAZITAET	= 0;
	public final double KEINPREIS 	= 0;
	public final Date KEINEDATEN	= new Date(0);
	public final String KEINKRITERIUM = "";
	
	/**
	 * Die Methode geht alle uebergebenen Parameter durch fuer alle Angebote und wenn ein Angebot dabei alle Parameter erfuellen kann wird es der
	 * Liste der Suchergebnisse angefaegt. Es gibt auch immer die Moeglichkeit fuer ein Parameter eine Nichtgewaehlt-Flag zu setzen, diese wird immer
	 * als erfuellt angesehen (Naeheres dazu in den Erklaerungen zu den Parametern).
	 * 
	 * @param typ Der Typ des Angebots in Form eines Integerflags, wenn kein Angebotstyp gewaehlt worden ist, lautet die Flag KEINETYP
	 * @param kapazitaet Die Anzahl der gewuenschten Plaetze als Integer, wenn kein Angebotstyp gewaehlt worden ist, lautet die Flag KEINEKAPAZITAET
	 * @param vonPreis Der minimale Preis, der gezahlt werden soll in Form eines Doubles, 
	 * 			wenn kein Preis gewaehlt worden ist, lautet die Flag KEINPREIS 
	 * @param bisPreis Der maximale Preis, der gezahlt werden soll in Form eines Doubles, 
	 * 			wenn kein Preis gewaehlt worden ist, lautet die Flag KEINPREIS
	 * @param daten Das Array an Daten an denen der Kunde ein Angebot buchen moechte, wenn kein Datum gewaehlt wurde, lautet die Flag KEINEDATEN
	 * @param kriterien Das Array an Kriterien das vom Kunden spezifiziert wurde. Hat der Kunde ein Kriterium nicht gesetzt, lautet die Flag 
	 * 			KEINKRITERIUM (dieses wird innerhalb des Arrays gesetzt).
	 * @return	Die ArrayList an Angeboten, die die genannten Kriterien erfuellen.
	 * @pre Startdatum ist kleinergleich als das Enddatum
	 * @pre Startdatum ist groessergleich dem heutigen Datum
	 * @pre Startpreis ist groesser als der Endpreis
	 */
	public ArrayList<Angebot> sucheAngebote(String name, String anbieter, int typ, int kapazitaet, double vonPreis, double bisPreis, Date von, Date bis, String[] kriterien)
		throws SuchException {
		assert !von.after(bis) || bis.equals(KEINEDATEN): "Startdatum liegt nicht vor Enddatum";
		assert Angebot.getFlagList().contains(typ): "Typ nicht vorhanden oder kein Typ gesetzt";
		assert vonPreis <= bisPreis: "Der Startpreis ist groesser als der Endpreis";
		
		//Heutigen Tag initialisieren
		Date heute = Methods.getHeuteNullUhr();
		
		assert !von.before(heute): "Startdatum liegt vor dem heutigen Datum";
		
		int alleTreffer = 5+kriterien.length;
		int treffer = 0;
		
		ArrayList<Angebot> suchErgebnisse = new ArrayList<Angebot>(); 
		ArrayList<Angebot> erstellteAngebote = getAktuelleAngebote(); 
		
		for(Angebot a:erstellteAngebote) {
			treffer = 0;
			
			if(a.getName().equals(name) || name.equals(KEINNAME)) 
				treffer++;
			
			if(a.getTyp() == typ) {
				if(a.getKapazitaet() >= kapazitaet || kapazitaet == KEINEKAPAZITAET) 
					treffer++;
				
				if(a.getAnbieterName().equals(anbieter) || anbieter.equals(KEINNAME))
					treffer++;
				
				if((a.getPreis() >= vonPreis && a.getPreis() <= bisPreis) || (vonPreis == KEINPREIS && bisPreis == KEINPREIS)) 
					treffer++;
				
				if(von.equals(KEINEDATEN)) 
					treffer++;
				else {
					if(!von.after(a.getEnddatum()) && (!bis.before(a.getStartdatum()) || bis.equals(KEINEDATEN)))
						treffer++;
				}
				ArrayList<Kriterium> kritContainer = a.getKriterien();
				int anzKrit = kriterien.length;
				for(int i=0; i<anzKrit; i++){
					if(kriterien[i].equals("")) 
						treffer++;
					else if(kritContainer.get(i).getWert().equals(kriterien[i])) 
						treffer++;
				}
			}
			if(treffer == alleTreffer)
				suchErgebnisse.add(a);
		}
		
		return suchErgebnisse;
	}

	/**
	 * Die Methode geht alle Angebote durch, das Aktuelle wird jeweils der Liste angefuegt, wenn die Liste mehr als 10 Angebot enthaelt wird sie
	 * sortiert (aufsteigend) und das erste Element entfernt. Die Sortierung wird auf Grund von Angebotsinternen Kriterien vorgenommen.
	 * 
	 * @return Es wird eine ArrayList von 10 Angeboten zurueckgegeben und zwar aufsteigend sortiert. Die Kriterien fuer die Vergleiche sind
	 * in den Angeboten spezifiziert.
	 */
	public ArrayList<Angebot> getTopAngebote(){
		ArrayList<Angebot> aktAngebote = new ArrayList<Angebot>();
		aktAngebote.addAll(getAktuelleAngebote());
		
		final int numberOfEntries=10;
		
		//sortiere mit ueberschriebenen compareTo()
		Collections.sort(aktAngebote);
		//absteigende Ordnung
		Collections.reverse(aktAngebote); 
		//liste trimmen
		if(aktAngebote.size()>numberOfEntries)
			aktAngebote = new ArrayList<Angebot>(aktAngebote.subList(0, numberOfEntries));
		
		return aktAngebote;
	}
	
	/**
	 * Gibt nur abgelaufene Angebote zurueck (Geendet vor heutigem Datum)
	 * 
	 * @return ArrayList an Angeboten
	 */
	public ArrayList<Angebot> getAbgelaufeneAngebote() {
		ArrayList<Angebot> result = new ArrayList<Angebot>();
		Angebotsverwaltung angv = Portal.Angebotsverwaltung();
		//Heutigen Tag initialisieren
		Date heute = Methods.getHeuteNullUhr();
		ArrayList<Angebot> erstellteAngebote = angv.getAllAngebote();
		
		for(Angebot ang:erstellteAngebote) {
			if(ang.getEnddatum().before(heute) || !ang.isAuffindbar()) {
				result.add(ang);
			}
		}
		
		Collections.reverse(result);
		
		return result;
	}
	
	/**
	 * Gibt nur aktuelle Angebote zurueck (Enddatum nach dem heutigen Datum)
	 * 
	 * @return ArrayList an Angeboten
	 */
	public ArrayList<Angebot> getAktuelleAngebote() {
		ArrayList<Angebot> result = new ArrayList<Angebot>();
		Angebotsverwaltung angv = Portal.Angebotsverwaltung();
		//Heutigen Tag initialisieren
		Date heute = Methods.getHeuteNullUhr();
		ArrayList<Angebot> erstellteAngebote = angv.getAllAngebote();
		
		for(Angebot ang:erstellteAngebote) {
			if(!(ang.getEnddatum().before(heute)) && ang.isAuffindbar()) {
				result.add(ang);
			}
		}
		
		Collections.reverse(result);
		
		return result;
	}
}
