package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import accounts.Accountverwaltung;
import accounts.Anbieter;
import accounts.Betreiber;
import accounts.Kunde;
import accounts.Nachricht;
import accounts.Nachrichtenverwaltung;
import buchungen.Buchung;
import buchungen.Buchungsverwaltung;

import com.thoughtworks.xstream.XStream;

public class Datenhaltung {
	private static final File anbFile = new File("Anbieter.xml"),
			betrFile = new File("Betreiber.xml"), kundFile = new File(
					"Kunden.xml"), msgFile = new File("Nachrichten.xml"),
			buchFile = new File("Buchungen.xml");

	private final static String header = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>";

	private Datenhaltung() {
	}

	private static XStream xs = new XStream();

	/**
	 * Speichert alle Accounts in spezifische XML-Dateien aufgespalten nach
	 * Benutzertyp
	 * 
	 * @param av
	 *            Angebotsverwaltungsobjekt
	 * @throws IOException
	 *             Fehler beim Schreiben
	 */
	public static void saveAllAccounts() throws IOException {
		if (anbFile.exists())
			anbFile.delete();
		if (betrFile.exists())
			betrFile.delete();
		if (kundFile.exists())
			kundFile.delete();
		Accountverwaltung av = Portal.Accountverwaltung();

		FileWriter f = new FileWriter(anbFile);
		f.write(header);
		xs.toXML(av.getAnbieter(), f);
		f.close();

		f = new FileWriter(betrFile);
		f.write(header);
		xs.toXML(av.getBetreiber(), f);
		f.close();

		f = new FileWriter(kundFile);
		f.write(header);
		xs.toXML(av.getKunden(), f);
		f.close();
	}

	/**
	 * Speichert alle Nachrichten, die in der Nachrichtenverwaltung existieren
	 * 
	 * @throws IOException
	 */
	public static void saveAllMessages() throws IOException {
		if (msgFile.exists())
			msgFile.delete();

		Nachrichtenverwaltung nv = Portal.Nachrichtenverwaltung();
		FileWriter f = new FileWriter(msgFile);
		f.write(header);
		xs.toXML(nv.getAlleNachrichten(), f);
		f.close();
	}

	/**
	 * Speichert alle Buchungen aus der Buchungsverwaltung
	 * 
	 * @throws IOException
	 */
	public static void saveAllBookings() throws IOException {
		if (buchFile.exists())
			buchFile.delete();

		Buchungsverwaltung bv = Portal.Buchungsverwaltung();
		FileWriter f = new FileWriter(buchFile);
		f.write(header);
		xs.toXML(bv.getAllBuchungen(), f);
		f.close();
	}

	/**
	 * Laedt persistierte Daten aus XML-Files falls vorhanden und regelt die
	 * uebernahme dieser Daten in der Portal-Klasse
	 */
	@SuppressWarnings("unchecked")
	public static void recoverSavedState() {
		ArrayList<Nachricht> nachrichten = new ArrayList<Nachricht>();
		ArrayList<Buchung> buchungen = new ArrayList<Buchung>();
		ArrayList<Anbieter> anbieter = new ArrayList<Anbieter>();
		ArrayList<Betreiber> betreiber = new ArrayList<Betreiber>();
		ArrayList<Kunde> kunden = new ArrayList<Kunde>();
		if (msgFile.exists())
			nachrichten = (ArrayList<Nachricht>) xs.fromXML(msgFile);
		if (buchFile.exists())
			buchungen = (ArrayList<Buchung>) xs.fromXML(buchFile);
		if (anbFile.exists())
			anbieter = (ArrayList<Anbieter>) xs.fromXML(anbFile);
		if (betrFile.exists())
			betreiber = (ArrayList<Betreiber>) xs.fromXML(betrFile);
		if (kundFile.exists())
			kunden = (ArrayList<Kunde>) xs.fromXML(kundFile);
		Portal.recover(new Accountverwaltung(anbieter, betreiber, kunden),
				new Buchungsverwaltung(buchungen), new Nachrichtenverwaltung(
						nachrichten));
	}

	/**
	 * Liest ein NICHT-XML-File ein und gibt die einzelnen Zeilen als
	 * String-Array aus
	 * 
	 * @param laenderFile
	 * @return
	 */
	public static String[] getStringArrayFromFile(File f) {
		ArrayList<String> reslist = new ArrayList<String>();
		RandomAccessFile raf;
		String line;
		try {
			raf = new RandomAccessFile(f, "r");
			while ((line = raf.readLine()) != null && !line.isEmpty())
				reslist.add(line);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace(); // TODO Handling??
		}

		String[] s = new String[reslist.size()];
		for (int i = 0; i < s.length; i++)
			s[i] = (String) reslist.get(i);
		return s;
	}
}
