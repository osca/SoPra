package graphic;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import accounts.Account;

public class Msg extends JDialog implements ActionListener{
	private JLabel absender;
	private JLabel empfaenger;
	
	private JLabel betreff;
	private JTextArea m;
	private JPanel up;
	private JPanel mid;
	private JPanel down;
	private JButton senden;
	private JButton verwerfen;
	
	private String msgText;
	
	public Msg(Account acc, Account ziel, String b){
		// to do set size
		setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
		setLayout(new BorderLayout (5,5));
		up = new JPanel(new GridLayout (3,0));
		mid = new JPanel(new BorderLayout (5,5));
		down = new JPanel(new BorderLayout (5,5));
		
		absender = new JLabel(acc.getName());
		up.add(absender);
		empfaenger = new JLabel(ziel.getName());
		up.add(empfaenger);
		betreff = new JLabel(b);
		up.add(betreff);
		

		m = new JTextArea();
		mid.add(m);
		
		senden = new JButton("Senden");
		senden.addActionListener(this);
		verwerfen = new JButton ("Verwerfen");
		verwerfen.addActionListener(this);
		down.add(BorderLayout.EAST, senden);
		down.add(BorderLayout.WEST, verwerfen);
		
		add(BorderLayout.NORTH, up);
		add(BorderLayout.CENTER, mid);
		add(BorderLayout.SOUTH, down);
		setVisible(true);
		setAlwaysOnTop(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==senden){
			msgText = m.getText();
		}
		else if(e.getSource()==verwerfen){
			msgText = null;
			hide();
		}
		
	}
	
	public String getText(){
		return msgText;
	}
}
