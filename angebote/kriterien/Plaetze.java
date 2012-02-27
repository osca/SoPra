package angebote.kriterien;

import java.util.Vector;

public class Plaetze extends Kriterium {

	public Plaetze(Vector<String> pwerte) {
		super(pwerte);
	}
	
	@Override
	public boolean isValid(Vector<String> pwerte) {
		if(pwerte.size() != 1)
			return false;
		
		try {
			Integer.parseInt(pwerte.get(1));
		}
		catch(NumberFormatException nfe) {
			return false;
		}
		
		return true;
	}
	
}
