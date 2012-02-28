package angebote;

import java.util.ArrayList;
import java.util.Date;

import main.Portal;

import accounts.Anbieter;
import angebote.kriterien.Kriterium;
import angebote.typen.Angebot;

public class Angebotsverarbeitung {
	public ArrayList<Angebot> sucheAngebote(Kriterium[] kriterien){
		ArrayList<Angebot> suchErgebnisse = new ArrayList<Angebot>();
		ArrayList<Angebot> erstellteAngebote = new ArrayList<Angebot>();
		ArrayList<Anbieter> anbieterListe = Portal.getSingletonObject().getAccountverwaltung().getAnbieter();
		int treffer=0;
		
		for(Anbieter a:anbieterListe){
			erstellteAngebote.addAll(a.getAngebote());
		}
		//Alle Angebote ausgelsen
		
		/*
		 * Für jedes Angebot wird die Anzahl der erlaubten Kriterien abgefragt. Stimmt die Anzahl der gesuchten Kriterien mit denen
		 * der erlaubten Kriterien überein wird weitergeprüft. Stimmen jetzt auch noch die jeweiligen Werte der Kriterien jeweils überein, 
		 * dann wird der TrefferCounter inkrementiert. Ist am Ende die Anzahl der 
		 * Treffer gleich der Anzahl der Kriterien. Soll das Angebot als Suchergebnis aufgeführt werden.*/
		for(Angebot a:erstellteAngebote){
			Kriterium[] kritContainer = a.getErlaubteKriterien();
			int anzKrit=a.getErlaubteKriterien().length;
			if(anzKrit==kriterien.length) {
				for(int i=0;i<anzKrit;i++){
					if(kritContainer[i].getWert()==kriterien[i].getWert()) treffer++;
				}
				if(treffer==anzKrit) suchErgebnisse.add(a);
			}
		}
		
		return suchErgebnisse;
	}
	
	public ArrayList<Angebot> getAbgelaufeneAngebote() {
		ArrayList<Angebot> result = new ArrayList<Angebot>();
		Date now = new Date();
		ArrayList<Anbieter> anbieterListe = Portal.getSingletonObject().getAccountverwaltung().getAnbieter();
		ArrayList<Angebot> erstellteAngebote = new ArrayList<Angebot>();
		
		for(Anbieter a:anbieterListe){
			erstellteAngebote.addAll(a.getAngebote());
		}
		
		for(Angebot ang:erstellteAngebote) {
			if(ang.getDaten()[ang.getDaten().length-1].before(now)) {
				result.add(ang);
			}
		}
		
		return result;
	}
}
