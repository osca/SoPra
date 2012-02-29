package graphic;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import accounts.Account;
import angebote.kriterien.Kriterium;


public class AngDetailScreen extends JPanel{
	private JScrollPane dscroll;
	private JPanel dPanel;
	private JPanel up;
	private JPanel sub_a;
	private JPanel sub_b;
	private JPanel mid;
	private JPanel down;
	private JLabel name;
	private JLabel typ;
	private JLabel datum;
	private JLabel anbieter;
	
	private JLabel fullinfo;
	
	private JLabel nullAcc;
	
	private JButton buchen;
	private JButton melden;
	//private JButton agb;
	private JButton kommentieren;
	
	private JButton loeschen;
	private JButton editieren;
	
	private JButton kontaktieren;
	
	
	public AngDetailScreen(int usertype, angebote.typen.Angebot a){
		dscroll = new JScrollPane();
		dPanel = new JPanel(new BorderLayout(5,5));
		up = new JPanel(new GridLayout(0,2));
		mid = new JPanel(new GridLayout(1,0));
		down = new JPanel(new BorderLayout(5,5));
		
		name = new JLabel(a.getIdetifier());
		typ = new JLabel (""+a.getTyp());		//GUCKEN BITTE
		datum = new JLabel(a.getDaten().toString());	// DATE			Rudis alte version; edit: Benjamin
		anbieter = new JLabel(); // edit  wenn implementiert
		
//		int nDaten = a.getDaten().length;
//		daten = new JLabel[nDaten];
		
//		for(int i = 0; i < nDaten; i++) {
//			daten[i].setText(a.getDaten()[i].toString());
//			sub_a.add(daten[i]);
//		}
		
		sub_a = new JPanel(new GridLayout(6,0));
		sub_a.add(name);
		sub_a.add(typ);
		sub_a.add(datum);				//Rudis alte version; edit: Benjamin
		sub_a.add(anbieter);
		sub_b = new JPanel(new GridLayout(6,0));
		String k[] = a.getErlaubteKriterien(); 
		for (int i =0;i<k.length;i++){
			JLabel krit = new JLabel(k[i]);
			sub_b.add(krit);
		}
		up.add(sub_a);
		up.add(sub_b);
		
		fullinfo = new JLabel(a.getFullInfo());
		mid.add(fullinfo);
		
		if(usertype==0){
			nullAcc = new JLabel("Sie müssen sich einlogen um weitere Aktionen durchzuführen");
			down.add(BorderLayout.CENTER, nullAcc);
		}
		else switch (usertype){
		case Account.KUNDE :{
			kommentieren = new JButton("Kommentieren");
			buchen = new JButton("Buchen");
			melden = new JButton("Melden");
			down.add(BorderLayout.EAST, kommentieren);
			down.add(BorderLayout.CENTER, buchen);
			down.add(BorderLayout.CENTER, melden);
		}
		case Account.ANBIETER:{
			loeschen = new JButton("Loeschen"); 
			editieren = new JButton("Editieren");
			down.add(BorderLayout.EAST, loeschen);
			down.add(BorderLayout.WEST, editieren);
		}
		case Account.BETREIBER:{
			loeschen = new JButton("Loeschen");
			kontaktieren = new JButton("Kontaktieren");
			down.add(BorderLayout.EAST, loeschen);
			down.add(BorderLayout.WEST, kontaktieren);
		}
		}
		
		
		
		dPanel.add(BorderLayout.NORTH, up);
		dPanel.add(BorderLayout.CENTER, mid);
		dPanel.add(BorderLayout.SOUTH, down);
		dscroll.add(dPanel);
		add(dscroll);
	}
}
