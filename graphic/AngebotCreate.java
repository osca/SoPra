package graphic;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import main.Portal;
import accounts.Anbieter;
import angebote.Angebotsverwaltung;
import angebote.kriterien.Bierpreis;
import angebote.kriterien.Klasse;
import angebote.kriterien.Klima;
import angebote.kriterien.Land;
import angebote.kriterien.Sterne;
import angebote.kriterien.Verpflegungsart;
import angebote.typen.Angebot;
import buchungen.InvalidDateException;
/**
 * AngebotCreate
 * Dies Klasse haendlet das Ersttelen der Angebote
 * @author Rudolf, Benjamin, Dennis, Denis
 */
@SuppressWarnings("serial")
public class AngebotCreate<FormattedTextField> extends JPanel implements ActionListener {
	private JPanel up;
	private JPanel sub_a;
	private JPanel sub_b;
	private JPanel sub_one;
	private JPanel sub_two;
	private JPanel mid;
	private JPanel down;

	private JTextField name;

	private JComboBox typ;
	private Vector<String> typ_list;
	private JFormattedTextField preis;
	private JTextField kap;
	private JFormattedTextField von;
	private JFormattedTextField bis;
	private JLabel anbieter;

	private JComboBox klima;
	private JComboBox sterne;
	private JComboBox verpflegung;
	private JComboBox bierpreis;
	private JComboBox klasse;

	private JTextArea beschreibung;

	private JButton loeschen;
	private JButton bestaetigen;
	private JComboBox land;
	private JComboBox ort;
	private JComboBox landz;
	private JComboBox ortz;
	private Anbieter anb;
	
