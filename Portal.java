import accounts.Accountverwaltung;
import accounts.Nachrichtenverwaltung;
import angebote.Angebotsverwaltung;
import buchungen.Buchungsverwaltung;
import angebot.Angebotsverarbeitung;


public class Portal {

	private Accountverwaltung accverw = new Accountverwaltung();
	private Angebotsverwaltung angebverw = new Angebotsverwaltung();
	private Buchungsverwaltung buchverw = new Buchungsverwaltung();
	private Nachrichtenverwaltung nachrverw = new Nachrichtenverwaltung();
	private Datenhaltung daha = new Datenhaltung();
	private Angebotsverarbeitung angebverar = new Angebotsverarbeitung();
	
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
