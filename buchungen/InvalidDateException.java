package buchungen;

public class InvalidDateException extends Exception {
	public InvalidDateException(String string) {
		super(string);
	}
	public InvalidDateException(){}

	private static final long serialVersionUID = 1L;
}

