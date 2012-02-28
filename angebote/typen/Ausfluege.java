package angebote.typen;

import java.util.Date;

import angebote.kriterien.Bierpreis;
import angebote.kriterien.Kriterium;
import angebote.kriterien.Ort;
import angebote.kriterien.Plaetze;

public class Ausfluege extends Angebot {
	
	/*public static final int ORT = 0,
							PLAETZE = 1,
							BIERPREIS = 2;*/
	
	private Ort ort = null;
	private Plaetze plaetze = null;
	private Bierpreis bierpreis = null;
	
	Kriterium[] erlaubteKriterien = {ort,plaetze,bierpreis};
	
	public Ausfluege(String pname, String pbeschreibung, double ppreis, Date[] pdaten, String port, String pplaetze, String pbierpreis) {
		super(pname, pbeschreibung, Angebot.AUSFLUG, ppreis, pdaten);
		ort = new Ort(port); 
		plaetze = new Plaetze(pplaetze); 
		bierpreis = new Bierpreis(pbierpreis);
	}
	
	public void setOrt(String wert) {
		ort.setWert(wert);
	}
	
	public void setPlaetze(String wert) {
		plaetze.setWert(wert);
	}
	
	public void setBierpreis(String wert) {
		bierpreis.setWert(wert);
	}
	
}
