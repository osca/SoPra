package angebote.kriterien;

import java.util.Vector;

public class Verpflegungsart extends Kriterium{
	String[] verpflegungsarten = {"All Inclusive","Vollpension","Halbpension","Nur Frühstück","Ohne Verpflegung"};
	Verpflegungsart(Vector<String> pwerte) {
		super(pwerte);
		erlaubteWerte = verpflegungsarten;
	}
	

}
