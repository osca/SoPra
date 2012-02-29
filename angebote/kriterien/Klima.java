package angebote.kriterien;

/**
 * Klima - Erbendes Kriterium
 * 
 * @author stephan
 */
public class Klima extends Kriterium {
	
	@SuppressWarnings("unused")
	private final static String name = "Klima";
	
	private static String[] wertebereich = {"Tropisch","Mediteran","Gemaessigt","Kalt"};
	
	public Klima(String pwert) {
		super(pwert);
	}
	
	public String[] getWertebereich() {
		return wertebereich;
	}

}