package graphic;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;
import javax.swing.border.Border;

import main.Portal;
import angebote.*;

public class DialogScreen extends JDialog 
{
	public final static int NAN_OPTION = 300;
	public final static int OK_OPTION = 23;
	public final static int OK_CANCEL_OPTION = 42;
	public final static int LABEL_LEFT = 666;
	public final static int LABEL_RIGHT = 88;
	public final static int BUTTONWIDTH = MainFrame.BUTTONWIDTH-(MainFrame.BUTTONWIDTH/4);
	public final static int BUTTONHEIGHT = MainFrame.BUTTONHEIGHT-(MainFrame.BUTTONHEIGHT/4);
	private JTextArea area;
	private JPanel leftPanel;
	private JPanel rightPanel;
	
	private MainFrame mainFrame;
	
	public DialogScreen(MainFrame frame, String title)
	{
		super();
		init(frame, title, null, OK_OPTION);
	}
	
	public DialogScreen(MainFrame frame, String title, int flag)
	{
		super();
		init(frame, title, null, flag);
	}
	
	public DialogScreen(MainFrame frame, String title, JButton[] buttons)
	{
		super();
		init(frame, title, buttons, NAN_OPTION);
	}
	
	public DialogScreen(MainFrame frame, String title, JButton[] buttons, int flag)
	{
		super();
		init(frame, title, buttons, flag);
	}
	
	private void init(MainFrame frame, String title, JButton[] but ,int flag) 
	{
		this.setTitle(title);
		this.addWindowListener(new WindowAdapter() 
        { 
            public void windowClosing(WindowEvent evt) 
            {
            	close();
            }
        });
		mainFrame = frame;
		mainFrame.setEnabled(false);
		
		JPanel main = new JPanel(new BorderLayout());
		main.setPreferredSize(new Dimension(MainFrame.BUTTONWIDTH*3, MainFrame.BUTTONHEIGHT*10));
		
		Border border = BorderFactory.createMatteBorder(4, 4, 4, 4, Color.LIGHT_GRAY);
		
		////
		
		JPanel north = new JPanel(new BorderLayout());
		JPanel center = new JPanel(new BorderLayout());
		JPanel south = new JPanel();
		
		north.setBorder(border);
		center.setBorder(border);
		south.setBorder(border);
		
		JPanel labelPanel = new JPanel(new GridLayout(1,0));
		leftPanel = new JPanel();
		labelPanel.add(leftPanel);
		JPanel starPanel = new JPanel();
		rightPanel = new JPanel();
		starPanel.add(rightPanel);
		area = new JTextArea();
		JPanel buttons = new JPanel();
		
		if(but != null)
			for(JButton b: but)
				buttons.add(b);
		
		if(flag == OK_OPTION || flag == OK_CANCEL_OPTION)
		{
			JButton ok = new JButton("OK");
			ok.setPreferredSize(new Dimension(BUTTONWIDTH, BUTTONHEIGHT));
			buttons.add(ok);
		    ok.addActionListener(new ActionListener() 
		    { 
		      public void actionPerformed(ActionEvent evt)
		      { 
				  onOK();
				  close();
		      }
		    });
		}
	    if(flag == OK_CANCEL_OPTION)
	    {
			JButton cancel = new JButton("Abbrechen");
			cancel.setPreferredSize(new Dimension(BUTTONWIDTH, BUTTONHEIGHT));
		    buttons.add(cancel);
		    cancel.addActionListener(new ActionListener() 
		    {  
		    	public void actionPerformed(ActionEvent evt)  
		    	{ 
		    		onCancel();
		    		close();  
		    	}
		    });
	    }
		
	    ////
	    
	    north.add(starPanel, BorderLayout.EAST);
	    north.add(labelPanel, BorderLayout.WEST);
		center.add(new JScrollPane(area), BorderLayout.CENTER);
		south.add(buttons);
		
		main.add(north, BorderLayout.NORTH);
		main.add(center, BorderLayout.CENTER);
		main.add(south, BorderLayout.SOUTH);
		
		area.setLineWrap(true);
		area.setWrapStyleWord(true);
		
		this.add(main);
		this.pack();
		this.setVisible(true);
		this.setAlwaysOnTop(true);
	}
	
	public void close()
	{
		mainFrame.setEnabled(true);
		this.dispose();
	}
	
	public void setContent(String t)
	{
		area.setText(t);
		this.repaint();
	}
	
	public String getContent()
	{
		return area.getText();
	}
	
	public void setEditable(boolean b)
	{
		area.setEditable(b);
	}
	
	public void addOnPanel(Component component, int flag)
	{
		if(flag == LABEL_LEFT)
			leftPanel.add(component);
		else if(flag == LABEL_RIGHT)
			rightPanel.add(component);
		this.repaint();
	}
	
	public void onOK()
	{}
	
	public void onCancel()
	{}
}
