package graphic;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	private JPanel sub_one;
	private JPanel sub_two;

	private JTextField name;
	private JComboBox typ;
	private Vector<String> typ_list;
	private JFormattedTextField vpreis;
	private JFormattedTextField bpreis;
	private JTextField kap;
	private JTextField anbieter;
	private JFormattedTextField von;
	private JFormattedTextField bis;
	private JTextField interval;

	private JTextField ort;
	private JTextField ortz;
	private JComboBox klima;
	private JTextField sterne;
	private JComboBox verpflegung;
	private JComboBox bierpreis;
	private JComboBox klasse;
	private String[] k;

	private JButton suche;
	
	public Suchmaske() throws ParseException {
		
		
		setLayout(new BorderLayout(5, 5));
		up = new JPanel(new GridLayout(0, 2));
		sub_a = new JPanel(new GridLayout(9, 2));
		sub_b = new JPanel(new GridLayout(6, 0));
		
		JLabel name_label= new JLabel("Name:");
		sub_a.add(name_label);
		name =new JTextField();
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
		vpreis = new JFormattedTextField(new DecimalFormat("#*0.##"));
		vpreis.setToolTipText("Bitte geben Sie eine Mindestpreis ein");
		sub_a.add(vpreis);
		
		JLabel bpreis_label = new JLabel("Endpreis:");
		sub_a.add(bpreis_label);
		bpreis = new JFormattedTextField(new DecimalFormat("#*0.##"));
		bpreis.setToolTipText("Bitte geben Sie einen Hoechstpreis ein");
		sub_a.add(bpreis);
		
		JLabel kap_label = new JLabel("Kapazitaet:");
		sub_a.add(kap_label);
		kap = new JFormattedTextField(NumberFormat.getInstance());
		kap.setToolTipText("Bitte geben Sie ein fuer wie viele Personen das Angebot gebucht werden soll");
		sub_a.add(kap);
		
		JLabel anbieter_label = new JLabel("Bitte geben Sie den gewünschten Anbieter ein:");
		sub_a.add(anbieter_label);
		anbieter = new JTextField();
		anbieter.setToolTipText("Bitte geben Sie den gewuenschten Anbieter ein");
		sub_a.add(anbieter);
		
		JLabel vdatum_label = new JLabel("Bitte geben Sie das Startdatum ein:");
		sub_a.add(vdatum_label);
		von = new JFormattedTextField(new SimpleDateFormat("dd/mm/yyyy"));
		sub_a.add(von);
		
		JLabel bdatum_label = new JLabel("Bitte geben Sie das Enddatum ein:");
		sub_a.add(bdatum_label);
		bis = new JFormattedTextField(new SimpleDateFormat("dd/mm/yyyy"));
		sub_a.add(bis);
		
		JLabel interval_label = new JLabel("Bitte geben Sie die gewünschte Länge ein:");
		sub_a.add(interval_label);
		interval = new JFormattedTextField(NumberFormat.getInstance());
		sub_a.add(interval);
		
		sub_b = new JPanel(new GridLayout(0, 2));
		sub_one = new JPanel(new GridLayout(6,1));
		sub_two = new JPanel(new GridLayout(6,1));
		sub_b.add(sub_a);
		sub_b.add(sub_two);
		
		
		
		up.add(sub_a);
		up.add(sub_b);

		mid = new JPanel(new FlowLayout());
		suche = new JButton("Suchen");
		mid.add(suche);
		
		add(BorderLayout.NORTH,up);
		add(BorderLayout.SOUTH,mid);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == typ) {
			
			sub_one.removeAll();
			sub_two.removeAll();
			sub_b.validate();
			if (typ.getSelectedItem().toString() == typ_list.elementAt(0)) {
				
			}

			if (typ.getSelectedItem().toString() == typ_list.elementAt(1)) {
				JLabel ort_label = new JLabel("Ort:");
				sub_one.add(ort_label);
				ort = new JTextField();
				ort.setToolTipText("Bitte geben Sie einen Ort");
				sub_two.add(ort);
				
				JLabel klima_label= new JLabel("Klima:");
				sub_one.add(klima_label);
				klima = new JComboBox(Klima.wertebereich);
				sub_two.add(klima);
				
				JLabel sterne_label= new JLabel("Sterne:");
				sub_one.add(sterne_label);
				sterne = new JTextField();
				sterne.setToolTipText("Bitte geben Sie Anzahl der Sterne ein");
				sub_two.add(sterne);
				
				JLabel verpfelgeung_label= new JLabel("Verpflegung:");
				sub_one.add(verpfelgeung_label);
				verpflegung = new JComboBox(Verpflegungsart.wertebereich);
				sub_two.add(verpflegung);
				
				JLabel bierpreis_label= new JLabel("Bierpreis:");
				sub_one.add(bierpreis_label);
				bierpreis = new JComboBox(Bierpreis.wertebereich);
				sub_two.add(bierpreis);
				
			}

			if (typ.getSelectedItem().toString() == typ_list.elementAt(2)) {
				JLabel ort_label = new JLabel("Ort:");
				ort = new JTextField();
				ort.setToolTipText("Bitte geben SIe einen Ort");
				sub_one.add(ort_label);
				sub_two.add(ort);
			
			}

			if (typ.getSelectedItem().toString() == typ_list.elementAt(3)) {
				JLabel ort_label= new JLabel("Ort:");
				sub_one.add(ort_label);
				ort = new JTextField();
				ort.setToolTipText("Bitte geben SIe einen Ort");
				sub_two.add(ort);
				
				JLabel bierpreis_label= new JLabel("Bierpreis:");
				sub_one.add(bierpreis_label);
				bierpreis = new JComboBox(Bierpreis.wertebereich);
				sub_two.add(bierpreis);
				
			}

			if (typ.getSelectedItem().toString() == typ_list.elementAt(4)) {
				JLabel ort_label= new JLabel("Startort:");
				sub_one.add(ort_label);
				ort = new JTextField();
				ort.setToolTipText("Bitte geben Sie einen Startort");
				sub_two.add(ort);
				JLabel ortz_label= new JLabel("Zielort:");
				sub_one.add(ortz_label);
				ortz = new JTextField();
				ortz.setToolTipText("Bitte geben Sie einen Zielort");
				sub_two.add(ortz);
				
				JLabel klasse_label= new JLabel("Klasse:");
				sub_one.add(klasse_label);
				klasse = new JComboBox(Klasse.wertebereich);
				sub_two.add(klasse);
				
				JLabel bierpreis_label= new JLabel("Bierpreie:");
				sub_one.add(bierpreis_label);
				bierpreis = new JComboBox(Bierpreis.wertebereich);
				sub_two.add(bierpreis);
				
			}
			sub_one.repaint();
			sub_two.repaint();
			sub_b.validate();
			sub_b.repaint();
			this.validate();
			this.repaint();
			
		}
		else if(e.getSource()==suche){
			
			String[] k =Angebotsverwaltung.angebotNameToErlaubteKriterien(typ.getSelectedItem().toString());
			
			for(int i=0;i < sub_two.getComponentCount(); i++)
			{
			 Component c = sub_two.getComponent(i);
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
