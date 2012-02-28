package graphic;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import angebote.typen.Angebot;


public class main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		
		Angebot x = new Angebot ("one","jfnjrnafjndkfkydfafasfaknkdnfknsdkfn");
		Angebot y = new Angebot ("two","nvcs dnv sdm vmsd vms dmv smdv smd vmsd v");
		Angebot z = new Angebot ("three","ksdmvnknsdkvnskdnvksd kjds ksd ksksdk");
		ArrayList<Angebot> list = new ArrayList<Angebot>();
		list.add(x);
		list.add(y);
		list.add(z);
		
		Mainframe j=new Mainframe();
		j.setLayout(new BorderLayout(5,5));
		ListeScreen<Angebot> a = new ListeScreen<Angebot>(j,list);
		j.add(BorderLayout.CENTER,a);
				/**
		for(int i=0;i<list.size();i++){
			JPanel a_i= new JPanel ();
			JLabel l = new JLabel(list.get(i).getName());
			a_i.add(l);
			JLabel d = new JLabel(list.get(i).getDis());
			a_i.add(d);
			gui.sPanel.add(a_i);
		}
				**/
		
	
		j.setVisible(true);
	
		j.pack();
		

	}
	


}
