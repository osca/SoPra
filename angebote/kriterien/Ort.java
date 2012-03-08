//TODO wertebereich fehlt

package angebote.kriterien;

/**
 * Ort - Erbendes Kriterium
 * 
 * @author stephan
 */
public class Ort extends Kriterium{
	
	public final static String name = "Ort";
	public final static String[] wertebereich = new String[0];
	/**
	 * Konstruktor
	 * 
	 * @param pwert Ort
	 * @throws IllegalArgumentException Ist die Eingabe valide?
	 */
	public Ort(String pwert) throws IllegalArgumentException {
		super(pwert);
	}

	/**
	 * Get Kriterienname
	 */
	public String getName(){
		return name;
	}
}
