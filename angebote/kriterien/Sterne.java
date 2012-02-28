package angebote.kriterien;

public class Sterne extends Kriterium {


	private final String name = "Sterne";
	
	protected static String[] wertebereich = {"1","1.5","2","2.5","3","3.5","4","4.5","5","5.5","6","6.5","7","7.5","8","8.5","9","9.5","10"};
	
	public Sterne(String pwert) {
		super(pwert);
	}
	
	public String[] getWertebereich() {
		return wertebereich;
	}
	public String getName() {
		return name;
	}
}
