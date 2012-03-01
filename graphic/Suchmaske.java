package graphic;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class Suchmaske extends JPanel {

	private JPanel up;
	private JPanel sub_a;
	private JPanel sub_b;
	private JPanel mid;

	public Suchmaske() {
		setLayout(new BorderLayout(5, 5));
		up = new JPanel(new GridLayout(0, 2));
		sub_a= new JPanel(new GridLayout(6,0));
		sub_b= new JPanel(new GridLayout(6,0));
		
		mid = new JPanel(new FlowLayout());
		
		
	}

}
