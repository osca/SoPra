package accounts;

public class LoeschenNichtMoeglichException extends Exception {

	private static final long serialVersionUID = 5648913248746578922L;

	public LoeschenNichtMoeglichException(){
		super();
	}
	public LoeschenNichtMoeglichException(String s){
		super(s);
	}
}