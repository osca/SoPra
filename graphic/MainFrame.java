package graphic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.DateFormatter;

import main.Datenhaltung;
import main.Portal;
import accounts.Account;
import accounts.AlreadyInUseException;
import accounts.Anbieter;
import accounts.Betreiber;
import accounts.Default;
import accounts.Gesperrt;
import accounts.Kunde;
import accounts.LoeschenNichtMoeglichException;
import accounts.LoginException;
import accounts.Nachricht;
import angebote.typen.Angebot;
import angebote.typen.Autovermietung;
import buchungen.Buchung;

public class MainFrame extends JFrame
{
	public static final int BUTTONWIDTH = 180;
	public static final int BUTTONHEIGHT = 38;

	protected static final String MSG_LOGIN_FEHLT = "Sie muessen sich einlogen um weitere Aktionen durchfuehren zu koennen";
	protected static final String MSG_LOGIN_FEHLERHALFT = "Der Account wurde nicht gefunden oder das Passwort ist falsch";		// 283
	protected static final String MSG_REG_EXISTIERT = "Die Benutzerinformationen sind bereits vergeben.";						// 392
	protected static final String MSG_BESCHWERDE = "Beschwerde";
	protected static final String MSG_ANGEBOT_GEMELDET = "Ein Kunde hat ein Angebot gemeldet!";
	protected static final String MSG_AGB_ERKLAERUNG = "Erklaerung der AGB des Anbieters: ";
	protected static final String MSG_GESAMMT_BEWERUNG = "Gesammtberwertung dieses Anbieters: ";
	protected static final String MSG_SAVE_ERROR = "Fehler beim speichern der Dateien";											// 351
	protected static final String MSG_SEARCH_KEINE = "Es wurden keine Ergebnisse gefunden";
	
	protected static final String QSN_ANGEBOT_MELDEN	= "Sind Sie sicher, dass sie dieses Angebot Melden moechten?";
	protected static final String QSN_BUCHEN = "Moechten Sie das Angebot wirklich Buchen?";
	
	private JButton loginButton;
	private JButton registerButton;
	private JButton nachrichtButton;
	private JButton eigeneButton;
	private JButton sucheButton;
	private JButton topButton;
	private JButton alleButton;
	private JButton erstelleButton;
	private JButton betreiberButton;
	private JButton offeneButton;
	private JButton loeschenButton;
	
	private Account account;
	private JPanel screen;
	private JScrollPane scroll;
	
	private ListeScreen list;
	
	private boolean logged = false;
	
	private MainFrame frame = this; //quick'n'dirty  nur voruebergehend
	private String agbFromFile;

