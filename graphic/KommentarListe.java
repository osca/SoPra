package graphic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import angebote.Kommentar;
import angebote.typen.Angebot;

public class KommentarListe	extends JPanel {
	
	
	
	public KommentarListe(Angebot a){
		setLayout(new BorderLayout());
		JPanel elementPanel = new JPanel(new GridLayout(0,1));

		ArrayList<Kommentar> kliste= a.getKommentare();
		
		for (int i=0;i<kliste.size();i++){
		//	onElement.setLayout(new GridLayout(1+i,0));
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			JPanel eventPanel = new JPanel(new GridLayout(2,1));
			JPanel komPanel = new JPanel(new GridLayout(1,2));
			JPanel fullp = new JPanel(new GridLayout(1,1));
			JLabel name = new JLabel(kliste.get(i).getIdentifier());
			komPanel.add(name);
			JLabel info = new JLabel(df.format(kliste.get(i).getZeitstempel())+" , Wertung: "+kliste.get(i).getBewertung());
			komPanel.add(info);
			eventPanel.add(komPanel);
			//JLabel full = new JLabel(kliste.get(i).getFullInfo());
			final JTextArea full = new JTextArea();
			full.setLineWrap(true);
			full.setWrapStyleWord(true);
			full.setEditable(false);
			full.setText(kliste.get(i).getFullInfo());
			fullp.add(full);
			eventPanel.add(fullp);
			eventPanel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.LIGHT_GRAY));
			elementPanel.add(eventPanel, BorderLayout.NORTH);
		}
		this.add(BorderLayout.NORTH,elementPanel);
		this.setBorder(BorderFactory.createMatteBorder(2,2,2,2, Color.LIGHT_GRAY));
		this.setBackground(Color.BLACK);
	}
}
