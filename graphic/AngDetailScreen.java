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
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import angebote.Kommentar;
import angebote.kriterien.Kriterium;
import angebote.typen.Angebot;
import buchungen.InvalidDateException;

@SuppressWarnings("serial")
public class AngDetailScreen extends JPanel {
	private JPanel up;
	private JPanel sub_a;
	private JPanel sub_b;
	private JPanel mid;
	private JPanel down;
	private JLabel name;
	private JLabel typ;
	private JLabel preis;
	private JLabel kap;
	private JLabel vondatum;
	private JLabel bisdatum;
	private JLabel anbieterlabel;
	private JLabel angebotwert;
	private JLabel anbieter_wertung;

	private JTextArea fullinfo;
	private JLabel nullAcc;

	private JButton buchen = new JButton("Buchen");
	private JButton melden = new JButton("Melden");
	private JButton kommentieren = new JButton("Kommentieren");
	private JButton loeschen = new JButton("Loeschen");
	private JButton hide;
	private JButton kontaktieren = new JButton("Kontaktieren");
	//private JButton editsave = new JButton("Aenderungen Speichern");
	protected Date q;
	protected Date u;
	final Angebot angebot;
	final Anbieter anbieter;
	
	final MainFrame frame;

	public AngDetailScreen(final MainFrame mainframe, Angebot a) {
			
		frame = mainframe;
		
		angebot = a;
		anbieter = Portal.Angebotsverwaltung().getAnbieter(angebot);
		String preis_str = "" + a.getPreis();
		String kap_str = "" + a.getKapazitaet();
		hide = new JButton();
		if(angebot.isAuffindbar()){
			hide.setText("Angebot verstecken");
		}
		else if(angebot.isAuffindbar()==false){
			hide.setText("Angebot zeigen");
		}
		

		this.setLayout(new BorderLayout());
		up = new JPanel(new GridLayout(0, 2));
		mid = new JPanel(new GridLayout(0, 1));
		down = new JPanel(new BorderLayout());

		name = new JLabel(angebot.getName());
		typ = new JLabel("" + Angebot.convertTypToName(angebot.getTyp()));
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		vondatum = new JLabel(formatter.format(angebot.getStartdatum()));
		bisdatum = new JLabel(formatter.format(angebot.getEnddatum()));
		DecimalFormat f = new DecimalFormat("#0.00"); 
		angebotwert = new JLabel(""+f.format(angebot.getWertung()));
		anbieterlabel = new JLabel(anbieter.getName());
		anbieter_wertung = new JLabel(f.format(anbieter.getWertung()));
		preis = new JLabel(preis_str);
		kap = new JLabel(kap_str);

		sub_a = new JPanel(new GridLayout(7, 2));
		JLabel name_l = new JLabel("Name:");
		sub_a.add(name_l);
		sub_a.add(name);
		JLabel typ_label = new JLabel("Typ:");
		sub_a.add(typ_label);
		sub_a.add(typ);
		JLabel preis_label = new JLabel("Preis:");
		sub_a.add(preis_label);
		sub_a.add(preis);
		JLabel kap_label = new JLabel("Kapazitaet:");
		sub_a.add(kap_label);
		sub_a.add(kap);
		JLabel vd_label = new JLabel("Startdatum:");
		sub_a.add(vd_label);
		sub_a.add(vondatum);
		JLabel bd_label = new JLabel("Enddatum:");
		sub_a.add(bd_label);
		sub_a.add(bisdatum);
		JLabel angebotwert_label = new JLabel("Wertung:");
		sub_a.add(angebotwert_label);
		sub_a.add(angebotwert);
		sub_b = new JPanel(new GridLayout(0, 2));
		JPanel sub_1 = new JPanel(new GridLayout(8, 1));
		final JPanel sub_2 = new JPanel(new GridLayout(8, 1));
		// String k[] =angebot.getErlaubteKriterien();
		ArrayList<Kriterium> w = angebot.getKriterien();
		for (int i = 0; i < w.size(); i++) {
			JLabel krit = new JLabel(w.get(i).getName());
			sub_1.add(krit);
			JLabel krit1 = new JLabel(w.get(i).getWert());
			sub_2.add(krit1);
		}
		JLabel anbieter_label = new JLabel("Anbieter:");
		sub_1.add(anbieter_label);
		sub_2.add(anbieterlabel);
		JLabel anb_wert_label = new JLabel("Gesamtwertung des Anbieters:");
		sub_1.add(anb_wert_label);
		sub_2.add(anbieter_wertung);
		sub_b.add(sub_1);
		sub_b.add(sub_2);
		
		up.add(sub_a);
		up.add(sub_b);
		
		fullinfo = new JTextArea(angebot.getFullInfo());
		fullinfo.setLineWrap(true);
		fullinfo.setWrapStyleWord(true);
		fullinfo.setBackground(Color.LIGHT_GRAY);
		fullinfo.setEditable(false);

		mid.add(fullinfo);

		/*
		 * for(Kommentar k : angebot.getKommentare()) { mid.add(new
		 * KommentarScreen(k)); }
		 */

		// ArrayList<Kommentar> kommentare = angebot.getKommentare();
		//
		// for(int i = 0; i < kommentare.size(); i++) {
		// mid.add(new KommentarScreen(kommentare.get(i)));
		// }
		JPanel button_panel = new JPanel(new FlowLayout());
		switch (Portal.Accountverwaltung().getLoggedIn().getTyp()) {
		case Account.NONE:
			nullAcc = new JLabel(MeldeDienst.MSG_LOGIN_FEHLT);
			button_panel.add(nullAcc);
			break;
		case Account.KUNDE:
			kommentieren.setEnabled(true);
			kommentieren.setPreferredSize(new Dimension(MainFrame.BUTTONWIDTH,
					MainFrame.BUTTONHEIGHT));
			button_panel.add(kommentieren);
			buchen.setPreferredSize(new Dimension(MainFrame.BUTTONWIDTH,
					MainFrame.BUTTONHEIGHT));
			button_panel.add(buchen);
			melden.setPreferredSize(new Dimension(MainFrame.BUTTONWIDTH,
					MainFrame.BUTTONHEIGHT));
			button_panel.add(melden);
			kontaktieren.setPreferredSize(new Dimension(MainFrame.BUTTONWIDTH,
					MainFrame.BUTTONHEIGHT));
			button_panel.add(kontaktieren);
			break;

		case Account.ANBIETER:

			if (Portal.Accountverwaltung().getLoggedIn().getName()
					.equals(angebot.getAnbieterName())) {
				button_panel.add(loeschen);
				loeschen.setPreferredSize(new Dimension(MainFrame.BUTTONWIDTH,
						MainFrame.BUTTONHEIGHT));
				hide.setPreferredSize(new Dimension(MainFrame.BUTTONWIDTH,
						MainFrame.BUTTONHEIGHT));
				button_panel.add(hide);
//				down.add(editsave);
//				editsave.disable();
//				editsave.setPreferredSize(new Dimension(MainFrame.BUTTONWIDTH,
//						MainFrame.BUTTONHEIGHT));
			}

		//	down.add(hide);

			if (Portal.Accountverwaltung().getLoggedIn().getName()
					.equals(angebot.getAnbieterName()))
				button_panel.add(loeschen);
			loeschen.setPreferredSize(new Dimension(MainFrame.BUTTONWIDTH,
					MainFrame.BUTTONHEIGHT));
			hide.setPreferredSize(new Dimension(MainFrame.BUTTONWIDTH,
					MainFrame.BUTTONHEIGHT));

			break;

		case Account.BETREIBER:
			loeschen.setPreferredSize(new Dimension(MainFrame.BUTTONWIDTH,
					MainFrame.BUTTONHEIGHT));
			button_panel.add(loeschen);
			kontaktieren.setPreferredSize(new Dimension(MainFrame.BUTTONWIDTH,
					MainFrame.BUTTONHEIGHT));
			button_panel.add(kontaktieren);
			break;

		}
		down.add(button_panel,BorderLayout.CENTER);
		this.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,
				Color.LIGHT_GRAY));
		// ///// veraendert
		this.add(BorderLayout.NORTH, up);
		this.add(BorderLayout.CENTER, mid);
		JPanel down_haupt = new JPanel(new BorderLayout(5, 5));
		down_haupt.add(BorderLayout.NORTH, down);
		JPanel down_kom = new JPanel();
		final KommentarListe kl = new KommentarListe(a);
		down_kom.add(kl);
		down_haupt.add(BorderLayout.CENTER, down_kom);
		this.add(BorderLayout.SOUTH, down_haupt);

		// ///////////////

		buchen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				try {
					JLabel label = new JLabel("Geben Sie den Zeitraum im Format dd/MM/yyyy an:");
					JLabel fromLabel = new JLabel("Von:");
					JLabel toLabel = new JLabel("Bis:");
					JFormattedTextField fromField = new JFormattedTextField(new MaskFormatter("##/##/####"));
					JFormattedTextField toField = new JFormattedTextField(new MaskFormatter("##/##/####"));

					if (JOptionPane.showConfirmDialog(null, new Object[] {label, fromLabel, fromField, toLabel, toField }, "Login", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
						if (fromField.getText().length() == 0 || toField.getText().length() == 0
								|| !Methods.isValidDatestring(fromField.getText()) || !Methods.isValidDatestring(toField.getText()))
							throw new IllegalArgumentException("Zeitraum nicht gueltig");

						SimpleDateFormat to = new SimpleDateFormat("dd/MM/yyyy");

						final Date toDate = to.parse(toField.getText());
						final Date fromDate = to.parse(fromField.getText());
						Date heute = Methods.getHeuteNullUhr();

						if (fromDate.before(heute) || toDate.before(heute)) {
							throw new InvalidDateException("Ihr Anfangs- oder Enddatum liegt vor dem heutigem Datum.");
						} else if (fromDate.after(toDate)) {
							throw new InvalidDateException("Ihr Enddatum liegt vor dem Startdatum");
						} else if (fromDate.before(angebot.getStartdatum())) {
							throw new InvalidDateException("Ihr Anfangsdatum liegt vor Beginn des Angebots");
						} else if (toDate.after(angebot.getEnddatum())) {
							throw new InvalidDateException("Ihr Enddatum liegt nach Ende des Angebots");
						}

						DialogScreen dialog = new DialogScreen(frame,"Buchen", DialogScreen.OK_CANCEL_OPTION) {
							@Override
							public void onOK() {
								try {
									Portal.Buchungsverwaltung().createBuchung((Kunde) Portal.Accountverwaltung().getLoggedIn(), angebot, fromDate, toDate);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
							
						};
						
						DecimalFormat f2 = new DecimalFormat("#0.00"); 
						String bewertung = f2.format(anbieter.getWertung());
						
						dialog.setEditable(false);
						dialog.addOnPanel(new JLabel("AGB des Anbieters: " + anbieterlabel.getText()), DialogScreen.LABEL_LEFT);
						dialog.addOnPanel(new JLabel("Bewertung: " + bewertung), DialogScreen.LABEL_RIGHT);
						dialog.setContent(anbieter.getAgb());
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(up.getParent(), e.getMessage());
				}
			}
		});
		melden.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (JOptionPane.showConfirmDialog(up.getParent(),
							MeldeDienst.QSN_ANGEBOT_MELDEN, "Angebot melden",
							JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
						Account account = Portal.Accountverwaltung()
								.getLoggedIn();
						Portal.Nachrichtenverwaltung()
								.sendeMeldungAnAlleBetreiber(
										account,
										MeldeDienst.MSG_BESCHWERDE,
										MeldeDienst.MSG_ANGEBOT_GEMELDET + "\n"
												+ "Anbieter: "
												+ anbieter.getName() + "\n"
												+ "Kunde: " + account.getName()
												+ "\n" + "Angebot: "
												+ angebot.getName(), angebot);
						Portal.Nachrichtenverwaltung().sendeNachricht(
								account,
								anbieter,
								MeldeDienst.MSG_BESCHWERDE,
								MeldeDienst.MSG_ANGEBOT_GEMELDET + "\n"
										+ "Anbieter: " + anbieter.getName()
										+ "\n" + "Kunde: " + account.getName()
										+ "\n" + "Angebot: "
										+ angebot.getName(), angebot);
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(up.getParent(), e.toString());
				}
			}
		});
		kommentieren.addActionListener(new ActionListener() {
			int bewertung = Kommentar.KEINEWERTUNG;
			boolean gebucht = false; // dirty, aber so geht es
			boolean kommentiert = false; // dirty, aber so geht es
			JComboBox bewertungCombo = new JComboBox(new String[] { "Auswahl",
					"1", "2", "3", "4", "5" });

			JLabel kundeLabel = new JLabel();
			JLabel bewertungLabel = new JLabel("Bewertung:");

			JButton okButton = new JButton("Abschicken");
			JButton cancelButton = new JButton("Abbrechen");

			DialogScreen dialog;
			Kommentar kommi;

			ActionListener okListener = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// Man kann immer kommentieren, also kann der text auch
					// immer leer sein
					if (dialog.getContent().length() <= 0) {
						JOptionPane.showMessageDialog(dialog,
								"Sie müssen einen Kommentar eingeben.");
						return;
					}
					// nur die Combo checken, wenn auch bewerten erlaubt ist =
					// gebucht ^ !kommentiert
					else if (gebucht && !kommentiert) {
						if (bewertungCombo.getSelectedIndex() == 0) {
							JOptionPane.showMessageDialog(dialog,
									"Sie müssen eine Bewertung auswaehlen.");
							return;
						}
					}

					bewertung = bewertungCombo.getSelectedIndex();
					if(bewertung == 0)
						bewertung = Kommentar.KEINEWERTUNG;
					kommi = new Kommentar(Portal.Accountverwaltung()
							.getLoggedIn().getName(), dialog.getContent(),
							bewertung);
					Portal.Angebotsverwaltung().addKommentar(angebot, kommi);
					dialog.dispose();
					kl.init(angebot);
					kl.validate();
					kl.repaint();
					validate();
					repaint();
					kommentieren.setEnabled(false);
				}
			};

			ActionListener cancelListener = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					dialog.dispose();
				}
			};

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Kunde loggedin = (Kunde) Portal.Accountverwaltung()
						.getLoggedIn();

				okButton.addActionListener(okListener);
				cancelButton.addActionListener(cancelListener);
				kundeLabel.setText(Portal.Accountverwaltung().getLoggedIn()
						.getName());

				dialog = new DialogScreen(frame,"Kommentieren", new JButton[] {
						okButton, cancelButton });

				dialog.addOnPanel(kundeLabel, DialogScreen.LABEL_LEFT);

				// ein Kunde darf nur bewerten, wenn er die Reise gebucht hat
				// und noch keine bewertung abgegeben hat.
				gebucht = Portal.Buchungsverwaltung().isBookedByKunde(angebot,
						loggedin);
				kommentiert = Portal.Angebotsverwaltung().isCommentedByKunde(
						angebot, loggedin); // kommentiert => bewertung bereits
											// erfolgt
				if (gebucht && !kommentiert) {
					bewertungCombo.setToolTipText("Je hoeher, desto besser.");
					dialog.addOnPanel(bewertungLabel, DialogScreen.LABEL_RIGHT);
					dialog.addOnPanel(bewertungCombo, DialogScreen.LABEL_RIGHT);
				}
			};
		});

		loeschen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					if(JOptionPane.showConfirmDialog(up.getParent(), "Sind Sie sicher, dass Sie das Angebot loeschen moechten?","Loeschen",JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION)
					{
						Portal.Angebotsverwaltung().delAngebot(angebot);
						JOptionPane.showMessageDialog(up.getParent(),"Angebot erfolgreich geloescht");
						if (Portal.Accountverwaltung().getLoggedIn().getTyp() == Account.BETREIBER)
							Portal.Nachrichtenverwaltung().sendeNachricht(
									Portal.Accountverwaltung().getLoggedIn(),
									anbieter, "Angebot wurde Gelöscht",
									"Ihr Angebot wurde vom Betreiber gelöscht!",
									angebot);
						removeAll();
						repaint();
					}
				} 
				catch (Exception e) {
					JOptionPane.showMessageDialog(up.getParent(),e.getMessage()+". Das Angebot wurde versteckt.");
					Portal.Angebotsverwaltung().setAuffindbar(angebot, false);
				}
			}
		});
		hide.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
