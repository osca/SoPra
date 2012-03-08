package angebote;

import graphic.Listable;

import java.util.Date;

/**
 * Entitaetsklasse fuer Kommmentare
 * 
 * @author osca
 */
public class Kommentar implements Listable{
	
	public static final int KEINEWERTUNG = -1;
	
	private String absender, text;
	private int bewertung;
	private Date zeitstempel;
	
	/**
	 * Konstruktor
	 * 
	 * @param pabsender Absender
	 * @param ptext Text
	 * @param pbewertung Bewertung
	 */
	public Kommentar(String pabsender, String ptext, int pbewertung) {
		absender = pabsender;
		text = ptext;
		bewertung = pbewertung;
		zeitstempel = new Date();
	}
	
	
	//-----------------------------------------------------------------------------//
	//	GETTER UND SETTER														   //
	//-----------------------------------------------------------------------------//

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
	

	//-----------------------------------------------------------------------------//
	//	LISTABLE																   //
	//-----------------------------------------------------------------------------//
	
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
