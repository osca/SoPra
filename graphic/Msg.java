package graphic;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Msg extends JDialog{
	private JLabel empfaenger;
	private JLabel betreff;
	private JTextField m;
	private JPanel up;
	private JPanel mid;
	private JPanel down;
	private JButton senden;
	private JButton verwerfen;
	
	public Msg(String anbieter, String b){
		// to do set size
		setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
		setLayout(new BorderLayout (5,5));
		up = new JPanel(new GridLayout (2,0));
		mid = new JPanel(new BorderLayout (5,5));
		down = new JPanel(new BorderLayout (5,5));
		
		empfaenger = new JLabel(anbieter);
		up.add(empfaenger);
		betreff = new JLabel(b);
		up.add(betreff);
		
		m = new JTextField();
		mid.add(m);
		
		senden = new JButton("Senden");
		verwerfen = new JButton ("Verwerfen");
		down.add(BorderLayout.EAST, senden);
		down.add(BorderLayout.WEST, verwerfen);
		
		add(BorderLayout.NORTH, up);
		add(BorderLayout.CENTER, mid);
		add(BorderLayout.SOUTH, down);
		setVisible(true);
		setAlwaysOnTop(true);
	}
}
