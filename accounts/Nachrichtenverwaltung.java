package accounts;

public class Nachrichtenverwaltung {
	
	public void sendeNachricht(Account absender, Account empfaegner, String betreff, String text){
		Nachricht msg = new Nachricht(betreff, text);
		empfaegner.addNachricht(msg);
		//absender.addNachricht(msg);	//TODO Postausgang??
	}
}
