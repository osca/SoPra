package graphic;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;

import main.Portal;
import angebote.*;

public class DialogScreen extends JDialog 
{
	private JTextArea area;
	private JLabel textLabel;
	
	public DialogScreen(JFrame comp, String title, int buttonCount) 
	{
		super(comp, title);
		
		this.setLocation(this.getParent().getWidth()/4, this.getParent().getHeight()/4);
		JPanel main = new JPanel(new BorderLayout());
		main.setPreferredSize(new Dimension(MainFrame.BUTTONWIDTH*3, MainFrame.BUTTONHEIGHT*8));
		
		Border border = BorderFactory.createMatteBorder(4, 4, 4, 4, Color.LIGHT_GRAY);
		
		////
		
		JPanel north = new JPanel();
		JPanel center = new JPanel(new BorderLayout());
		JPanel south = new JPanel();
		
		north.setBorder(border);
		center.setBorder(border);
		south.setBorder(border);
		
		textLabel = new JLabel();
		area = new JTextArea();
		JPanel buttons = new JPanel();
		
		if(buttonCount > 0)
		{
			JButton ok = new JButton("OK");
			ok.setPreferredSize(new Dimension(MainFrame.BUTTONWIDTH, MainFrame.BUTTONHEIGHT));
			buttons.add(ok);
		    ok.addActionListener(new ActionListener() 
		    { 
		      public void actionPerformed(ActionEvent evt)
		      { 
				  onOK();
				  dispose();
		      }
		    });
		}
	    if(buttonCount > 1)
	    {
			JButton cancel = new JButton("Abbrechen");
			cancel.setPreferredSize(new Dimension(MainFrame.BUTTONWIDTH, MainFrame.BUTTONHEIGHT));
		    buttons.add(cancel);
		    cancel.addActionListener(new ActionListener() {  public void actionPerformed(ActionEvent evt)   { dispose();  } });
	    }
		
	    ////
	    
		north.add(textLabel);
		center.add(new JScrollPane(area), BorderLayout.CENTER);
		south.add(buttons);
		
		main.add(north, BorderLayout.NORTH);
		main.add(center, BorderLayout.CENTER);
		main.add(south, BorderLayout.SOUTH);
		
		this.add(main);
		this.pack();
		this.setVisible(true);
	}
	
	public void setLabelContent(String l)
	{
		textLabel.setText(l);
	}
	
	public void setContent(String t)
	{
		area.setText(t);
	}
	
	public String getContent()
	{
		return area.getText();
	}
	
	public void setEditable(boolean b)
	{
		area.setEditable(b);
	}
	
	public void onOK()
	{
		
	}
}
