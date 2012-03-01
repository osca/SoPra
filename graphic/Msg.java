package graphic;

import accounts.*;
import angebote.typen.Angebot;
import main.Portal;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


/**
 * @author Rudi
 * @edit : Benjamin
 */
public class Msg extends JDialog {
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
	
	// added bei Benjamin
	private Account sender;
	private Account empf;
	private String betr;
	private Angebot bezug;
	
	// added bei benjamin: Angebot angebot
	public Msg(Account acc, Account ziel, String b, Angebot angebot){
		sender = acc;
		empf = ziel;
		betr = b;
		bezug = angebot;
		// to do set size
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		setLayout(new BorderLayout (5, 5));
		setSize(new Dimension(500, 350));
		
		up = new JPanel(new GridLayout (3, 0));
		mid = new JPanel(new BorderLayout (5, 5));
		down = new JPanel(new BorderLayout (5, 5));
		
		absender = new JLabel(acc.getName());
		up.add(absender);
		empfaenger = new JLabel(ziel.getName());
		up.add(empfaenger);
		betreff = new JLabel(b);
		up.add(betreff);

		m = new JTextArea();
		mid.add(m);
		
		senden = new JButton("Senden");
		senden.addActionListener(sendenListener);
		verwerfen = new JButton ("Verwerfen");
		verwerfen.addActionListener(verwerfenListener);
		down.add(BorderLayout.EAST, senden);
		down.add(BorderLayout.WEST, verwerfen);
		
		add(BorderLayout.NORTH, up);
		add(BorderLayout.CENTER, mid);
		add(BorderLayout.SOUTH, down);
		
		setVisible(true);
		setAlwaysOnTop(true);
	}
	
	public String getText(){
		return msgText;
	}
	
	ActionListener sendenListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			Portal.getSingletonObject().getNachrichtenverwaltung().sendeNachricht(sender, empf, betr, msgText, bezug);
		}
	};
	
	ActionListener verwerfenListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	};
}
