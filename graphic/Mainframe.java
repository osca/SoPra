import javax.swing.JFrame;


public class Mainframe extends JFrame{
	
	
	public <T extends Listable> void showDetail(T obj){
		if(obj.getClass().equals(Angebot.class)){
		
			
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
	

}
