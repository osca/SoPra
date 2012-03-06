package angebote.kriterien;

import java.io.File;

import main.Datenhaltung;

public class Land extends Kriterium{
	
	public final static String name = "Land";
	private static File laenderFile = new File("laender.txt");
	public final static String[] wertebereich = Datenhaltung.getStringArrayFromFile(laenderFile);
	
	public Land(String pwert) throws IllegalArgumentException {
		super(pwert);
	}

	public String getName(){
		return name;
	}
	
	public String[] getOrte(){
		return getOrte(wert);
	}
	
	public static String[] getOrte(String laenderName){
		return Datenhaltung.getStringArrayFromFile(new File("cities/"+laenderName+".txt"));
	}
}
