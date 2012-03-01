package graphic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
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
		
		
		setLayout(new GridLayout(1,0));
		lscroll = new JScrollPane();
		sPanel= new JPanel(new GridLayout(0,1));
		//size des scrolls setzen
		for (int i=0;i<list.size();i++){
			final int j = i;
			JPanel listenerPanel = new JPanel();
			listenerPanel.addMouseListener(new MouseListener(){

				@Override
				public void mouseClicked(MouseEvent e) {
					mainframe.showDetail(list.get(j));
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
			});
			JLabel name = new JLabel(list.get(i).getIdetifier());
			listenerPanel.add(name);
			JLabel info = new JLabel(list.get(i).getAdditionalInfo());
			listenerPanel.add(info);
			listenerPanel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.LIGHT_GRAY));
			sPanel.add(listenerPanel);
		}
		
		this.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.LIGHT_GRAY));
		
		lscroll.add(sPanel);
		lscroll.setViewportView(sPanel);
		add(lscroll);
	}

	
}
