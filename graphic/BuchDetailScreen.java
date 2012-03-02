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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import main.Portal;

import buchungen.Buchung;

public class BuchDetailScreen extends JPanel {
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
	
	private JButton aenderungsanfrage;
	private JButton stornieren;
	
	public BuchDetailScreen(Buchung b){
		dscroll = new JScrollPane();
		dPanel = new JPanel(new BorderLayout(5,5));
		up = new JPanel(new GridLayout(0,2));
		mid = new JPanel(new GridLayout(1,0));
		down = new JPanel(new BorderLayout(5,5));
		
		name = new JLabel(b.getIdentifier());
		typ = new JLabel (""+Portal.getSingletonObject().getBuchungsverwaltung().getReferringAngebot(b).getTyp());		//GUCKEN BITTE
		datum = new JLabel(b.getVon().toString()+ " - "+b.getBis().toString());	// DATE
		anbieter = new JLabel(); // edit  wenn implementiert
		
		sub_a = new JPanel(new GridLayout(6,0));
		sub_a.add(name);
		sub_a.add(typ);
		sub_a.add(datum);
		sub_a.add(anbieter);
		
		sub_b = new JPanel(new GridLayout(6,0));
		String k[] = Portal.getSingletonObject().getBuchungsverwaltung().getReferringAngebot(b).getErlaubteKriterien(); 
		for (int i =0;i<k.length;i++){
			JLabel krit = new JLabel(k[i]);
			sub_b.add(krit);
		}
		up.add(sub_a);
		up.add(sub_b);
		
		fullinfo = new JLabel(Portal.getSingletonObject().getBuchungsverwaltung().getReferringAngebot(b).getFullInfo());
		mid.add(fullinfo);
		
		aenderungsanfrage = new JButton("Aenderungsanfrage");
		aenderungsanfrage.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				JTextField betreff = new JTextField("Betreff");
				JTextArea area = new JTextArea("");
				JOptionPane.showConfirmDialog(null,new Object[]{betreff,area},"Login",JOptionPane.OK_CANCEL_OPTION);
			}
		});
		stornieren = new JButton("Stronieren");
		down.add(BorderLayout.EAST, aenderungsanfrage);
		down.add(BorderLayout.WEST, stornieren);
		
		dPanel.add(BorderLayout.NORTH, up);
		dPanel.add(BorderLayout.CENTER, mid);
		dPanel.add(BorderLayout.SOUTH,down);
		
		this.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.LIGHT_GRAY));
		
		//dscroll.add(dPanel);
		add(dPanel);
	}
}
