package angebote.kriterien;

/**
 * Bierpreis - Erbendes Kriterium
 * 
 * @author stephan
 */
public class Bierpreis extends Kriterium {
	
	public final static String name = "Bierpreis";

	private static String[] wertebereich = {"Guenstig","Normal","Teuer","Unbezahlbar"};
	
	/**
	 * Konstruktor
	 * 
	 * @param pwert Bierpreis
	 * @throws IllegalArgumentException Ist die Eingabe valide?
	 */
	public Bierpreis(String pwert) throws IllegalArgumentException {
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
