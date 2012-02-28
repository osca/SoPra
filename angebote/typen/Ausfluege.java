package angebote.typen;

import java.util.ArrayList;
import java.util.Date;

import angebote.kriterien.Kriterium;
import angebote.kriterien.Ort;

public class Ausfluege extends Angebot {
	
	private static final int BESCHREIBUNG = 0,
							 ORT = 1,
							 PLAETZE = 2,
							 BIERPREIS = 3;
	
	Kriterium[] erlaubteKriterien = {new Ort(null) };
	
	public Ausfluege(String pname, double ppreis, Date[] pdaten) {
		super(pname, Angebot.AUSFLUG, ppreis, pdaten);
	}
	
	public void setBeschreibung(ArrayList<String> werte) {
		erlaubteKriterien[BESCHREIBUNG].setWerte(werte);
	}
	
}
