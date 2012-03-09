package graphic;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class MaxTextDocument extends PlainDocument{
	int maxLaenge;
	
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