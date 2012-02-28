package angebote.kriterien;

import java.util.ArrayList;

public class Klasse extends Kriterium{
	
	protected static String[] wertebereich = {"First class","Business","Economy"};

	public Klasse(ArrayList<String> pwerte) throws IllegalArgumentException {
		super(pwerte);
	}
	public String[] getWertebereich() {
		return wertebereich;
	}

}
