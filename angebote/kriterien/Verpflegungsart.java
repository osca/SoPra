package angebote.kriterien;
//@author stephan

public class Verpflegungsart extends Kriterium{
	
	private final static String name = "Verpflegungsart";
	
	public Verpflegungsart(String pwert) throws IllegalArgumentException {
		super(pwert);
	}

	private static String[] wertebereich = {"All Inclusive","Bierflatrate","Vollpension","Halbpension","Nur Frühstück","Ohne Verpflegung"};

	public static String[] getWertebereich() {
		return wertebereich;
	}

	public static String getName() {
		return name;
	}
	
}
