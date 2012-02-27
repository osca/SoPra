package angebote.kriterien;

import java.util.Vector;

public abstract class Kriterium {
	
	private Vector<String> werte;
	
	Kriterium(Vector<String> pwerte) throws IllegalArgumentException{
		if(isValid(pwerte)){
			werte = pwerte;
		} else {
			throw new IllegalArgumentException();
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
