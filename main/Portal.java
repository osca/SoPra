package main;

import accounts.Accountverwaltung;
import accounts.Nachrichtenverwaltung;
import angebote.Angebotsverarbeitung;
import angebote.Angebotsverwaltung;
import buchungen.Buchungsverwaltung;


public class Portal {

	private static Accountverwaltung accverw = new Accountverwaltung();
	private static Angebotsverwaltung angebverw = new Angebotsverwaltung();
	private static Buchungsverwaltung buchverw = new Buchungsverwaltung();
	private static Nachrichtenverwaltung nachrverw = new Nachrichtenverwaltung();
	private static Angebotsverarbeitung angebverar = new Angebotsverarbeitung();	//stateless
	
	private Portal(){}	//nicht instanzierbar => Singleton
	
	/** 
	 * @return Accountverwaltungsobjekt
	 */
	public static Accountverwaltung Accountverwaltung(){
		return accverw;
	}
	
	/**
	 * @return Angebotsverwaltungsobjekt
	 */
	public static Angebotsverwaltung Angebotsverwaltung() {
		return angebverw;
	}

	/**
	 * @return Buchungsverwaltungsobjekt
	 */
	public static Buchungsverwaltung Buchungsverwaltung() {
		return buchverw;
	}

	/**
	 * @return Nachrichtenverwaltungsobjekt
	 */
	public static Nachrichtenverwaltung Nachrichtenverwaltung() {
		return nachrverw;
	}

	/**
	 * @return Angebotsverarbeitungsobjekt
	 */
	public static Angebotsverarbeitung Angebotsverarbeitung() {
		return angebverar;
	}
	
	/**
	 * Stellt den in den Argumenten uebgergebenen Zustand des Systems wieder her
	 * @param accv Accountverwaltung mit Benutzerlisten
	 * @param angebotsverwaltung 
	 * @param buchv Buchungsverwaltung mit Buchungslisten
	 * @param nachv Nachrichtenverwaltung mit Nachrichtenlisten
	 */
	public static void recover(Accountverwaltung accv, Angebotsverwaltung angvw, Buchungsverwaltung buchv, Nachrichtenverwaltung nachv){
		accverw = accv;
		angebverw = angvw;
		buchverw = buchv;
		nachrverw = nachv;
	}
}
