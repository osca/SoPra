package angebote.kriterien;


public class Klasse extends Kriterium{
	
	@SuppressWarnings("unused")
	private final static String name = "Klasse";
	
	private static String[] wertebereich = {"First class","Business","Economy"};

	public Klasse(String pwert) throws IllegalArgumentException {
		super(pwert);
	}
	
	public String[] getWertebereich() {
		return wertebereich;
	}
}
