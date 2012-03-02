package graphic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import main.Portal;
import accounts.Account;
import angebote.typen.Angebot;


public class AngDetailScreen extends JPanel{
	private JPanel up;
	private JPanel sub_a;
	private JPanel sub_b;
	private JPanel mid;
	private JPanel down;
	private JLabel name;
	private JLabel typ;
	private JLabel datum;
	private JLabel anbieter;
	
	private JTextArea fullinfo;
	private JLabel nullAcc;
	
	private JButton buchen = new JButton("Buchen");
	private JButton melden = new JButton("Melden");
	private JButton kommentieren = new JButton("Kommentieren");
	private JButton loeschen = new JButton("Löschen");
	private JButton editieren = new JButton("Editieren");
	private JButton kontaktieren = new JButton("kontaktieren");
	
	final Angebot angebot;
	
	public AngDetailScreen(int usertype, Angebot a){
		
		angebot = a;
		
		this.setLayout(new BorderLayout());
		up = new JPanel(new GridLayout(0,2));
		mid = new JPanel(new GridLayout(1,0));
		down = new JPanel(new BorderLayout(5,5));
		
		name = new JLabel(angebot.getIdentifier());
		typ = new JLabel (""+angebot.getTyp());		
		datum = new JLabel(angebot.getDaten()[0].toString());	// DATE			Rudis alte version; edit: Benjamin
		anbieter = new JLabel(); 
		
		sub_a = new JPanel(new GridLayout(6,0));
		sub_a.add(name);
		sub_a.add(typ);
		sub_a.add(datum);				
		sub_a.add(anbieter);
		sub_b = new JPanel(new GridLayout(6,0));
		String k[] = angebot.getErlaubteKriterien(); 
		for (int i =0;i<k.length;i++){
			JLabel krit = new JLabel(k[i]);
			sub_b.add(krit);
		}
		up.add(sub_a);
		up.add(sub_b);
		
		fullinfo= new JTextArea(angebot.getFullInfo());
		fullinfo.setLineWrap(true);
		fullinfo.setWrapStyleWord(true);
		fullinfo.setBackground(Color.LIGHT_GRAY);
		fullinfo.setEditable(false);
		
		mid.add(fullinfo);
		
		///////////////////
		
		if(usertype==0){
			nullAcc = new JLabel("Sie mï¿½ssen sich einlogen um weitere Aktionen durchzufï¿½hren");
			down.add(BorderLayout.CENTER, nullAcc);
		}
		else switch (usertype){
		case Account.KUNDE :
			down.add(BorderLayout.EAST, kommentieren);
			down.add(BorderLayout.CENTER, buchen);
			down.add(BorderLayout.WEST, melden);
			break;
		
		case Account.ANBIETER:
			down.add(BorderLayout.EAST, loeschen);
			down.add(BorderLayout.WEST, editieren);
			break;
		
		case Account.BETREIBER:
			down.add(BorderLayout.EAST, loeschen);
			down.add(BorderLayout.WEST, kontaktieren);
			break;
		
		}
		
		this.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.LIGHT_GRAY));
		
		this.add(BorderLayout.NORTH, up);
		this.add(BorderLayout.CENTER, mid);
		this.add(BorderLayout.SOUTH, down);
		
		/////////////////
		
		
		buchen.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				try
				{//TODO
					JOptionPane.showConfirmDialog(up.getParent(), angebot.getAnbieter().getAgb(), "Buchung", JOptionPane.OK_CANCEL_OPTION);
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(up.getParent(), e.toString());
				}
			}
		});
		melden.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				try
				{
					if(JOptionPane.showConfirmDialog(up.getParent(), "Sind Sie sicher, dass sie dieses Angebot Melden möchten?", "Angebot melden", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION)
						Portal.getSingletonObject().getNachrichtenverwaltung().sendeNachricht(Portal.getSingletonObject().getAccountverwaltung().getLoggedIn(), angebot.getAnbieter(), "Angebot gemeldet!", "Ein Angebot wurde Gemeldet", angebot);
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(up.getParent(), e.toString());
				}
			}
		});
		kommentieren.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				String s = JOptionPane.showInputDialog(up.getParent(), "", "", JOptionPane.OK_CANCEL_OPTION);
				
			}
		});
		loeschen.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				
			}
		});
		editieren.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				
			}
		});
		kontaktieren.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				
			}
		});
	}
}
