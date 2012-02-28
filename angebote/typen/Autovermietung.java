package angebote.typen;

import java.util.Date;

import angebote.kriterien.Beschreibung;
import angebote.kriterien.Kriterium;
import angebote.kriterien.Ort;
import angebote.kriterien.Plaetze;

public class Autovermietung extends Angebot {
	
	private static final int 	BESCHREIBUNG = 0,
								ORT = 1,
								PLAETZE = 2;

	
	Kriterium[] erlaubteKriterien = {new Beschreibung(null),new Ort(null),new Plaetze(null)};

	public Autovermietung(String pname, double ppreis, Date[] pdaten) {
		super(pname, Angebot.AUTOVERMIETUNG, ppreis, pdaten);
	}
	
	//SETTER
	public void setBeschreibung(String wert) {
		erlaubteKriterien[BESCHREIBUNG].setWert(wert);
	}
	public void setOrt(String wert) {
		erlaubteKriterien[ORT].setWert(wert);
	}
	public void setPlaetze(String wert) {
		erlaubteKriterien[PLAETZE].setWert(wert);
	}
	
}
