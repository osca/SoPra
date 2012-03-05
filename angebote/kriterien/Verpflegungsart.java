package angebote.kriterien;

/**
 * Verpflegungsart - Erbendes Kriterium
 * 
 * @author stephan
 */
public class Verpflegungsart extends Kriterium{
	
	public final static String name = "Verpflegungsart";
	
	public final static String[] wertebereich = {"All Inclusive","Bierflatrate","Vollpension","Halbpension","Nur Fruehstück","Ohne Verpflegung"};
	
	/**
	 * Konstruktor
	 * 
	 * @param pwert Verpflegungsart
	 * @throws IllegalArgumentException Ist die Eingabe valide?
	 */
	public Verpflegungsart(String pwert) throws IllegalArgumentException {
		super(pwert);
	}

	public String getName(){
		return name;
	}
}
