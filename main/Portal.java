package main;

import accounts.Accountverwaltung;
import accounts.Nachrichtenverwaltung;
import angebote.Angebotsverarbeitung;
import angebote.Angebotsverwaltung;
import buchungen.Buchungsverwaltung;


public class Portal {

	private Accountverwaltung accverw;
	private Angebotsverwaltung angebverw;
	private Buchungsverwaltung buchverw;
	private Nachrichtenverwaltung nachrverw;
	private Angebotsverarbeitung angebverar;
	private static Portal single = new Portal();
	
	private Portal(){
		nachrverw = new Nachrichtenverwaltung();
		accverw = new Accountverwaltung();
		angebverw = new Angebotsverwaltung();
		buchverw = new Buchungsverwaltung();
		angebverar = new Angebotsverarbeitung();
	}
	
	/** gibt das Accountverwaltungsobjekt zurück
	 * @return Accountverwaltungsobjekt
	 */
	public static Accountverwaltung Accountverwaltung(){
		return single.accverw;
	}
	
	/**
	 * @return Angebotsverwaltungsobjekt
	 */
	public static Angebotsverwaltung Angebotsverwaltung() {
		return single.angebverw;
	}

	/**
	 * @return Buchungsverwaltungsobjekt
	 */
	public static Buchungsverwaltung Buchungsverwaltung() {
		return single.buchverw;
	}

	/**
	 * @return Nachrichtenverwaltungsobjekt
	 */
	public static Nachrichtenverwaltung Nachrichtenverwaltung() {
		return single.nachrverw;
	}

	/**
	 * @return Angebotsverarbeitungsobjekt
	 */
	public static Angebotsverarbeitung Angebotsverarbeitung() {
		return single.angebverar;
	}
	
	/**
	 * Stellt den in den Argumenten uebgergebenen Zustand des Systems wieder her
	 * @param accv Accountverwaltung mit Benutzerlisten
	 * @param buchv Buchungsverwaltung mit Buchungslisten
	 * @param nachv Nachrichtenverwaltung mit Nachrichtenlisten
	 */
	public static void recover(Accountverwaltung accv, Buchungsverwaltung buchv, Nachrichtenverwaltung nachv){
		single.accverw = accv;
		single.buchverw = buchv;
		single.nachrverw = nachv;
	}
}
