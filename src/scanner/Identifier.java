
package scanner;

import java.awt.*;
import javax.swing.*;
public class Identifier extends JPanel{
    private int w=100;
    private int h=70;
    private int x;
    private int y;
    private Color c;
    String text;
    
    public Identifier(int x,int y,String text)
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
    g.setColor(Color.MAGENTA);
    g.fillOval(x, y,w,h);
    g.setColor(Color.BLACK);
    g.drawString("id", x+40, y+25);
    g.drawString("( "+text.substring(1)+" )", x+25, y+50);
    }
}
