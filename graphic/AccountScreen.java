package graphic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

import buchungen.Bestaetigung;

import main.Portal;

import accounts.Account;
import accounts.Anbieter;
import accounts.Gesperrt;
import accounts.Kunde;

public class AccountScreen extends JPanel
{
	private JLabel[] labels;
	
	public AccountScreen(final Account account)
	{
		this.setLayout(new BorderLayout());
		Border border = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.LIGHT_GRAY);
		this.setBorder(border);
		
		GridLayout grid = new GridLayout(0,1);
		grid.setHgap(4);
		
		////
		
		final JPanel main = new JPanel(new GridLayout(1,0));
		main.setBorder(border);
		
		JPanel left = new JPanel(grid);
		
		int linkeSeite = 4;
		labels = new JLabel[8];
		labels[0] = new JLabel("Name:");
		labels[1] = new JLabel("E-Mail Adresse:");
		labels[2] = new JLabel("Status:");
		labels[3] = new JLabel("Anzahl Buchungen/Angebote");
		
		for(int i=0; i<labels.length-linkeSeite; i++)
			left.add(labels[i]);
		
		/////
		
		JPanel right = new JPanel(grid);
		
		labels[4] = new JLabel(account.getName());
		labels[5] = new JLabel(account.getEmail());
		labels[6] = new JLabel(account.getStatus());
		
		
		if(account.getTyp() == Account.KUNDE)
			labels[7] = new JLabel(""+Portal.Buchungsverwaltung().getBuchungen((Kunde)account).size());
		if(account.getTyp() == Account.ANBIETER)
			labels[7] = new JLabel(""+Portal.Angebotsverwaltung().getAngebote((Anbieter)account).size());
		else
			labels[7] = new JLabel();
		
		for(int i=linkeSeite; i<labels.length; i++)
			right.add(labels[i]);
		
		//////
		
		JPanel buttonPanel = new JPanel(new GridLayout(1,0));
		final JButton status = new JButton();
		status.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				try
				{
					if(JOptionPane.showConfirmDialog(main, "Status aendern?", "Status", JOptionPane.OK_OPTION) == JOptionPane.OK_OPTION)
					{
						if(account.isGesperrt())
						{
							Portal.Accountverwaltung().setAccountGesperrt(account, Gesperrt.NEIN);
							status.setText("Sperren");
							labels[6].setText(account.getStatus());
						}
						else
						{
							Portal.Accountverwaltung().setAccountGesperrt(account, Gesperrt.JA);
							status.setText("Entsperren");
							labels[6].setText(account.getStatus());
						}
						status.repaint();
					}
				}
				catch(Exception e)
				{//TODO exceptionhandling
					e.printStackTrace();
					JOptionPane.showMessageDialog(main, e.getMessage());
				}
			}
			
		});
		status.setPreferredSize(new Dimension(MainFrame.BUTTONWIDTH, MainFrame.BUTTONHEIGHT));
		if(account.isGesperrt())
			status.setText("Entsperren");
		else
			status.setText("Sperren");
		buttonPanel.add(status);
		
		////
		
		if(account.getTyp() == Account.ANBIETER)
		{
			JPanel agbPanel = new JPanel();
			JLabel agbLabel = new JLabel(((Anbieter)account).getAgb());
			agbPanel.add(agbLabel);
			this.add(agbPanel, BorderLayout.CENTER);
		}
		
		////
		
		
		main.add(left);
		main.add(right);
		
		this.add(main, BorderLayout.NORTH);
		this.add(buttonPanel, BorderLayout.SOUTH);
	}
	
	
}
