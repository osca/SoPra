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

	private Account account;
	private JPanel screen;
	private JScrollPane scroll;
	
	private ListeScreen list;

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

		JButton loginButton = new JButton("Login");
		loginButton.setPreferredSize(new Dimension(BUTTONWIDTH, BUTTONHEIGHT));
		JButton registerButton = new JButton("Registrieren");
		registerButton.setPreferredSize(new Dimension(BUTTONWIDTH, BUTTONHEIGHT));
		JButton eigeneButton = new JButton("Eigene Angebote");
		eigeneButton.setPreferredSize(new Dimension(BUTTONWIDTH, BUTTONHEIGHT));
		JButton sucheButton = new JButton("Suche Angebote");
		sucheButton.setPreferredSize(new Dimension(BUTTONWIDTH, BUTTONHEIGHT));
		
		buttonPanel.add(loginButton);
		buttonPanel.add(eigeneButton);
		buttonPanel.add(sucheButton);
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
				showHome();
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
			JTextField nameField = new JTextField("Name");
			JTextField passwordField = new JTextField("Password");
			JLabel label = new JLabel();
					
			label.setText("Bitte geben Sie die Anmeldeinformationen an");
	
			JOptionPane.showConfirmDialog(this,new Object[]{label, nameField, passwordField},"Login",JOptionPane.OK_CANCEL_OPTION);
			
			//Portal.getSingletonObject().getAccountverwaltung().logIn(nameField.getText(), passwordField.getText());
			if(Portal.getSingletonObject().getAccountverwaltung().getLoggedIn() != null)
			{
				//account = Portal.getSingletonObject().getAccountverwaltung().getLoggedIn();
				
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
	
	private void showHome()
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
	
	private void showEigene()
	{
		
	}
	
	private void showSuche()
	{}

	/////////////////////////
	
	public static void main(String[] args)
	{
		MainFrame f = new MainFrame();
	}

}