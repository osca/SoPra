package graphic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

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
	public static final int BUTTONHEIGHT = 38;
	public static final int BUTTONWIDTH = 180;

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
		this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		
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
		
		ArrayList<Angebot> al = new ArrayList<Angebot>();
		for(int i=0;i<100;i++)
			al.add(new Flug(new Anbieter("horst","@","fu.fu"),"name", "asdfkjalösdfmnklamsdlfkmalsdmflamnsdlfmnaklsmdfklmaklsdmflkamsdlfkmasdfasdf", 23, 23.5, new Date[] { new Date() }, "hier", "ziel", "7", "unbezahlbar"));
		list = new ListeScreen(this, al);
		
		screen.add(list);
		
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
				JTextField nameField = new JTextField("Name");
				JTextField passwordField = new JTextField("Password");
				JLabel label = new JLabel("Bitte geben Sie die Anmeldeinformationen an");
		
				if(JOptionPane.showConfirmDialog(this,new Object[]{label, nameField, passwordField},"Login",JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION)
				{
				
					Portal.Accountverwaltung().logIn(nameField.getText(), passwordField.getText());
					account = Portal.Accountverwaltung().getLoggedIn();
					
					eigeneButton.setEnabled(true);
					nachrichtButton.setEnabled(true);
					
					nachrichtButton.setText(nachrichtButton.getText()+" ("+Portal.Nachrichtenverwaltung().getAnzahlUngelesenerNachrichten()+")");
					
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
				
				eigeneButton.setEnabled(false);
				eigeneButton.setText("Angebote/Buchungen");
				nachrichtButton.setEnabled(false);
				erstelleButton.setEnabled(false);
				loginButton.setText("Login");
				nachrichtButton.setText("Nachrichten");
				
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
			JTextField nameField = new JTextField("Name");
			JTextField emailField = new JTextField("E-Mail-Adresse");
			JTextField passwordField = new JTextField("Password");
			
			JLabel choice = new JLabel("Wählen sie bitte Ihren Accounttypen");
			final JComboBox drop = new JComboBox(new String[]{"Kunde","Anbieter"});
			final JTextArea agb = new JTextArea("Bitte füllen Sie Ihre Allgemeinen Geschäftsbedingungen aus!");
			
			if(JOptionPane.showConfirmDialog(this,new Object[]{label,nameField,emailField,passwordField,choice,drop},"Registrierung",JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION)
			{
				if(drop.getSelectedIndex() == 0)
					Portal.Accountverwaltung().createKunde(emailField.getText(), nameField.getText(), passwordField.getText());
				else
					Portal.Accountverwaltung().createAnbieter(emailField.getText(), nameField.getText(), passwordField.getText());
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