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
	/**
	 * 
	 * @return
	 */
	public ArrayList<Anbieter> getAnbieter(){
		return (ArrayList<Anbieter>) xs.fromXML(anbFile);
	}
	public ArrayList<Betreiber> getBetreiber(){
		return (ArrayList<Betreiber>) xs.fromXML(betrFile);
	}
	public ArrayList<Kunde> getKunde(){
		return (ArrayList<Kunde>) xs.fromXML(kundFile);
	}
}
