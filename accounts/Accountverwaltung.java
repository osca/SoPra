package accounts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import main.Datenhaltung;
import main.Portal;
import angebote.Angebotsverwaltung;
import angebote.typen.Angebot;
import buchungen.Bestaetigung;
import buchungen.Buchung;
import buchungen.Buchungsverwaltung;

/**
 * Verwaltet alle drei Accounttypen. Fuehrt saemtliche nicht-triviale Methoden
 * fuer Accounts aus.
 * 
 * @author jay, stephan, osca
 */
public class Accountverwaltung {

	private ArrayList<Anbieter> anbieter = new ArrayList<Anbieter>();
	private ArrayList<Betreiber> betreiber = new ArrayList<Betreiber>();
	private ArrayList<Kunde> kunden = new ArrayList<Kunde>();
	private Account loggedIn = new Default();

	/**
	 * Konstruktor mit leeren Benutzer-Listen
	 * 
	 * @param anb
	 *            Anbieter
	 * @param betr
	 *            Betreiber
	 * @param kund
	 *            Kunde
	 */
	public Accountverwaltung() {
	}

	/**
	 * Legt die Accountverwaltung mit den gewuenschten Listen an.
	 * 
	 * @param anbieter
	 * @param betreiber
	 * @param kunden
	 */
	public Accountverwaltung(ArrayList<Anbieter> anbieter,
			ArrayList<Betreiber> betreiber, ArrayList<Kunde> kunden) {
		this.anbieter = anbieter;
		this.betreiber = betreiber;
		this.kunden = kunden;
	}

	public Account createAccount(int typFlag, String email, String name,
			String password) throws AlreadyInUseException {
		switch (typFlag) {
		case Account.NONE:
			return new Default();
		case Account.ANBIETER:
			return createAnbieter(email, name, password);
		case Account.BETREIBER:
			return createBetreiber(email, name, password);
		case Account.KUNDE:
			return createKunde(email, name, password);
		default:
			return null;
		}
	}

	/**
	 * Erstelle Kunde
	 * 
	 * @param email
	 *            E-Mail Adresse
	 * @param name
	 *            Username
	 * @param password
	 *            Password
	 * @throws AlreadyInUseException
	 *             Account E-Mail oder Username schon vergeben
	 */
	public Kunde createKunde(String email, String name, String password)
			throws AlreadyInUseException {
		if (!isFreeEmail(email) || !isFreeName(name))
			throw new AlreadyInUseException();
		Kunde k = new Kunde(email, name, password);
		kunden.add(k);
		return k;
	}

	/**
	 * Erstelle Anbieter
	 * 
	 * @param email
	 *            E-Mail Adresse
	 * @param name
	 *            Username
	 * @param password
	 *            Password
	 * @throws AlreadyInUseException
	 *             Account E-Mail oder Username schon vergeben
	 */
	public Anbieter createAnbieter(String email, String name, String password)
			throws AlreadyInUseException {
		if (!isFreeEmail(email) || !isFreeName(name))
			throw new AlreadyInUseException();
		Anbieter a = new Anbieter(email, name, password);
		anbieter.add(a);
		return a;
	}
	
	/**
	 * Erstelle Anbieter mit AGB
	 * 
	 * @param email
	 *            E-Mail Adresse
	 * @param name
	 *            Username
	 * @param password
	 *            Password
	 * @param agb
	 * 			  Allgemeine Gesch�ftsbedingungen
	 * @throws AlreadyInUseException
	 *             Account E-Mail oder Username schon vergeben
	 */
	public Anbieter createAnbieter(String email, String name, String password, String agb)
			throws AlreadyInUseException {
		if (!isFreeEmail(email) || !isFreeName(name))
			throw new AlreadyInUseException();
		Anbieter a = new Anbieter(email, name, password, agb);
		anbieter.add(a);
		return a;
	}

	/**
	 * Erstelle Betreiber
	 * 
	 * @param email
	 *            E-Mail Adresse
	 * @param name
	 *            Username
	 * @param password
	 *            Password
	 * @throws AlreadyInUseException
	 *             Account E-Mail oder Username schon vergeben
	 */
	public Betreiber createBetreiber(String email, String name, String password)
			throws AlreadyInUseException {
		if (!isFreeEmail(email) || !isFreeName(name))
			throw new AlreadyInUseException();
		Betreiber b = new Betreiber(email, name, password);
		betreiber.add(b);
		return b;
	}

