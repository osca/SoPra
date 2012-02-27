package angebote.kriterien;
//@author stephan

import java.util.Vector;

public class Verpflegungsart extends Kriterium{
	Verpflegungsart(Vector<String> pwerte) throws IllegalArgumentException {
		super(pwerte);
	}

	private static String[] wertebereich = {"All Inclusive","Vollpension","Halbpension","Nur Frühstück","Ohne Verpflegung"};

	public static String[] getWertebereich() {
		return wertebereich;
	}
	
}
