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


public class ListeScreen <T extends Listable> extends JPanel {
	static JScrollPane lscroll;
	static JPanel sPanel;
	
	public ListeScreen(final MainFrame mainframe, final ArrayList<T> list){
		
		this.setBackground(Color.LIGHT_GRAY);
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.LIGHT_GRAY));
		
		JPanel elementPanel = new JPanel(new GridLayout(0,1));
		final Class<T> cls = (Class<T>) list.get(0).getClass();
		
		for (int i=0;i<list.size();i++){
			final int j = i;
			final JPanel eventPanel = new JPanel();
			eventPanel.setLayout(new BoxLayout(eventPanel,BoxLayout.X_AXIS));
			final JPanel event_two=new JPanel(new GridLayout(1,3));
			eventPanel.addMouseListener(new MouseListener(){

				@Override
				public void mouseClicked(MouseEvent e) {}

				@Override
				public void mouseEntered(MouseEvent e) 
				{
					eventPanel.setBackground(Color.white);
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
			JPanel name_p = new JPanel(new GridLayout(1,2));
			name_p.add(name,BorderLayout.CENTER);
			event_two.add(name_p,BorderLayout.WEST);
			JLabel info = new JLabel(list.get(i).getAdditionalInfo());
			event_two.add(info, BorderLayout.CENTER);
			JLabel status = new JLabel(list.get(i).getStatus());
			event_two.add(status,BorderLayout.EAST);
			eventPanel.add(event_two);
			eventPanel.setPreferredSize(new Dimension(MainFrame.BUTTONWIDTH, MainFrame.BUTTONHEIGHT));
			eventPanel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.LIGHT_GRAY));
			elementPanel.add(eventPanel);
		}
		
		this.add(new JScrollPane(elementPanel), BorderLayout.NORTH);
	}
}
