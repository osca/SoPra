package graphic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.text.DateFormatter;

import main.Datenhaltung;
import main.Portal;
import accounts.Account;
import accounts.AlreadyInUseException;
import accounts.Anbieter;
import accounts.Betreiber;
import accounts.Default;
import accounts.Kunde;
import accounts.LoginException;
import accounts.Nachricht;
import angebote.typen.Angebot;
import angebote.typen.Autovermietung;
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
	
	private MainFrame frame = this; //quick'n'dirty  nur vor�bergehend
	private String agbFromFile;

	public MainFrame()
	{
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (d.width - getSize().width);
	    int y = (d.height - getSize().height);
	    setLocation(x/4, y/4);
		this.setPreferredSize(new Dimension(x/2,y/2));
		
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
		
		screen = new JPanel(new GridLayout(1,0));
		screen.setBorder(border);
		
		scroll = new JScrollPane(screen);
		scroll.setMaximumSize(new Dimension(BUTTONWIDTH * 2,BUTTONWIDTH * 50));
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
		
		
		////////////////
		
		this.pack();
		this.setVisible(true);
		
		//////////////

		showTopAngebote();
		
		try
		{
			Betreiber bet = Portal.Accountverwaltung().createBetreiber("Bet@Reiber.de", "admin", "boss");
			Anbieter an = Portal.Accountverwaltung().createAnbieter("a@hit.er", "dolf", "1", "Ihre Seele gehoert mir!");
			Kunde kuh = Portal.Accountverwaltung().createKunde("med@wurst.de", "dr", "1");
			Autovermietung auto = Portal.Angebotsverwaltung().createAutovermietung(an, "automiethaus", "wir habens", 4, 532, new Date(1), new Date(151465143512312L), "hell");
			Portal.Buchungsverwaltung().createBuchung(kuh, auto, new Date(151465143012312L), new Date(151465143512312L));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public <T extends Listable> void showDetail(T obj) 
	{
		if(obj.getListableTyp() == Listable.ANGEBOT)
		{
			screen.removeAll();
			screen.add(new AngDetailScreen((Angebot)obj));
			scroll.setViewportView(screen);
			scroll.repaint();
		}
		else if(obj.getListableTyp() == Buchung.BUCHUNG)
		{
			screen.removeAll();
			screen.add(new BuchDetailScreen((Buchung)obj));
			scroll.setViewportView(screen);
			scroll.repaint();
		}
		else if(obj.getListableTyp() == Account.ACCOUNT)
		{
			screen.removeAll();
			screen.add(new AccountScreen((Account)obj));
			scroll.setViewportView(screen);
			scroll.repaint();
		}
		else
		{
			try
			{
				final Nachricht nachricht = (Nachricht)obj;
				if(Portal.Accountverwaltung().getLoggedIn().getTyp() != Account.BETREIBER)
				{
					DialogScreen dialog = new DialogScreen(nachricht.getBetreff(),DialogScreen.OK_OPTION);
					dialog.setEditable(false);
					dialog.addOnPanel(new JLabel("Absender: "+nachricht.getAbsender()), DialogScreen.LABEL_LEFT);
					dialog.setContent(nachricht.getText());
					nachricht.setGelesen(true);
					nachrichtButton.setText("Nachricht"+" ("+Portal.Nachrichtenverwaltung().getAnzahlUngelesenerNachrichten(account)+")");
				}
				else
				{
					JButton[] button = new JButton[1];
					button[0] = new JButton("Zum Angebot");
					button[0].setPreferredSize(new Dimension(DialogScreen.BUTTONHEIGHT, DialogScreen.BUTTONWIDTH));
					button[0].addActionListener(new ActionListener()
					{
						@Override
						public void actionPerformed(ActionEvent arg0)
						{
							showDetail(Portal.Angebotsverwaltung().getAngebotByNummer(nachricht.getAngebotsNummer()));
						}
					});
					DialogScreen dialog = new DialogScreen(nachricht.getBetreff(),DialogScreen.OK_OPTION);
					dialog.setEditable(false);
					dialog.addOnPanel(new JLabel("Absender: "+nachricht.getAbsender()), DialogScreen.LABEL_LEFT);
					dialog.setContent(nachricht.getText());
					nachricht.setGelesen(true);
					nachrichtButton.setText("Nachricht"+" ("+Portal.Nachrichtenverwaltung().getAnzahlUngelesenerNachrichten(account)+")");
				}
				this.repaint();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, e.getMessage());
			}
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
					
					nachrichtButton.setText("Nachricht"+" ("+Portal.Nachrichtenverwaltung().getAnzahlUngelesenerNachrichten(account)+")");
					
					loginButton.setText("Logout");
					if(account.getTyp() == Account.KUNDE)
						eigeneButton.setText("Eigene Buchungen");
					else if(account.getTyp() == Account.ANBIETER)
					{
						eigeneButton.setText("Eigene Angebote");
						erstelleButton.setEnabled(true);
					}
					else
						eigeneButton.setText("Alle Accounts");
					
					showTopAngebote();
					this.repaint();
					JOptionPane.showMessageDialog(this, "Erfolgreich angemeldet");
					logged = true;
					setTitle("Eingeloggt als: "+account.getName());
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
		catch(LoginException e)
		{
			//e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
		catch (IOException e)
		{
			//e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage());
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
						throw new AlreadyInUseException();
					
					
					JButton fcb = new JButton("AGB laden");
					fcb.setPreferredSize(new Dimension(new Dimension(BUTTONWIDTH, BUTTONHEIGHT)));
					final JFileChooser fc = new JFileChooser();
					JButton[] button_array = new JButton[1];
					button_array[0]=fcb;	
					button_array[0].addActionListener(new ActionListener()
					{
						@Override
						public void actionPerformed(ActionEvent e) 
						{
							int x =fc.showOpenDialog(null);
							if(x==JFileChooser.APPROVE_OPTION)
							{
								agbFromFile = Datenhaltung.getStringFromFile(fc.getSelectedFile());
							}
						}
					});
					DialogScreen dialog = new DialogScreen("Allgemeine Geschaeftsbedingungen",button_array, DialogScreen.OK_CANCEL_OPTION)
					{
						@Override
						public void onOK()
						{
							try 
							{
								Portal.Accountverwaltung().createAnbieter(emailField.getText(), nameField.getText(), new String(passwordField.getPassword()),this.getContent());
								JOptionPane.showMessageDialog(this, "Registrierung war Erfolgreich");
							} 
							catch (AlreadyInUseException e) 
							{
								e.printStackTrace();
								JOptionPane.showMessageDialog(this, MeldeDienst.MSG_REG_EXISTIERT);
							}
						}
						
						@Override
						public void onCancel()
						{
							JOptionPane.showMessageDialog(this, "Registrierung abgebrochen!");
						}
					};
					
					
					
					dialog.addOnPanel(new JLabel("Bitte geben Sie Ihre allgemeinen Geschaeftsbedingungen an!"), DialogScreen.LABEL_LEFT);
				}
			}
		}
		catch(AlreadyInUseException e)
		{
			//e.printStackTrace();
			JOptionPane.showMessageDialog(this, MeldeDienst.MSG_REG_EXISTIERT);
		}
	}
	
	private void showEigene()
	{
//		try
//		{
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
//		}
//		catch(Exception e)
//		{//TODO exceptionhandling
//			e.printStackTrace();
//			JOptionPane.showMessageDialog(this, e.toString());
//		}
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
						JOptionPane.showMessageDialog(this, MeldeDienst.MSG_SEARCH_KEINE);
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
	
	private void showNachrichten()
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
			list = new ListeScreen(this, Portal.Angebotsverwaltung().getAllAngebote());
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

	/////////////////////////
	
	public void dispose(){
		try{
			Datenhaltung.saveAllData();
			System.exit(0);
		}catch(IOException ioe){
			//TODO maybe something?
		}
	}
	
	public static void main(String[] args)
	{
		MainFrame f = new MainFrame();
	}
}