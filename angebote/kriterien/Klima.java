package angebote.kriterien;

public class Klima extends Kriterium{
	Vector<String>[] klimata = {""};
	
	Klima(String[] pwerte) {
		super(pwerte);
		erlaubteWerte = klimata;
	}
}