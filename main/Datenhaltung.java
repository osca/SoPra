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

import com.thoughtworks.xstream.XStream;

public class Datenhaltung {
	private static final File anbFile = new File("Anbieter.xml"),
			betrFile = new File("Betreiber.xml"), kundFile = new File("Kunden.xml");

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
	public static void saveAllAccounts(Accountverwaltung av) throws IOException {
		xs.toXML(av.getAnbieter(), new FileWriter(anbFile));
		xs.toXML(av.getBetreiber(), new FileWriter(betrFile));
		xs.toXML(av.getKunden(), new FileWriter(kundFile));
	}

	/**
	 * Liest die aktuell in XML gespeicherte Liste an Anbietern aus
	 * 
	 * @return Anbieter-Liste
	 */
	public static ArrayList<Anbieter> getAnbieter() {
		if (!anbFile.exists())
			return new ArrayList<Anbieter>();
		return (ArrayList<Anbieter>) xs.fromXML(anbFile);
	}

	/**
	 * Liest die aktuell in XML gespeicherte Liste an Betreiber aus
	 * 
	 * @return Betreiber-Liste
	 */
	public static ArrayList<Betreiber> getBetreiber() {
		if (!betrFile.exists())
			return new ArrayList<Betreiber>();
		return (ArrayList<Betreiber>) xs.fromXML(betrFile);
	}

	/**
	 * Liest die aktuell in XML gespeicherte Liste an Kunden aus
	 * 
	 * @return Kunden-Liste
	 */
	public static ArrayList<Kunde> getKunde() {
		if (!kundFile.exists())
			return new ArrayList<Kunde>();
		return (ArrayList<Kunde>) xs.fromXML(kundFile);
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
