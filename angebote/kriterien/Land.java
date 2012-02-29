package angebote.kriterien;

import java.io.File;

import main.Portal;

public class Land extends Kriterium{
	
	public final static String name = "Land";
	private static File laenderFile = new File("laender");
	private static String[] wertebereich = Portal.getSingletonObject().getDatenhaltung().getStringArrayFromFile(laenderFile);

	public Land(String pwert) throws IllegalArgumentException {
		super(pwert);
	}
	
	public String[] getWertebereich(){
		return wertebereich;
	}
	

}
