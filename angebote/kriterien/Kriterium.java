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
	 * @throws IllegalArgumentException Wenn der Wert nicht valide ist, throw!
	 */
	public Kriterium(String pwerte) throws IllegalArgumentException{
		if(isValid(pwerte)){
			wert = pwerte;
		} else {
			throw new IllegalArgumentException();
		}
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
}
