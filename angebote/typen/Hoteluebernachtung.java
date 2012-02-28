package angebote.typen;

import java.util.Date;

import angebote.kriterien.Beschreibung;
import angebote.kriterien.Bierpreis;
import angebote.kriterien.Klima;
import angebote.kriterien.Kriterium;
import angebote.kriterien.Ort;
import angebote.kriterien.Plaetze;
import angebote.kriterien.Sterne;
import angebote.kriterien.Verpflegungsart;

public class Hoteluebernachtung extends Angebot {

	private static final int BESCHREIBUNG = 0,
			 				 ORT = 1,
			 				 KLIMA = 2,
			 				 BETTEN = 3,
			 				 STERNE = 4,
			 				 VERPFLEGUNGSART = 5,
			 				 BIERPREIS = 6;
	
	Kriterium[] erlaubteKriterien = new Kriterium[7];
	
	public Hoteluebernachtung(String pname, double ppreis, Date[] pdaten, String beschreibung, String ort, String klima, String betten, String sterne, String verpflegungsart, String bierpreis) {
		super(pname, Angebot.HOTEL, ppreis, pdaten);
		erlaubteKriterien[BESCHREIBUNG] = new Beschreibung(beschreibung);
		erlaubteKriterien[ORT] = new Ort(ort);
		erlaubteKriterien[KLIMA] = new Klima(klima);
		erlaubteKriterien[BETTEN] = new Plaetze(betten);
		erlaubteKriterien[STERNE] = new Sterne(sterne);
		erlaubteKriterien[VERPFLEGUNGSART] = new Verpflegungsart(verpflegungsart);
		erlaubteKriterien[BIERPREIS] = new Bierpreis(bierpreis);
	}
	
	public void setBeschreibung(String wert) {
		erlaubteKriterien[BESCHREIBUNG].setWert(wert);
	}
	
	public void setOrt(String wert) {
		erlaubteKriterien[ORT].setWert(wert);
	}
	
	public void setKlima(String wert) {
		erlaubteKriterien[KLIMA].setWert(wert);
	}
	
	public void setBetten(String wert) {
		erlaubteKriterien[BETTEN].setWert(wert);
	}
	
	public void setSterne(String wert) {
		erlaubteKriterien[STERNE].setWert(wert);
	}
	
	public void setVerpflegungsart(String wert) {
		erlaubteKriterien[VERPFLEGUNGSART].setWert(wert);
	}
	
	public void setBierpreis(String wert) {
		erlaubteKriterien[BIERPREIS].setWert(wert);
	}
	
}
