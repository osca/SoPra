package angebote.kriterien;

public class Klima extends Kriterium {
	

	private final String name = "Klima";
	
	protected static String[] wertebereich = {"Tropisch","Mediteran","Gem��igt","Kalt"};
	
	public Klima(String pwert) {
		super(pwert);
	}
	
	public String[] getWertebereich() {
		return wertebereich;
	}
	public String getName() {
		return name;
	}
}