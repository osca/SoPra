package angebote.typen;

import java.util.Date;

public class Ausfluege extends Angebot {
	
	//TODO erlaubte Kriterien hier rein?

	public Ausfluege(String pname, double ppreis, Date[] pdaten) {
		super(pname, Angebot.AUSFLUG, ppreis, pdaten);
	}
	
}
