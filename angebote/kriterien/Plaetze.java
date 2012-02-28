package angebote.kriterien;

import java.util.ArrayList;

public class Plaetze extends Kriterium {

	public Plaetze(ArrayList<String> pwerte) {
		super(pwerte);
	}
	
	@Override
	public boolean isValid(ArrayList<String> pwerte) {
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
