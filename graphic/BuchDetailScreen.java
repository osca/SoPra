package graphic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import main.Portal;

import accounts.Account;
import accounts.Anbieter;
import accounts.Kunde;
import angebote.typen.Angebot;
import buchungen.Bestaetigung;
import buchungen.Buchung;

public class BuchDetailScreen extends JPanel 
{

	private GridLayout grid;
	private JPanel north;
	private JPanel center;
	private JPanel south;

	private JTextArea fullinfo;
	
	private JButton buttonLinks;
	private JButton buttonRechts;
	private JButton buttonAngebot;
	
	private MainFrame frame;
	
	public BuchDetailScreen(final MainFrame mainframe, final JButton buchungsbutton, final Buchung buchung)
	{
		Border border = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.LIGHT_GRAY);
		grid = new GridLayout(0,1);
		grid.setHgap(4);
		this.setLayout(new BorderLayout());
		this.setBorder(border);
		
		frame = mainframe;
		
		////////////
		
		JPanel labelPanel = new JPanel(new BorderLayout());
		
		north = new JPanel(new GridLayout(1,0));
		center = new JPanel(new GridLayout(1,0));
		south = new JPanel(new GridLayout(1,0));
		
		north.setBorder(border);
		center.setBorder(border);
		
		////////////
		
		JPanel left = new JPanel(grid);
		final int length = 11;

		JLabel[] labels = new JLabel[length];
		labels[0] = new JLabel("  Name des Angebots: ");
		labels[1] = new JLabel("  Kunde der gebucht: ");
		labels[2] = new JLabel("  Angebotstyp: ");
		labels[3] = new JLabel("  Beginndatum: ");
		labels[4] = new JLabel("  Enddatum: ");
		labels[5] = new JLabel("  Status der Buchung: ");
		
		for(int i=0; i<(labels.length+1)/2; i++)
			left.add(labels[i]);
		
		///////
		
		JPanel right = new JPanel(grid);
		Angebot angebot = Portal.Angebotsverwaltung().getAngebotByNummer(buchung.getAngebotsNummer());
		Kunde kunde = Portal.Buchungsverwaltung().getKunde(buchung);

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		labels[6] = new JLabel(angebot.getName());
		labels[7] = new JLabel(kunde.getName());
		labels[8] = new JLabel(Angebot.convertTypToName(angebot.getAngebotsNummer()));
		labels[9] = new JLabel(formatter.format(angebot.getStartdatum()));
		labels[10] = new JLabel(formatter.format(angebot.getStartdatum()));
		
		final JLabel status = new JLabel(buchung.getStatus());
		
		for(int i=(length+1)/2; i<labels.length; i++)
			right.add(labels[i]);
		right.add(status);
		
		////////////
		
		fullinfo = new JTextArea(Portal.Buchungsverwaltung().getReferringAngebot(buchung).getFullInfo());
		fullinfo.setEditable(false);
		fullinfo.setLineWrap(true);
		fullinfo.setWrapStyleWord(true);
		fullinfo.setBackground(Color.LIGHT_GRAY);
		center.add(fullinfo);
		
		buttonLinks = new JButton();
		buttonRechts = new JButton();
		buttonAngebot = new JButton("Zum Angebot");
		buttonLinks.setPreferredSize(new Dimension(MainFrame.BUTTONWIDTH, MainFrame.BUTTONHEIGHT));
		buttonRechts.setPreferredSize(new Dimension(MainFrame.BUTTONWIDTH, MainFrame.BUTTONHEIGHT));
		buttonAngebot.setPreferredSize(new Dimension(MainFrame.BUTTONWIDTH, MainFrame.BUTTONHEIGHT));
		
		////////
				
