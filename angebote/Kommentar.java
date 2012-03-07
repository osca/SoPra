package angebote;

import graphic.Listable;

import java.util.Date;

public class Kommentar implements Listable{
	
	public static final int KEINEWERTUNG = -1;
	
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
	

	//Listable-Methoden:
	@Override
	public String getAdditionalInfo() {
		return getZeitstempel()+ " , "+getBewertung();
	}

	@Override
	public String getFullInfo() {
		return getText();
	}

	@Override
	public String getIdentifier() {
		return getAbsender()+", "+getBewertung();
	}

	@Override
	public int getListableTyp() {
		return Listable.KOMMENTAR;
	}

	@Override
	public String getStatus() {
		return "";
	}
	
}
