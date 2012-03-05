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
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.Portal;
import angebote.Angebotsverwaltung;
import angebote.kriterien.Bierpreis;
import angebote.kriterien.Klasse;
import angebote.kriterien.Klima;
import angebote.kriterien.Verpflegungsart;
import angebote.typen.Angebot;

public class Suchmaske extends JPanel{

	private JPanel up;
	private JPanel sub_a;
	private JPanel sub_b;
	private JPanel mid;
	private JPanel sub_one;
	private JPanel sub_two;
	private JPanel down;	

	private JTextField name;
	private JComboBox typ;
	private Vector<String> typ_list;
	private JFormattedTextField vpreis;
	private JFormattedTextField bpreis;
	private JTextField kap = new JTextField();
	private JTextField anbieter = new JTextField();
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
	private JButton suche;
	
	private ArrayList<Angebot> list;
	
	public Suchmaske() throws ParseException {
		
		setLayout(new BorderLayout(5, 5));
		up = new JPanel(new GridLayout(0, 2));
		sub_a = new JPanel(new GridLayout(8, 2));
		sub_b = new JPanel(new GridLayout(6, 0));
		
		//Name+Label
		JLabel name_label= new JLabel("Name:");
		sub_a.add(name_label);
		name =new JTextField();
		name.setToolTipText("Bitte Namen eingeben");
		sub_a.add(name);
		
		
		//Typ+Lable+Vector mit Item-Strings
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
		typ.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				addScreen();
			}
		});
		sub_a.add(typ);
		
		//Startpreis+Label
		JLabel vpreis_label = new JLabel("Startpreis:");
		sub_a.add(vpreis_label);
		vpreis = new JFormattedTextField(new DecimalFormat("#*0.##"));
		vpreis.setToolTipText("Bitte geben Sie eine Mindestpreis ein");
		sub_a.add(vpreis);
		
		//Endpreis+Label
		JLabel bpreis_label = new JLabel("Endpreis:");
		sub_a.add(bpreis_label);
		bpreis = new JFormattedTextField(new DecimalFormat("#*0.##"));
		bpreis.setToolTipText("Bitte geben Sie einen Hoechstpreis ein");
		sub_a.add(bpreis);
		
		//Abieter+Label
		JLabel anbieter_label = new JLabel("Bitte geben Sie den gewünschten Anbieter ein:");
		sub_a.add(anbieter_label);
		anbieter = new JTextField();
		anbieter.setToolTipText("Bitte geben Sie den gewuenschten Anbieter ein");
		sub_a.add(anbieter);
		
		//Startdatum+Label
		JLabel vdatum_label = new JLabel("Bitte geben Sie das Startdatum (im DD/MM/YYYY Format)ein:");
		sub_a.add(vdatum_label);
		von = new JFormattedTextField(new SimpleDateFormat("dd/mm/yyyy"));
		sub_a.add(von);
		
		//Enddatum+Label
		JLabel bdatum_label = new JLabel("Bitte geben Sie das Enddatum (im DD/MM/YYYY Format)ein:");
		sub_a.add(bdatum_label);
		bis = new JFormattedTextField(new SimpleDateFormat("dd/mm/yyyy"));
		sub_a.add(bis);
		
		//Laenge+Label
		JLabel interval_label = new JLabel("Bitte geben Sie die gewünschte Laenge ein:");
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
		suche.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				readContent();
			}
		});
		mid.add(suche);
		
		down = new JPanel();
		
		add(BorderLayout.NORTH,up);
		add(BorderLayout.CENTER,mid);
		add(BorderLayout.SOUTH,down);
		
	}

	public void addScreen()
	{
			sub_one.removeAll();
			sub_two.removeAll();
			sub_b.validate();

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
			sub_b.removeAll();
			sub_b.add(sub_one);
			sub_b.add(sub_two);
			sub_b.validate();
			sub_b.repaint();
			this.validate();
			this.repaint();
	}
	
	public void readContent()
	{
		try
		{
			String[] k =Angebotsverwaltung.angebotNameToErlaubteKriterien(typ.getSelectedItem().toString());
			
			for(int i=0;i < sub_two.getComponentCount(); i++)
			{
				 Component c = sub_two.getComponent(i);
				 if(c instanceof JComboBox)
				 {
					  k[i]=((JComboBox) c).getSelectedItem().toString();
				 }
				 if(c instanceof JFormattedTextField)
				 {
					  k[i]= ((JFormattedTextField) c).getText();
				 }
			}
//			Date[] date = Methods.dater(von.getText(),bis.getText(),Integer.parseInt(interval.getText()));
			if(typ.getSelectedIndex()!=0)
			{	
				int chosenType = Angebot.convertNameToTyp(typ.getSelectedItem().toString());
				int chosenKapazitaet = Integer.parseInt(kap.getText());
				double chosenVPreis = Double.parseDouble(vpreis.getText());
				double chosenBPreis =  Double.parseDouble(bpreis.getText());
				Date q=null;
				Date w=null;
				try {
					q = Methods.stringToDate(von.getText());
				} catch (ParseException e1) {

					e1.printStackTrace();
				}
				try {
					w = Methods.stringToDate(bis.getText());
				} catch (ParseException e1) {
					
					e1.printStackTrace();
				}
				list = Portal.Angebotsverarbeitung().sucheAngebote(name.getText(),chosenType, chosenKapazitaet, chosenVPreis, chosenBPreis,q,w , k);
				onSearch();
			}
			else
				JOptionPane.showMessageDialog(this, "Sie müssen einen Angebotstypen wählen");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.toString());
		}
	}
	
	public ArrayList<Angebot> getList()
	{
		return list;
	}
	
	public void onSearch()
	{
		
	}
}