	public MainFrame()
	{
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (d.width - getSize().width/2);
	    int y = (d.height - getSize().height/2);
	    this.setLocation(x/8, y/8);
		this.setPreferredSize(new Dimension(x*2/3, y*2/3));
		
		try
		{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		/////////
		
		account = new Default();
		
		//////////

		Border border = BorderFactory.createMatteBorder(0, 2, 0, 2, Color.LIGHT_GRAY);
		
		///////////
		setName("blaFrame");//TODO
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
		
		screen = new JPanel(new GridLayout(1,0));
		screen.setBorder(border);
		
		scroll = new JScrollPane(screen);
		scroll.getVerticalScrollBar().setUnitIncrement(20);
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
		betreiberButton = new JButton("Betreiber hinzufuegen");
		betreiberButton.setPreferredSize(new Dimension(BUTTONWIDTH, BUTTONHEIGHT));
		betreiberButton.setVisible(false);
		offeneButton = new JButton("Kundenbuchungen");
		offeneButton.setPreferredSize(new Dimension(BUTTONWIDTH, BUTTONHEIGHT));
		offeneButton.setVisible(false);
		loeschenButton = new JButton("Account Loeschen");
		loeschenButton.setEnabled(false);
		loeschenButton.setPreferredSize(new Dimension(BUTTONWIDTH, BUTTONHEIGHT));
		
		buttonPanel.add(loginButton);
		buttonPanel.add(eigeneButton);
		buttonPanel.add(sucheButton);
		buttonPanel.add(nachrichtButton);
		buttonPanel.add(erstelleButton);
		buttonPanel.add(topButton);
		buttonPanel.add(alleButton);
		buttonPanel.add(offeneButton);
		buttonPanel.add(betreiberButton);
		buttonPanel.add(loeschenButton);
		registerPanel.add(registerButton);

		// /////////	
		
		homeButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				showTopAngebote();
			}
		});
		topButton.addActionListener(new ActionListener()
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
				showNachrichten();
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
		betreiberButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				addBetreiber();
			}
		});
		offeneButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				showOffeneBuchungen();
			}
		});
		loeschenButton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				showLoeschen();
			}
		});
		
		////////////////
		
		showTopAngebote();
		
		this.pack();
		this.setVisible(true);
	}

	public <T extends Listable> void showDetail(T obj) 
	{
		try
		{
			if(obj.getListableTyp() == Listable.ANGEBOT)
			{
				screen.removeAll();
				screen.add(new AngDetailScreen(this, (Angebot)obj));
				scroll.setViewportView(screen);
				scroll.repaint();
			}
			else if(obj.getListableTyp() == Buchung.BUCHUNG)
			{
				screen.removeAll();
				screen.add(new BuchDetailScreen(this,offeneButton,(Buchung)obj));
				scroll.setViewportView(screen);
				scroll.repaint();
			}
			else if(obj.getListableTyp() == Account.ACCOUNT)
			{
				screen.removeAll();
				screen.add(new AccountScreen((Account)obj, eigeneButton));
				scroll.setViewportView(screen);
				scroll.repaint();
			}
			else
			{
				final Nachricht nachricht = (Nachricht)obj;
				final Account absender = Portal.Accountverwaltung().getAccountByName(nachricht.getAbsender());

				DialogScreen dialog = new DialogScreen(this, nachricht.getBetreff(),DialogScreen.OK_OFFER_ANSWER_OPTION)
				{
					@Override
					public void onAnswer()
					{
						DialogScreen dialog = new DialogScreen(frame, "Kontaktieren", DialogScreen.OK_CANCEL_OPTION)
						{
							@Override
							public void onOK()
							{
								Portal.Nachrichtenverwaltung().sendeNachricht(account, absender, "RE: "+nachricht.getBetreff(),getContent(), Portal.Angebotsverwaltung().getAngebotByNummer(nachricht.getAngebotsNummer()));
							}
						};
						dialog.addOnPanel(new JLabel(account.getName()), DialogScreen.LABEL_LEFT);
					}
					
					public void onOffer()
					{
						showDetail(Portal.Angebotsverwaltung().getAngebotByNummer(nachricht.getAngebotsNummer()));
					}
				};
				dialog.setEditable(false);
				dialog.addOnPanel(new JLabel("Absender: "+nachricht.getAbsender()), DialogScreen.LABEL_LEFT);
				dialog.setContent(nachricht.getText());
				nachricht.setGelesen(true);
				nachrichtButton.setText("Nachricht"+" ("+Portal.Nachrichtenverwaltung().getAnzahlUngelesenerNachrichten(account)+")");
				
				this.repaint();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage());
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
				JLabel passwordLabel = new JLabel("Passwort");
				JPasswordField passwordField = new JPasswordField();
				JLabel label = new JLabel("Bitte geben Sie die Anmeldeinformationen an");
		
				if(JOptionPane.showConfirmDialog(this,new Object[]{label, nameLabel, nameField, passwordLabel, passwordField},"Login",JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION)
				{
					account = Portal.Accountverwaltung().logIn(nameField.getText(), new String(passwordField.getPassword()));
					
					eigeneButton.setEnabled(true);
					nachrichtButton.setEnabled(true);
					registerButton.setEnabled(false);
					
					nachrichtButton.setText("Nachricht"+" ("+Portal.Nachrichtenverwaltung().getAnzahlUngelesenerNachrichten(account)+")");
					
					loginButton.setText("Logout");
					loeschenButton.setEnabled(true);
					
					if(account.getTyp() == Account.KUNDE)
					{
						if(((Kunde)account).getFirstLogin()){
							JOptionPane.showMessageDialog(this, "Willkommen");
							((Kunde)account).setFirstLogin();
						}
						eigeneButton.setText("Eigene Buchungen");
					}
					else if(account.getTyp() == Account.ANBIETER)
					{	
						if(((Anbieter)account).getFirstLogin()){
							JOptionPane.showMessageDialog(this, "Willkommen");
							((Anbieter)account).setFirstLogin();
						}
						eigeneButton.setText("Eigene Angebote");
						erstelleButton.setEnabled(true);
						offeneButton.setText("Kundenbuchungen "+"("+Portal.Buchungsverwaltung().getAnzahlUnbearbeiteterBuchungen((Anbieter)account)+")");
						offeneButton.setVisible(true);
					}
					else if(account.getTyp() == Account.BETREIBER)
					{
					//	if(Betreiber.getAnbieterReg()){
					//		JOptionPane.showMessageDialog(this, "Bitte schalten Sie die neuen Anbieter frei");
					//	}
						eigeneButton.setText("Alle Accounts ("+Portal.Accountverwaltung().getUnbearbeiteteAnbieter().size()+")");
						offeneButton.setVisible(true);
						offeneButton.setText("Alle Buchungen");
						betreiberButton.setVisible(true);
					}
					
					showTopAngebote();
					JOptionPane.showMessageDialog(this, "Erfolgreich angemeldet");
					this.setTitle("Eingeloggt als: "+account.getName());
					this.repaint();
					logged = true;
				}
			}
			else
			{
				logOut();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}

	private void showRegister()
	{
		try
		{
			JLabel label = new JLabel("Bitte geben Sie die Registrierinformationen an");
			JLabel nameLabel = new JLabel("Name");
			final JTextField nameField = new JTextField();
			JLabel emailLabel = new JLabel("E-Mail-Adresse");
			final JTextField emailField = new JTextField();
			JLabel passwordLabel = new JLabel("Passwort");
			final JPasswordField passwordField = new JPasswordField();
			
			JLabel choice = new JLabel("Waehlen sie bitte Ihren Accounttypen");
			JComboBox drop = new JComboBox(new String[]{"Kunde","Anbieter"});
			
			if(JOptionPane.showConfirmDialog(this,new Object[]{label,nameLabel,nameField,emailLabel,emailField,passwordLabel,passwordField,choice,drop},"Registrierung",JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION)
			{
				if(drop.getSelectedIndex() == 0)
				{
					Portal.Accountverwaltung().createKunde(emailField.getText(), nameField.getText(), new String(passwordField.getPassword()));
					JOptionPane.showMessageDialog(this, "Registrierung war Erfolgreich");
				}
				else
				{
					if(!Portal.Accountverwaltung().isFreeEmail(emailField.getText()))
						throw new AlreadyInUseException("Die E-Mail-Adresse wird bereits verwendet!");
					
					
					JButton fcb = new JButton("AGB laden");
					fcb.setPreferredSize(new Dimension(new Dimension(DialogScreen.BUTTONWIDTH, DialogScreen.BUTTONHEIGHT)));
					final JFileChooser fc = new JFileChooser();
					fc.setFileFilter(new FileFilter() {
						    public boolean accept(File f) {	
						    	return (f.isDirectory() || f.getName().toLowerCase().endsWith(".txt"));
						    }
						    public String getDescription () { 
						    	return "'.txt'"; 
						    }  
					});
					fc.setAcceptAllFileFilterUsed(false);
					fc.setMultiSelectionEnabled(false);
					JButton[] button_array = new JButton[1];
					button_array[0]=fcb;	
					final DialogScreen dialog = new DialogScreen(this, "Allgemeine Geschaeftsbedingungen",button_array, DialogScreen.OK_CANCEL_OPTION)
					{
						@Override
						public void onOK()
						{
							try 
							{
								Anbieter an = Portal.Accountverwaltung().createAnbieter(emailField.getText(), nameField.getText(), new String(passwordField.getPassword()));
								an.setAgb(this.getContent());
								JOptionPane.showMessageDialog(this, "Registrierung war Erfolgreich");
							} 
							catch (Exception e) 
							{
								e.printStackTrace();
								JOptionPane.showMessageDialog(this, e.getMessage());
							}
						}
						
						@Override
						public void onCancel()
						{
							JOptionPane.showMessageDialog(this, "Registrierung abgebrochen!");
						}
					};
					dialog.addOnPanel(new JLabel("Bitte geben Sie Ihre allgemeinen Geschaeftsbedingungen an!"), DialogScreen.LABEL_LEFT);
					button_array[0].addActionListener(new ActionListener()
					{
						@Override
						public void actionPerformed(ActionEvent e) 
						{
							try
							{
								int x =fc.showOpenDialog(null);
								if(x==JFileChooser.APPROVE_OPTION)
								{
									agbFromFile = Datenhaltung.getStringFromFile(fc.getSelectedFile());
									dialog.setContent(agbFromFile);
								}
							}
							catch(Exception ex)
							{
								JOptionPane.showMessageDialog(null, ex.getMessage());
							}
						}
					});
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage());
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
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}
	
	private void showSuche()
	{
		try
		{
			screen.removeAll();
			screen.add(new SuchScreen()
			{
				@Override
				public void onSearch()
				{
					if(this.getList() != null)
					{
						screen.removeAll();
						list = new ListeScreen(frame, this.getList());
						screen.add(list);
						scroll.setViewportView(screen);
						scroll.repaint();
					}
					else
						JOptionPane.showMessageDialog(this, MSG_SEARCH_KEINE);
				}
			});
			scroll.setViewportView(screen);
			scroll.repaint();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}
	
	public void showNachrichten()
	{
		try
		{
			screen.removeAll();
			list = new ListeScreen<Nachricht>(this, Portal.Nachrichtenverwaltung().getErhalteneNachrichten(account));
			screen.add(list);
			scroll.setViewportView(screen);
			scroll.repaint();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage());			
		}
	}
	
	private void showTopAngebote()
	{
		try
		{
			screen.removeAll();
			list = new ListeScreen<Angebot>(this, Portal.Angebotsverarbeitung().getTopAngebote());
			screen.add(list);
			scroll.setViewportView(screen);
			scroll.repaint();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage());
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
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}
	
	private void showAlleAngebote()
	{
		try
		{
			screen.removeAll();
			list = new ListeScreen(this, Portal.Angebotsverarbeitung().getAktuelleAngebote());
			screen.add(list);
			scroll.setViewportView(screen);
			scroll.repaint();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}
	
	private void showOffeneBuchungen()
	{
		try
		{

			screen.removeAll();
			if(account.getTyp() == Account.BETREIBER)
				list = new ListeScreen(this, Portal.Buchungsverwaltung().getAllBuchungen());
			else
				list = new ListeScreen(this, Portal.Buchungsverwaltung().getBuchungen((Anbieter)account));
			screen.add(list);
			scroll.setViewportView(screen);
			scroll.repaint();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}
	
	private void showLoeschen()
	{
		try
		{
			switch(account.getTyp())
			{
				case Account.KUNDE:
					if(JOptionPane.showConfirmDialog(this, "Moechten Sie den Account wirklich loeschen?", "Loeschen?", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) 
					{
						Portal.Accountverwaltung().delAccount(account);
						logOut();
					}
				break;
				
				case Account.ANBIETER:
					if(JOptionPane.showConfirmDialog(this, "Moechten Sie den Account wirklich loeschen?", "Loeschen?", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION)
					{
						Portal.Accountverwaltung().delAccount(account);
						logOut();
					}
				break;
					
				case Account.BETREIBER:	
					if (JOptionPane.showConfirmDialog(this, "Moechten Sie den Account wirklich loeschen?", "Loeschen?", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) 
					{
						Portal.Accountverwaltung().delAccount(account);
						logOut();
					}
				break;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}
	
	private void logOut() 
	{
		try
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
			betreiberButton.setVisible(false);
			offeneButton.setVisible(false);
			loeschenButton.setEnabled(false);
	
			this.setTitle("");
			this.repaint();
			logged = false;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}
	
	private void addBetreiber()
	{
		try
		{
			JLabel label = new JLabel("Bitte geben Sie die Registrierinformationen an");
			JLabel nameLabel = new JLabel("Name");
			final JTextField nameField = new JTextField();
			JLabel emailLabel = new JLabel("E-Mail-Adresse");
			final JTextField emailField = new JTextField();
			JLabel passwordLabel = new JLabel("Passwort");
			final JPasswordField passwordField = new JPasswordField();
			
			if(JOptionPane.showConfirmDialog(this,new Object[]{label,nameLabel,nameField,emailLabel,emailField,passwordLabel,passwordField},"Betreiber hinzufuegen",JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION)
			{
				Portal.Accountverwaltung().createBetreiber(emailField.getText(), nameField.getText(), new String(passwordField.getPassword()));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}

	/////////////////////////
	
	public void dispose(){
		try{
			Datenhaltung.saveAllData();
			System.exit(0);
		}catch(IOException ioe){
			//TODO maybe something?
		}
	}
}