package angebote.typen;

import java.util.Date;

public class Autovermietung extends Angebot {
	
	//TODO erlaubte Kriterien hier rein?

	public Autovermietung(String pname, double ppreis, Date[] pdaten) {
		super(pname, Angebot.AUTOVERMIETUNG, ppreis, pdaten);
	}
	
}
