package graphic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import angebote.Kommentar;
import angebote.typen.Angebot;

public class KommentarListe	extends JPanel {
	
	
	
	public KommentarListe(Angebot a){
		JPanel elementPanel = new JPanel();
		ArrayList<Kommentar> kliste= a.getKommentare();
		
		for (int i=0;i<kliste.size();i++){
			final int j = i;
			JPanel eventPanel = new JPanel(new BorderLayout(5,5));
			JPanel komPanel = new JPanel(new GridLayout(1,2));
			JLabel name = new JLabel(kliste.get(i).getIdentifier());
			komPanel.add(name);
			JLabel info = new JLabel(kliste.get(i).getAdditionalInfo());
			komPanel.add(info);
			eventPanel.add(BorderLayout.NORTH,komPanel);
			JLabel full = new JLabel(kliste.get(i).getFullInfo());
			eventPanel.add(BorderLayout.SOUTH,full);
			eventPanel.setPreferredSize(new Dimension(MainFrame.BUTTONWIDTH, MainFrame.BUTTONHEIGHT));
			eventPanel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.LIGHT_GRAY));
			elementPanel.add(eventPanel);
		}
		JScrollPane scroll = new JScrollPane();
		scroll.add(elementPanel);
		add(scroll);
	}
}
