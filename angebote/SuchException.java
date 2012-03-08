package angebote;

/**
 * SuchException
 * 
 * @author osca
 */
public class SuchException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	SuchException (String fehlermeldung){
		super(fehlermeldung);
	}
	
}
