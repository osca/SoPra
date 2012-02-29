package accounts;

import java.util.Date;

import angebote.typen.Angebot;
/**
 * @author Stephan
 */


public class Nachricht {

	private Date zeitStempel;
	private String text, betreff;
	private Account absender, empfaenger;
	private Angebot angebot;
	private boolean gelesen;

	public Account getAbsender() {
		return absender;
	}

	public Account getEmpfaenger() {
		return empfaenger;
	}

	public Angebot getAngebot() {
		return angebot;
	}

	public String getText() {
		return text;
	}

	public String getBetreff() {
		return betreff;
	}

	public Date getZeitStempel() {
		return zeitStempel;
	}

	public boolean isGelesen() {
		return gelesen;
	}

	public void setGelesen(boolean gelesen) {
		this.gelesen = gelesen;
	}

	public Nachricht(String subj, String txt, Account abs, Account empf, Angebot ang) {
		text = txt;
		betreff = subj;
		absender = abs;
		empfaenger = empf;
		angebot = ang;
		gelesen = false;
		zeitStempel = new Date();
	}
}
