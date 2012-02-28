package angebote.kriterien;


public class Bierpreis extends Kriterium {
	
	protected static String[] wertebereich = {"G�nstig","Normal","Teuer","Unbezahlbar"};
	
	public Bierpreis(String pwert) {
		super(pwert);
	}
	
	public String[] getWertebereich() {
		return wertebereich;
	}
	
}
