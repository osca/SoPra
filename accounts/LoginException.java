package accounts;

/**
 * LoginException wird ausgelöst, wenn das eingegebene Password oder Name nicht existiert/richtig ist.
 * 
 * @author osca
 */
public class LoginException extends Exception {

	private static final long serialVersionUID = -4610297373385659070L;

	public LoginException(String string) {
		super(string);
	}

}
