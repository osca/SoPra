package accounts;

public class LoginException extends Exception {

	private static final long serialVersionUID = -4610297373385659070L;

	public LoginException(){}
	
	public LoginException(String string) {
		super(string);
	}

}
