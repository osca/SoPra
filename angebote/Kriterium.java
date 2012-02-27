package angebote;

public abstract class Kriterium {
	
	@SuppressWarnings("unused")
	private String[] erlaubteWerte,werte;
	
	Kriterium(String[] perlaubteWerte,String[] pwerte) {
		erlaubteWerte = perlaubteWerte;
		werte = pwerte;
	}

	public String[] getWerte() {
		return werte;
	}

	public void setWerte(String[] werte) {
		this.werte = werte;
	}
	
}
