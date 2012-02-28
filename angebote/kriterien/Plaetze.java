package angebote.kriterien;


public class Plaetze extends Kriterium {
	
	private final static String name = "Plaetze";
	
	String[] wertebereich = null;
	
	public Plaetze(String pwert) {
		super(pwert);
	}
	
	@Override
	public boolean isValid(String pwert) {
		try {
			Integer.parseInt(pwert);
		}
		catch(NumberFormatException nfe) {
			return false;
		}
		
		return true;
	}

	public static String getName() {
		return name;
	}
	
}
