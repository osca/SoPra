package graphic;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
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
	
	private ActionListener ortListener;
	
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
			felder[1] = new JFormattedTextField(new NumberFormatter());
			felder[2] = new JFormattedTextField(new DecimalFormat("#*0.00"));
			felder[3] = new JFormattedTextField(new DecimalFormat("#*0.00"));
			felder[4] = new JFormattedTextField();
			felder[5] = new JFormattedTextField(new MaskFormatter("##/##/####"));
			felder[6] = new JFormattedTextField(new MaskFormatter("##/##/####"));
			
			for(int i=0; i<felder.length; i++)
				felderPanel.add(felder[i]);
		} 
		catch (ParseException e) 
		{
			e.printStackTrace();
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
		{//TODO exceptionhandling
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}
	
	private void readContent()
	{
		try
		{
			if(types.getSelectedIndex() == 0)
				JOptionPane.showMessageDialog(this, "Sie muessen einen Angebotstypen waehlen");
			
			else if((!felder[2].getText().equals("") && (new Double(felder[2].getText()).compareTo(0.00)) >= 0 )
					|| (!felder[3].getText().equals("") && (new Double(felder[3].getText()).compareTo(0.00)) >= 0 ))
				if(!(new Double(felder[2].getText()).compareTo(new Double(felder[3].getText())) <= 0))
					JOptionPane.showMessageDialog(this, "Es gibt keinen negativen Preis");
				else
					JOptionPane.showMessageDialog(this, "Der Startpreis ist groesser als der Endpreis");
			
			else {
				//Heutigen Tag initialisieren
				Date heute = new Date();
				Calendar cal = new GregorianCalendar();
				cal.setTime(heute);
				cal.set(Calendar.HOUR_OF_DAY, 0);
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 0);
				cal.set(Calendar.MILLISECOND, 0);
				
				int typ = Angebot.convertNameToTyp(types.getSelectedItem().toString());
				String[] kriterien = new String[fieldList.size()+boxList.size()];
				
				for(int i=0; i<fieldList.size(); i++)
					kriterien[i] = fieldList.get(i).getText();
				for(int i=0; i<boxList.size(); i++)
					kriterien[i] = ""+boxList.get(i).getSelectedItem();
				
				String name = felder[0].getText();
				int laenge = Portal.Angebotsverarbeitung().KEINEKAPAZITAET;
				double vonPreis = Portal.Angebotsverarbeitung().KEINPREIS;
				double bisPreis = Portal.Angebotsverarbeitung().KEINPREIS;
				Date von = Portal.Angebotsverarbeitung().KEINEDATEN;
				Date bis = Portal.Angebotsverarbeitung().KEINEDATEN;
				
				if(!felder[1].getText().equals(""))
					laenge = Integer.parseInt(felder[1].getText());

				if(!felder[2].getText().equals(""))
					vonPreis = new Double(felder[2].getText());
				
				if(!felder[3].getText().equals(""))
					bisPreis = new Double(felder[3].getText());
				
				if(!felder[5].getText().equals("  /  /    "))
					von = Methods.stringToDate(felder[5].getText());
				
				if(!felder[6].getText().equals("  /  /    "))
					bis = Methods.stringToDate(felder[6].getText());
				
				if(von.before(cal.getTime()))
					von = cal.getTime();
				
				if(!von.equals(Portal.Angebotsverarbeitung().KEINEDATEN) && !bis.equals(Portal.Angebotsverarbeitung().KEINEDATEN) && bis.before(von))
					JOptionPane.showMessageDialog(this, "Startdatum liegt nach dem Enddatum!");
				else
					result = Portal.Angebotsverarbeitung().sucheAngebote(name, typ, laenge, vonPreis, bisPreis, von, bis, kriterien);
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}
	
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
		boxList.add(new JComboBox(Klima.wertebereich));
		boxList.add(new JComboBox(Verpflegungsart.wertebereich));
		boxList.add(new JComboBox(Bierpreis.wertebereich));
	}
	private void type2()
	{
		labelList.add(new JLabel("Land:"));
		labelList.add(new JLabel("Ort:"));
		setComboBox();
	}
	private void type3()
	{
		labelList.add(new JLabel("Land:"));
		labelList.add(new JLabel("Ort:"));
		labelList.add(new JLabel("Bierpreis:"));
		
		setComboBox();
		boxList.add(new JComboBox(Bierpreis.wertebereich));
	}
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
		boxList.add(new JComboBox(Klasse.wertebereich));
		boxList.add(new JComboBox(Bierpreis.wertebereich));
	}
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
