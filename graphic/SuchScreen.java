package graphic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import main.Portal;
import angebote.kriterien.Bierpreis;
import angebote.kriterien.Klasse;
import angebote.kriterien.Klima;
import angebote.kriterien.Land;
import angebote.kriterien.Verpflegungsart;
import angebote.typen.Angebot;


/**
 *  SuchScreen wird auf dem Hauptfenster dargestellt um eine Suche zu unterstützen.
 * @author delikat, Rudi
 *
 */
@SuppressWarnings("serial")
public class SuchScreen extends JPanel
{	
	private final static Object[] TYPLIST = new Object[]{"Typ","Hoteluebernachtung","Autovermietung","Ausflug","Flug"};
	
	private JComboBox types;
	private JPanel newScreen = new JPanel(new BorderLayout());
	
	private ArrayList<JLabel> labelList = new ArrayList<JLabel>();
	private ArrayList<JFormattedTextField> fieldList = new ArrayList<JFormattedTextField>();
	private ArrayList<JComboBox> boxList = new ArrayList<JComboBox>();
	
	private GridLayout grid;
	private JPanel north;
	private JPanel center;
	
	private JFormattedTextField[] felder;
	private ArrayList<Angebot> result;
	
	/**
	 * Konstruktor. Initialisiert alle Attribute und stellt sie anschliessend dar.
	 */
	public SuchScreen()
	{
		
		Border border = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.LIGHT_GRAY);
		grid = new GridLayout(0,1);
		grid.setHgap(4);
		this.setLayout(new BorderLayout());
		this.setBorder(border);
		
		JPanel main = new JPanel(new BorderLayout());
		//main.setPreferredSize(new Dimension(MainFrame.BUTTONWIDTH,MainFrame.BUTTONWIDTH));
		
		////////////
		
		north = new JPanel(new GridLayout(1,0));
		center = new JPanel(new GridLayout(1,0));
		
		north.setBorder(border);
		center.setBorder(border);
		
		////////////
		
		JPanel labelPanel = new JPanel(grid);
		JLabel[] labels = new JLabel[8];
		
		labels[0] = new JLabel("  Angebots-Typ");
		labels[1] = new JLabel("  Name");
		labels[2] = new JLabel("  Kapazitaet");
		labels[3] = new JLabel("  Startpreis");
		labels[4] = new JLabel("  Endpreis");
		labels[5] = new JLabel("  Anbieter");
		labels[6] = new JLabel("  Startdatum");
		labels[7] = new JLabel("  Enddatum");
		
		
		for(int i=0; i<labels.length; i++)
			labelPanel.add(labels[i]);
		
		JPanel felderPanel = new JPanel(grid);