	/**
	 * Loggt einen Account an der Verwaltung als aktiv ein.
	 * 
	 * @param identifier
	 *            entweder E-Mail oder Nick
	 * @param password
	 *            passwort
	 * @throws LoginException
	 *             Falls Passwort nicht passend oder Account nicht gefunden (mit
	 *             entspr. Nachricht)
	 */
	public void logIn(String identifier, String password) throws LoginException {
		Account acc = getAccountByIdentifier(identifier);
		if (acc == null)
			throw new LoginException("Account wurde nicht gefunden");
		if (!acc.getPassword().equals(password))
			throw new LoginException("Passwort war falsch");
		loggedIn = acc;
	}

	/**
	 * @return der aktuell eingeloggte Account
	 */
	public Account getLoggedIn() {
		return loggedIn;
	}

	public void logOut() throws IOException {
		// TODO Exceptions oder Warnungen (Wahrscheinlich nicht, wenn die JayDialogs OnTop sind und hinterer Thread freezed
		Datenhaltung.saveAllData();
		loggedIn = new Default();
	}

	/**
	 * Account (de)aktivieren
	 * 
	 * @param acc
	 *            Freizuschaltender Account
	 * @param enable
	 *            Aktiv oder nicht
	 * @throws Exception Wenn kein Betreiber eingeloggt ist
	 */
	public void setEnableAccount(Account acc, boolean enable) throws Exception {
		if(!betreiber.contains(loggedIn))
			throw new Exception("Sie sind nicht als Betreiber eingeloggt!");
		acc.setGesperrt(!enable);
	}

	/**
	 * Loesche einen Account und saemtliche Abhaengigkeiten
	 * 
	 * @param acc
	 *            Der zu loeschende Account
	 * @throws LoeschenNichtMoeglichException
	 *             Loeschen ist nicht moeglich
	 * @pre zu loeschender Account darf nicht null sein
	 */
	public void delAccount(Account acc) throws LoeschenNichtMoeglichException {
		assert acc != null : "null-Account kann nicht geloescht werden";

		Date heute = new Date();

		switch (acc.getTyp()) {
		case (Account.ANBIETER): {
			Anbieter anbieteracc = (Anbieter) acc;
			ArrayList<Angebot> zuLoeschendeAngebote = Portal
					.Angebotsverwaltung().getAngebote(anbieteracc);

			// Gibt es noch offene Buchungen Schleife
			for (Angebot a : zuLoeschendeAngebote) {
				if (a.getEnddatum().compareTo(heute) > 0) {
					ArrayList<Buchung> buchungen = Portal.Buchungsverwaltung()
							.getBuchungen(a);

					for (Buchung b : buchungen) {
						if (b.getBis().compareTo(heute) > 0
								&& (Bestaetigung.JA == b.getBestaetigt()))
							throw new LoeschenNichtMoeglichException(
									"Sie haben noch offene Buchungen");
					}
				}
			}

			// Angebote und deren Buchungen loeschen
			Buchungsverwaltung buchungsVerwaltung = Portal.Buchungsverwaltung();
			Angebotsverwaltung angebotsVerwaltung = Portal.Angebotsverwaltung();

			for (Angebot a : zuLoeschendeAngebote) {
				ArrayList<Buchung> buchungen = Portal.Buchungsverwaltung()
						.getBuchungen(a);

				for (Buchung b : buchungen) {
					buchungsVerwaltung.delBuchung(b);
					Portal.Buchungsverwaltung().delBuchung(b);
					// TODO Duerfen Buchungen wirklich geloescht werden? Nicht
					// besser canceln?
				}

				angebotsVerwaltung.delAngebot(a);
			}
			break;
		}

		case (Account.KUNDE): {
			Kunde kundenacc = (Kunde) acc;
			ArrayList<Buchung> kundenbuchungen = Portal.Buchungsverwaltung()
					.getBuchungen(kundenacc);

			// Hat der Kunde noch anstehende bestaetigte Buchungen?
			for (Buchung b : kundenbuchungen) {
				if (b.getBestaetigt() == Bestaetigung.JA
						&& b.getBis().compareTo(heute) > 0) {
					throw new LoeschenNichtMoeglichException(
							"Sie haben noch anstehende bestaetigte Buchungen");
				}
			}

			// Loesche saemtliche mit dem Kunden verbundene Buchungen
			Buchungsverwaltung buchungsVerwaltung = Portal.Buchungsverwaltung();

			for (Buchung b : buchungsVerwaltung.getBuchungen(kundenacc))
				buchungsVerwaltung.delBuchung(b);

			break;
		}

		case (Account.BETREIBER): {
			// Ist er der letzte Betreiber? Hoffentlich nicht...
			if (getBetreiber().size() < 2)
				throw new LoeschenNichtMoeglichException(
						"You're the last unicorn!");

			break;
		}
		}

		// Loesche saemtliche mit dem Account verbundenen Nachrichten
		Nachrichtenverwaltung nachrichtenVerwaltung = Portal
				.Nachrichtenverwaltung();

		nachrichtenVerwaltung.delAllNachrichten(acc);

		boolean success = anbieter.remove(acc) || betreiber.remove(acc)
				|| kunden.remove(acc);
		// Ist der Account sicher aus der Liste geloescht?
		if (!success)
			throw new LoeschenNichtMoeglichException(
					"Der Account wurde nicht gefunden!");
	}

