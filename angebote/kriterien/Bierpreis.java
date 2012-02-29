package angebote.kriterien;

/**
 * Bierpreis - Erbendes Kriterium
 * 
 * @author stephan
 */
public class Bierpreis extends Kriterium {
	
	@SuppressWarnings("unused")
	private final static String name = "Bierpreis";

	private static String[] wertebereich = {"Guenstig","Normal","Teuer","Unbezahlbar"};
	
	public Bierpreis(String pwert) {
		super(pwert);
	}
	
	public String[] getWertebereich() {
		return wertebereich;
	}
	
}
