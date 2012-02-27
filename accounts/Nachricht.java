package accounts;

public class Nachricht {
	private String text, betreff;
	public String getText() {
		return text;
	}
	public String getBetreff() {
		return betreff;
	}
	public Nachricht(String subj, String txt){
		text=txt;
		betreff=subj;
	}
	
}
