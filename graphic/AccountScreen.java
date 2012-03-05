package graphic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import main.Portal;

import accounts.Account;
import accounts.Anbieter;
import accounts.Kunde;

public class AccountScreen extends JPanel
{
	public AccountScreen(Account account)
	{
		this.setLayout(new BorderLayout());
		Border border = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.LIGHT_GRAY);
		this.setBorder(border);
		
		GridLayout grid = new GridLayout(0,1);
		grid.setHgap(4);
		
		////
		
		JPanel left = new JPanel(grid);
		left.setBorder(border);
		
		JLabel[] labels = new JLabel[10];
		labels[0] = new JLabel("Name:");
		labels[1] = new JLabel("E-Mail Adresse:");
		labels[2] = new JLabel("Password:");
		labels[3] = new JLabel("Status:");
		labels[4] = new JLabel("Anzahl Buchungen/Angebote");
		
		for(int i=0; i<labels.length-5; i++)
			left.add(labels[i]);
		
		/////
		
		JPanel right = new JPanel(grid);
		
		labels[5] = new JLabel(account.getName());
		labels[6] = new JLabel(account.getEmail());
		labels[7] = new JLabel(account.getPassword());
		labels[8] = new JLabel(account.getStatus());

		if(account.getTyp() == Account.KUNDE)
			labels[9] = new JLabel(""+Portal.Buchungsverwaltung().getBuchungen((Kunde)account).size());
		if(account.getTyp() == Account.ANBIETER)
			labels[9] = new JLabel(""+Portal.Angebotsverwaltung().getAngebote((Anbieter)account).size());
		
		for(int i=5; i<labels.length; i++)
			right.add(labels[i]);
		
		this.add(left, BorderLayout.WEST);
		this.add(right, BorderLayout.EAST);
	}
}
