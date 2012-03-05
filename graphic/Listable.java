package graphic;

import buchungen.Bestaetigung;


public interface  Listable {
	
	public static final int ANGEBOT = 0,
							BUCHUNG = 1,
							NACHRICHT = 2,
							ACCOUNT = 3,
							KOMMENTAR = 4;
	
	public String getIdentifier();
	
	public String getAdditionalInfo();
	
	public String getFullInfo();
	
	public String getStatus(); // vllt. 
	
	public int getListableTyp();
	
}
