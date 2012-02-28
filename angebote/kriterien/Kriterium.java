package angebote.kriterien;
//@author stephan
import java.util.ArrayList;

public abstract class Kriterium {
	
	protected ArrayList<String> werte;
	
	public Kriterium(ArrayList<String> pwerte) throws IllegalArgumentException{
		if(isValid(pwerte)){
			werte = pwerte;
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	public boolean isValid(ArrayList<String> pwerte) {
		return true;
	}
	
	public ArrayList<String> getWerte() {
		return werte;
	}

	public void setWerte(ArrayList<String> werte) {
		this.werte = werte;
	}
	
}
