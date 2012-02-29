package angebote.kriterien;
//@author stephan

public abstract class Kriterium {
	
	protected String wert;
	private static String name;
	
	public Kriterium(String pwerte) throws IllegalArgumentException{
		if(isValid(pwerte)){
			wert = pwerte;
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	public boolean isValid(String pwerte) {
		return true;
	}
	
	public String getWert() {
		return wert;
	}

	public void setWert(String wert) {
		this.wert = wert;
	}
	
	public String getName() {
		return name;
	}
}
