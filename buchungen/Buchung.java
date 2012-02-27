package buchungen;

import buchungen.Bestaetigung;

public class Buchung {
	
	private static int anzahl;
	private int buchungsnummer;
	private Bestaetigung bestaetigt;
	
	public Buchung() {
		bestaetigt = Bestaetigung.JA;
	}
}
