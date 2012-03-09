package graphic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import angebote.Kommentar;
import angebote.typen.Angebot;

/**
 * KommentarListe
 * 
 * Haendelt die Kommentarlisten unterhalb der Angebotdetails
 *
 * @author Rudi, Benni, Denis
 */
@SuppressWarnings("serial")
public class KommentarListe extends JPanel {
	
	/**
	 * Trivialer Konstruktor
	 * @param a Angebot zu welchem die Kommentarliste erstellt wird
	 */
	public KommentarListe(Angebot a) {
		setLayout(new BorderLayout());

		init(a);
	}
	
	/**
	 * Die Methode ruft alle Kommentare eines Angebots auf und erstellt
	 * daraus ein Panel mit einer Liste aller Kommentare
	 * @param a Angebot zu welchem die Kommentarliste erstellt wird
	 */
	public void init(Angebot a) {
		JPanel elementPanel = new JPanel();
		elementPanel.setLayout(new BoxLayout(elementPanel, BoxLayout.Y_AXIS));

		ArrayList<Kommentar> kliste = a.getKommentare();

		for (int i = kliste.size() - 1; i >= 0; i--) {
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			JPanel eventPanel = new JPanel(new BorderLayout());
			JPanel komPanel = new JPanel(new GridLayout(1, 2));
			JPanel fullp = new JPanel(new GridLayout(1, 1));
			JLabel name = new JLabel(kliste.get(i).getAbsender());
			komPanel.add(name);

			if (kliste.get(i).getBewertung() != Kommentar.KEINEWERTUNG) {
				JLabel info = new JLabel(df.format(kliste.get(i)
						.getZeitstempel())
						+ " , Wertung: "
						+ kliste.get(i).getBewertung());
				komPanel.add(info);
			}

			eventPanel.add(komPanel, BorderLayout.NORTH);
			final JTextArea full = new JTextArea();
			full.setBackground(null);
			full.setLineWrap(true);
			full.setWrapStyleWord(true);
			full.setEditable(false);
			full.setText(kliste.get(i).getFullInfo());
			fullp.add(full);
			eventPanel.add(fullp, BorderLayout.CENTER);
			eventPanel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,
					Color.LIGHT_GRAY));
			elementPanel.add(eventPanel, BorderLayout.NORTH);
		}
		add(BorderLayout.NORTH, elementPanel);
		setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.LIGHT_GRAY));
		setBackground(Color.BLACK);

	}
}
