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

	public final static int KEINE_BUCHUNG = -1;
	
	private static int anzahl = 0;
	private int id;
	private Date zeitstempel;
	private String text, betreff;
	private String absenderName, empfaengerName;
	private int angebot,buchungsnummer;
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
		absenderName = abs.getName();
		empfaengerName = empf.getName();
		angebot = ang.getAngebotsNummer();
		gelesen = false;
		zeitstempel = new Date();
		buchungsnummer = KEINE_BUCHUNG;
	}
	
	
	//-----------------------------------------------------------------------------//
	//	GETTER UND SETTER														   //
	//-----------------------------------------------------------------------------//
	
	/**
	 * Get Absender
	 * 
	 * @return Absender der Nachricht
	 */
	public String getAbsender() {
		return absenderName;
	}

	/**
	 * Get Empfaenger
	 * 
	 * @return Empfaenger der Nachricht
	 */
	public String getEmpfaenger() {
		return empfaengerName;
	}

	/**
	 * Get Angebot
	 * 
	 * @return Verknuepftes Angebot einer Nachricht
	 */
	public int getAngebotsNummer() {
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
	 * Get Buchungsnummer
	 * 
	 * @return Buchungsnummer
	 */
	public int getBuchungsnummer() {
		return buchungsnummer;
	}
	
	/**
	 * Set Buchungsnummer
	 * 
	 * @param bnummer Buchungsnummer
	 */
	public void setBuchungsnummer(int bnummer) {
		buchungsnummer = bnummer;
	}
	
	/**
	 * Get Anzahl aller Nachrichten
	 * 
	 * @return Anzahl aller Nachrichten
	 */
	public int getAnzahl() {
		return anzahl;
	}

	
	//-----------------------------------------------------------------------------//
	//	LISTABLE																   //
	//-----------------------------------------------------------------------------//
	
	@Override
	public String getIdentifier() {
		return "["+id+"] "+absenderName;
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
			return "		gelesen";
		else
			return "		ungelesen";
	}

	@Override
	public int getListableTyp() {
		return Listable.NACHRICHT;
	}
}
