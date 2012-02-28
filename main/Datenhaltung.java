package main;
import java.io.File;
import java.util.ArrayList;

import accounts.*;

import com.thoughtworks.xstream.XStream;

public class Datenhaltung {
	private File anbFile = new File("Anbieter.xml"),
				 betrFile = new File("Betreiber.xml"),
				 kundFile = new File("Kunde.xml");
	private XStream xs = new XStream();
	
	public ArrayList<Anbieter> getAnbieter(){
		return null;
	}
	public ArrayList<Betreiber> getBetreiber(){
		return null;
	}
	public ArrayList<Kunde> getKunde(){
		return null;
	}
}
