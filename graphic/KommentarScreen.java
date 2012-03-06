package graphic;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import angebote.Kommentar;

/**
 * Dient der Darstellung von Kommentaren in AngebotDetailScreen
 * 
 * @author Benjamin
 */
public class KommentarScreen extends JPanel {
	private JPanel topLabelPanel;
	private JLabel bewertung;
	private JLabel absender;
	private JTextArea text;
	
	public KommentarScreen(Kommentar k) {
		setLayout(new BorderLayout());
		
		bewertung.setText(k.getAdditionalInfo());
		absender.setText(k.getAbsender());
		
		topLabelPanel = new JPanel(new BorderLayout());
		
		topLabelPanel.add(absender, BorderLayout.WEST);
		topLabelPanel.add(bewertung, BorderLayout.EAST);
		
		add(topLabelPanel, BorderLayout.NORTH);
		
		text = new JTextArea();
		text.setEditable(false);
		
		add(text, BorderLayout.CENTER);
	}
}
