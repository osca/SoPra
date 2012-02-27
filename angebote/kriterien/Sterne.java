package angebote.kriterien;

import java.util.Vector;

public class Sterne extends Kriterium {

	protected static String[] wertebereich = {"1","1.5","2","2.5","3","3.5","4","4.5","5","5.5","6","6.5","7","7.5","8","8.5","9","9.5","10"};
	
	public Sterne(Vector<String> pwerte) {
		super(pwerte);
	}
	
	public String[] getWertebereich() {
		return wertebereich;
	}
}