package accounts;

public class Betreiber extends Account {
	
	private static boolean anbieterReg = false;

	public Betreiber(String em, String nm, String pw) {
		super(em, nm, pw);
		// TODO Auto-generated constructor stub
	}
	
	public static boolean getAnbieterReg() {
		return anbieterReg;
	}
	
	public static void setAnbieterReg(boolean regged) {
		anbieterReg = regged;
	}
	
	@Override
	public int getTyp(){
		return Account.BETREIBER;
	}

}
