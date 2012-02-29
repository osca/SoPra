package angebote;

import java.util.Date;

import angebote.typen.Angebot;

public class Kommentar {
	
	private String absender, text;
	private int bewertung;
	private Date zeitstempel;
	private Angebot angebot;
	
	public Kommentar(Angebot pangebot, String pabsender, String ptext, int pbewertung) {
		angebot = pangebot;
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
	
	public Angebot getAngebot() {
		return angebot;
	}
	
}
