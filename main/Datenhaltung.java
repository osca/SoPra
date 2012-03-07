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
import angebote.Angebotsverwaltung;
import angebote.typen.Angebot;
import buchungen.Buchung;
import buchungen.Buchungsverwaltung;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class Datenhaltung {
	private static final String path = System.getProperty("user.dir")+"/ReisePortal/";
	private static final File anbFile = new File(path+"Anbieter.xml"),
			betrFile = new File(path+"Betreiber.xml"), 
			kundFile = new File(path+"Kunden.xml"), 
			msgFile = new File(path+"Nachrichten.xml"),
			offFile = new File(path+"Angebote.xml"),
			buchFile = new File(path+"Buchungen.xml");

	private final static String header = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
//	private final static String header = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>";

	private Datenhaltung() {}

	private static XStream xs = initXStream();

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

		FileWriter f = new FileWriter(msgFile);
		f.write(header);
		xs.toXML(Portal.Nachrichtenverwaltung().getAlleNachrichten(), f);
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

		FileWriter f = new FileWriter(buchFile);
		f.write(header);
		Object[] attr = new Object[]{
				Portal.Buchungsverwaltung().getAllBuchungen(),
				Buchung.getAnzahl()
		};
		xs.toXML(attr,f);
		f.close();
	}
	
	public static void saveAllOffers() throws IOException {
		if (offFile.exists())
			offFile.delete();

		FileWriter f = new FileWriter(offFile);
		f.write(header);
		Object[] attr = new Object[]{
				Portal.Angebotsverwaltung().getAllAngebote(),
				Angebot.getAnzahl()
		};
		xs.toXML(attr, f);
		f.close();
	}


	public static void saveAllData() throws IOException {
		saveAllAccounts();
		saveAllBookings();
		saveAllOffers();
		saveAllMessages();
	}
	/**
	 * Laedt persistierte Daten aus XML-Files falls vorhanden und regelt die
	 * uebernahme dieser Daten in der Portal-Klasse
	 */
	@SuppressWarnings("unchecked")
	public static void recoverSavedState() {
		ArrayList<Nachricht> nachrichten = new ArrayList<Nachricht>();
		ArrayList<Buchung> buchungen = new ArrayList<Buchung>();
		ArrayList<Angebot> angebote = new ArrayList<Angebot>();
		ArrayList<Anbieter> anbieter = new ArrayList<Anbieter>();
		ArrayList<Betreiber> betreiber = new ArrayList<Betreiber>();
		ArrayList<Kunde> kunden = new ArrayList<Kunde>();
		int buchungsAnzahl = 0, angebotsAnzahl = 0;
		if (msgFile.exists())
			nachrichten = (ArrayList<Nachricht>) xs.fromXML(msgFile);
		if (buchFile.exists()){
			Object[] attr = (Object[]) xs.fromXML(buchFile);
			buchungen = (ArrayList<Buchung>) attr[0];
			buchungsAnzahl = (Integer) attr[1];
		}
		if (offFile.exists()){
			Object[] attr = (Object[]) xs.fromXML(offFile);
			angebote = (ArrayList<Angebot>) attr[0];
			angebotsAnzahl = (Integer) attr[1];
		}
		if (anbFile.exists())
			anbieter = (ArrayList<Anbieter>) xs.fromXML(anbFile);
		if (betrFile.exists())
			betreiber = (ArrayList<Betreiber>) xs.fromXML(betrFile);
		if (kundFile.exists())
			kunden = (ArrayList<Kunde>) xs.fromXML(kundFile);
		Portal.recover(new Accountverwaltung(anbieter, betreiber, kunden),
				new Angebotsverwaltung(angebote), angebotsAnzahl,
				new Buchungsverwaltung(buchungen), buchungsAnzahl, 
				new Nachrichtenverwaltung(nachrichten));
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
			e.printStackTrace();
		}

		String[] s = new String[reslist.size()];
		for (int i = 0; i < s.length; i++)
			s[i] = (String) reslist.get(i);
		return s;
	}
	
	/**
	 * Gibt den Datei-Inhalt als einzelnen String aus. Zeilen werden mit \n getrennt
	 * @param f File-Objekt als Referenz zur Datei
	 * @return 
	 */
	public static String getStringFromFile(File f){
		String arr[] = getStringArrayFromFile(f);
		String res = "";
		for(String s : arr)
			res += s+"\n";
		return res;
	}

	private static XStream initXStream() {
		XStream res = new XStream();
		String	osn = System.getProperty("os.name"),
					osv = System.getProperty("os.version");
		if(		(osn.matches(".*Linux.*") && osv.startsWith("2.6."))
				|| osn.matches(".*OS X.*")){
			res = new XStream(new DomDriver());
			System.out.println("Dom initialized");
		}
//		res.alias(name, type);
		return res;
	}
	
}
