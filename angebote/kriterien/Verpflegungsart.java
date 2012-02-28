package angebote.kriterien;
//@author stephan

public class Verpflegungsart extends Kriterium{
	
	public Verpflegungsart(String pwert) throws IllegalArgumentException {
		super(pwert);
	}

	private static String[] wertebereich = {"All Inclusive","Bierflatrate","Vollpension","Halbpension","Nur Frühstück","Ohne Verpflegung"};

	public static String[] getWertebereich() {
		return wertebereich;
	}
	
}
