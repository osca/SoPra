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
	public final static int NAN_OPTION = 300;
	public final static int OK_OPTION = 23;
	public final static int OK_CANCEL_OPTION = 42;
	public final static int LABEL_LEFT = 666;
	public final static int LABEL_RIGHT = 88;
	
	private JTextArea area;
	private JPanel leftPanel;
	private JPanel rightPanel;
	
	public DialogScreen()
	{
		super();
		init("", null, NAN_OPTION);
	}
	
	public DialogScreen(String title)
	{
		super();
		init(title, null, OK_OPTION);
	}
	
	public DialogScreen(String title, int flag)
	{
		super();
		init(title, null, flag);
	}
	
	public DialogScreen(JFrame frame, String title, int flag)
	{
		super(frame);
		init(title, null, flag);
	}
	
	public DialogScreen(String title, JButton[] buttons)
	{
		super();
		init(title, buttons, NAN_OPTION);
	}
	
	public DialogScreen(String title, JButton[] buttons, int flag)
	{
		super();
		init(title, buttons, flag);
	}
	
	private void init(String title, JButton[] but ,int flag) 
	{
		this.setTitle(title);
		
		if(this.getParent()!=null)
			this.setLocation(this.getParent().getWidth()/4, this.getParent().getHeight()/4);
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
			ok.setPreferredSize(new Dimension(MainFrame.BUTTONWIDTH-(MainFrame.BUTTONWIDTH/4), MainFrame.BUTTONHEIGHT-(MainFrame.BUTTONHEIGHT/4)));
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
	    if(flag == OK_CANCEL_OPTION)
	    {
			JButton cancel = new JButton("Abbrechen");
			cancel.setPreferredSize(new Dimension(MainFrame.BUTTONWIDTH-(MainFrame.BUTTONWIDTH/4), MainFrame.BUTTONHEIGHT-(MainFrame.BUTTONHEIGHT/4)));
		    buttons.add(cancel);
		    cancel.addActionListener(new ActionListener() 
		    {  
		    	public void actionPerformed(ActionEvent evt)  
		    	{ 
		    		onCancel();
		    		dispose();  
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
		
		this.add(main);
		this.pack();
		this.setVisible(true);
		//setModal(true);
		setAlwaysOnTop(true);
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
