package angebote.kriterien;

public class KriterienVerwaltung {
	public String[] nameTOErlaubteWerte(String name){
		if		(name.equals(Bierpreis.name)) 		return Bierpreis.wertebereich;
		else if (name.equals(Klasse.name)) 			return Klasse.wertebereich;
		else if (name.equals(Klima.name))			return Klima.wertebereich;
		else if (name.equals(Land.name))			return Land.wertebereich;
		else if (name.equals(Ort.name))				return Ort.wertebereich;
		else if (name.equals(Sterne.name))			return Sterne.wertebereich;
		else if (name.equals(Verpflegungsart.name))	return Verpflegungsart.wertebereich;
		else 										return null;
	}
	

}
