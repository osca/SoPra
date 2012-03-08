package angebote.kriterien;

/**
 * Kriterium: abstrakte Oberklasse
 * 
 * @author stephan
 */
public abstract class Kriterium {
	
	protected String wert;
	
	/**
	 * Abstrakter Konstruktor mit einer Wertevalidierung (IllegalArgumentException)
	 * 
	 * @param pwerte Uebergebener Wert
	 * @pre pwerte muss isValid erfuellen
	 * @see isValid
	 */
	public Kriterium(String pwerte) throws IllegalArgumentException{
		assert isValid(pwerte) : "Ungueltiger Wert fuer Kriterienauspraegung";
			wert = pwerte;
	}
	
	/**
	 * Validierungsmethode
	 * 
	 * @param pwerte
	 * @return Standardmaessig true, falls keine spezielle Validierung gefragt ist
	 */
	public boolean isValid(String pwerte) {
		return true;
	}
	
	/**
	 * Get aktuellen Wert
	 * 
	 * @return Wert
	 */
	public String getWert() {
		return wert;
	}
	
	public abstract String getName();

	/**
	 * Set Wert
	 * 
	 * @param wert Wert
	 */
	public void setWert(String wert) {
		this.wert = wert;
	}
	
	public static String[] nameToErlaubteWerte(String name){
		if		(name.equals(Bierpreis.name)) 					return Bierpreis.wertebereich;
		else if (name.equals(Klasse.name)) 					return Klasse.wertebereich;
		else if (name.equals(Klima.name))					return Klima.wertebereich;
		else if (name.equals(Land.name))					return Land.wertebereich;
		else if (name.equals(Ort.name))						return Ort.wertebereich;
		else if (name.equals(Sterne.name))					return Sterne.wertebereich;
		else if (name.equals(Verpflegungsart.name))	return Verpflegungsart.wertebereich;
		else 										return null;
	}
}
