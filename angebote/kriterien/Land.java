package angebote.kriterien;

import java.io.File;

import main.Datenhaltung;

public class Land extends Kriterium{
	
	final static String name = "Land";
	private static File laenderFile = new File("laender");
	final static String[] wertebereich = Datenhaltung.getStringArrayFromFile(laenderFile);

	public Land(String pwert) throws IllegalArgumentException {
		super(pwert);
	}

	public String getName(){
		return name;
	}
}
