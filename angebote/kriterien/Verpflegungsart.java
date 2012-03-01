package angebote.kriterien;

/**
 * Verpflegungsart - Erbendes Kriterium
 * 
 * @author stephan
 */
public class Verpflegungsart extends Kriterium{
	
	public final static String name = "Verpflegungsart";
	
	private static String[] wertebereich = {"All Inclusive","Bierflatrate","Vollpension","Halbpension","Nur Frühstück","Ohne Verpflegung"};
	
	/**
	 * Konstruktor
	 * 
	 * @param pwert Verpflegungsart
	 * @throws IllegalArgumentException Ist die Eingabe valide?
	 */
	public Verpflegungsart(String pwert) throws IllegalArgumentException {
		super(pwert);
	}

	/**
	 * Get Wertebereich
	 * 
	 * @return Wertebereich
	 */
	public static String[] getWertebereich() {
		return wertebereich;
	}
	
}
