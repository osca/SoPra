package angebote.kriterien;

public class Klima extends Kriterium {
	

	private final static String name = "Klima";
	
	protected static String[] wertebereich = {"Tropisch","Mediteran","Gemaessigt","Kalt"};
	
	public Klima(String pwert) {
		super(pwert);
	}
	
	public String[] getWertebereich() {
		return wertebereich;
	}
	public static String getName() {
		return name;
	}
}