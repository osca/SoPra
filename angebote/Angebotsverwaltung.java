package angebote;

import java.util.Vector;

public class Angebotsverwaltung {

	private Vector<Angebot> angebote;
	
	private Vector<Kommentar> getKommentare(Angebot angebot) {
		return angebot.kommentare;
	}
}
