package graphic;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
/**
 *MaxTextDocument
 *
 *Die Klasse regelt die maximale Länge von Strings (wenn benoetigt).
 *@author Benjamin
 */
public class MaxTextDocument extends PlainDocument{
	private static final long serialVersionUID = 8316268063920093876L;
	int maxLaenge;
	/**
	 * Trivialer Konstruktor der Klasse
	 * @param max
	 */
	public MaxTextDocument(int max) {
		maxLaenge = max;
	}
	
	@Override
	public void insertString(int offset, String s, AttributeSet a) {
		if(s.length() == 0)
	        return;
	    
		if(getLength() + s.length() < maxLaenge) {
	        try {
				super.insertString(offset, s, a);
			} catch (BadLocationException e) {
			}
	    }
	}
}