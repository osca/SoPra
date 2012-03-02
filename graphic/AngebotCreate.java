package graphic;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import accounts.Anbieter;
import angebote.kriterien.Bierpreis;
import angebote.kriterien.Klasse;
import angebote.kriterien.Klima;
import angebote.kriterien.Verpflegungsart;


public class AngebotCreate extends JPanel implements ActionListener {

	private JPanel up;
	private JPanel sub_a;
	private JPanel sub_b;
	private JPanel mid;
	private JPanel down;

	private JTextField name;

	private JComboBox typ;
	private Vector<String> typ_list;
	private DefaultComboBoxModel typ_m;
	// private Shice date ---> vllt.
	private JTextField preis;
	private JTextField kap;
	private JLabel anbieter;

	// Kriterien
	private JTextField ort;
	private JTextField ortz;
	private JComboBox klima;
	private JTextField sterne;
	private JComboBox verpflegung;
	private JComboBox bierpreis;
	private JComboBox klasse;
	private String[] k;

	private JTextArea beschreibung;

	private JButton verwerfen;
	private JButton bestaetigen;

	public AngebotCreate(Anbieter a) throws ParseException {
		setLayout(new BorderLayout(5, 5));
		
//		MaskFormatter formatter = new MaskFormatter("***********************************************");
//		formatter.setValidCharacters("ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz");
		
		up = new JPanel(new GridLayout(0, 2));
		sub_a = new JPanel(new GridLayout(6, 2));
		name = new JTextField();
		JLabel name_label= new JLabel("Name:");
		sub_a.add(name_label);
		name.setToolTipText("Bitte Namen eingeben");
		sub_a.add(name);
		
		JLabel typ_label = new JLabel("Typ:");
		sub_a.add(typ_label);
		typ_list = new Vector<String>();
		typ_list.add("Typ");
		typ_list.add("Hoteluebernachtung");
		typ_list.add("Autovermietung");
		typ_list.add("Ausflueg");
		typ_list.add("Flug");
		typ = new JComboBox(typ_list);
		typ.setToolTipText("Bitte waehlen Sie eine Typ aus");
		typ.addActionListener(this);

		sub_a.add(typ);
		JLabel preis_label = new JLabel("Preis:");
		sub_a.add(preis_label);
		preis = new JTextField();
		sub_a.add(preis);
		JLabel kap_label = new JLabel("Kapazitaet:");
		sub_a.add(kap_label);
		kap = new JTextField();
		sub_a.add(kap);
		JLabel anbieter_label = new JLabel("Anbieter:");
		sub_a.add(anbieter_label);
		anbieter = new JLabel(a.getName());
		sub_a.add(anbieter);
		sub_b = new JPanel(new GridLayout(6, 2));
		up.add(sub_a);
		up.add(sub_b);

		mid = new JPanel(new GridLayout(1, 0));
		beschreibung = new JTextArea();
		beschreibung.setEditable(true);
		mid.add(beschreibung);

		down = new JPanel(new BorderLayout(5, 5));
		verwerfen = new JButton("Verwerfen");
		down.add(BorderLayout.EAST, verwerfen);
		bestaetigen = new JButton("Bestätigen");
		down.add(BorderLayout.WEST, bestaetigen);

		add(BorderLayout.NORTH, up);
		add(BorderLayout.CENTER, mid);
		add(BorderLayout.SOUTH, down);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == typ) {
			sub_b.removeAll();
			if (typ.getSelectedItem().toString() == typ_list.elementAt(0)) {
				
			}

			if (typ.getSelectedItem().toString() == typ_list.elementAt(1)) {
				JLabel ort_label = new JLabel("Ort:");
				sub_b.add(ort_label);
				ort = new JTextField();
				ort.setToolTipText("Bitte geben Sie einen Ort");
				sub_b.add(ort);
				
				JLabel klima_label= new JLabel("Klima:");
				sub_b.add(klima_label);
				klima = new JComboBox(Klima.wertebereich);
				sub_b.add(klima);
				
				JLabel sterne_label= new JLabel("Sterne:");
				sub_b.add(sterne_label);
				sterne = new JTextField();
				sterne.setToolTipText("Bitte geben Sie Anzahl der Sterne ein");
				sub_b.add(sterne);
				
				JLabel verpfelgeung_label= new JLabel("Verpflegung:");
				sub_b.add(verpfelgeung_label);
				verpflegung = new JComboBox(Verpflegungsart.wertebereich);
				sub_b.add(verpflegung);
				
				JLabel bierpreis_label= new JLabel("Bierpreie:");
				sub_b.add(bierpreis_label);
				bierpreis = new JComboBox(Bierpreis.wertebereich);
				sub_b.add(bierpreis);
				
			}

			if (typ.getSelectedItem().toString() == typ_list.elementAt(2)) {
				JLabel ort_label = new JLabel("Ort:");
				ort = new JTextField();
				ort.setToolTipText("Bitte geben SIe einen Ort");
				sub_b.add(ort_label);
				sub_b.add(ort);
			
			}

			if (typ.getSelectedItem().toString() == typ_list.elementAt(3)) {
				JLabel ort_label= new JLabel("Ort:");
				sub_b.add(ort_label);
				ort = new JTextField();
				ort.setToolTipText("Bitte geben SIe einen Ort");
				sub_b.add(ort);
				
				JLabel bierpreis_label= new JLabel("Bierpreis:");
				sub_b.add(bierpreis_label);
				bierpreis = new JComboBox(Bierpreis.wertebereich);
				sub_b.add(bierpreis);
				
			}

			if (typ.getSelectedItem().toString() == typ_list.elementAt(4)) {
				JLabel ort_label= new JLabel("Startort:");
				sub_b.add(ort_label);
				ort = new JTextField();
				ort.setToolTipText("Bitte geben Sie einen Startort");
				sub_b.add(ort);
				JLabel ortz_label= new JLabel("Zielort:");
				sub_b.add(ortz_label);
				ortz = new JTextField();
				ortz.setToolTipText("Bitte geben Sie einen Zielort");
				sub_b.add(ortz);
				
				JLabel klasse_label= new JLabel("Klasse:");
				sub_b.add(klasse_label);
				klasse = new JComboBox(Klasse.wertebereich);
				sub_b.add(klasse);
				
				JLabel bierpreis_label= new JLabel("Bierpreie:");
				sub_b.add(bierpreis_label);
				bierpreis = new JComboBox(Bierpreis.wertebereich);
				sub_b.add(bierpreis);
				
			}
			sub_b.repaint();
		}
		
	}
	
	

}
