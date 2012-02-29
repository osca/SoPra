package angebote;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import main.Portal;

import accounts.Anbieter;
import angebote.kriterien.Kriterium;
import angebote.typen.Angebot;

public class Angebotsverarbeitung {
	public ArrayList<Angebot> sucheAngebote(Kriterium[] kriterien){
		ArrayList<Angebot> suchErgebnisse = new ArrayList<Angebot>();
		ArrayList<Angebot> erstellteAngebote = getAktuelleAngebote();
		int treffer=0;
		
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
	
	public ArrayList<Angebot> getTopAngebote(){
		ArrayList<Angebot> aktAngebote = getAktuelleAngebote();
		ArrayList<Angebot> topAngebote = new ArrayList<Angebot>();
		final int numberOfEntries=10;
		
		for(Angebot a:aktAngebote){
			int curBuchungen = a.getBuchungen().size();
			topAngebote.add(a);
			Collections.sort(topAngebote);
			topAngebote.remove(0);
			
		}
		
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
