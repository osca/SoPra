//wertebereich waere echt groß

package angebote.kriterien;


//@author stephan

public class Ort extends Kriterium{
	
	private final String name = "Ort";

	public Ort(String pwert) throws IllegalArgumentException {
		super(pwert);
	}

	public String getName() {
		return name;
	}
	
}
