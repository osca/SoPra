package graphic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.text.DateFormatter;
import javax.swing.text.MaskFormatter;

import main.Portal;
import accounts.Account;
import accounts.Anbieter;
import accounts.Betreiber;
import accounts.Default;
import accounts.Kunde;
import angebote.typen.Angebot;
import angebote.typen.Flug;
import buchungen.Buchung;

public class MainFrame extends JFrame
{
	public static final int BUTTONWIDTH = 180;
	public static final int BUTTONHEIGHT = 38;

	private JButton loginButton;
	private JButton registerButton;
	private JButton nachrichtButton;
	private JButton eigeneButton;
	private JButton sucheButton;
	private JButton topButton;
	private JButton alleButton;
	private JButton erstelleButton;
	
	private Account account;
	private JPanel screen;
	private JScrollPane scroll;
	
	private ListeScreen list;
	
	private boolean logged = false;

	public MainFrame()
	{
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		/////////
		
		account = new Default();
		
		//////////

		Border border = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.LIGHT_GRAY);
		
		///////////

		JPanel headerPanel = new JPanel(new BorderLayout());
		headerPanel.setBorder(border);
		this.add(headerPanel, BorderLayout.NORTH);
		
		JPanel menuPanel = new JPanel(new BorderLayout());
		menuPanel.setBorder(border);
		this.add(menuPanel, BorderLayout.WEST);
		
		JPanel registerPanel = new JPanel(new GridLayout(1,0));
		headerPanel.add(registerPanel, BorderLayout.EAST);
		
		GridLayout grid = new GridLayout(0,1);
		grid.setVgap(4);
		JPanel buttonPanel = new JPanel(grid);
		menuPanel.add(buttonPanel, BorderLayout.NORTH);
		
		screen = new JPanel();
		screen.setBorder(border);
		screen.setLayout(new GridLayout(1,0));
		
		scroll = new JScrollPane(screen);
		this.add(scroll, BorderLayout.CENTER);

		JPanel homeButtonPanel = new JPanel();
		headerPanel.add(homeButtonPanel, BorderLayout.WEST);
		
		////////////////

		JButton homeButton = new JButton(new ImageIcon("house_unpressed.png"));

		homeButton.setPreferredSize(new Dimension(BUTTONWIDTH, BUTTONHEIGHT*2));
		homeButton.setPressedIcon(new ImageIcon("house_pressed.png"));
		homeButton.setOpaque(false);
		homeButton.setContentAreaFilled(false);
		homeButton.setBorderPainted(false);
		homeButtonPanel.add(homeButton);

		loginButton = new JButton("Login");
		loginButton.setPreferredSize(new Dimension(BUTTONWIDTH, BUTTONHEIGHT));
		registerButton = new JButton("Registrieren");
		registerButton.setPreferredSize(new Dimension(BUTTONWIDTH, BUTTONHEIGHT));
		sucheButton = new JButton("Suche Angebote");
		sucheButton.setPreferredSize(new Dimension(BUTTONWIDTH, BUTTONHEIGHT));
		topButton = new JButton("Top Angebote");
		topButton.setPreferredSize(new Dimension(BUTTONWIDTH, BUTTONHEIGHT));
		nachrichtButton = new JButton("Nachrichten");
		nachrichtButton.setPreferredSize(new Dimension(BUTTONWIDTH, BUTTONHEIGHT));
		nachrichtButton.setEnabled(false);
		eigeneButton = new JButton("Angebote/Buchungen");
		eigeneButton.setPreferredSize(new Dimension(BUTTONWIDTH, BUTTONHEIGHT));
		eigeneButton.setEnabled(false);
		alleButton = new JButton("Alle Angebote");
		alleButton.setPreferredSize(new Dimension(BUTTONWIDTH, BUTTONHEIGHT));
		erstelleButton = new JButton("Angebot erstellen");
		erstelleButton.setPreferredSize(new Dimension(BUTTONWIDTH, BUTTONHEIGHT));
		erstelleButton.setEnabled(false);
		
		buttonPanel.add(loginButton);
		buttonPanel.add(eigeneButton);
		buttonPanel.add(sucheButton);
		buttonPanel.add(nachrichtButton);
		buttonPanel.add(erstelleButton);
		buttonPanel.add(topButton);
		buttonPanel.add(alleButton);
		registerPanel.add(registerButton);

		// /////////	
		
//		ArrayList<Angebot> al = new ArrayList<Angebot>();
//		for(int i=0;i<100;i++)
//			al.add(new Flug(new Anbieter("horst","@","fu.fu"),"name", "asdfkjalösdfmnklamsdlfkmalsdmflamnsdlfmnaklsmdfklmaklsdmflkamsdlfkmasdfasdf", 23, 23.5, new Date[] { new Date() }, "hier", "ziel", "7", "unbezahlbar"));
//		list = new ListeScreen(this, al);
//		
//		screen.add(list);
//		
		/////////////////
		
		homeButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				showTopAngebote();
			}
		});
	
		
		loginButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				showLogin();
			}
		});
		
		registerButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				showRegister();
			}
		});
		
		eigeneButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent argR0) 
			{
				showEigene();
			}
		});
		
		sucheButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				showSuche();
			}
		});
		
		nachrichtButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				showNachricht();
			}
		});
		
		alleButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				showAlleAngebote();
			}
		});
		
		erstelleButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				showErstelle();
			}
		});
		
		////////////////
		
		this.pack();
		this.setVisible(true);
		
		//////////////
		
		Portal.Angebotsverwaltung().createFlug(new Anbieter("horst","@","fu.fu"),"name", "asdfkjalösdfmnklamsdlfkmalsdmflamnsdlfmnaklsmdfklmaklsdmflkamsdlfkmasdfasdf", 23, 23.5, new Date[] { new Date() }, "hier", "ziel", "7", "unbezahlbar");
		
		showTopAngebote();
	}

	public <T extends Listable> void showDetail(T obj) 
	{
		if(obj.getListableTyp() == Angebot.ANGEBOT)
		{
			System.out.println(((Angebot)obj).getIdentifier());
			
			screen.removeAll();
			screen.add(new AngDetailScreen(Account.KUNDE, (Angebot)obj));
			scroll.setViewportView(screen);
			scroll.repaint();
		}
		else
		{
			screen.removeAll();
			screen.add(new BuchDetailScreen((Buchung)obj));
			scroll.setViewportView(screen);
			scroll.repaint();
		}
	}

	/////////////////////////
	
	private void showLogin() 
	{
		try
		{
			if(!logged)
			{
				JLabel nameLabel = new JLabel("Name");
				JTextField nameField = new JTextField();
				JLabel passwordLabel = new JLabel("Password");
				JPasswordField passwordField = new JPasswordField();
				JLabel label = new JLabel("Bitte geben Sie die Anmeldeinformationen an");
		
				if(JOptionPane.showConfirmDialog(this,new Object[]{label, nameLabel, nameField, passwordLabel, passwordField},"Login",JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION)
				{
				
					Portal.Accountverwaltung().logIn(nameField.getText(), new String(passwordField.getPassword()));
					account = Portal.Accountverwaltung().getLoggedIn();
					
					eigeneButton.setEnabled(true);
					nachrichtButton.setEnabled(true);
					registerButton.setEnabled(false);
					
					nachrichtButton.setText(nachrichtButton.getText()+" ("+Portal.Nachrichtenverwaltung().getAnzahlUngelesenerNachrichten(account)+")");
					
					loginButton.setText("Logout");
					if(account.getTyp() == Account.KUNDE)
						eigeneButton.setText("Eigene Buchungen");
					else if(account.getTyp() == Account.ANBIETER)
					{
						eigeneButton.setText("Eigene Angebote");
						erstelleButton.setEnabled(true);
					}
					else
						eigeneButton.setText("Alle Accounds");
					
					this.repaint();
					logged = true;
				}
			}
			else
			{
				Portal.Accountverwaltung().logOut();
				JOptionPane.showMessageDialog(this, "Erfolgreich Abgemeldet"+"\n"+"Danke und auf Wiedersehen!");
				showTopAngebote();
				
				eigeneButton.setEnabled(false);
				eigeneButton.setText("Angebote/Buchungen");
				nachrichtButton.setEnabled(false);
				erstelleButton.setEnabled(false);
				loginButton.setText("Login");
				nachrichtButton.setText("Nachrichten");
				registerButton.setEnabled(true);
				
				this.repaint();
				logged = false;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.toString());
		}
	}

	private void showRegister()
	{
		try
		{
			JLabel label = new JLabel("Bitte geben Sie die Registrierinformationen an");
			JFormattedTextField tf = new JFormattedTextField(new DateFormatter(DateFormat.getDateInstance (DateFormat.SHORT, Locale.GERMAN)));
			JLabel nameLabel = new JLabel("Name");
			final JTextField nameField = new JTextField();
			JLabel emailLabel = new JLabel("E-Mail-Adresse");
			final JTextField emailField = new JTextField();
			JLabel passwordLabel = new JLabel("Password");
			final JPasswordField passwordField = new JPasswordField();
			
			JLabel choice = new JLabel("Wählen sie bitte Ihren Accounttypen");
			JComboBox drop = new JComboBox(new String[]{"Kunde","Anbieter"});
			
			if(JOptionPane.showConfirmDialog(this,new Object[]{label,nameLabel,nameField,emailLabel,emailField,passwordLabel,passwordField,choice,drop},"Registrierung",JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION)
			{
				if(drop.getSelectedIndex() == 0)
					Portal.Accountverwaltung().createKunde(emailField.getText(), nameField.getText(), new String(passwordField.getPassword()));
				else
				{
					final JDialog dialog = new JDialog(this,"AGB");
				    dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				    dialog.setSize(BUTTONWIDTH*2 + 24, (BUTTONHEIGHT*10)+8);
				    dialog.setLocation(dialog.getParent().WIDTH/2, dialog.getParent().HEIGHT/2);
				    
				    //
				    
				    JPanel main = new JPanel(null);
				    
				    final JTextArea agb = new JTextArea();
				    JScrollPane agbScroll = new JScrollPane(agb);
				    agbScroll.setBounds(8, 40, BUTTONWIDTH*2, (BUTTONHEIGHT*7));
				    
				    JLabel agbLabel = new JLabel("Bitte tragen Sie Ihre allgemeinen Geschäftsbedingungen ein!");
				    agbLabel.setBounds(4, 8, BUTTONWIDTH*2, BUTTONHEIGHT);
				    
					//
				    
				    JButton okButton = new JButton("OK");
				    okButton.setBounds(8, (BUTTONHEIGHT*8)+8, BUTTONWIDTH, BUTTONHEIGHT);
				    okButton.setText("OK");
				    okButton.addActionListener(new ActionListener() 
				    { 
				      public void actionPerformed(ActionEvent evt)
				      { 
				    	  try
				    	  {
				    		  Portal.Accountverwaltung().createAnbieter(emailField.getText(), nameField.getText(), new String(passwordField.getPassword()));
				    		  dialog.dispose();
				    	  }
				    	  catch(Exception e)
				    	  {
				    		  JOptionPane.showMessageDialog(dialog, e.toString());
				    	  }
				      }
				    });
				    JButton cancelButton = new JButton("Abbrechen");
				    cancelButton.setBounds(BUTTONWIDTH + 8, (BUTTONHEIGHT*8)+8, BUTTONWIDTH, BUTTONHEIGHT);
				    cancelButton.setText("Abbrechen");
				    cancelButton.addActionListener(new ActionListener()   {  public void actionPerformed(ActionEvent evt)   { dialog.dispose();  } });
				    
				    main.add(okButton);
				    main.add(cancelButton);
				    main.add(agbScroll);
				    main.add(agbLabel);
				    
				    dialog.add(main);
				    dialog.setResizable(false);
				    dialog.setVisible(true);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.toString());
		}
	}
	
	private void showEigene()
	{
		try
		{
			screen.removeAll();
			
			if(account.getTyp() == Account.KUNDE)
				list = new ListeScreen(this, Portal.Buchungsverwaltung().getBuchungen((Kunde)account));
			else if(account.getTyp() == Account.ANBIETER)
				list = new ListeScreen(this, Portal.Angebotsverwaltung().getAngebote((Anbieter)account));
			else
				list = new ListeScreen(this, Portal.Accountverwaltung().getAccounts());
			
			screen.add(list);
			scroll.setViewportView(screen);
			scroll.repaint();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.toString());
		}
	}
	
	private void showSuche()
	{
		try
		{
			screen.removeAll();
			screen.add(new Suchmaske());
			scroll.setViewportView(screen);
			scroll.repaint();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.toString());
		}
	}
	
	private void showNachrichten()
	{
		try
		{
			screen.removeAll();
//			screen.add(new Msg());
			scroll.setViewportView(screen);
			scroll.repaint();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.toString());			
		}
	}
	
	private void showTopAngebote()
	{
		try
		{
			screen.removeAll();
			ArrayList arr = Portal.Angebotsverarbeitung().getTopAngebote();
			list = new ListeScreen(this, Portal.Angebotsverarbeitung().getTopAngebote());
			screen.add(list);
			scroll.setViewportView(screen);
			scroll.repaint();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.toString());
		}
	}
	
	private void showNachricht()
	{
		try
		{
		}
		catch(Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.toString());
		}
	}
	
	private void showErstelle()
	{
		try
		{
			screen.removeAll();
			screen.add(new AngebotCreate((Anbieter)account));
			scroll.setViewportView(screen);
			scroll.repaint();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.toString());
		}
	}
	
	private void showAlleAngebote()
	{
		try
		{
			screen.removeAll();
			list = new ListeScreen(this, Portal.Angebotsverwaltung().getAllAngebote());
			screen.add(list);
			scroll.setViewportView(screen);
			scroll.repaint();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.toString());
		}
	}

	/////////////////////////
	
	public static void main(String[] args)
	{
		MainFrame f = new MainFrame();
	}
}