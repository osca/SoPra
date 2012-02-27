import java.util.*;
public class Morsecodierer
{
    private BinTree Baum;
    
    public Morsecodierer()
    {        
        BinTree links4 = new BinTree("H");BinTree rechts4 = new BinTree("V");
        BinTree links3 = new BinTree("S",links4,rechts4);
        links4 = new BinTree("F");rechts4 = new BinTree("Ü");
        BinTree rechts3 = new BinTree("U",links4,rechts4);
        BinTree links2 = new BinTree("I",links3,rechts3);
        links4 = new BinTree("L");rechts4 = new BinTree("Ä");
        links3 = new BinTree("R",links4,rechts4);
        links4 = new BinTree("P");rechts4 = new BinTree("J");
        rechts3 = new BinTree("W",links4,rechts4);
        BinTree rechts2 = new BinTree ("A",links3,rechts3);
        BinTree links1 = new BinTree("E", links2,rechts2);
        links4 = new BinTree("B");rechts4 = new BinTree("X");
        links3 = new BinTree("D",links4,rechts4);
        links4 = new BinTree("C");rechts4 = new BinTree("Y");
        rechts3 = new BinTree("K",links4,rechts4);
        links2 = new BinTree("N",links3,rechts3);
        links4 = new BinTree("Q");rechts4 = new BinTree("Z");
        links3 = new BinTree("G",links4,rechts4);
        links4 = new BinTree("Ö");rechts4 = new BinTree("CH");
        rechts3 = new BinTree("O",links4,rechts4);
        rechts2 = new BinTree("M",links3,rechts3);
        BinTree rechts1 = new BinTree("T",links2,rechts2);
        Baum = new BinTree("",links1,rechts1);
    }
    
    public String Morse2Text(String pMorsetext)
    {
        int Zaehler = 0;
        char Zeichen;
        String Ausgabe = "";
        BinTree aktuell = Baum;
        while (Zaehler < pMorsetext.length())
        {            
                Zeichen = pMorsetext.charAt(Zaehler);//naechstes Zeichen
                if (Zeichen == '/')//Zeichen beendet
                {
                    Ausgabe = Ausgabe + (String) aktuell.getRootItem();
                    aktuell = Baum;//wieder oben im Baum beginnen            
                }
                else
                {                    
                    if (Zeichen == '.') aktuell = aktuell.getLeftTree();//nach links gehen           
                    else aktuell = aktuell.getRightTree();//nach rechts gehen                    
                }
                Zaehler++;
        }
        return Ausgabe;
    }
    
    public String Text2Morse(String pText)
    {
        String Ergebnis = "";
        int Position = 0;
        while (Position < pText.length())
        {
            char Zeichen = pText.charAt(Position);//naechsten Buchstaben lesen
            String Morse = gib_Morse(Zeichen,"",Baum);//Code fuer diesen Buchstaben holen
            Ergebnis = Ergebnis + Morse + "/";
            Position++;
        }
        return Ergebnis;
    }
    
    private String gib_Morse(char pBuchstabe,String pKette, BinTree pBaum)
    {
        String Ergebnislinks = "";
        if (pBaum != null)
        {
            if (pBaum.getRootItem().equals(""+pBuchstabe)) return pKette;//gefunden!
            else
            {
                Ergebnislinks = gib_Morse(pBuchstabe,pKette+'.',pBaum.getLeftTree());
                if (Ergebnislinks == "")
                    return gib_Morse(pBuchstabe,pKette+'-',pBaum.getRightTree());
            }
        }
        return Ergebnislinks;
    }            
    
    public void Ausgabe()
    {
        Ausgabe(Baum);
    }
    
    public void Ausgabe(BinTree b)
    {
        if (b != null)
        {
            Ausgabe(b.getLeftTree());
            System.out.println(b.getRootItem().toString());
            Ausgabe(b.getRightTree());
        }
    }        
}