//				try {
//					repaint(sub_a, vondatum);
//					repaint(sub_a, bisdatum);
//				} catch (ParseException e) {
//					e.printStackTrace();
//				}

//				fullinfo.setEditable(true);
//				editsave.setEnabled(true);
				if(angebot.isAuffindbar()){
					try{
						Portal.Angebotsverwaltung().setAuffindbar(angebot, false);
					hide.setText("Angebot anzeigen");
					}
					catch(IllegalArgumentException e){
						JOptionPane.showMessageDialog(null,
								e.getMessage(), "Angebot Erstellung",
								JOptionPane.OK_OPTION);
					}
					
				}
				else if(angebot.isAuffindbar()==false){
					try{
						Portal.Angebotsverwaltung().setAuffindbar(angebot,true);
					hide.setText("Angebot verstecken");
					}
					catch(IllegalArgumentException e){
						JOptionPane.showMessageDialog(null,
								e.getMessage(), "Angebot Erstellung",
								JOptionPane.OK_OPTION);
					}
				}

			}
		});
		kontaktieren.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					DialogScreen dialog = new DialogScreen(frame, "Kontaktieren",DialogScreen.OK_CANCEL_OPTION) {
						@Override
						public void onOK() {
							Portal.Nachrichtenverwaltung().sendeNachricht(Portal.Accountverwaltung().getLoggedIn(),anbieter, "Kontaktaufnahme", getContent(),angebot);
						}
					};
					dialog.addOnPanel(new JLabel(Portal.Accountverwaltung()
							.getLoggedIn().getName()), DialogScreen.LABEL_LEFT);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(up.getParent(), e.toString());
				}
			}
		});
