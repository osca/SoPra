package accounts;

/**
 * AlreadyInUseException wird ausgeloest, wenn ein Account bereits registriert ist.
 * 
 * @author osca
 */
public class AlreadyInUseException extends Exception {
	private static final long serialVersionUID = 8058644619212255947L;
	
	public AlreadyInUseException(String s){
		super(s);
	}

}
