package accounts;

public class Betreiber extends Account{

	public Betreiber(String em, String nm, String pw) {
		super(em, nm, pw);
		// TODO Auto-generated constructor stub
	}
	@Override
	public int getTyp(){
		return Account.BETREIBER;
	}

}
