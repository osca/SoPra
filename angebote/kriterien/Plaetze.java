package angebote.kriterien;


public class Plaetze extends Kriterium {
	
	@SuppressWarnings("unused")
	private final static String name = "Plaetze";
	
	private String[] wertebereich = null;
	
	public Plaetze(String pwert) {
		super(pwert);
	}
	
	public String[] getWertebereich() {
		return wertebereich;
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
	
}
