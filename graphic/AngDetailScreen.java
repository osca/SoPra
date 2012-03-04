package graphic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import buchungen.InvalidDateException;

import main.Portal;
import accounts.Account;
import accounts.Anbieter;
import accounts.Kunde;
import angebote.typen.Angebot;


public class AngDetailScreen extends JPanel{
	private JPanel up;
	private JPanel sub_a;
	private JPanel sub_b;
	private JPanel mid;
	private JPanel down;
	private JLabel name;
	private JLabel typ;
	private JLabel vondatum;
	private JLabel bisdatum;
	private JLabel anbieterl;
	
	private JTextArea fullinfo;
	private JLabel nullAcc;
	
	private JButton buchen = new JButton("Buchen");
	private JButton melden = new JButton("Melden");
	private JButton kommentieren = new JButton("Kommentieren");
	private JButton loeschen = new JButton("Löschen");
	private JButton editieren = new JButton("Editieren");
	private JButton kontaktieren = new JButton("kontaktieren");
	
	final Angebot angebot;
	final Anbieter anbieter;
	
	public AngDetailScreen(Angebot a){
		
		angebot = a;
		anbieter = Portal.Angebotsverwaltung().getAnbieter(angebot);
		
		this.setLayout(new BorderLayout());
		up = new JPanel(new GridLayout(0,2));
		mid = new JPanel(new GridLayout(1,0));
		down = new JPanel(new GridLayout(1,0));
		
		name = new JLabel(angebot.getIdentifier());
		typ = new JLabel (""+Angebot.typToString(angebot.getTyp()));
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		vondatum = new JLabel(formatter.format(angebot.getStartdatum()));
		bisdatum = new JLabel(formatter.format(angebot.getEnddatum()));
		anbieterl = new JLabel(); 
		
		sub_a = new JPanel(new GridLayout(6,0));
		sub_a.add(name);
		sub_a.add(typ);
		sub_a.add(vondatum);		
		sub_a.add(bisdatum);
		sub_a.add(anbieterl);
		sub_b = new JPanel(new GridLayout(6,0));
		String k[] = angebot.getErlaubteKriterien(); //WTF funkt?!
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
		
		switch (Portal.Accountverwaltung().getLoggedIn().getTyp()){
		case Account.NONE:
			nullAcc = new JLabel(MeldeDienst.MSG_LOGIN_FEHLT);
			down.add(BorderLayout.CENTER, nullAcc);
			break;
		case Account.KUNDE :
			down.add(kommentieren);
			down.add(buchen);
			down.add(melden);
			down.add(kontaktieren);
			break;
		
		case Account.ANBIETER:
			down.add(loeschen);
			down.add(editieren);
			break;
		
		case Account.BETREIBER:
			down.add(loeschen);
			down.add(kontaktieren);
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
				{
					if(JOptionPane.showConfirmDialog(up.getParent(), MeldeDienst.QSN_BUCHEN) == JOptionPane.OK_OPTION)
					{
						DialogScreen dialog = new DialogScreen(null,"Buchen",DialogScreen.OK_CANCEL_OPTION)
						{
							@Override
							public void onOK()
							{
								try 
								{// TODO scheiss datum
									Portal.Buchungsverwaltung().createBuchung((Kunde) Portal.Accountverwaltung().getLoggedIn(), angebot, angebot.getStartdatum(),angebot.getEnddatum()); //TODO Die daten bei buchung
								}
								catch (Exception e) 
								{
									e.printStackTrace();
								}
							}
						};
						dialog.setEditable(false);
						dialog.setLabelContent(MeldeDienst.MSG_AGB_ERKLÄRUNG + anbieterl.getText(), DialogScreen.LABEL_LEFT);
						dialog.setLabelContent(MeldeDienst.MSG_GESAMMT_BEWERUNG + anbieter.getWertung(), DialogScreen.LABEL_RIGHT);
						dialog.setContent(anbieter.getAgb());
					}
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
					if(JOptionPane.showConfirmDialog(up.getParent(), MeldeDienst.QSN_ANGEBOT_MELDEN, "Angebot melden", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION)
					{ //TODO welcher betreiber soll die meldung bekommen
						Portal.Nachrichtenverwaltung().sendeNachricht(Portal.Accountverwaltung().getLoggedIn(), Portal.Accountverwaltung().getBetreiber().get(0), MeldeDienst.MSG_BESCHWERDE, MeldeDienst.MSG_ANGEBOT_GEMELDET,angebot);
						Portal.Nachrichtenverwaltung().sendeNachricht(Portal.Accountverwaltung().getLoggedIn(), anbieter, MeldeDienst.MSG_BESCHWERDE, MeldeDienst.MSG_ANGEBOT_GEMELDET, angebot);
					}
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
				try
				{
					Portal.Nachrichtenverwaltung().sendeNachricht(Portal.Accountverwaltung().getLoggedIn(), anbieter, "Kontaktaufnahme",JOptionPane.showInputDialog(up.getParent(), "Bitte geben sie Ihre Nachricht ein!"), angebot);
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(up.getParent(), e.toString());
				}
			}
		});
	}
}
