package angebote.typen;

import java.util.Date;

public class Flug extends Angebot {
	
	//TODO erlaubte Kriterien hier rein?

	public Flug(String pname, double ppreis, Date[] pdaten) {
		super(pname, Angebot.FLUG, ppreis, pdaten);
	}
	
}
