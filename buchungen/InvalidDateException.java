package buchungen;

/**
 * InvalidDateException wird ausgeloest, wenn das Date "abgelaufen ist".
 * 
 * @author osca
 */
public class InvalidDateException extends Exception {
	public InvalidDateException(String string) {
		super(string);
	}
	
	private static final long serialVersionUID = 1L;
}

