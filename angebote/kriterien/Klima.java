package angebote.kriterien;

/**
 * Klima - Erbendes Kriterium
 * 
 * @author stephan
 */
public class Klima extends Kriterium {
	
	public final static String name = "Klima";
	
	public final static String[] wertebereich = {"Tropisch","Mediteran","Gemaessigt","Kalt"};
	
	/**
	 * Konstruktor
	 * 
	 * @param pwert Klima
	 * @throws IllegalArgumentException Ist die Eingabe valide?
	 */
	public Klima(String pwert) throws IllegalArgumentException {
		super(pwert);
	}

	public String getName(){
		return name;
	}

}