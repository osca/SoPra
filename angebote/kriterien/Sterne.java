package angebote.kriterien;

/**
 * Sterne - Erbendes Kriterium
 * 
 * @author stephan
 */
public class Sterne extends Kriterium {
	
	public final static String name = "Sterne";
	
	public final static String[] wertebereich = {"0","1","2","3","4","5","6","7"};
	
	/**
	 * Konstruktor
	 * 
	 * @param pwert Sterne
	 * @throws IllegalArgumentException Ist die Eingabe valide?
	 */
	public Sterne(String pwert) {
		super(pwert);
	}

	public String getName(){
		return name;
	}
}
