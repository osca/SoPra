package graphic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.text.MaskFormatter;

import main.Portal;
import accounts.Account;
import accounts.Anbieter;
import accounts.Kunde;
import accounts.LoeschenNichtMoeglichException;
import angebote.kriterien.Kriterium;
import angebote.typen.Angebot;
import buchungen.InvalidDateException;


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
	private JButton loeschen = new JButton("Loeschen");
	private JButton editieren = new JButton("Editieren");
	private JButton kontaktieren = new JButton("Kontaktieren");
	
	final Angebot angebot;
	final Anbieter anbieter;
	
	public AngDetailScreen(Angebot a){
		
		angebot = a;
		anbieter = Portal.Angebotsverwaltung().getAnbieter(angebot);
		
		this.setLayout(new BorderLayout());
		up = new JPanel(new GridLayout(0,2));
		mid = new JPanel(new GridLayout(1,0));
		down = new JPanel(new GridLayout(1,0));
		
		name = new JLabel(angebot.getName());
		typ = new JLabel (""+Angebot.typToString(angebot.getTyp()));
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		vondatum = new JLabel(formatter.format(angebot.getStartdatum()));
		bisdatum = new JLabel(formatter.format(angebot.getEnddatum()));
		anbieterl = new JLabel(); 
		
		sub_a = new JPanel(new GridLayout(6,2));
		JLabel name_l= new JLabel("Name:");
		sub_a.add(name_l);
		sub_a.add(name);
		JLabel typ_label= new JLabel("Typ:");
		sub_a.add(typ_label);
		sub_a.add(typ);
		JLabel vd_label= new JLabel("Startdatum:");
		sub_a.add(vd_label);
		sub_a.add(vondatum);
		JLabel bd_label= new JLabel("Enddatum:");
		sub_a.add(bd_label);
		sub_a.add(bisdatum);
		JLabel anbieter_label= new JLabel("Anbieter:");
		sub_a.add(anbieterl);
		sub_b = new JPanel(new GridLayout(6,2));
		ArrayList<Kriterium> k = angebot.getKriterien(); 
		for (int i =0;i<k.size();i++){
			JLabel krit = new JLabel(""+k.get(i).name);
			sub_b.add(krit);
			JLabel krit1 = new JLabel(""+k.get(i).getWert());
			sub_b.add(krit1);
			
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
					JLabel label = new JLabel("Geben Sie den Zeitraum an:");
					JLabel fromLabel = new JLabel("Von:");
					JLabel toLabel = new JLabel("Bis:");
					JFormattedTextField fromField = new JFormattedTextField(new MaskFormatter("##/##/####"));
					JFormattedTextField toField = new JFormattedTextField(new MaskFormatter("##/##/####"));
					
					if(JOptionPane.showConfirmDialog(null, new Object[]{label, fromLabel, fromField, toLabel, toField}, "Login", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION)
					{
						if (fromField.getText().length() == 0 || toField.getText().length() == 0) throw new IllegalArgumentException();
						
						SimpleDateFormat to = new SimpleDateFormat("dd/MM/yyyy");

						final Date toDate = to.parse(toField.getText());
						final Date fromDate = to.parse(fromField.getText());
						
						if(fromDate.after(toDate) || fromDate.before(angebot.getStartdatum()) || toDate.after(angebot.getEnddatum())){
							throw new InvalidDateException();
						}

						
						DialogScreen dialog = new DialogScreen(null, "Buchen", DialogScreen.OK_CANCEL_OPTION)
						//TODO vllt. da das datum von bis setten und dann der buchung �bergeben
						{
							@Override
							public void onOK()
							{
								try 
								{
									// edit benjamin: angebot.getStartdatum() durch fromDate ersetzt etc.
									Portal.Buchungsverwaltung().createBuchung((Kunde) Portal.Accountverwaltung().getLoggedIn(), angebot, fromDate, toDate); 
								}
								catch (Exception e) 
								{
									e.printStackTrace();
								}
							}
						};
						dialog.setEditable(false);
						dialog.setLabelContent(MeldeDienst.MSG_AGB_ERKLAERUNG + anbieterl.getText(), DialogScreen.LABEL_LEFT);
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
					{ 
						Account account = Portal.Accountverwaltung().getLoggedIn();
						Portal.Nachrichtenverwaltung().sendeMeldungAnAlleBetreiber(account, MeldeDienst.MSG_BESCHWERDE, MeldeDienst.MSG_ANGEBOT_GEMELDET,angebot);
						Portal.Nachrichtenverwaltung().sendeNachricht(account, anbieter, MeldeDienst.MSG_BESCHWERDE, MeldeDienst.MSG_ANGEBOT_GEMELDET+"\n"+"Anbieter: "+anbieter+"\n"+"Kunde: "+account+"\n"+"Angebot: "+angebot.getName(), angebot);
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
				try {
					Portal.Angebotsverwaltung().delAngebot(angebot);
					JOptionPane.showMessageDialog(null, "Angebot erfolgreich geloescht");
				} catch (LoeschenNichtMoeglichException e) {
					JOptionPane.showConfirmDialog(null, "Loeschen nicht moeglich");
					e.printStackTrace();
				}
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
