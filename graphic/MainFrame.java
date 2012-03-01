package graphic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ButtonModel;
import javax.swing.DefaultButtonModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.UIManager;
import javax.swing.border.Border;

import main.Datenhaltung;
import main.Portal;

import accounts.Account;
import accounts.Default;
import accounts.Kunde;
import angebote.typen.Angebot;
import angebote.typen.Flug;
import buchungen.Buchung;

public class MainFrame extends JFrame
{
	private static final int BUTTONHEIGHT = 38;
	private static final int BUTTONWIDTH = 180;

	private JButton loginButton;
	private JButton logoutButton;
	private JButton registerButton;
	private JButton nachrichtButton;
	private JButton buchenButton;
	private JButton angebotButton;
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
		logoutButton = new JButton("Logout");
		logoutButton.setPreferredSize(new Dimension(BUTTONWIDTH, BUTTONHEIGHT));
		registerButton = new JButton("Registrieren");
		registerButton.setPreferredSize(new Dimension(BUTTONWIDTH, BUTTONHEIGHT));
		buchenButton = new JButton("Eigene Buchungen");
		buchenButton.setPreferredSize(new Dimension(BUTTONWIDTH, BUTTONHEIGHT));
		sucheButton = new JButton("Suche Angebote");
		sucheButton.setPreferredSize(new Dimension(BUTTONWIDTH, BUTTONHEIGHT));
		topButton = new JButton("Top Angebote");
		topButton.setPreferredSize(new Dimension(BUTTONWIDTH, BUTTONHEIGHT));
		nachrichtButton = new JButton("Nachrichten");
		nachrichtButton.setPreferredSize(new Dimension(BUTTONWIDTH, BUTTONHEIGHT));
		angebotButton = new JButton("Eigene Angebote");
		angebotButton.setPreferredSize(new Dimension(BUTTONWIDTH, BUTTONHEIGHT));
		alleButton = new JButton("Alle Angebote");
		alleButton.setPreferredSize(new Dimension(BUTTONWIDTH, BUTTONHEIGHT));
		erstelleButton = new JButton("Angebot erstellen");
		erstelleButton.setPreferredSize(new Dimension(BUTTONWIDTH, BUTTONHEIGHT));
		
		buttonPanel.add(loginButton);
		buttonPanel.add(sucheButton);
		buttonPanel.add(topButton);
		registerPanel.add(registerButton);

		// /////////
//
//		Flug f = new Flug("name", "beschreibung", 23, 23.5, new Date[] { new Date() }, "hier", "ziel", "7", "unbezahlbar");
//
//		AngDetailScreen ang = new AngDetailScreen(0, f);
//		//ang.setPreferredSize(new Dimension(400, 400));
//		//ang.setBackground(Color.black);
//			
//		screen.add(ang);
//		screen.add(new BuchDetailScreen(new Buchung(f, new Kunde("name", "email","password"),new Date(), new Date())));
		
		
		ArrayList<Angebot> al = new ArrayList<Angebot>();
		for(int i=0;i<100;i++)
			al.add(new Flug(null,"name", "beschreibung", 23, 23.5, new Date[] { new Date() }, "hier", "ziel", "7", "unbezahlbar"));
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
				login();
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
		
		buchenButton.addActionListener(new ActionListener()
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
		
		////////////////
		
		this.pack();
		this.setVisible(true);
		
	}

	public <T extends Listable> void showDetail(T obj) 
	{
		if(obj.getListableTyp() == Angebot.ANGEBOT)
		{
			System.out.println(((Angebot)obj).getIdetifier());
			
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
	
	private void login() 
	{
		try
		{
			if(!logged)
			{
				JTextField nameField = new JTextField("Name");
				JTextField passwordField = new JTextField("Password");
				JLabel label = new JLabel();
						
				label.setText("Bitte geben Sie die Anmeldeinformationen an");
		
				if(JOptionPane.showConfirmDialog(this,new Object[]{label, nameField, passwordField},"Login",JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION)
				{
					
				
					//Portal.getSingletonObject().getAccountverwaltung().logIn(nameField.getText(), passwordField.getText());
					//if(Portal.getSingletonObject().getAccountverwaltung().getLoggedIn() != null)
					//{
						//account = Portal.getSingletonObject().getAccountverwaltung().getLoggedIn();
						loginButton.setText("Logout");
						this.repaint();
						logged = true;
					//}
				}
			}
			else
			{
				Portal.getSingletonObject().getAccountverwaltung().logOut();
				loginButton.setText("Login");
				this.repaint();
				logged = false;
			}
		}
		catch(Exception e)
		{
			
		}
	}
	
	private void logout()
	{
		try
		{
			if(logged)
			{
				
				
				JOptionPane.showMessageDialog(this, "Erfolgreich Abgemeldet"+"\n"+"Danke und auf Wiedersehen!");
				logged = false;
			}
		}
		catch(Exception e)
		{
			
		}
	}
	
	private void showRegister()
	{
		try
		{
			
		}
		catch(Exception e)
		{
			
		}
	}
	
	private void showEigene()
	{
		
	}
	
	private void showSuche()
	{}
	
	private void showTopAngebote()
	{
		try
		{
			screen.removeAll();
			list = new ListeScreen(this, Portal.getSingletonObject().getAngebotsverarbeitung().getTopAngebote());
			screen.add(list);
			scroll.setViewportView(screen);
			scroll.repaint();
		}
		catch(Exception e)
		{
			
		}
	}

	/////////////////////////
	
	public static void main(String[] args)
	{
		MainFrame f = new MainFrame();
	}

}