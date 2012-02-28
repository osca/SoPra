package graphic;

import javax.swing.JFrame;

import accounts.Account;


public class Mainframe extends JFrame{
	
	private Account acc;
	
	public <T extends Listable> void showDetail(T obj){
		if(obj.getClass().equals(angebote.typen.Angebot.class)){
		
			
			/*ListeScreen.scroll.remove(ListeScreen.sPanel);
			ListeScreen.scroll.add();*/
			
		}
	/*	else if(obj.getClass().equals(Buchung.class)){
			
		}
		else if(obj.getClass().equals(Account.class)){
			
		}
		else if(obj.getClass().equals(Nachrichten.class)){
			
		}*/
	}
	
	public Account getUser(){
		return acc;
	}
	

}
