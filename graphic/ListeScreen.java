package graphic;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class ListeScreen <T extends Listable> extends JPanel {
	static JScrollPane lscroll;
	static JPanel sPanel;
	
	public ListeScreen(final Mainframe mainframe, final ArrayList<T> list){
		
		
		setLayout(new GridLayout(1,0));
		lscroll = new JScrollPane();
		sPanel= new JPanel(new GridLayout(7,0));
		//size des scrolls setzen
		for (int i=0;i<list.size();i++){
			final int j = i;
			JPanel p = new JPanel();
			p.addMouseListener(new MouseListener(){

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
			p.add(name);
			JLabel info = new JLabel(list.get(i).getAdditionalInfo());
			p.add(info);
			sPanel.add(p);
			
		}
		lscroll.add(sPanel);
		lscroll.setViewportView(sPanel);
		add(lscroll);
		setVisible(true);
		
	}

	
}
