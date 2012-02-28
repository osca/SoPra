package angebote.kriterien;


public class Klasse extends Kriterium{
	
	public final String name = "Klasse";
	
	protected static String[] wertebereich = {"First class","Business","Economy"};

	public Klasse(String pwert) throws IllegalArgumentException {
		super(pwert);
	}
	public String[] getWertebereich() {
		return wertebereich;
	}

}
