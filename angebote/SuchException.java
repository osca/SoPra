package angebote;

/**
 * SuchException wird ausgeloest, wenn ein Fehler bei der Suche von Angeboten auftritt.
 * 
 * @author osca
 */
public class SuchException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	SuchException (String fehlermeldung){
		super(fehlermeldung);
	}
	
}
