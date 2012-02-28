package angebote.typen;

import java.util.Date;

public class Hoteluebernachtung extends Angebot {

	private static final int BESCHREIBUNG = 0,
			 				 ORT = 1,
			 				 KLIMA = 2,
			 				 BETTEN = 3,
			 				 STERNE = 4,
			 				 VERPFLEGUNGSART = 5,
			 				 BIERPREIS = 6;
	
	public Hoteluebernachtung(String pname, double ppreis, Date[] pdaten) {
		super(pname, Angebot.HOTEL, ppreis, pdaten);
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
