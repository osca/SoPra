package angebote.kriterien;

/**
 * Klasse - Erbendes Kriterium
 * 
 * @author stephan
 */
public class Klasse extends Kriterium{
	
	@SuppressWarnings("unused")
	private final static String name = "Klasse";
	
	private static String[] wertebereich = {"First Class","Business","Economy"};

	/**
	 * Konstruktor
	 * 
	 * @param pwert Klasse
	 * @throws IllegalArgumentException Ist die Eingabe valide?
	 */
	public Klasse(String pwert) throws IllegalArgumentException {
		super(pwert);
	}
	
	/**
	 * Get Wertebereich
	 * 
	 * @return Wertebereich
	 */
	public String[] getWertebereich() {
		return wertebereich;
	}
}
