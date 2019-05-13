package scanner;

import java.awt.*;
import javax.swing.*;
import java.util.*;

public class WriteStatement extends JPanel{
    private int w=100;
    private int h=70;
    private int x;
    private int y;
    public ArrayList<String> children = new ArrayList<String>();
    String text;
    
    public WriteStatement(int x,int y)
    {
        this.x=x;
        this.y=y;
    }
    
     public void draw(Graphics g)
    {
        super.paint(g);
        g.setFont(new Font("Serif",Font.BOLD,24));
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(x, y,w,h);
        g.setColor(Color.BLACK);
        g.drawString("write", x+20, y+40);
        g.drawLine(x+w/2,y+h, x+w/2, y+h+50);
        g.drawLine(x+w,y+(h/2), x+w+150, y+(h/2));
    }
}