	/**
	 * Get Anbieterliste
	 * 
	 * @return Anbieterliste
	 */
	public ArrayList<Anbieter> getAnbieter() {
		return anbieter;
	}

	/**
	 * Get Betreiberliste
	 * 
	 * @return Betreiberliste
	 */
	public ArrayList<Betreiber> getBetreiber() {
		return betreiber;
	}

	/**
	 * Get Kundenliste
	 * 
	 * @return Kundenliste
	 */
	public ArrayList<Kunde> getKunden() {
		return kunden;
	}

	/**
	 * Get Accountliste
	 * 
	 * @return Accountliste
	 */
	public ArrayList<Account> getAccounts() {
		ArrayList<Account> result = new ArrayList<Account>();
		result.addAll(getAnbieter());
		result.addAll(getBetreiber());
		result.addAll(getKunden());
		return result;
	}

	/**
	 * Sucht einen Account über seinen Nick-/Unternehmensnamen - CaseInsensitive
	 * 
	 * @param name
	 * @return passender Account oder null falls nicht gefunden
	 */
	public Account getAccountByName(String name) {
		for (Account acc : getAccounts())
			if (acc.getName().toLowerCase().equals(name.toLowerCase()))
				return acc;
		return null;
	}

	/**
	 * Sucht einen Account über seine E-Mail-Adresse - CaseInsensitive
	 * 
	 * @param email
	 * @return passender Account oder null falls nicht gefunden
	 */
	public Account getAccountByEmail(String email) {
		for (Account acc : getAccounts())
			if (acc.getEmail().toLowerCase().equals(email.toLowerCase()))
				return acc;
		return null;
	}

	/**
	 * Sucht einen Account über seine "Identifikation" (= Name oder email, siehe
	 * Pflichtenheft)
	 * 
	 * @param ident
	 * @return passender Account oder null falls nicht gefunden
	 */
	public Account getAccountByIdentifier(String ident) {
		Account acc1 = getAccountByEmail(ident), acc2 = getAccountByName(ident);
		if (acc1 != null)
			return acc1;
		return acc2;
	}

	/**
	 * Gibt einen qualifizierten Namen zu einer Flag zurueck;
	 * 
	 * @param flag
	 * @return
	 */
	public String convertFlagToName(int flag) {
		switch (flag) {
		case Account.NONE:
			return "Default";
		case Account.ANBIETER:
			return "Anbieter";
		case Account.BETREIBER:
			return "Betreiber";
		case Account.KUNDE:
			return "Kunde";
		default:
			return "Kein Account";
		}
	}

	/**
	 * Ist diese E-Mail Adresse schon vergeben?
	 * 
	 * @param email
	 *            E-Mail Adresse
	 * @return Vergeben oder nicht
	 */
	public boolean isFreeEmail(String email) {
		String emailValid = ".+@[a-zA-Z0-9-\\.]+\\.[a-zA-Z]+";
		if(! email.matches(emailValid) && !email.contains(".."))
			throw new IllegalArgumentException("Die gewuenschte E-Mail-Adresse ist von keiner gueltigen Form");
		for (Account a : getAccounts())
			if (a.getEmail().toLowerCase().equals(email.toLowerCase()))
				return false;
		return true;
	}

	/**
	 * Ist dieser Username schon vergeben?
	 * 
	 * @param name
	 *            Username
	 * @return Vergeben oder nicht
	 */
	public boolean isFreeName(String name) {
		if(name.length()<2)
			throw new IllegalArgumentException("Bitte wählen Sie einen Namen mit mehr als 2 Zeichen");
		for (Account a : getAccounts())
			if (a.getName().toLowerCase().equals(name.toLowerCase()))
				return false;
		return true;
	}
	
	/**
	 * Einen Betreiber hinzufuegen
	 * 
	 * @param email Email des neuen Betreibers
	 * @param name Username des neuen Betreibers
	 * @param password Password des neuen Betreibers
	 * @throws Exception 
	 */
	public void addBetreiber(String email, String name, String password) throws Exception {
		if(betreiber.contains(loggedIn) && isFreeEmail(email) && isFreeName(name)) {
			betreiber.add(new Betreiber(email,name,password));
		}
		else
			throw new Exception("Sie sind kein Betreiber");
	}
}
