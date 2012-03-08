package angebote.kriterien;

/**
 * Klasse - Erbendes Kriterium
 * 
 * @author stephan
 */
public class Klasse extends Kriterium{
	
	public final static String name = "Klasse";
	
	public final static String[] wertebereich = {"First Class","Business","Economy"};

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
	 * Get Kriterienname
	 */
	public String getName(){
		return name;
	}
}
