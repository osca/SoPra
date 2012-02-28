package angebote;

import java.util.ArrayList;

import main.Portal;

import accounts.Anbieter;
import angebote.kriterien.Kriterium;
import angebote.typen.Angebot;

public class Angebotsverarbeitung {
	@SuppressWarnings("null")
	public ArrayList<Angebot> sucheAngebote(Kriterium[] kriterien){
		ArrayList<Angebot> suchErgebnisse = null;
		ArrayList<Angebot> erstellteAngebote = null;
		ArrayList<Anbieter> anbieterListe = Portal.getSingletonObject().getAccountverwaltung().getAnbieter();
		
		for(Anbieter a:anbieterListe){
			erstellteAngebote.addAll(a.getAngebote());
		}
		//Alle Angebote ausgelsen
		
		for(int i=0;i<kriterien.length;i++){
			for(Angebot a:erstellteAngebote){
				int anzKrit=a.getErlaubteKriterien().length;
				for(int j=0;j<anzKrit;j++){
					if(kriterien[i]==a.getErlaubteKriterien()[j]) suchErgebnisse.add(a);
				}
			}
		}
		
		return suchErgebnisse;
	}
}
