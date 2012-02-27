package angebote.kriterien;

import java.util.Vector;

public class Klima extends Kriterium{
	
	Vector<String> klimata = new Vector<String>();
	
	Klima(Vector<String> pwerte) {
		super(pwerte);
		
		klimata.add("Sonnig");
		klimata.add("");
		
		erlaubteWerte = klimata;
	}
}