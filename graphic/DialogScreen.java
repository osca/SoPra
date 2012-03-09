package graphic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class DialogScreen extends JDialog 
{
	public final static int NAN_OPTION = 300;
	public final static int OK_OPTION = 23;
	public final static int OK_CANCEL_OPTION = 40;
	public final static int OK_CANCEL_ANSWER_OPTION = 13;
	public final static int OK_ANSWER_OPTION = 12;
	public final static int OK_OFFER_OPTION = 10;
	public final static int OK_OFFER_CANCEL_OPTION = 41;
	public final static int OK_OFFER_ANSWER_OPTION = 39;
	public final static int ALL_OPTION = 42;
	public final static int LABEL_LEFT = 666;
	public final static int LABEL_RIGHT = 88;
	public final static int BUTTONWIDTH = MainFrame.BUTTONWIDTH-(MainFrame.BUTTONWIDTH/4);
	public final static int BUTTONHEIGHT = MainFrame.BUTTONHEIGHT-(MainFrame.BUTTONHEIGHT/4);
	private JTextArea area;
	private JPanel leftPanel;
	private JPanel rightPanel;
	
	private MainFrame mainFrame;
	private JButton answer;
	private JButton offer;
	
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
		
		this.setLocation(mainFrame.getX()+(mainFrame.getWidth()/4), mainFrame.getY()+(mainFrame.getHeight()/4));
		
		JPanel main = new JPanel(new BorderLayout());
		main.setPreferredSize(new Dimension(MainFrame.BUTTONWIDTH*4, MainFrame.BUTTONHEIGHT*10));
		
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
		
		if(flag != NAN_OPTION)
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
	    if(flag == OK_CANCEL_OPTION || flag == OK_CANCEL_ANSWER_OPTION || flag == OK_OFFER_CANCEL_OPTION)
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
	    if(flag == OK_CANCEL_ANSWER_OPTION || flag == OK_CANCEL_ANSWER_OPTION || flag == OK_OFFER_ANSWER_OPTION)
	    {
	    	answer = new JButton("Antworten");
	    	answer.setPreferredSize(new Dimension(BUTTONWIDTH, BUTTONHEIGHT));
		    buttons.add(answer);
		    answer.addActionListener(new ActionListener() 
		    {  
		    	public void actionPerformed(ActionEvent evt)  
		    	{ 
		    		onAnswer();
		    		close();  
		    	}
		    });
	    }
	    if(flag == OK_OFFER_OPTION || flag == OK_OFFER_CANCEL_OPTION || flag == OK_OFFER_ANSWER_OPTION)
	    {
	    	offer = new JButton("Zum Angebot");
	    	offer.setPreferredSize(new Dimension(BUTTONWIDTH, BUTTONHEIGHT));
		    buttons.add(offer);
		    offer.addActionListener(new ActionListener() 
		    {  
		    	public void actionPerformed(ActionEvent evt)  
		    	{ 
		    		onOffer();
		    		close();  
		    	}
		    });
	    }
	    
	    if(but != null)
			for(JButton b: but)
				buttons.add(b);
		
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
	
	public void setAnswerButtonName(String name)
	{
		if(answer != null)
			answer.setText(name);
	}
	public void setOfferButtonName(String name)
	{
		if(offer != null)
			offer.setText(name);
	}
	
	public void onOK()
	{}
	
	public void onCancel()
	{}
	
	public void onAnswer()
	{}
	
	public void onOffer()
	{}
}
