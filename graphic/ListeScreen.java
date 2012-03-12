package graphic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


/**
 * ListeScreen wird genutzt um alle Listable-Klassen als Liste darstellen, wobei ein Element die Details anzeigen kann.
 * @author Rudolf, delikat
 *
 * @param <T>	T ist ein generischer Typ, welcher sicher stellt, dass alle Listable-Klassen dargestellt werden können.
 */
public class ListeScreen <T extends Listable> extends JPanel {
	
	private static final long serialVersionUID = 1813896135878948672L;
	static JScrollPane lscroll;
	static JPanel sPanel;
	
	/**
	 * Konstruktor, welche alle Layouts und Elemente initialisiert.
	 * @param mainframe		Das Hauptfenster, auf dem die Liste eingefügt wird.
	 * @param list			Die Liste, welche dargestellt wird.
	 */
	@SuppressWarnings("unchecked")
	public ListeScreen(final MainFrame mainframe, final ArrayList<T> list){
		
		this.setBackground(Color.LIGHT_GRAY);
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.LIGHT_GRAY));
		
		JPanel elementPanel = new JPanel(new GridLayout(0,1));
		
		final Class<T> cls;
		if(list.size()>0)
			cls = (Class<T>) list.get(0).getClass();
		else cls = (Class<T>) list.getClass();
		
		for (int i=0;i<list.size();i++){
			final int j = i;
			final JPanel eventPanel = new JPanel();
			eventPanel.setLayout(new GridLayout(1,0));
			eventPanel.addMouseListener(new MouseListener(){

				@Override
				public void mouseClicked(MouseEvent e) {}

				@Override
				public void mouseEntered(MouseEvent e) 
				{
					eventPanel.setBackground(Color.white);
					eventPanel.getParent().repaint();
				}

				@Override
				public void mouseExited(MouseEvent e) 
				{
					eventPanel.setBackground(null);
				}

				@Override
				public void mousePressed(MouseEvent e) {}

				@Override
				public void mouseReleased(MouseEvent e) {
					mainframe.showDetail(list.get(j));
					if(cls.equals(accounts.Nachricht.class))
						mainframe.showNachrichten();
				}
			});
			
			JLabel name = new JLabel(list.get(i).getIdentifier());
			name.setText("        " +list.get(i).getIdentifier());
			JLabel info = new JLabel(list.get(i).getAdditionalInfo());
			JLabel status = new JLabel(list.get(i).getStatus());
			eventPanel.add(name);
			eventPanel.add(info);
			eventPanel.add(status);
			eventPanel.setPreferredSize(new Dimension(MainFrame.BUTTONWIDTH, MainFrame.BUTTONHEIGHT));
			eventPanel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.LIGHT_GRAY));
			this.add(eventPanel);
			elementPanel.add(eventPanel);
		}
		
		this.add(new JScrollPane(elementPanel), BorderLayout.NORTH);
	}
}
