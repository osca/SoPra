package angebote.kriterien;

public abstract class Kriterium {
	
	@SuppressWarnings("unused")
	protected String[] erlaubteWerte;
	@SuppressWarnings("unused")
	private String[] werte;
	
	Kriterium(String[] pwerte) {
		if(isValid(pwerte)){
			werte = pwerte;
		}
	}
	public boolean isValid(String[] pwerte){
		return true;
	}
	public String[] getWerte() {
		return werte;
	}

	public void setWerte(String[] werte) {
		this.werte = werte;
	}
	
}
