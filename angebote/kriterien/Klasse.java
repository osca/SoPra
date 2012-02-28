package angebote.kriterien;


public class Klasse extends Kriterium{
	
	private final static String name = "Klasse";
	
	protected static String[] wertebereich = {"First class","Business","Economy"};

	public Klasse(String pwert) throws IllegalArgumentException {
		super(pwert);
	}
	public String[] getWertebereich() {
		return wertebereich;
	}
	public static String getName() {
		return name;
	}

}
