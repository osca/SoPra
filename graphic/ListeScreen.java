package graphic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class ListeScreen <T extends Listable> extends JPanel {
	static JScrollPane lscroll;
	static JPanel sPanel;
	
	public ListeScreen(final MainFrame mainframe, final ArrayList<T> list){
		
		this.setBackground(Color.black);
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.LIGHT_GRAY));
		
		JPanel elementPanel = new JPanel(new GridLayout(0,1));
		
		for (int i=0;i<list.size();i++){
			final int j = i;
			JPanel eventPanel = new JPanel();
			eventPanel.addMouseListener(new MouseListener(){

				@Override
				public void mouseClicked(MouseEvent e) {}

				@Override
				public void mouseEntered(MouseEvent e) {}

				@Override
				public void mouseExited(MouseEvent e) {}

				@Override
				public void mousePressed(MouseEvent e) {}

				@Override
				public void mouseReleased(MouseEvent e) {
					mainframe.showDetail(list.get(j));
				}
			});
			
			JLabel name = new JLabel(list.get(i).getIdentifier());
			eventPanel.add(name);
			JLabel info = new JLabel(list.get(i).getAdditionalInfo());
			eventPanel.add(info);
			eventPanel.setPreferredSize(new Dimension(MainFrame.BUTTONWIDTH, MainFrame.BUTTONHEIGHT));
			eventPanel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.LIGHT_GRAY));
			elementPanel.add(eventPanel);
		}
		
		this.add(new JScrollPane(elementPanel), BorderLayout.NORTH);
	}
}
