package angebote.kriterien;

import java.io.File;

import main.Datenhaltung;

public class Land extends Kriterium{
	
	@SuppressWarnings("unused")
	private final static String name = "Land";
	private static File laenderFile = new File("laender");
	private static String[] wertebereich = Datenhaltung.getStringArrayFromFile(laenderFile);

	public Land(String pwert) throws IllegalArgumentException {
		super(pwert);
	}
	
	public String[] getWertebereich(){
		return wertebereich;
	}
	

}
