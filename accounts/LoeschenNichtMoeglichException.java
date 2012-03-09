package accounts;

/**
 * LoeschenNichtMoeglichException wird ausgelöst, wenn eine Account nicht gelöscht werden kann.
 * 
 * @author osca
 */
public class LoeschenNichtMoeglichException extends Exception {

	private static final long serialVersionUID = 5648913248746578922L;

	public LoeschenNichtMoeglichException(String s){
		super(s);
	}
}
