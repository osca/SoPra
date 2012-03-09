package graphic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import main.Portal;
import accounts.Account;
import accounts.Anbieter;
import angebote.kriterien.Kriterium;
import angebote.typen.Angebot;
import buchungen.Bestaetigung;
import buchungen.Buchung;

@SuppressWarnings("serial")
public class BuchDetailScreen extends JPanel {

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
		
		north = new JPanel(new GridLayout(0,2));
		center = new JPanel(new GridLayout(1,0));
		south = new JPanel(new GridLayout(1,0));
		
		north.setBorder(border);
		center.setBorder(border);
		
		Angebot angebot = Portal.Angebotsverwaltung().getAngebotByNummer(buchung.getAngebotsNummer());
		Anbieter anbieter = Portal.Angebotsverwaltung().getAnbieter(angebot);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		DecimalFormat f = new DecimalFormat("#0.00"); 
		
		JPanel sub_a = new JPanel(new GridLayout(0,2));
		JPanel sub_one = new JPanel(new GridLayout(10,1));
		JPanel sub_two = new JPanel(new GridLayout(10,1));
		int length_left = 10;
		JLabel [] sub_one_labels = new JLabel[length_left];
		sub_one_labels[0] = new JLabel("Angebotsnummer:");
		sub_one_labels[1] = new JLabel("Angebotsname:");
		sub_one_labels[2] = new JLabel("Angebotswertung:");
		sub_one_labels[3] = new JLabel("Anbieter:");
		sub_one_labels[4] = new JLabel("Anbieterwertung:");
		sub_one_labels[5] = new JLabel("Typ:");
		sub_one_labels[6] = new JLabel("Startdatum der Buchung:");
		sub_one_labels[7] = new JLabel("Startdatum der Buchung:");
		sub_one_labels[8] = new JLabel("Gebucht von:");
		sub_one_labels[9] = new JLabel("Preis");
		
		JLabel [] sub_two_labels = new JLabel[length_left];
		sub_two_labels[0] = new JLabel(""+angebot.getAngebotsNummer());
		sub_two_labels[1] = new JLabel(angebot.getName());
		sub_two_labels[2] = new JLabel(""+f.format(angebot.getWertung()));
		sub_two_labels[3] = new JLabel(anbieter.getName());
		sub_two_labels[4] = new JLabel(""+f.format(anbieter.getWertung()));
		sub_two_labels[5] = new JLabel("" + Angebot.convertTypToName(angebot.getTyp()));
		sub_two_labels[6] = new JLabel(formatter.format(buchung.getVon()));
		sub_two_labels[7] = new JLabel(formatter.format(buchung.getBis()));
		sub_two_labels[8] = new JLabel(buchung.getKundenName());
		sub_two_labels[9] = new JLabel(""+f.format(angebot.getPreis()));
		

		
		JPanel sub_b = new JPanel(new GridLayout(0,2));
		JPanel sub_1 = new JPanel(new GridLayout(8,1));
		JPanel sub_2 = new JPanel(new GridLayout(8,1));
		ArrayList<Kriterium> w = angebot.getKriterien();
		for (int i = 0; i < w.size(); i++) {
			JLabel krit = new JLabel(w.get(i).getName());
			sub_1.add(krit);
			JLabel krit1 = new JLabel(w.get(i).getWert());
			sub_2.add(krit1);
		}
		
		
		

		final JLabel status = new JLabel(buchung.getStatus());
		JLabel status_label = new JLabel("Status:");
		sub_1.add(status_label);
		sub_2.add(status);
	
		
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
		 	

