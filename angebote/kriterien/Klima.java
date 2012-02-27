package angebote.kriterien;

import java.util.Vector;

public class Klima extends Kriterium {
	
	protected static String[] wertebereich = {"Tropisch","Mediteran","Gemäßigt","Kalt"};
	
	public Klima(Vector<String> pwerte) {
		super(pwerte);
	}
	
	public String[] getWertebereich() {
		return wertebereich;
	}
}