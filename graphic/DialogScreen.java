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
	// die flags werden uebergeben, damit DialogScreen weiss, welche er darstellen soll
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
	
	/**
	 * Konstruktor der nur einen Titel erwartet.
	 * Es muss ein Fenster uebergeben werden, damit der DialogScreen in den Vordergrund gesetzt wird.
	 * 
	 * @param frame MainFrame
	 * @param title Titel des Fensters
	 */
	public DialogScreen(MainFrame frame, String title)
	{
		super();
		init(frame, title, null, OK_OPTION);
	}
	
	/**
	 * Es wird, zusaetzlich zu den Parametern wie bei dem obrigen Konstruktor, noch eine Flag mitgegeben.
	 * Die Flags zeigen an, welche vordefinierten JButton auf dem DialogScreen gesetzt werden.
	 * 
	 * @param frame MainFrame
	 * @param title Titel des Fensters
	 * @param flag Flag zum setzen
	 */
	public DialogScreen(MainFrame frame, String title, int flag)
	{
		super();
		init(frame, title, null, flag);
	}
	
	/**
	 * Es kann, statt der Flag, ein JButton-Array mitgegeben werden, welches auf der DialogScreen gesetzt wird.
	 * 
	 * @param frame MainFrame
	 * @param title Titel des Fensters
	 * @param buttons Array an JButtons
	 */
	public DialogScreen(MainFrame frame, String title, JButton[] buttons)
	{
		super();
		init(frame, title, buttons, NAN_OPTION);
	}
	
	/**
	 * Eine Zusammenfassung der obrigen Konstruktoren.
	 * 
	 * @param frame MainFrame
	 * @param title Titel des Fensteres
	 * @param buttons Array an JButtons
	 * @param flag Flags zum setzen
	 */
	public DialogScreen(MainFrame frame, String title, JButton[] buttons, int flag)
	{
		super();
		init(frame, title, buttons, flag);
	}
	

	// baut den DialogScreen auf
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
		this.setLocation(mainFrame.getX()+(mainFrame.getWidth()/4), mainFrame.getY()+(mainFrame.getHeight()/4));
		
		////
		
		mainFrame = frame;
		mainFrame.setEnabled(false);
		
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
		
		// die abarbeitung der flags.
		// die flags geben an, welche buttons gesetzt werden
		
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
	    
	    // alle buttons, wenn buttonarray vorhanden
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
	
	/**
	 * Schlieﬂt den DialogScreen und setzt die MainFrame wieder frei.
	 */
	public void close()
	{
		mainFrame.setEnabled(true);
		this.dispose();
	}
	
	/**
	 * Setzt den Inhalt des JTextAreaFields, das auf dem DialogScreens liegt.
	 * 
	 * @param t Der Text als String
	 */
	public void setContent(String t)
	{
		area.setText(t);
		this.repaint();
	}
	
	/**
	 * Gibt den Inhalt des JTextAreaFields aus.
	 * 
	 * @return Text als String
	 */
	public String getContent()
	{
		return area.getText();
	}
	
	/**
	 * Setzt das JTextAreaFields, jenachdem was fuer ein boolischer Operator uebergeben wurde.
	 * 
	 * @param b 
	 */
	public void setEditable(boolean b)
	{
		area.setEditable(b);
	}
	
	/**
	 * Setzt ein Component auf die Position die als Flag uebergeben wurde.
	 * FLAG_LEFT oder FLAG_RIGHT.
	 * 
	 * @param component Das Component zum setzen
	 * @param flag Die Flag
	 */
	public void addOnPanel(Component component, int flag)
	{
		if(flag == LABEL_LEFT)
			leftPanel.add(component);
		else if(flag == LABEL_RIGHT)
			rightPanel.add(component);
		this.repaint();
	}
	
	/**
	 * Setzt den Namen des "Antworten"-Buttons
	 * 
	 * @param name Der neue Name als String
	 */
	public void setAnswerButtonName(String name)
	{
		if(answer != null)
			answer.setText(name);
	}
	/**
	 * Setzt den Namen des "Angebots"-Buttons
	 * 
	 * @param name Der neue Name als String
	 */
	public void setOfferButtonName(String name)
	{
		if(offer != null)
			offer.setText(name);
	}
	
	/**
	 * Wird beim klicken des OK-Buttons aufgerufen.
	 */
	public void onOK()
	{}
	/**
	 * Wird beim klicken des OK-Buttons aufgerufen.
	 */	
	public void onCancel()
	{}
	/**
	 * Wird beim klicken des OK-Buttons aufgerufen.
	 */	
	public void onAnswer()
	{}
	/**
	 * Wird beim klicken des OK-Buttons aufgerufen.
	 */	
	public void onOffer()
	{}
}
