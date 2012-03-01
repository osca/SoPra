package angebote;

import java.util.Date;

import angebote.typen.Angebot;

public class Kommentar {
	
	private String absender, text;
	private int bewertung;
	private Date zeitstempel;
	
	public Kommentar(String pabsender, String ptext, int pbewertung) {
		absender = pabsender;
		text = ptext;
		bewertung = pbewertung;
		zeitstempel = new Date();
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

	public Date getZeitstempel() {
		return zeitstempel;
	}
	
}
