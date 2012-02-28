package angebote.kriterien;


public class Bierpreis extends Kriterium {
	
	public final String name = "Bierpreis";
	
	protected static String[] wertebereich = {"Guenstig","Normal","Teuer","Unbezahlbar"};
	
	public Bierpreis(String pwert) {
		super(pwert);
	}
	
	public String[] getWertebereich() {
		return wertebereich;
	}
	
}
