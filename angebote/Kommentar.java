package angebote;

import java.util.Date;

public class Kommentar {
	
	private String absender, text;
	private int bewertung;
	private Date erstellungsDatum;
	
	public Kommentar(String pabsender, String ptext, int pbewertung) {
		absender = pabsender;
		text = ptext;
		bewertung = pbewertung;
		erstellungsDatum = new Date();
	}

	public String getAbsender() {
		return absender;
	}

	public String getText() {
		return text;
	}

	public int getBewertung() {
		return bewertung;
	}

	public Date getErstellungsDatum() {
		return erstellungsDatum;
	}
	
}
