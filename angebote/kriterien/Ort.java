//wertebereich waere echt groß

package angebote.kriterien;


//@author stephan

public class Ort extends Kriterium{
	
	private final static String name = "Ort";

	public Ort(String pwert) throws IllegalArgumentException {
		super(pwert);
	}

	public static String getName() {
		return name;
	}
	
}
