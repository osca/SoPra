package angebote.typen;

import java.util.ArrayList;
import java.util.Date;

import angebote.kriterien.Beschreibung;
import angebote.kriterien.Kriterium;
import angebote.kriterien.Ort;
import angebote.kriterien.Plaetze;

public class Autovermietung extends Angebot {
	
	private static final int BESCHREIBUNG = 0,
			 ORT = 1,
			 PLAETZE = 2;

	
	Kriterium[] erlaubteKriterien = {new Beschreibung(null),new Ort(null),new Plaetze(null)};

	public Autovermietung(String pname, double ppreis, Date[] pdaten) {
		super(pname, Angebot.AUTOVERMIETUNG, ppreis, pdaten);
	}
	
	//SETTERo
	public void setBeschreibung(ArrayList<String> werte) {
		erlaubteKriterien[BESCHREIBUNG].setWerte(werte);
	}
	public void setOrt(ArrayList<String> werte) {
		erlaubteKriterien[ORT].setWerte(werte);
	}
	public void setPlaetze(ArrayList<String> werte) {
		erlaubteKriterien[PLAETZE].setWerte(werte);
	}
	
}
