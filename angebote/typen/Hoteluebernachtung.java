package angebote.typen;

import java.util.Date;

public class Hoteluebernachtung extends Angebot {
	
	//TODO erlaubte Kriterien hier rein?

	public Hoteluebernachtung(String pname, double ppreis, Date[] pdaten) {
		super(pname, Angebot.HOTEL, ppreis, pdaten);
	}
	
}
