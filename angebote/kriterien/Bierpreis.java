package angebote.kriterien;

import java.util.ArrayList;

public class Bierpreis extends Kriterium {
	
	protected static String[] wertebereich = {"G�nstig","Normal","Teuer","Unbezahlbar"};
	
	public Bierpreis(ArrayList<String> pwerte) {
		super(pwerte);
	}
	
	public String[] getWertebereich() {
		return wertebereich;
	}
	
}
