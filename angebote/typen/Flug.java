package angebote.typen;

import java.util.Date;

import angebote.kriterien.Beschreibung;
import angebote.kriterien.Bierpreis;
import angebote.kriterien.Kriterium;
import angebote.kriterien.Ort;
import angebote.kriterien.Plaetze;

public class Flug extends Angebot {
	
	private static final int BESCHREIBUNG = 0,
			 				 START = 1,
			 				 ZIEL = 2,
			 				 PLAETZE = 2,
			 				 KLASSE = 3,
			 				 BIERPREIS = 4;

	Kriterium[] erlaubteKriterien = {new Beschreibung(null),new Ort(null),new Ort(null),new Plaetze(null),new ,new Bierpreis(null)};
	
	public Flug(String pname, double ppreis, Date[] pdaten) {
		super(pname, Angebot.FLUG, ppreis, pdaten);
	}
	
}
