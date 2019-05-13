package scanner;

import java.awt.*;
import javax.swing.*;
public class OpStatement extends JPanel{
    private int w=100;
    private int h=70;
    private int x;
    private int y;
    private Color c;
    String text;
    
    public OpStatement(int x,int y,String text)
    {
        this.x=x;
        this.y=y;
        this.c=c;
        this.text=text;
    }
     public void draw(Graphics g)
    {
        super.paint(g);
        g.setFont(new Font("Serif",Font.BOLD,24));
        g.setColor(Color.GREEN);
        g.fillOval(x, y,w,h);
        g.setColor(Color.BLACK);
        g.drawString("op", x+40, y+25);
        g.drawString("( "+text+" ) ", x+30, y+50);
        g.drawLine(x+w/2, y+h, x+10, y+h+30);
        g.drawLine(x+w/2, y+h, x+w-10, y+h+30);
    }
     
}