		if (Portal.Accountverwaltung().getLoggedIn().getTyp() == Account.KUNDE) {
			buttonLinks.setText("Aenderungsanfrage");
			buttonLinks.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					DialogScreen dialog = new DialogScreen(frame,
							"Aenderungsanfrage", DialogScreen.OK_CANCEL_OPTION) {
						@Override
						public void onOK() {
							Portal.Nachrichtenverwaltung().sendeNachricht(
									Portal.Accountverwaltung().getLoggedIn(),
									Portal.Angebotsverwaltung().getAnbieter(
											Portal.Buchungsverwaltung()
													.getReferringAngebot(
															buchung)),
									"Aenderungsanfrage",
									getContent(),
									Portal.Buchungsverwaltung()
											.getReferringAngebot(buchung));
						}
					};
					dialog.addOnPanel(new JLabel(Portal.Accountverwaltung()
							.getLoggedIn().getName()), DialogScreen.LABEL_LEFT);
				}
			});
			buttonRechts.setText("Stornieren");
			buttonRechts.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if (JOptionPane.showConfirmDialog(
							fullinfo.getParent(),
							"Wollen Sie wirklich eine Stornierungsanfrage senden?",
							"Sicher?", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
						Portal.Nachrichtenverwaltung().sendeNachricht(
								Portal.Accountverwaltung().getLoggedIn(),
								Portal.Angebotsverwaltung().getAnbieter(
										Portal.Buchungsverwaltung()
												.getReferringAngebot(buchung)),
								"Stornierunsganfrage",
								"Der Kunde moechte seine Buchung stornieren",
								Portal.Buchungsverwaltung()
										.getReferringAngebot(buchung));
						buchung.setStornierungsAnfrage(true);
						JOptionPane.showMessageDialog(fullinfo.getParent(),
								"Ihre Stornierungsanfrage wurde gesendet");
					}
				}
			});
		}
		if (Portal.Accountverwaltung().getLoggedIn().getTyp() == Account.ANBIETER) {
			buttonRechts.setText("Status aendern");
			buttonLinks.setText("Kontaktieren");
			buttonLinks.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					DialogScreen dialog = new DialogScreen(frame,
							"Kontaktaufnahme", DialogScreen.OK_CANCEL_OPTION) {
						@Override
						public void onOK() {
							Portal.Nachrichtenverwaltung().sendeNachricht(
									Portal.Accountverwaltung().getLoggedIn(),
									Portal.Accountverwaltung()
											.getAccountByName(
													buchung.getKundenName()),
									"Kontaktaufnahme",
									getContent(),
									Portal.Buchungsverwaltung()
											.getReferringAngebot(buchung));
						}
					};
					dialog.addOnPanel(new JLabel(Portal.Accountverwaltung()
							.getLoggedIn().getName()), DialogScreen.LABEL_LEFT);
				}
			});
			buttonRechts.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						if (buchung.getBestaetigt() == Bestaetigung.JA) {
							if (JOptionPane.showConfirmDialog(null,
									"Wollen Sie die Buchung stornieren?",
									"Buchungsanfrage",
									JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
								if (buchung.getStornierungsAnfrage()) {
									Portal.Buchungsverwaltung().setBestaetigt(
											buchung, Bestaetigung.NEIN);
									Anbieter an = (Anbieter) Portal
											.Accountverwaltung().getLoggedIn();
									Portal.Nachrichtenverwaltung()
											.sendeNachricht(
													an,
													Portal.Accountverwaltung()
															.getAccountByName(
																	buchung.getKundenName()),
													"Buchungsbestaetigung",
													"Der Anbieter "
															+ an.getName()
															+ " hat Ihre Buchung abgelehnt!",
													Portal.Angebotsverwaltung()
															.getAngebotByNummer(
																	buchung.getAngebotsNummer()));
									JOptionPane.showMessageDialog(null,
											"Buchung abgelehnt");
									buttonRechts.setText("Buchung bestaetigen");
								} else
									JOptionPane
											.showMessageDialog(null,
													"Es liegt keine Stornierungsanfrage vor");
							}
						} else {
							if (JOptionPane.showConfirmDialog(null,
									"Wollen Sie die Buchung bestaetigen?",
									"Buchungsanfrage",
									JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
								Portal.Buchungsverwaltung().setBestaetigt(
										buchung, Bestaetigung.JA);
								Anbieter an = (Anbieter) Portal
										.Accountverwaltung().getLoggedIn();
								Portal.Nachrichtenverwaltung()
										.sendeNachricht(
												an,
												Portal.Accountverwaltung()
														.getAccountByName(
																buchung.getKundenName()),
												"Buchungsbestaetigung",
												"Der Anbieter "
														+ an.getName()
														+ " hat Ihre Buchung bestaetigt!",
												Portal.Angebotsverwaltung()
														.getAngebotByNummer(
																buchung.getAngebotsNummer()));
								JOptionPane.showMessageDialog(null,
										"Buchung bestaetigt");
								buttonRechts.setText("Buchung stornieren");
							}
						}
						buchungsbutton.setText("Kundenbuchungen "
								+ "("
								+ Portal.Buchungsverwaltung()
										.getAnzahlUnbearbeiteterBuchungen(
												(Anbieter) Portal
														.Accountverwaltung()
														.getLoggedIn()) + ")");
						status.setText(buchung.getStatus());

					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
				}
			});
		}

		buttonAngebot.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				((MainFrame) mainframe).showDetail(Portal.Buchungsverwaltung()
						.getReferringAngebot(buchung));
			}
		});

		for(int i=0;i<length_left;i++){
			sub_one.add(sub_one_labels[i]);
			sub_two.add(sub_two_labels[i]);
		}
		sub_a.add(sub_one);
		sub_a.add(sub_two);
		sub_b.add(sub_1);
		sub_b.add(sub_2);

		north.add(sub_a,BorderLayout.EAST);
		north.add(sub_b,BorderLayout.CENTER);
		JPanel button_panel = new JPanel(new FlowLayout());
		button_panel.add(buttonLinks);
		button_panel.add(buttonRechts);
		button_panel.add(buttonAngebot);
		south.add(button_panel,BorderLayout.CENTER);
		
		this.add(north, BorderLayout.NORTH);
		this.add(center, BorderLayout.CENTER);
		this.add(south, BorderLayout.SOUTH);
	}
}