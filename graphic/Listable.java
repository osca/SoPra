package graphic;
/**
 * Das Interface Listable bildet eine einheitliche Schnittstelle
 * graphisch listbare Komponenten
 */
public interface  Listable {
	
	public static final int ANGEBOT = 50,
							BUCHUNG = 51,
							NACHRICHT = 52,
							ACCOUNT = 53,
							KOMMENTAR = 54;
	
	public String getIdentifier();
	
	public String getAdditionalInfo();
	
	public String getFullInfo();
	
	public String getStatus(); // vllt. 
	
	public int getListableTyp();
	
}
