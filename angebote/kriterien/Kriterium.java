package angebote.kriterien;

import java.util.Vector;

public abstract class Kriterium {
	
	protected static String[] erlaubteWerte;
	private Vector<String> werte;
	
	Kriterium(Vector<String> pwerte) {
		if(isValid(pwerte)){
			werte = pwerte;
		}
	}
	public boolean isValid(Vector<String> pwerte){
		return true;
	}
	public Vector<String> getWerte() {
		return werte;
	}

	public void setWerte(Vector<String> werte) {
		this.werte = werte;
	}
	
}