//		editsave.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				fullinfo.setEditable(false);
//				String[] k = Angebotsverwaltung
//						.angebotNameToErlaubteKriterien(typ.getText());
//				for (int i = 0; i < sub_2.getComponentCount(); i++) {
//					Component c = sub_2.getComponent(i);
//					if (c instanceof JLabel) {
//						k[i] = ((JLabel) c).getText();
//					}
//				}
//				q = null;
//				u = null;
//				double result = 0;
//				result = Double.parseDouble(preis.getText());
//
//				try {
//					q = Methods.stringToDate(vondatum.getText());
//					u = Methods.stringToDate(bisdatum.getText());
//				} catch (ParseException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				try {
//					Portal.Angebotsverwaltung()
//							.createAngebot(
//									(Anbieter) Portal.Accountverwaltung()
//											.getLoggedIn(), name.getText(),
//									fullinfo.getText(),
//									Angebot.convertNameToTyp(typ.getText()),
//									result, Integer.parseInt(kap.getText()), q,
//									u, k);
//				} catch (NumberFormatException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (InvalidDateException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//
//			}
//			
//		});
	}

//	public void repaint(JPanel panel, Component datum) throws ParseException {
//		Component[] comps = panel.getComponents();
//		int pos = -1;
//		for (int i = 0; i < comps.length; i++) {
//			if (comps[i].equals(datum))
//				pos = i;
//		}
//
//		panel.remove(datum);
//		JFormattedTextField von = new JFormattedTextField(new MaskFormatter(
//				"##/##/####"));
//		datum = von;
//		panel.add(datum, null, pos);
//
//		panel.revalidate();
//		panel.repaint();
//	}
}
