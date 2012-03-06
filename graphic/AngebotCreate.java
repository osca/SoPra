package graphic;

import java.awt.BorderLayout;
import java.awt.Component;
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
import angebote.kriterien.Ort;
import angebote.kriterien.Verpflegungsart;
import angebote.typen.Angebot;
import buchungen.InvalidDateException;


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
	private JTextField interval;
	private JLabel anbieter;

	// Kriterien
	private JTextField ort;
	//private JTextField ortz;
	private JComboBox klima;
	private JTextField sterne;
	private JComboBox verpflegung;
	private JComboBox bierpreis;
	private JComboBox klasse;

	private JTextArea beschreibung;
	
	private JButton loeschen;
	private JButton bestaetigen;
	private JComboBox land;
	private JComboBox landz;
	private JComboBox ortz;
	

	public AngebotCreate(Anbieter a) throws ParseException {
		
		setLayout(new BorderLayout(5, 5));
		up = new JPanel(new GridLayout(2, 1));
		sub_a = new JPanel(new GridLayout(9, 2));
		
		//Name+Label
		name = new JTextField();
		JLabel name_label= new JLabel("Name:");
		sub_a.add(name_label);
		name.setToolTipText("Bitte Namen eingeben");
		sub_a.add(name);
		
		//Typ-ComboBox+Label+Vector mit Item-Strings
		JLabel typ_label = new JLabel("Typ:");
		sub_a.add(typ_label);
		typ_list = new Vector<String>();
		typ_list.add("Typ");
		typ_list.add("Hotel");
		typ_list.add("Autovermietung");
		typ_list.add("Ausflug");
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
		
		//Preis+Label
		JLabel preis_label = new JLabel("Preis:");
		sub_a.add(preis_label);
		preis =new JFormattedTextField(new DecimalFormat("#*0.00"));
		sub_a.add(preis);
		
		//Kapazitaet+Label
		JLabel kap_label = new JLabel("Kapazitaet:");
		sub_a.add(kap_label);
		NumberFormat nformat = NumberFormat.getIntegerInstance();
		nformat.setGroupingUsed(false);
		kap = new JFormattedTextField(nformat);
		sub_a.add(kap);
		
		//Startdatum+Label
		JLabel von_label = new JLabel("Startdatum (im DD/MM/YYYY Format):");
		sub_a.add(von_label);
		von = new JFormattedTextField(new MaskFormatter("##/##/####"));
		sub_a.add(von);
		
		//Enddatum+Label
		JLabel bis_label = new JLabel("Enddatum (im DD/MM/YYYY Format):");
		sub_a.add(bis_label);
		bis = new JFormattedTextField(new MaskFormatter("##/##/####"));
		sub_a.add(bis);
		
//		//Laenge+Label
//		JLabel interval_label = new JLabel("Laenge:");
//		sub_a.add(interval_label);
//		interval =new JFormattedTextField(NumberFormat.getInstance());
//		sub_a.add(interval);
		
		//Anbieter
		JLabel anbieter_label = new JLabel("Anbieter:");
		sub_a.add(anbieter_label);
		anbieter = new JLabel(a.getName());
		sub_a.add(anbieter);
		
		sub_b = new JPanel(new GridLayout(0, 2));
		sub_one = new JPanel(new GridLayout(6,1));
		sub_two = new JPanel(new GridLayout(6,1));
		
		sub_b.add(sub_one);
		sub_b.add(sub_two);
		up.add(sub_a);
		up.add(sub_b);

		
		//Beschreibung des Angebots
		mid = new JPanel(new GridLayout(1, 0));
		beschreibung = new JTextArea("Bitte geben Sie hier die Beschreibung ein");
		beschreibung.setEditable(true);
		beschreibung.setWrapStyleWord(true);
		mid.add(beschreibung);

		down = new JPanel(new BorderLayout(5, 5));
		loeschen = new JButton("Alle Eingaben Loeschen");
		loeschen.addActionListener(this);
		down.add(BorderLayout.EAST, loeschen);
		bestaetigen = new JButton("Bestätigen");
		bestaetigen.addActionListener(this);
		down.add(BorderLayout.WEST, bestaetigen);

		add(BorderLayout.NORTH, up);
		add(BorderLayout.CENTER, mid);
		add(BorderLayout.SOUTH, down);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		 if(e.getSource()==bestaetigen){
			String[] k =Angebotsverwaltung.angebotNameToErlaubteKriterien(typ.getSelectedItem().toString());
			
			for(int i=0;i < sub_two.getComponentCount(); i++)
			{
				  Component c = sub_two.getComponent(i);
				  if(c instanceof JComboBox)
				  {
					  k[i]=((JComboBox) c).getSelectedItem().toString();
				  }
				  else if(c instanceof JTextField)
				  {
					  k[i]= ((JTextField) c).getText();
				  }
			}

			Date q=null;
			Date w=null;
			final double result = ((Number) preis.getValue()).doubleValue();
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
			try {
				Portal.Angebotsverwaltung().createAngebot((Anbieter) Portal.Accountverwaltung().getLoggedIn(), name.getText(), beschreibung.getText(), Angebot.convertNameToTyp(typ.getSelectedItem().toString()), result, Integer.parseInt(kap.getText()), q,w, k);
				JOptionPane.showMessageDialog(this, "Erstellung erfolgreich");
				name.setText(null);
				typ.setSelectedIndex(0);
				preis.setText(null);
				kap.setText(null);
				von.setText(null);
				bis.setText(null);
				beschreibung.setText("Bitte geben Sie hier eine Beschreibung ein");
				addScreen();
				
			} catch (NumberFormatException e1) {
			
				e1.printStackTrace();
			} catch (InvalidDateException e1) {
				JOptionPane.showMessageDialog(this, "Ueberpruefen Sie das Datum", "Angebot Erstellung", JOptionPane.OK_OPTION);
				e1.printStackTrace();
			}
			
			
		}
		
		else if(e.getSource()==loeschen){
			name.setText(null);
			typ.setSelectedIndex(0);
			preis.setText(null);
			kap.setText(null);
			von.setText(null);
			bis.setText(null);
			interval.setText(null);
			beschreibung.setText("Bitte geben Sie hier eine Beschreibung ein");
			addScreen();
			
		}
	}
	
	public void addScreen()
	{
			sub_one.removeAll();
			sub_two.removeAll();
			sub_b.validate();
			//TODO anzahl der elemente pruefen
			if (typ.getSelectedItem().toString() == typ_list.elementAt(1)) {
				//JLabel land_label = new JLabel("Land:");
				//sub_one.add(land_label);
				//JLabel s_ort_label = new JLabel("Ort:");
				//sub_one.add(ort_label);
				//land = new JComboBox(Ort.wertebereich);
				//ort = new JComboBox(land.getSelectedItem().getOrte());
				//sub_two.add(land);
				//sub_two.add(ort);JLabel ort_label= new JLabel("Ort:");
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
				//JLabel land_label = new JLabel("Land:");
				//sub_one.add(land_label);
				//JLabel s_ort_label = new JLabel("Ort:");
				//sub_one.add(ort_label);
				//land = new JComboBox(Ort.wertebereich);
				//ort = new JComboBox(land.getSelectedItem().getOrte());
				//sub_two.add(land);
				//sub_two.add(ort);JLabel ort_label= new JLabel("Ort:");
				JLabel ort_label = new JLabel("Ort:");
				ort = new JTextField();
				ort.setToolTipText("Bitte geben SIe einen Ort");
				sub_one.add(ort_label);
				sub_two.add(ort);
			
			}

			if (typ.getSelectedItem().toString() == typ_list.elementAt(3)) {
				//JLabel land_label = new JLabel("Land:");
				//sub_one.add(land_label);
				//JLabel s_ort_label = new JLabel("Ort:");
				//sub_one.add(ort_label);
				//land = new JComboBox(Ort.wertebereich);
				//ort = new JComboBox(land.getSelectedItem().getOrte());
				//sub_two.add(land);
				//sub_two.add(ort);JLabel ort_label= new JLabel("Ort:");
				JLabel ort_label = new JLabel("Ort:");
				sub_one.add(ort_label);
				ort = new JTextField();
				ort.setToolTipText("Bitte geben Sie einen Ort");
				sub_two.add(ort);
				
				JLabel bierpreis_label= new JLabel("Bierpreis:");
				sub_one.add(bierpreis_label);
				bierpreis = new JComboBox(Bierpreis.wertebereich);
				sub_two.add(bierpreis);
				
			}

			if (typ.getSelectedItem().toString() == typ_list.elementAt(4)) {
				JLabel s_land_label = new JLabel("Startland:");
				JLabel s_ort_label = new JLabel("Startort:");
				sub_one.add(s_land_label);
				sub_one.add(s_ort_label);
				land = new JComboBox(Land.getWertebereich());
				ort = new JComboBox(Land.getOrte(land.getSelectedItem()));
				sub_two.add(land);
				sub_two.add(ort);
//				JLabel ort_label= new JLabel("Startort:");
//				sub_one.add(ort_label);
//				ort = new JTextField();
//				ort.setToolTipText("Bitte geben Sie einen Startort");
//				sub_two.add(ort);
				JLabel ortz_label= new JLabel("Zielort:");
				JLabel z_land_label = new JLabel("Zielland:");
				JLabel z_ort_label = new JLabel("Zielort:");
				sub_one.add(z_land_label);
				sub_one.add(z_ort_label);
				landz = new JComboBox(Land.getWertebereich());
				ortz = new JComboBox(land.getSelectedItem().getOrte());
				sub_two.add(landz);
				sub_two.add(ortz);
//				sub_one.add(ortz_label);
//				ortz = new JTextField();
//				ortz.setToolTipText("Bitte geben Sie einen Zielort");
//				sub_two.add(ortz);
				
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
	
//	public void repaintOrt(){
//		String x = land.getSeletedItem().toString();
//		ort=new JComboBox(x.getOrte());
//		sub_one.repaint();
//		sub_two.repaint();
//		sub_b.removeAll();
//		sub_b.add(sub_one);
//		sub_b.add(sub_two);
//		sub_b.validate();
//		sub_b.repaint();
//		this.validate();
//		this.repaint();	
//	}
	

}
