package graphic;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class AngDetailScreen extends JPanel{
	private JScrollPane dscroll;
	private JPanel dPanel;
	private JPanel up;
	private JPanel mid;
	private JPanel down;
	private JLabel name;
	private JLabel typ;
	private JLabel datum;
	private JLabel anbieter;
	
	private JLabel fullinfo;
	private JButton buchen;
	private JButton melden;
	//private JButton agb;
	private JButton kommentieren;
	
	private JButton loschen;
	private JButton editieren;
	
	private JButton kontaktieren;
	
	
	public AngDetailScreen(angebote.typen.Angebot a){
		dscroll = new JScrollPane();
		dPanel = new JPanel(new BorderLayout(5,5));
		up = new JPanel(new GridLayout(4,0));
		mid = new JPanel(new GridLayout(1,0));
		down = new JPanel(new BorderLayout(5,5));
		
		name = new JLabel(a.getIdetifier());
		typ = new JLabel (""+a.getTyp());		//GUCKEN BITTE
		datum = new JLabel(a.getDaten().toString());	// DATE
		anbieter = new JLabel(); // edit  wenn implementiert
		
		up.add(name);
		up.add(typ);
		up.add(datum);
		up.add(anbieter);
		
		fullinfo = new JLabel(a.getFullInfo());
		dPanel.add(BorderLayout.NORTH, up);
		dPanel.add(BorderLayout.CENTER, mid);
		dPanel.add(BorderLayout.SOUTH, down);
		dscroll.add(dPanel);
		add(dscroll);
		setVisible(true);
	}
}
