package main;
import accounts.Accountverwaltung;
import accounts.Nachrichtenverwaltung;
import angebote.Angebotsverwaltung;
import buchungen.Buchungsverwaltung;
import angebote.Angebotsverarbeitung;


public class Portal {

	private Datenhaltung daha;
	private Accountverwaltung accverw;
	private Angebotsverwaltung angebverw;
	private Buchungsverwaltung buchverw;
	private Nachrichtenverwaltung nachrverw;
	private Angebotsverarbeitung angebverar;
	private static Portal single = new Portal();
	
	private Portal(){
		daha = new Datenhaltung();
		accverw = new Accountverwaltung(daha.getAnbieter(), daha.getBetreiber(), daha.getKunde());
		angebverw = new Angebotsverwaltung();
		buchverw = new Buchungsverwaltung();
		nachrverw = new Nachrichtenverwaltung();
		angebverar = new Angebotsverarbeitung();
	}
	
	public static Portal getSingletonObject(){
		return single;
	}
	
	public Accountverwaltung getAccountverwaltung(){
		return accverw;
	}

	public Angebotsverwaltung getAngebverw() {
		return angebverw;
	}

	public Buchungsverwaltung getBuchverw() {
		return buchverw;
	}

	public Nachrichtenverwaltung getNachrverw() {
		return nachrverw;
	}

	public Datenhaltung getDaha() {
		return daha;
	}

	public Angebotsverarbeitung getAngebverar() {
		return angebverar;
	}
}
