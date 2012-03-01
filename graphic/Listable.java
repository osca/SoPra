package graphic;


public interface  Listable {
	
	public static final int ANGEBOT = 0,
							BUCHUNG = 1,
							NACHRICHT = 2,
							ACCOUNT = 3;
	
	public String getIdetifier();
	
	public String getAdditionalInfo();
	
	public String getFullInfo();
	
	public String getStatus(); // vllt. 
	
	public int getListableTyp();
	
}
