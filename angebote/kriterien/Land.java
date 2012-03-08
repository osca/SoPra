package angebote.kriterien;

import java.io.File;

import main.Datenhaltung;

/**
 * Land - Erbendes Kriterium
 * 
 * @author osca
 */
public class Land extends Kriterium{
	
	private static File laenderFile = new File("laender.txt");
	
	public final static String name = "Land";
	public final static String[] wertebereich = Datenhaltung.getStringArrayFromFile(laenderFile);
	
	/**
	 * Konstruktor
	 * 
	 * @param pwert Land
	 * @throws IllegalArgumentException
	 */
	public Land(String pwert) throws IllegalArgumentException {
		super(pwert);
	}

	/**
	 * Get Kriterienname
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Get Orte
	 * 
	 * @return Orteliste
	 */
	public String[] getOrte() {
		return getOrte(wert);
	}
	
	/**
	 * Get Orte nach Laendername
	 * 
	 * @param laenderName Laendername
	 * @return Orteliste
	 */
	public static String[] getOrte(String laenderName){
		return Datenhaltung.getStringArrayFromFile(new File("cities/"+laenderName+".txt"));
	}
}
