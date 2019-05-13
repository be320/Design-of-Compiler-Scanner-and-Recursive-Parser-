
package scanner;

import java.awt.*;
import javax.swing.*;
public class ReadStatement extends JPanel{
    private int w=100;
    private int h=70;
    private int x;
    private int y;
    private Color c;
    String text;
    
    public ReadStatement(int x,int y,String text)
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
    g.setColor(Color.CYAN);
    g.fillRect(x, y,w,h);
    g.setColor(Color.BLACK);
    g.drawString("read", x+30, y+25);
    if(!text.equals("error"))
    {
        g.drawString("( "+text.substring(1)+" ) ", x+30, y+50);
    }
    else {
        g.setColor(Color.RED);
        g.drawString("( "+text+" ) ", x+10, y+50);
        g.setColor(Color.BLACK);
    }
    g.drawLine(x+w,y+(h/2), x+w+50, y+(h/2));
    }
}
