package graphic;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import accounts.Anbieter;
import angebote.kriterien.Bierpreis;
import angebote.kriterien.Klasse;
import angebote.kriterien.Klima;
import angebote.kriterien.Verpflegungsart;


public class AngebotCreate extends JPanel implements ActionListener{
	//b1.setToolTipText("Click this button to disable the middle button.");
	private JPanel up;
	private JPanel sub_a;
	private JPanel sub_b;
	private JPanel mid;
	private JPanel down;
	
	private JTextField name;
	
	private JComboBox typ;
	private Vector<String> typ_l;
	private DefaultComboBoxModel typ_m;
	//private Shice date ---> vllt.
	private JTextField preis;
	private JTextField kap;
	private JLabel anbieter;
	
	//Krit dropdown
	private JTextField ort;
	private JTextField ortz;
	private JComboBox klima;
	private Vector<String> klima_l;
	private JTextField sterne;
	private JComboBox verpflegung;
	private Vector<String> verpflegung_l;
	private JComboBox bierpreis;
	private Vector<String> bierpreis_l;
	private JComboBox klasse;
	private Vector<String> klasse_l;
	
	private JTextField beschreibung;
	
	private JButton verwerfen;
	private JButton bestaetigen;
	
	public AngebotCreate(Anbieter a){
		setLayout(new BorderLayout(5,5));
		
		up = new JPanel(new GridLayout(2,0));
		sub_a = new JPanel(new GridLayout(6,0));
		name = new JTextField();
		name.setToolTipText("Bitte Namen eingeben");
		sub_a.add(name);
		
		
		typ_l= new Vector<String>();
		typ_l.add("Typ");
		typ_l.add("Hoteluebernachtung");
		typ_l.add("Autovermietung");
		typ_l.add("Ausflueg");
		typ_l.add("Flug");
		typ = new JComboBox(typ_l);
		typ.setToolTipText("Bitte waehlen Sie eine Typ aus");
		typ.addActionListener(this);
		
		sub_a.add(typ);
		preis= new JTextField();
		sub_a.add(preis);
		kap = new JTextField();
		sub_a.add(kap);
		anbieter = new JLabel(a.getName());
		sub_a.add(anbieter);
		sub_b = new JPanel(new GridLayout(6,0));
		up.add(sub_a);
		//up.add(sub_b);
		
		mid = new JPanel(new GridLayout(1,0));
		beschreibung = new JTextField();
		mid.add(beschreibung);
		
		down = new JPanel(new BorderLayout(5,5));
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
		
		/**
		 * to do : dynamische erzeugung mit add-methode und of else über erlaubte kriterien
		 */
		if(e.getSource()==typ_l.elementAt(0)){
			sub_b.removeAll();
		}
		
		if(e.getSource()==typ_l.elementAt(1)){
			ort = new JTextField();
			ort.setToolTipText("Bitte geben SIe einen Ort");
			sub_b.add(ort);
			
			
			klima = new JComboBox(Klima.wertebereich);
			sub_b.add(klima);
			
			sterne = new JTextField();
			sterne.setToolTipText("Bitte geben Sie Anzahl der Sterne ein");
			sub_b.add(sterne);
			
			
			verpflegung = new JComboBox(Verpflegungsart.wertebereich);
			sub_b.add(verpflegung);

			bierpreis =  new JComboBox(Bierpreis.wertebereich);
			sub_b.add(bierpreis);
		}
		
		if(e.getSource()==typ_l.elementAt(2)){
			ort = new JTextField();
			ort.setToolTipText("Bitte geben SIe einen Ort");
			sub_b.add(ort);
		}
		
		if(e.getSource()==typ_l.elementAt(3)){
			ort = new JTextField();
			ort.setToolTipText("Bitte geben SIe einen Ort");
			sub_b.add(ort);
		
			bierpreis =  new JComboBox(Bierpreis.wertebereich);
			sub_b.add(bierpreis);
		}
		
		if(e.getSource()==typ_l.elementAt(4)){
			ort = new JTextField();
			ort.setToolTipText("Bitte geben SIe einen Startort");
			sub_b.add(ort);
			
			ortz = new JTextField();
			ortz.setToolTipText("Bitte geben SIe einen Zielort");
			sub_b.add(ortz);
			
			klasse = new JComboBox(Klasse.wertebereich);
			sub_b.add(klasse);
			
			bierpreis =  new JComboBox(Bierpreis.wertebereich);
			sub_b.add(bierpreis);
		}
	}
		
}
