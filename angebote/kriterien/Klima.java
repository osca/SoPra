package angebote.kriterien;

public class Klima extends Kriterium {
	
	public final String name = "Klima";
	
	protected static String[] wertebereich = {"Tropisch","Mediteran","Gem��igt","Kalt"};
	
	public Klima(String pwert) {
		super(pwert);
	}
	
	public String[] getWertebereich() {
		return wertebereich;
	}
}