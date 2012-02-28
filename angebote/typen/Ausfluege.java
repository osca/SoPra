package angebote.typen;

import java.util.ArrayList;
import java.util.Date;

import angebote.kriterien.Beschreibung;
import angebote.kriterien.Bierpreis;
import angebote.kriterien.Kriterium;
import angebote.kriterien.Ort;
import angebote.kriterien.Plaetze;

public class Ausfluege extends Angebot {
	
	private static final int BESCHREIBUNG = 0,
							 ORT = 1,
							 PLAETZE = 2,
							 BIERPREIS = 3;
	
	Kriterium[] erlaubteKriterien = {new Beschreibung(null),new Ort(null),new Plaetze(null),new Bierpreis(null)};
	
	public Ausfluege(String pname, double ppreis, Date[] pdaten) {
		super(pname, Angebot.AUSFLUG, ppreis, pdaten);
	}
	
	public void setBeschreibung(ArrayList<String> werte) {
		erlaubteKriterien[BESCHREIBUNG].setWerte(werte);
	}
	
	public void setOrt(ArrayList<String> werte) {
		erlaubteKriterien[ORT].setWerte(werte);
	}
	
	public void setPlaetze(ArrayList<String> werte) {
		erlaubteKriterien[PLAETZE].setWerte(werte);
	}
	
	public void setBierpreis(ArrayList<String> werte) {
		erlaubteKriterien[BIERPREIS].setWerte(werte);
	}
	
}
