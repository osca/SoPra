package angebote;

import java.util.LinkedList;

public class Angebotsverwaltung {

	private LinkedList<Angebot> angebote;
	
	private LinkedList<Kommentar> getKommentare(Angebot angebot) {
		return angebot.kommentare;
	}
}
