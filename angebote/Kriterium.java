package angebote;

public abstract class Kriterium {
	
	private String[] erlaubteWerte,werte;
	
	Kriterium(String[] perlaubteWerte,String[] pwerte) {
		erlaubteWerte = perlaubteWerte;
		werte = pwerte;
	}
	
}
