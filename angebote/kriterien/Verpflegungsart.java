package angebote.kriterien;

import java.util.Vector;

public class Verpflegungsart extends Kriterium{
	Vector<String> verpflegungsarten = new Vector<String>();
	Verpflegungsart(Vector<String> pwerte) {
		super(pwerte);
		verpflegungsarten.add("All Inclusive");
		erlaubteWerte = verpflegungsarten;
	}
	

}
