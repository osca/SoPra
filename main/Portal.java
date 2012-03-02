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
	
	/** Gibt das Portalobjekt zurück
	 * @return PortalSingleton
	 */
/*	public static Portal getSingletonObject(){
		return single;
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
}
