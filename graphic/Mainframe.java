package graphic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

import buchungen.Buchung;

import accounts.Account;
import accounts.Kunde;
import angebote.typen.Flug;


public class MainFrame extends JFrame
{
	private static final int BUTTONHEIGHT = 24;
	private static final int BUTTONWIDTH = 120;
	
	private Account acc;
	
	private JPanel screen;
	
	public MainFrame()
	{
		this.setLayout(new BorderLayout());
		
		//////////
		
		Border border = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.LIGHT_GRAY);
		
		//////////
		
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new BorderLayout());
		northPanel.setBorder(border);
		this.add(northPanel,BorderLayout.NORTH);
		
		JPanel westPanel = new JPanel();
		westPanel.setLayout(new FlowLayout());
		westPanel.setBorder(border);
		this.add(westPanel,BorderLayout.WEST);
		
		screen = new JPanel();
		screen.setBorder(border);
		this.add(screen,BorderLayout.CENTER);
		
		JPanel homeButtonPanel = new JPanel();
		northPanel.add(homeButtonPanel, BorderLayout.WEST);
		
//		JPanel backButtonPanel = new JPanel();
//		
		
		//////////
		
		JButton homeButton = new JButton(new ImageIcon("house_unpressed.png"));
		homeButton.setPreferredSize(new Dimension(BUTTONWIDTH,BUTTONHEIGHT*2));
		homeButton.setPressedIcon(new ImageIcon("house_pressed.png"));
		homeButton.setOpaque(false);
		homeButton.setContentAreaFilled(false);
		homeButton.setBorderPainted(false);
		homeButtonPanel.add(homeButton);
		
		JButton loginButton = new JButton("Login");
		loginButton.setPreferredSize(new Dimension(BUTTONWIDTH,BUTTONHEIGHT));
		westPanel.add(loginButton);
		
		///////////
		Flug f = new Flug("name", "beschreibung", 23, 23.5, new Date[]{new Date()}, "hier", "ziel", "7", "unbezahlbar");
		
		AngDetailScreen ang = new AngDetailScreen(1,f);
		ang.setPreferredSize(new Dimension(400,400));
		screen.add(ang);
		screen.add(new BuchDetailScreen(new Buchung(new Flug("name", "beschreibung", 23, 23.5, new Date[]{new Date()}, "hier", "ziel", "7", "unbezahlbar"), new Kunde("name","email","password"))));
		
		
		this.pack();
		this.setVisible(true);
	}
	
	
	
	public <T extends Listable> void showDetail(T obj){
		if(obj.getClass().equals(angebote.typen.Angebot.class)){
		
			
			/*ListeScreen.scroll.remove(ListeScreen.sPanel);
			ListeScreen.scroll.add();*/
			
		}
	/*	else if(obj.getClass().equals(Buchung.class)){
			
		}
		else if(obj.getClass().equals(Account.class)){
			
		}
		else if(obj.getClass().equals(Nachrichten.class)){
			
		}*/
	}
	
	
	
	
	public Account getUser(){
		return acc;
	}
	
	
	public static void main(String[] args)
	{
		MainFrame f = new MainFrame();
	}

}
