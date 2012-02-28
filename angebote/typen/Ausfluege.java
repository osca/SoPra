package angebote.typen;

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
	
	Kriterium[] erlaubteKriterien = new Kriterium[4];
	
	public Ausfluege(String pname, double ppreis, Date[] pdaten, String beschreibung, String ort, String plaetze, String bierpreis) {
		super(pname, Angebot.AUSFLUG, ppreis, pdaten);
		erlaubteKriterien[BESCHREIBUNG] = new Beschreibung(beschreibung);
		erlaubteKriterien[ORT] = new Ort(ort); 
		erlaubteKriterien[PLAETZE] = new Plaetze(plaetze); 
		erlaubteKriterien[BIERPREIS] = new Bierpreis(bierpreis); 
	}
	
	public void setBeschreibung(String wert) {
		erlaubteKriterien[BESCHREIBUNG].setWert(wert);
	}
	
	public void setOrt(String wert) {
		erlaubteKriterien[ORT].setWert(wert);
	}
	
	public void setPlaetze(String wert) {
		erlaubteKriterien[PLAETZE].setWert(wert);
	}
	
	public void setBierpreis(String wert) {
		erlaubteKriterien[BIERPREIS].setWert(wert);
	}
	
}
