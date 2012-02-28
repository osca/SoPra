package angebote.typen;

import java.util.Date;

import angebote.kriterien.Kriterium;
import angebote.kriterien.Ort;
import angebote.kriterien.Plaetze;

public class Autovermietung extends Angebot {
	
	/*public static final int ORT = 1,
							PLAETZE = 2;*/
	
	private Ort ort = null;
	private Plaetze plaetze = null;
	
	Kriterium[] erlaubteKriterien = {ort,plaetze};

	public Autovermietung(String pname, String pbeschreibung, double ppreis, Date[] pdaten, String port, String pplaetze) {
		super(pname, pbeschreibung, Angebot.AUTOVERMIETUNG, ppreis, pdaten);
		ort = new Ort(port);
		plaetze = new Plaetze(pplaetze);
	}
	
	public void setOrt(String wert) {
		ort.setWert(wert);
	}
	
	public void setPlaetze(String wert) {
		plaetze.setWert(wert);
	}
	
}
