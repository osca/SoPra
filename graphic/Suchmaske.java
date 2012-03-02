package graphic;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import main.Portal;
import angebote.Angebotsverwaltung;
import angebote.kriterien.Bierpreis;
import angebote.kriterien.Klasse;
import angebote.kriterien.Klima;
import angebote.kriterien.Verpflegungsart;
import angebote.typen.Angebot;

public class Suchmaske extends JPanel implements ActionListener {

	private JPanel up;
	private JPanel sub_a;
	private JPanel sub_b;
	private JPanel mid;

	private JFormattedTextField name;
	private JComboBox typ;
	private Vector<String> typ_list;
	private JFormattedTextField vpreis;
	private JFormattedTextField bpreis;
	private JFormattedTextField kap;
	private JFormattedTextField anbieter;
	private JFormattedTextField von;
	private JFormattedTextField bis;
	private JFormattedTextField interval;

	private JTextField ort;
	private JTextField ortz;
	private JComboBox klima;
	private JTextField sterne;
	private JComboBox verpflegung;
	private JComboBox bierpreis;
	private JComboBox klasse;
	private String[] k;

	private JButton suche;
	private JButton abbrechen;

	public Suchmaske() throws ParseException {
		

		MaskFormatter preisformat = new MaskFormatter("******.**");
		preisformat.setValidCharacters("0123456789");
		MaskFormatter date_f = new MaskFormatter("##/##/####");
		date_f.setValidCharacters("0123456789");
		MaskFormatter interv = new MaskFormatter("**");
		interv.setValidCharacters("0123456789");
		MaskFormatter stringformat =new MaskFormatter(Methods.format4long(30));
		stringformat.setValidCharacters("abcdefghijklmopqrstuvwxyz1234567890ABCDEFGHIJKLMOPQRSTUVWXYZ");
		
		setLayout(new BorderLayout(5, 5));
		up = new JPanel(new GridLayout(0, 2));
		sub_a = new JPanel(new GridLayout(9, 2));
		sub_b = new JPanel(new GridLayout(6, 0));
		JLabel name_label= new JLabel("Name:");
		sub_a.add(name_label);
		name =new JFormattedTextField(stringformat);
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
		JLabel vpreis_label = new JLabel("Startpreis:");
		sub_a.add(vpreis_label);
		vpreis = new JFormattedTextField(preisformat);
		vpreis.setToolTipText("Bitte geben Sie eine Mindestpreis ein");
		sub_a.add(vpreis);
		JLabel bpreis_label = new JLabel("Endpreis:");
		sub_a.add(bpreis_label);
		bpreis = new JFormattedTextField(preisformat);
		bpreis.setToolTipText("Bitte geben Sie einen Hoechstpreis ein");
		sub_a.add(bpreis);
		JLabel kap_label = new JLabel("Kapazitaet:");
		sub_a.add(kap_label);
		kap = new JFormattedTextField(interv);
		kap.setToolTipText("Bitte geben Sie ein fuer wie viele Personen das Angebot gebucht werden soll");
		sub_a.add(kap);
		anbieter = new JFormattedTextField(stringformat);
		anbieter.setToolTipText("Bitte geben Sie den gewuenschten Anbieter ein");
		sub_a.add(anbieter);
		sub_b = new JPanel(new GridLayout(6, 0));
		von = new JFormattedTextField(date_f);
		sub_a.add(von);
		bis = new JFormattedTextField(date_f);
		sub_a.add(bis);
		interval = new JFormattedTextField(interv);
		sub_a.add(interval);
		up.add(sub_a);
		up.add(sub_b);

		mid = new JPanel(new FlowLayout());
		suche = new JButton("Suchen");
		mid.add(suche);
		
		add(BorderLayout.NORTH,up);
		add(BorderLayout.SOUTH,mid);
		//abbrechen = new JButton("Abbrechen");

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		MaskFormatter stringformatone = null;
		try {
			stringformatone = new MaskFormatter(Methods.format4long(30));
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		stringformatone.setValidCharacters("abcdefghijklmopqrstuvwxyz1234567890ABCDEFGHIJKLMOPQRSTUVWXYZ");
		
		if (e.getSource() == typ) {
			sub_b.removeAll();
			if (typ.getSelectedItem().toString() == typ_list.elementAt(0)) {
				
			}

			if (typ.getSelectedItem().toString() == typ_list.elementAt(1)) {
				JLabel ort_label = new JLabel("Ort:");
				sub_b.add(ort_label);
				ort = new JFormattedTextField(stringformatone);
				ort.setToolTipText("Bitte geben Sie einen Ort");
				sub_b.add(ort);
				
				JLabel klima_label= new JLabel("Klima:");
				sub_b.add(klima_label);
				klima = new JComboBox(Klima.wertebereich);
				sub_b.add(klima);
				
				JLabel sterne_label= new JLabel("Sterne:");
				sub_b.add(sterne_label);
				sterne = new JFormattedTextField(stringformatone);
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
				ort = new JFormattedTextField(stringformatone);
				ort.setToolTipText("Bitte geben SIe einen Ort");
				sub_b.add(ort);
			
			}

			if (typ.getSelectedItem().toString() == typ_list.elementAt(3)) {
				JLabel ort_label= new JLabel("Ort:");
				sub_b.add(ort_label);
				ort =new JFormattedTextField(stringformatone);
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
				ort = new JFormattedTextField(stringformatone);
				ort.setToolTipText("Bitte geben Sie einen Startort");
				sub_b.add(ort);
				JLabel ortz_label= new JLabel("Zielort:");
				sub_b.add(ortz_label);
				ortz = new JFormattedTextField(stringformatone);
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
		else if(e.getSource()==suche){
			
			String[] k =Angebotsverwaltung.angebotNameToErlaubteKriterien(typ.getSelectedItem().toString());
			
			for(int i=0;i < sub_b.getComponentCount(); i++)
			{
			 Component c = sub_b.getComponent(i);
			  if(c instanceof JComboBox){
				  k[i]=((JComboBox) c).getSelectedItem().toString();
			  }
			  if(c instanceof JFormattedTextField){
				  k[i]= ((JFormattedTextField) c).getText();
			  }
			       
			}
			Date[] date = null;
			try {
				date = Methods.dater(von.getText(),bis.getText(),Integer.parseInt(interval.getText()));
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			Portal.Angebotsverarbeitung().sucheAngebote(name.getText(),Angebot.convertNameToTyp(typ.getSelectedItem().toString()), Integer.parseInt(kap.getText()), Double.parseDouble(vpreis.getText()), 
					Double.parseDouble(bpreis.getText()), date, k);
			}
//		else if(e.getSource()==abbrechen){
//			
//		}
	}
	
	
}
