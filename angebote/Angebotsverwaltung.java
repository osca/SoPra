package angebote;

import java.util.Vector;

import angebote.typen.Angebot;

public class Angebotsverwaltung {

	private Vector<Angebot> angebote;
	
	public Vector<Kommentar> getKommentare(Angebot angebot) {
		return angebot.kommentare;
	}
}
