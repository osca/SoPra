package angebote.kriterien;

import java.util.Vector;

public abstract class Kriterium {
	
	@SuppressWarnings("unused")
	protected Vector<String> erlaubteWerte;
	@SuppressWarnings("unused")
	private Vector<String> werte;
	
	Kriterium(Vector<String> pwerte) {
		if(isValid(pwerte)){
			werte = pwerte;
		}
	}
	public boolean isValid(Vector<String> pwerte){
		for(int i=0;i<pwerte.size();i++){
			erlaubteWerte.contains(pwerte.get(i));
		}
		return true;
	}
	public Vector<String> getWerte() {
		return werte;
	}

	public void setWerte(Vector<String> werte) {
		this.werte = werte;
	}
	
}
