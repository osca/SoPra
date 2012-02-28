package angebote.typen;

import java.util.Date;

import angebote.kriterien.Kriterium;
import angebote.kriterien.Ort;

public class Ausfluege extends Angebot {
	
	Kriterium[] erlaubteKriterien = {new Ort(null),new };
	
	public Ausfluege(String pname, double ppreis, Date[] pdaten) {
		super(pname, Angebot.AUSFLUG, ppreis, pdaten);
	}
	
	private void main(String[] args) {
		erlaubteKriterien[0].setWerte(werte);
	}
	
	public void setOrt(ArrayList<String> werte) {
		erlaubteKriterien[0].setWerte(werte);
	}
	
}
