package angebote.kriterien;

/**
 * Sterne - Erbendes Kriterium
 * 
 * @author stephan
 */
public class Sterne extends Kriterium {
	
	public final static String name = "Sterne";
	
	public final static String[] wertebereich = {"0","0.5","1","1.5","2","2.5","3","3.5","4","4.5","5","5.5","6","6.5","7","7.5","8","8.5","9","9.5","10"};
	
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
