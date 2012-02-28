package accounts;

public class Nachrichtenverwaltung {
	
	public void sendeNachricht(Account absender, Account empfänger, String betreff, String text){
		Nachricht msg = new Nachricht(betreff, text);
		empfänger.addNachricht(msg);
		//absender.addNachricht(msg);	//TODO Postausgang??
	}
}
