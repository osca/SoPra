package graphic;

import java.awt.*;
import javax.swing.*;
import angebote.*;

public class KommentarScreen extends JPanel {
	private JLabel verfasser;
	private JLabel bewertung;
	private JTextArea text;
	
	public KommentarScreen(Kommentar kommi) {
		verfasser = new JLabel(kommi.getAbsender());
		bewertung = new JLabel(Integer.toString(kommi.getBewertung()));
		text = new JTextArea(kommi.getText());
		
		setLayout(new BorderLayout());
		
		add(verfasser, BorderLayout.BEFORE_LINE_BEGINS);
		add(bewertung, BorderLayout.AFTER_LINE_ENDS);
		add(text, BorderLayout.CENTER);
		setVisible(true);
	}
}
