package angebote.kriterien;

public class Verpflegungsart extends Kriterium{
	String[] verpflegungsarten = {"All Inclusive","Vollpension","Halbpension","NurFrühstück","Ohne Verpflegung"};
	Verpflegungsart(String[] pwerte) {
		super(pwerte);
		erlaubteWerte = verpflegungsarten;
		// TODO Auto-generated constructor stub
	}
	

}
