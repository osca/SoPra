package accounts;

import graphic.Listable;

import java.util.Date;

import angebote.typen.Angebot;

/**
 * Entitaetsklasse fuer Nachricht
 * 
 * @author Stephan
 * @edit osca
 */
public class Nachricht implements Listable {

	private static int anzahl = 0;
	private int id;
	private Date zeitstempel;
	private String text, betreff;
	private Account absender, empfaenger;
	private Angebot angebot;
	private boolean gelesen;

	/**
	 * Konstruktor einer Nachricht
	 * 
	 * @param subj Betreff
	 * @param txt Text
	 * @param abs Absender
	 * @param empf Empfaenger
	 * @param ang Verknuepftes Angebot
	 */
	public Nachricht(String subj, String txt, Account abs, Account empf, Angebot ang) {
		id = anzahl++;
		text = txt;
		betreff = subj;
		absender = abs;
		empfaenger = empf;
		angebot = ang;
		gelesen = false;
		zeitstempel = new Date();
	}
	
	/**
	 * Get Absender
	 * 
	 * @return Absender der Nachricht
	 */
	public Account getAbsender() {
		return absender;
	}

	/**
	 * Get Empfaenger
	 * 
	 * @return Empfaenger der Nachricht
	 */
	public Account getEmpfaenger() {
		return empfaenger;
	}

	/**
	 * Get Angebot
	 * 
	 * @return Verknuepftes Angebot einer Nachricht
	 */
	public Angebot getAngebot() {
		return angebot;
	}

	/**
	 * Get Text
	 * 
	 * @return Text einer Nachricht
	 */
	public String getText() {
		return text;
	}

	/**
	 * Get Betreff
	 * 
	 * @return Betreff einer Nachricht
	 */
	public String getBetreff() {
		return betreff;
	}

	/**
	 * Get Zeitstepmpel
	 * 
	 * @return Datum und Zeit einer Nachricht
	 */
	public Date getZeitstempel() {
		return zeitstempel;
	}

	/**
	 * Ist die Nachricht als gelesen markiert?
	 * 
	 * @return True oder False
	 */
	public boolean isGelesen() {
		return gelesen;
	}

	/**
	 * Set Gelesen
	 * 
	 * @param gelesen Gelesen?
	 */
	public void setGelesen(boolean gelesen) {
		this.gelesen = gelesen;
	}
	
	/**
	 * Get Id
	 * 
	 * @return Id der Nachricht
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Get Anzahl aller Nachrichten
	 * 
	 * @return Anzahl aller Nachrichten
	 */
	public int getAnzahl() {
		return anzahl;
	}

	@Override
	public String getIdentifier() {
		return ""+id;
	}

	@Override
	public String getAdditionalInfo() {
		return betreff;
	}

	@Override
	public String getFullInfo() {
		return text;
	}

	@Override
	public String getStatus() {
		if(gelesen)
			return "gelesen";
		else
			return "ungelesen";
	}

	@Override
	public int getListableTyp() {
		return Listable.NACHRICHT;
	}
}
