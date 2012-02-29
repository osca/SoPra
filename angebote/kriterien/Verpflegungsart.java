package angebote.kriterien;
//@author stephan

public class Verpflegungsart extends Kriterium{
	
	@SuppressWarnings("unused")
	private final static String name = "Verpflegungsart";
	
	private static String[] wertebereich = {"All Inclusive","Bierflatrate","Vollpension","Halbpension","Nur Frühstück","Ohne Verpflegung"};
	
	public Verpflegungsart(String pwert) throws IllegalArgumentException {
		super(pwert);
	}

	public static String[] getWertebereich() {
		return wertebereich;
	}
	
}
