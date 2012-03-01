package graphic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

import main.Datenhaltung;
import main.Portal;

import accounts.Account;
import accounts.Kunde;
import angebote.typen.Angebot;
import angebote.typen.Flug;
import buchungen.Buchung;

public class MainFrame extends JFrame
{
	private static final int BUTTONHEIGHT = 24;
	private static final int BUTTONWIDTH = 120;

	private Account account;
	private JPanel screen;
	private JScrollPane scroll;
	
	private ListeScreen list;

	public MainFrame()
	{
		this.setLayout(new BorderLayout());
		
		/////////
		
		
		
		//////////

		Border border = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.LIGHT_GRAY);
		GridLayout grid = new GridLayout(0,1);
		
		///////////

		JPanel northPanel = new JPanel();
		northPanel.setLayout(new BorderLayout());
		northPanel.setBorder(border);
		this.add(northPanel, BorderLayout.NORTH);
		
		JPanel westPanel = new JPanel();
		westPanel.setLayout(new FlowLayout());
		westPanel.setBorder(border);
		this.add(westPanel, BorderLayout.WEST);

		screen = new JPanel();
		screen.setBorder(border);
		screen.setLayout(grid);
		
		scroll = new JScrollPane(screen);
		//scroll.setViewportView(screen);
		this.add(scroll, BorderLayout.CENTER);

		JPanel homeButtonPanel = new JPanel();
		northPanel.add(homeButtonPanel, BorderLayout.WEST);

		// JPanel backButtonPanel = new JPanel();

		//

		// ////////

		JButton homeButton = new JButton(new ImageIcon("house_unpressed.png"));

		homeButton.setPreferredSize(new Dimension(BUTTONWIDTH, BUTTONHEIGHT * 2));
		homeButton.setPressedIcon(new ImageIcon("house_pressed.png"));
		homeButton.setOpaque(false);
		homeButton.setContentAreaFilled(false);
		homeButton.setBorderPainted(false);
		homeButtonPanel.add(homeButton);

		JButton loginButton = new JButton("Login");
		loginButton.setPreferredSize(new Dimension(BUTTONWIDTH, BUTTONHEIGHT));

		westPanel.add(loginButton);

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
		
		
		this.pack();
		this.setVisible(true);
	}

	public <T extends Listable> void showDetail(T obj) 
	{
		if(obj.getClass().equals(Angebot.class))
		{
			System.out.println(((Angebot)obj).getIdetifier());
			
			screen.removeAll();
			screen.add(new AngDetailScreen(account.getTyp(), (Angebot)obj));
			scroll.setViewportView(screen);
			scroll.repaint();
			this.repaint();
		}
		else
		{
			screen.removeAll();
			screen.add(new BuchDetailScreen((Buchung)obj));
			scroll.setViewportView(screen);
			scroll.repaint();
			this.repaint();
		}
	}

	public static void main(String[] args)
	{
		MainFrame f = new MainFrame();
	}

}