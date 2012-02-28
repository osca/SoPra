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
	
}
