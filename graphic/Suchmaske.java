package graphic;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
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

	private JTextField name;
	private JComboBox typ;
	private Vector<String> typ_list;
	private JTextField vpreis;
	private JTextField bpreis;
	private JTextField kap;
	private JTextField anbieter;
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
		setLayout(new BorderLayout(5, 5));
		up = new JPanel(new GridLayout(0, 2));
		sub_a = new JPanel(new GridLayout(9, 0));
		sub_b = new JPanel(new GridLayout(6, 0));
		name = new JTextField();
		name.setToolTipText("Bitte Namen eingeben");
		sub_a.add(name);
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
		vpreis = new JTextField();
		vpreis.setToolTipText("Bitte geben Sie eine Mindestpreis ein");
		sub_a.add(vpreis);
		bpreis = new JTextField();
		bpreis.setToolTipText("Bitte geben Sie einen Hoechstpreis ein");
		sub_a.add(bpreis);
		kap = new JTextField();
		kap.setToolTipText("Bitte geben Sie ein fuer wie viele Personen das Angebot gebucht werden soll");
		sub_a.add(kap);
		anbieter = new JTextField();
		anbieter.setToolTipText("Bitte geben Sie den gewuenschten Anbieter ein");
		sub_a.add(anbieter);
		sub_b = new JPanel(new GridLayout(6, 0));
//		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		MaskFormatter formatter = new MaskFormatter("##/##/####");
		formatter.setValidCharacters("0123456789");
		von = new JFormattedTextField(formatter);
		sub_a.add(von);
		bis = new JFormattedTextField(formatter);
		sub_a.add(bis);
//		MaskFormatter interv = new MaskFormatter("**");
//		formatter.setValidCharacters("0123456789");
		up.add(sub_a);
		up.add(sub_b);

		mid = new JPanel(new FlowLayout());
		suche = new JButton("Suchen");
		abbrechen = new JButton("Abbrechen");

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
				ort = new JTextField();
				ort.setToolTipText("Bitte geben SIe einen Ort");
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
		else if(e.getSource()==suche){
			
			String[] k =Angebotsverwaltung.angebotNameToErlaubteKriterien(typ.getSelectedItem().toString());
			
			for(int i=0;i < sub_b.getComponentCount(); i++)
			{
			 Component c = sub_b.getComponent(i);
			  if(c instanceof JComboBox){
				  k[i]=((JComboBox) c).getSelectedItem().toString();
			  }
			  if(c instanceof JTextField){
				  k[i]= ((JTextField) c).getText();
			  }
			       
			}
			Date[] date = null;
			try {
				date = Methods.dater(von.getText(),bis.getText(),1);
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			Portal.getSingletonObject().getAngebotsverarbeitung().sucheAngebote(name.getText(),Angebot.convertNameToTyp(typ.getSelectedItem().toString()), Integer.parseInt(kap.getText()), Double.parseDouble(vpreis.getText()), 
					Double.parseDouble(bpreis.getText()), date, k);
			}
		else if(e.getSource()==abbrechen){
			
		}
	}
	
	
}
