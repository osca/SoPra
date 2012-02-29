package graphic;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class AngebotCreate extends JPanel{
	//b1.setToolTipText("Click this button to disable the middle button.");
	private JPanel up;
	private JPanel sub_a;
	private JPanel sub_b;
	private JPanel mid;
	private JPanel down;
	
	private JTextField name;
	private JComboBox typ;
	//private Shice date ---> vllt.
	private JTextField preis;
	private JTextField kap;
	private JLabel anbieter;
	
	//Krit dropdown
	
	private JTextField beschreibung;
	
	private JButton verwerfen;
	private JButton bestaetigen;
	
	public AngebotCreate(){
		setLayout(new BorderLayout(5,5));
		
		up = new JPanel(new GridLayout(2,0));
		sub_a = new JPanel(new GridLayout(6,0));
		//sub_b = new JPanel(new GridLayout(6,0)); bei krits
		
		mid = new JPanel(new GridLayout(1,0));
		beschreibung = new JTextField();
		mid.add(beschreibung);
		
		down = new JPanel(new BorderLayout(5,5));
		verwerfen = new JButton("Verwerfen");
		down.add(BorderLayout.EAST, verwerfen);
		bestaetigen = new JButton("Bestätigen");
		down.add(BorderLayout.WEST, bestaetigen);
		
		add(BorderLayout.NORTH, up);
		add(BorderLayout.CENTER, mid);
		add(BorderLayout.SOUTH, down);
		setVisible(true);
	}
	
}