	/**
	 * Der Konstruktor ist trivial.
	 * @param a Der Anbieter, welcher das Angebot erstellt
	 * @throws ParseException Eine Exception beim Parsen des Datums
	 */
	public AngebotCreate(Anbieter a) throws ParseException {
		anb = a;
		init(anb);
	}
	/**
	 * Diese Methode baut die GUI des Screen komplett auf,
	 * ist aber trivial.
	 * @param a Der Anbieter, welcher das Angebot erstellt
	 * @throws ParseException Eine Exception beim Parsen des Datums
	 */
	private void init(Anbieter a) throws ParseException {

		setLayout(new BorderLayout(5, 5));
		up = new JPanel(new BorderLayout(5, 5));
		sub_a = new JPanel(new GridLayout(9, 2));

		// Name+Label
		name = new JTextField();
		name.setDocument(new MaxTextDocument(MainFrame.TEXTFIELDLENGTH));
		JLabel name_label = new JLabel("Name:");
		sub_a.add(name_label);
		name.setToolTipText("Bitte Namen eingeben");
		sub_a.add(name);

		// Typ-ComboBox+Label+Vector mit Item-Strings
		JLabel typ_label = new JLabel("Typ:");
		sub_a.add(typ_label);
		typ_list = new Vector<String>();
		typ_list.add("Typ");
		typ_list.add("Hoteluebernachtung");
		typ_list.add("Autovermietung");
		typ_list.add("Ausflug");
		typ_list.add("Flug");
		typ = new JComboBox(typ_list);
		typ.setToolTipText("Bitte waehlen Sie eine Typ aus");
		typ.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				addScreen();
			}
		});
		sub_a.add(typ);

		// Preis+Label
		JLabel preis_label = new JLabel("Preis:");
		sub_a.add(preis_label);
		preis = new JFormattedTextField(new DecimalFormat("#*0.00"));
		sub_a.add(preis);

		// Kapazitaet+Label
		JLabel kap_label = new JLabel("Kapazitaet:");
		sub_a.add(kap_label);
		NumberFormat nformat = NumberFormat.getIntegerInstance();
		nformat.setGroupingUsed(false);
		kap = new JFormattedTextField(nformat);
		kap.setDocument(new MaxTextDocument(MainFrame.TEXTFIELDLENGTH));
		sub_a.add(kap);

		// Startdatum+Label
		JLabel von_label = new JLabel("Startdatum (im DD/MM/YYYY Format):");
		sub_a.add(von_label);
		von = new JFormattedTextField(new MaskFormatter("##/##/####"));
		sub_a.add(von);

		// Enddatum+Label
		JLabel bis_label = new JLabel("Enddatum (im DD/MM/YYYY Format):");
		sub_a.add(bis_label);
		bis = new JFormattedTextField(new MaskFormatter("##/##/####"));
		sub_a.add(bis);

		// //Laenge+Label
		// JLabel interval_label = new JLabel("Laenge:");
		// sub_a.add(interval_label);
		// interval =new JFormattedTextField(NumberFormat.getInstance());
		// sub_a.add(interval);

		// Anbieter
		JLabel anbieter_label = new JLabel("Anbieter:");
		sub_a.add(anbieter_label);
		anbieter = new JLabel(a.getName());
		sub_a.add(anbieter);

		sub_b = new JPanel(new GridLayout(0, 2));
		sub_one = new JPanel(new GridLayout(6, 1));
		sub_two = new JPanel(new GridLayout(6, 1));

		sub_b.add(sub_one);
		sub_b.add(sub_two);
		JPanel center = new JPanel(new BorderLayout(5, 5));
		up.add(BorderLayout.CENTER, sub_a);
		up.add(BorderLayout.SOUTH, center);
		center.add(BorderLayout.NORTH, sub_b);

		up.add(BorderLayout.SOUTH, center);

		// Beschreibung des Angebots
		mid = new JPanel(new GridLayout(1, 0));
		beschreibung = new JTextArea(
				"Bitte geben Sie hier die Beschreibung ein");
		beschreibung.setEditable(true);
		beschreibung.setLineWrap(true);
		beschreibung.setWrapStyleWord(true);
		mid.add(beschreibung);

		down = new JPanel(new FlowLayout());
		loeschen = new JButton("Alle Eingaben Loeschen");
		loeschen.setPreferredSize(new Dimension(MainFrame.BUTTONWIDTH,
				MainFrame.BUTTONHEIGHT));
		loeschen.addActionListener(this);
		
		bestaetigen = new JButton("Bestaetigen");
		bestaetigen.setPreferredSize(new Dimension(MainFrame.BUTTONWIDTH,
				MainFrame.BUTTONHEIGHT));
		bestaetigen.addActionListener(this);
		down.add(bestaetigen);
		down.add(loeschen);

		center.add(BorderLayout.CENTER, mid);

		add(BorderLayout.NORTH, up);
		// add(BorderLayout.CENTER, mid);
		add(BorderLayout.CENTER, down);
		setVisible(true);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {		
		if (e.getSource() == bestaetigen) {			
			String[] k = Angebotsverwaltung.angebotNameToErlaubteKriterien(typ.getSelectedItem().toString());

			for (int i = 0; i < sub_two.getComponentCount(); i++) {
				Component c = sub_two.getComponent(i);
				if (c instanceof JComboBox) {
					k[i] = ((JComboBox) c).getSelectedItem().toString();
				} else if (c instanceof JTextField) {
					k[i] = ((JTextField) c).getText();
				}
			}

			Date q = null;
			Date w = null;
			
			Date heute = Methods.getHeuteNullUhr();
			
			double result = 0.0;
			if (name.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this,
						"Bitte geben Sie einen gueltigen Namen ein",
						"Angebot erstellen", JOptionPane.OK_OPTION);

			} else if (typ.getSelectedIndex() == 0) {
				JOptionPane.showMessageDialog(this,
						"Bitte waehlen Sie einen Typ aus ",
						"Angebot erstellen", JOptionPane.OK_OPTION);

			} else if (preis.getValue() == null || preis.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this,
						"Bitte geben Sie eine gueltigen Preis ein",
						"Angebot erstellen", JOptionPane.OK_OPTION);
			} else if (kap.getText() == null
					|| kap.getText().isEmpty() || Integer.parseInt(kap.getText()) <= 0) {
				JOptionPane.showMessageDialog(this,
						"Bitte geben Sie eine gueltige Kapazitaet ein",
						"Angebot erstellen", JOptionPane.OK_OPTION);
			} else if (preis.getText() != null || !preis.getText().isEmpty()) {
				result = Double.parseDouble(preis.getText().replace(",", "."));
			}  
			if (land.getSelectedItem().equals("")) {
				JOptionPane.showMessageDialog(this, 
						"Sie muessen ein Land auswaehlen", 
						"Angebot erstellen", JOptionPane.OK_OPTION);
				return;
			} 
			if (typ.getSelectedItem().toString() == typ_list.elementAt(4)) {
				if (landz.getSelectedItem().equals("")) {
					JOptionPane.showMessageDialog(this, 
							"Sie muessen ein Land als Ziel auswaehlen",
							"Angebot erstellen", JOptionPane.OK_OPTION);
					return;
				}
			}
			
			if (result != 0) {
				try {
					q = Methods.stringToDate(von.getText());
					w = Methods.stringToDate(bis.getText());
					
					if(!Methods.isValidDatestring(von.getText()) || !Methods.isValidDatestring(bis.getText()))
						JOptionPane.showMessageDialog(this,
								"Ungueltiges Datum",
								"Angebot Erstellung", JOptionPane.OK_OPTION);
					else if(w.before(q))
						JOptionPane.showMessageDialog(this,
								"Das Enddatum ist vor dem Startdatum",
								"Angebot Erstellung", JOptionPane.OK_OPTION);
					else if(heute.after(q))
						JOptionPane.showMessageDialog(this,
								"Das Startdatum ist vor dem heutigen Datum",
								"Angebot Erstellung", JOptionPane.OK_OPTION);
					else {
						try {
							Portal.Angebotsverwaltung().createAngebot(
									(Anbieter) Portal.Accountverwaltung()
											.getLoggedIn(),
									name.getText(),
									beschreibung.getText(),
									Angebot.convertNameToTyp(typ.getSelectedItem()
											.toString()), result,
									Integer.parseInt(kap.getText()), q, w, k);
							JOptionPane.showMessageDialog(this,
									"Erstellung erfolgreich");
							this.removeAll();
							this.validate();
							this.repaint();
							init(anb);
						} catch (InvalidDateException e1) {
							JOptionPane.showMessageDialog(this,
									"Ueberpruefen Sie das Datum",
									"Angebot Erstellung", JOptionPane.OK_OPTION);
						}
					}
				} catch (ParseException e2) {
					JOptionPane.showMessageDialog(this,
							"Ueberpruefen Sie das Datum",
							"Angebot Erstellung", JOptionPane.OK_OPTION);
				}
			}
		}
		else if (e.getSource() == loeschen) {
			this.removeAll();
			this.validate();
			this.repaint();
			try {
				init(anb);
			} catch (ParseException e1) {
				
			}
			beschreibung.setText("Bitte geben Sie hier eine Beschreibung ein");
			addScreen();

		}
	}
	/**
	 * Diese Methode regelt den dynamischen Aufbau des Kriterien-Panels,
	 * sodass bei verschiedenen Typen der Anbeiter auch  dementsprechende 
	 * Krieterien zur Auswahl hat.
	 */
	public void addScreen() {
		sub_one.removeAll();
		sub_two.removeAll();
		sub_b.validate();
		
		ort = new JComboBox();
		ort.addItem("");
		
		land = new JComboBox();
		land.addItem("");
		for(String s: Land.wertebereich)
			land.addItem(s);

		if (typ.getSelectedItem().toString() == typ_list.elementAt(1)) {
			JLabel land_label = new JLabel("Land:");
			sub_one.add(land_label);
			JLabel s_ort_label = new JLabel("Ort:");
			sub_one.add(s_ort_label);
	
			land.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					repaintOrt();
				}

			});

			sub_two.add(land);
			sub_two.add(ort);

			JLabel klima_label = new JLabel("Klima:");
			sub_one.add(klima_label);
			klima = new JComboBox(Klima.wertebereich);
			sub_two.add(klima);

			JLabel sterne_label = new JLabel("Sterne:");
			sub_one.add(sterne_label);
			sterne = new JComboBox(Sterne.wertebereich);
			sterne.setToolTipText("Bitte geben Sie Anzahl der Sterne ein");
			sub_two.add(sterne);

			JLabel verpfelgeung_label = new JLabel("Verpflegung:");
			sub_one.add(verpfelgeung_label);
			verpflegung = new JComboBox(Verpflegungsart.wertebereich);
			sub_two.add(verpflegung);

			JLabel bierpreis_label = new JLabel("Bierpreis:");
			sub_one.add(bierpreis_label);
			bierpreis = new JComboBox(Bierpreis.wertebereich);
			sub_two.add(bierpreis);

		}

		if (typ.getSelectedItem().toString() == typ_list.elementAt(2)) {
			JLabel land_label = new JLabel("Land:");
			sub_one.add(land_label);
			JLabel s_ort_label = new JLabel("Ort:");
			sub_one.add(s_ort_label);
			land.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					repaintOrt();
				}
			});
			sub_two.add(land);
			sub_two.add(ort);
			@SuppressWarnings("unused")
			JLabel ort_label = new JLabel("Ort:");

		}

		if (typ.getSelectedItem().toString() == typ_list.elementAt(3)) {
			JLabel land_label = new JLabel("Land:");
			sub_one.add(land_label);
			JLabel s_ort_label = new JLabel("Ort:");
			sub_one.add(s_ort_label);
			sub_two.add(land);
			land.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					repaintOrt();
				}

			});
			sub_two.add(ort);

			JLabel bierpreis_label = new JLabel("Bierpreis:");
			sub_one.add(bierpreis_label);
			bierpreis = new JComboBox(Bierpreis.wertebereich);
			sub_two.add(bierpreis);

		}

		if (typ.getSelectedItem().toString() == typ_list.elementAt(4)) {
			JLabel s_land_label = new JLabel("Startland:");
			JLabel s_ort_label = new JLabel("Startort:");
			sub_one.add(s_land_label);
			sub_one.add(s_ort_label);
			land.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					repaintOrt();
				}

			});
			sub_two.add(land);
			sub_two.add(ort);

			@SuppressWarnings("unused")
			JLabel ortz_label = new JLabel("Zielort:");
			JLabel z_land_label = new JLabel("Zielland:");
			JLabel z_ort_label = new JLabel("Zielort:");
			sub_one.add(z_land_label);
			sub_one.add(z_ort_label);
			landz = new JComboBox();
			landz.addItem("");
			for(String s: Land.wertebereich)
				landz.addItem(s);
			
			landz.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					Component[] comps = sub_two.getComponents();
					int pos = -1;
					for (int i = 0; i < comps.length; i++) {
						if (comps[i].equals(ortz))
							pos = i;
					}
					
					if (!landz.getSelectedItem().equals("")) {
						sub_two.remove(ortz);
						ortz = new JComboBox(((Land.getOrte((String) landz.getSelectedItem()))));
						sub_two.add(ortz, null, pos);

						sub_two.revalidate();
						sub_two.repaint();
					}
					else {
						ortz.removeAll();
						ortz.addItem("");
					}
					
				}

			});
			ortz = new JComboBox();
			ortz.addItem("");
			sub_two.add(landz);
			sub_two.add(ortz);

			JLabel klasse_label = new JLabel("Klasse:");
			sub_one.add(klasse_label);
			klasse = new JComboBox(Klasse.wertebereich);
			sub_two.add(klasse);

			JLabel bierpreis_label = new JLabel("Bierpreie:");
			sub_one.add(bierpreis_label);
			bierpreis = new JComboBox(Bierpreis.wertebereich);
			sub_two.add(bierpreis);

		}
		sub_a.validate();
		sub_a.repaint();
		sub_one.repaint();
		sub_two.repaint();
		sub_b.removeAll();
		sub_b.add(sub_one);
		sub_b.add(sub_two);
		sub_b.validate();
		sub_b.repaint();
		this.validate();
		this.repaint();
	}
	/**
	 * Sorgt fuer das neue Einlesen der Orte aus einer Datei, nachdem das Land gewechselt wurde.
	 */
	public void repaintOrt() {
		Component[] comps = sub_two.getComponents();
		int pos = -1;
		for (int i = 0; i < comps.length; i++) {
			if (comps[i].equals(ort))
				pos = i;
		}

		if (!land.getSelectedItem().equals("")) {
			sub_two.remove(ort);
			ort = new JComboBox(((Land.getOrte((String) land.getSelectedItem()))));
			sub_two.add(ort, null, pos);
	
			sub_two.revalidate();
			sub_two.repaint();
		}
		else {
			ort.removeAllItems();
			ort.addItem("");
			ort.repaint();
		}
	}

}