		if(Portal.Accountverwaltung().getLoggedIn().getTyp() == Account.KUNDE)
		{
			buttonLinks.setText("Aenderungsanfrage");
			buttonLinks.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent arg0) 
				{
					DialogScreen dialog = new DialogScreen(frame, "Aenderungsanfrage", DialogScreen.OK_CANCEL_OPTION)
					{
						@Override
						public void onOK()
						{
							Portal.Nachrichtenverwaltung().sendeNachricht(Portal.Accountverwaltung().getLoggedIn(), Portal.Angebotsverwaltung().getAnbieter(Portal.Buchungsverwaltung().getReferringAngebot(buchung)), "Aenderungsanfrage", getContent(), Portal.Buchungsverwaltung().getReferringAngebot(buchung));
						}
					};
					dialog.addOnPanel(new JLabel(Portal.Accountverwaltung().getLoggedIn().getName()), DialogScreen.LABEL_LEFT);
				}
			});
			buttonRechts = new JButton("Stornieren");
			buttonRechts.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent arg0) 
				{
					if(JOptionPane.showConfirmDialog(fullinfo.getParent(), "Wollen Sie wirklich eine Stornierungsanfrage senden?", "Sicher?", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION)
					{
						Portal.Nachrichtenverwaltung().sendeNachricht(Portal.Accountverwaltung().getLoggedIn(), Portal.Angebotsverwaltung().getAnbieter(Portal.Buchungsverwaltung().getReferringAngebot(buchung)),"Stornierunsganfrage", "Der Kunde moechte seine Buchung stornieren", Portal.Buchungsverwaltung().getReferringAngebot(buchung));
						JOptionPane.showMessageDialog(fullinfo.getParent(), "Ihre Stornierungsanfrage wurde gesendet");
					}
				}
			});
		}
		if(Portal.Accountverwaltung().getLoggedIn().getTyp() == Account.ANBIETER)
		{
			buttonRechts.setText("Status aendern");
			buttonLinks.setText("Kontaktieren");
			buttonLinks.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent arg0) 
				{
					DialogScreen dialog = new DialogScreen(frame, "Kontaktaufnahme", DialogScreen.OK_CANCEL_OPTION)
					{
						@Override
						public void onOK()
						{
							Portal.Nachrichtenverwaltung().sendeNachricht(Portal.Accountverwaltung().getLoggedIn(), Portal.Accountverwaltung().getAccountByName(buchung.getKundenName()), "Kontaktaufnahme", getContent(), Portal.Buchungsverwaltung().getReferringAngebot(buchung));
						}
					};
					dialog.addOnPanel(new JLabel(Portal.Accountverwaltung().getLoggedIn().getName()), DialogScreen.LABEL_LEFT);
				}
			});
			buttonRechts.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent arg0) 
				{
					try
					{
						if(buchung.getBestaetigt() == Bestaetigung.JA)
						{
							if(JOptionPane.showConfirmDialog(null, "Wollen Sie die Buchung stornieren?","Buchungsanfrage", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION)
							{
								Portal.Buchungsverwaltung().setBestaetigt(buchung, Bestaetigung.NEIN);
								Anbieter an = (Anbieter)Portal.Accountverwaltung().getLoggedIn();
								Portal.Nachrichtenverwaltung().sendeNachricht(an, Portal.Accountverwaltung().getAccountByName(buchung.getKundenName()), "Buchungsbestaetigung", "Der Anbieter "+an.getName()+" hat Ihre Buchung abgelehnt!", Portal.Angebotsverwaltung().getAngebotByNummer(buchung.getAngebotsNummer()));
								JOptionPane.showMessageDialog(null, "Buchung abgelehnt");
								buttonRechts.setText("Buchung bestaetigen");
							}
						}
						else
						{
							if(JOptionPane.showConfirmDialog(null, "Wollen Sie die Buchung bestaetigen?","Buchungsanfrage", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION)
							{
								Portal.Buchungsverwaltung().setBestaetigt(buchung, Bestaetigung.JA);
								Anbieter an = (Anbieter)Portal.Accountverwaltung().getLoggedIn();
								Portal.Nachrichtenverwaltung().sendeNachricht(an, Portal.Accountverwaltung().getAccountByName(buchung.getKundenName()), "Buchungsbestaetigung", "Der Anbieter "+an.getName()+" hat Ihre Buchung bestaetigt!", Portal.Angebotsverwaltung().getAngebotByNummer(buchung.getAngebotsNummer()));
								JOptionPane.showMessageDialog(null, "Buchung bestaetigt");
								buttonRechts.setText("Buchung stornieren");
							}
						}
						buchungsbutton.setText("Kundenbuchungen "+"("+Portal.Buchungsverwaltung().getAnzahlUnbearbeiteterBuchungen((Anbieter)Portal.Accountverwaltung().getLoggedIn())+")");
						status.setText(buchung.getStatus());

					}
					catch(Exception e)
					{
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
				}
			});
		}
		
		buttonAngebot.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				((MainFrame)mainframe).showDetail(Portal.Buchungsverwaltung().getReferringAngebot(buchung));
			}
		});
		
		////////////

		north.add(left, BorderLayout.WEST);
		north.add(right, BorderLayout.EAST);
		
		labelPanel.add(north, BorderLayout.WEST);
		
		south.add(buttonLinks);
		south.add(buttonRechts);
		south.add(buttonAngebot);
		
		this.add(labelPanel, BorderLayout.NORTH);
		this.add(center, BorderLayout.CENTER);
		this.add(south, BorderLayout.SOUTH);
	}
}
