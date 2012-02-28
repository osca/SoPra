package angebote.kriterien;

import java.util.ArrayList;

public class Klima extends Kriterium {
	
	protected static String[] wertebereich = {"Tropisch","Mediteran","Gem��igt","Kalt"};
	
	public Klima(ArrayList<String> pwerte) {
		super(pwerte);
	}
	
	public String[] getWertebereich() {
		return wertebereich;
	}
}