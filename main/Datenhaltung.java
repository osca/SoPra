package main;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import accounts.*;

import com.thoughtworks.xstream.XStream;

public class Datenhaltung {
	private File anbFile = new File("Anbieter.xml"),
				 betrFile = new File("Betreiber.xml"),
				 kundFile = new File("Kunde.xml");
	private XStream xs = new XStream();
	/**
	 * Speichert alle Accounts in spezifische XML-Dateien aufgespalten nach Benutzertyp
	 * @param av Angebotsverwaltungsobjekt
	 * @throws IOException Fehler beim Schreiben
	 */
	public void saveAllAccounts(Accountverwaltung av) throws IOException{
		xs.toXML(av.getAnbieter(), new FileWriter(anbFile));
		xs.toXML(av.getBetreiber(), new FileWriter(betrFile));
		xs.toXML(av.getKunden(), new FileWriter(kundFile));
	}
	/** Liest die aktuell in XML gespeicherte Liste an Anbietern aus
	 * @return Anbieter-Liste
	 */
	public ArrayList<Anbieter> getAnbieter(){
		return (ArrayList<Anbieter>) xs.fromXML(anbFile);
	}
	/** Liest die aktuell in XML gespeicherte Liste an Betreiber aus
	 * @return Betreiber-Liste
	 */
	public ArrayList<Betreiber> getBetreiber(){
		return (ArrayList<Betreiber>) xs.fromXML(betrFile);
	}
	/** Liest die aktuell in XML gespeicherte Liste an Kunden aus
	 * @return Kunden-Liste
	 */
	public ArrayList<Kunde> getKunde(){
		return (ArrayList<Kunde>) xs.fromXML(kundFile);
	}
	/**
	 * Liest ein NICHT-XML-File ein und gibt die einzelnen Zeilen als String-Array aus
	 * @param laenderFile
	 * @return
	 */
	public String[] getStringArrayFromFile(File laenderFile) {
		//TODO blabla
		return null;
	}
}