		types = new JComboBox(TYPLIST);
		types.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				addScreen();
			}
		});
		felderPanel.add(types);
		
		////////////////
		
		try 
		{
			felder = new JFormattedTextField[7];	//entspricht dem labelarray
			felder[0] = new JFormattedTextField();
			felder[0].setDocument(new MaxTextDocument(MainFrame.TEXTFIELDLENGTH));
			felder[1] = new JFormattedTextField(new NumberFormatter());
			felder[2] = new JFormattedTextField(new DecimalFormat("#*0.00"));
			felder[3] = new JFormattedTextField(new DecimalFormat("#*0.00"));
			felder[4] = new JFormattedTextField();
			felder[4].setDocument(new MaxTextDocument(MainFrame.TEXTFIELDLENGTH));
			felder[5] = new JFormattedTextField(new MaskFormatter("##/##/####"));
			felder[6] = new JFormattedTextField(new MaskFormatter("##/##/####"));
			
			for(int i=0; i<felder.length; i++)
				felderPanel.add(felder[i]);
		} 
		catch (ParseException e) 
		{
			//e.printStackTrace();
		}
		
		////////////////
		
		JButton search = new JButton("Suchen");
		search.setPreferredSize(new Dimension(MainFrame.BUTTONWIDTH, MainFrame.BUTTONHEIGHT));
		search.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				readContent();
				onSearch();
			}
		});
		JPanel buttonPanel = new JPanel(new FlowLayout());
		buttonPanel.add(search);
		
		///////////////
		
		north.add(labelPanel, BorderLayout.WEST);
	    north.add(felderPanel, BorderLayout.EAST);
		center.add(newScreen, BorderLayout.NORTH);
		
		main.add(north, BorderLayout.CENTER);
		main.add(center, BorderLayout.SOUTH);
		this.add(main, BorderLayout.NORTH);
		this.add(buttonPanel, BorderLayout.CENTER);
	}
	
	/**
	 * Ermöglich das dynamische hinzufügen von GUI-Elementen abhänging vom voher gewählten Angebots-Typen.
	 */
	public void addScreen()
	{
		newScreen = new JPanel(new BorderLayout());
		center.removeAll();
		labelList.clear();
		fieldList.clear();
		boxList.clear();
		
		try
		{
			if(types.getSelectedItem().toString() == TYPLIST[1]) 
				type1();
			else if(types.getSelectedItem().toString() == TYPLIST[2]) 
				type2();
			else if(types.getSelectedItem().toString() == TYPLIST[3]) 
				type3();
			else if (types.getSelectedItem().toString() == TYPLIST[4]) 
				type4();
			

			JPanel newLeft = new JPanel(grid);
			JPanel newRight = new JPanel(grid);
			
			for(int i=0; i<labelList.size(); i++)
				newLeft.add(labelList.get(i));
			for(int i=0; i<fieldList.size(); i++)
				newRight.add(fieldList.get(i));
			for(int i=0; i<boxList.size(); i++)
				newRight.add(boxList.get(i));
			
			center.add(newLeft);
			center.add(newRight);
			
			this.validate();
			this.repaint();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}
	
	/**
	 * Liest alle nötigen Informationen aus den GUI-Elementen aus.
	 */
	private void readContent()
	{
		try
		{
			double vonPreis = Portal.Angebotsverarbeitung().KEINPREIS;
			double bisPreis = Portal.Angebotsverarbeitung().KEINPREIS;
			
			if(!felder[2].getText().equals(""))
				vonPreis = Double.parseDouble(felder[2].getText().replace(",", "."));
			
			if(!felder[3].getText().equals(""))
				bisPreis = Double.parseDouble(felder[3].getText().replace(",", "."));
			
			if(types.getSelectedIndex() == 0)
				JOptionPane.showMessageDialog(this, "Sie muessen einen Angebotstypen waehlen");
			
			else if((!felder[2].getText().equals("") && vonPreis < 0)
					|| (!felder[3].getText().equals("") && bisPreis < 0))
				JOptionPane.showMessageDialog(this, "Es gibt keinen negativen Preis");
			
			else if((!felder[2].getText().equals("") && !felder[3].getText().equals(""))
					&& !(vonPreis <= bisPreis))
					JOptionPane.showMessageDialog(this, "Der Startpreis ist groesser als der Endpreis");
			
			else {
				//Heutigen Tag initialisieren
				Date heute = Methods.getHeuteNullUhr();
				
				int typ = Angebot.convertNameToTyp(types.getSelectedItem().toString());
				String[] kriterien = new String[fieldList.size()+boxList.size()];
				
				for(int i=0; i<fieldList.size(); i++)
					kriterien[i] = fieldList.get(i).getText();
				for(int i=0; i<boxList.size(); i++)
					kriterien[i] = ""+boxList.get(i).getSelectedItem();
				
				String name = felder[0].getText();
				int laenge = Portal.Angebotsverarbeitung().KEINEKAPAZITAET;
				Date von = Portal.Angebotsverarbeitung().KEINEDATEN;
				Date bis = Portal.Angebotsverarbeitung().KEINEDATEN;
				String anbieter = Portal.Angebotsverarbeitung().KEINNAME;
				boolean datumfail = false;
				
				if(!felder[1].getText().equals(""))
					laenge = Integer.parseInt(felder[1].getText());
				
				if(!felder[4].getText().equals(""))
					anbieter = felder[4].getText();
				
				if(!felder[5].getText().equals("  /  /    "))
					if(Methods.isValidDatestring(felder[5].getText()))
						von = Methods.stringToDate(felder[5].getText());
					else
						datumfail = true;
				if(!felder[6].getText().equals("  /  /    "))
					if(Methods.isValidDatestring(felder[6].getText()))
						bis = Methods.stringToDate(felder[6].getText());
					else
						datumfail = true;
				
				if(von.before(heute))
					von = heute;
				
				if(!von.equals(Portal.Angebotsverarbeitung().KEINEDATEN) && !bis.equals(Portal.Angebotsverarbeitung().KEINEDATEN) && bis.before(von))
					JOptionPane.showMessageDialog(this, "Startdatum liegt nach dem Enddatum!");
				
				else if(!datumfail)
					result = Portal.Angebotsverarbeitung().sucheAngebote(name, anbieter, typ, laenge, vonPreis, bisPreis, von, bis, kriterien);
				else
					JOptionPane.showMessageDialog(this, "Ungueltiges Datum");
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}
	
	/**
	 * Initialisiert die GUI-Elemente für den Typ "Hoteluebernachtung".
	 */
	private void type1() 
	{
		labelList.add(new JLabel("Land:"));
		labelList.add(new JLabel("Ort:"));
		labelList.add(new JLabel("Sterne:"));
		labelList.add(new JLabel("Klima:"));
		labelList.add(new JLabel("Verpflegung:"));
		labelList.add(new JLabel("Bierpreis:"));
		
		final JComboBox land = new JComboBox();
		land.addItem("");
		for(String s: Land.wertebereich)
			land.addItem(s);
		final JComboBox ort = new JComboBox();
		land.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				repaintOrt(ort, land);
			}
			
		});
		boxList.add(land);
		boxList.add(ort);
		
		JComboBox comboBier = new JComboBox();
		JComboBox comboKlima = new JComboBox();
		JComboBox comboVerpflegung = new JComboBox();
		
		comboBier.addItem("");
		for(int i=0; i<Bierpreis.wertebereich.length; i++)
			comboBier.addItem(Bierpreis.wertebereich[i]);
		
		comboKlima.addItem("");
		for(int i=0; i<Klima.wertebereich.length; i++)
			comboKlima.addItem(Klima.wertebereich[i]);
		
		comboVerpflegung.addItem("");
		for(int i=0; i<Verpflegungsart.wertebereich.length; i++)
			comboVerpflegung.addItem(Verpflegungsart.wertebereich[i]);
		
		boxList.add(comboBier);
		boxList.add(comboKlima);
		boxList.add(comboVerpflegung);
	}
	/**
	 * Initialisiert die GUI-Elemente für den Typ "Autovermietung".
	 */
	private void type2()
	{
		labelList.add(new JLabel("Land:"));
		labelList.add(new JLabel("Ort:"));
		setComboBox();
	}
	
	/**
	 * Initialisiert die GUI-Elemente für den Typ "Hoteluebernachtung".
	 */
	private void type3()
	{
		labelList.add(new JLabel("Land:"));
		labelList.add(new JLabel("Ort:"));
		labelList.add(new JLabel("Bierpreis:"));
		
		setComboBox();
		
		JComboBox comboBier = new JComboBox();
		
		comboBier.addItem("");
		for(int i=0; i<Bierpreis.wertebereich.length; i++)
			comboBier.addItem(Bierpreis.wertebereich[i]);
		boxList.add(comboBier);
	}
	
	/**
	 * Initialisiert die GUI-Elemente für den Typ "Ausflug".
	 */
	private void type4()
	{
		labelList.add(new JLabel("Startland:"));
		labelList.add(new JLabel("Startort:"));
		labelList.add(new JLabel("Zieland:"));
		labelList.add(new JLabel("Zielort:"));
		labelList.add(new JLabel("Klasse:"));
		labelList.add(new JLabel("Bierpreis:"));
		
		setComboBox();
		setComboBox();
		
		JComboBox comboKlasse = new JComboBox();
		JComboBox comboBier = new JComboBox();
		
		comboKlasse.addItem("");
		for(int i=0; i<Klasse.wertebereich.length; i++)
			comboKlasse.addItem(Klasse.wertebereich[i]);
		boxList.add(comboKlasse);
		
		comboBier.addItem("");
		for(int i=0; i<Bierpreis.wertebereich.length; i++)
			comboBier.addItem(Bierpreis.wertebereich[i]);
		boxList.add(comboBier);
	}
	
	/**
	 * Initialisiert die GUI-Elemente für den Typ "Flug".
	 */
	private void setComboBox()
	{
		final JComboBox land = new JComboBox();
		land.addItem("");
		for(String s: Land.wertebereich)
			land.addItem(s);
		final JComboBox ort = new JComboBox();
		ort.addItem("");
		land.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				repaintOrt(ort, land);
			}
			
		});
		boxList.add(land);
		boxList.add(ort);
	}
	
	/**
	 * Sorgt fuer das neue Einlesen der Orte aus einer Datei, nachdem das Land gewechselt wurde.
	 * @param ort
	 * @param land
	 */
	public void repaintOrt(JComboBox ort, JComboBox land)
	{		
		if(!land.getSelectedItem().equals("")) {
			String[] laender = (Land.getOrte((String)land.getSelectedItem()));
			ort.removeAllItems();
			ort.addItem("");
			for(String s: laender)
				ort.addItem(s);
		}
		else {
			ort.removeAllItems();
			ort.addItem("");
		}
		ort.repaint();
	}
	
	
	public ArrayList<Angebot> getList()
	{
		return result;
	}
	
	public void onSearch()
	{}
}
