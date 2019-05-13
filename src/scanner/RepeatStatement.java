
package scanner;

import java.awt.*;
import javax.swing.*;
public class RepeatStatement extends JPanel{
    private int w=100;
    private int h=70;
    private int x;
    private int y;
    private Color c;
    String text;
    
    public RepeatStatement(int x,int y)
    {
        this.x=x;
        this.y=y;
    }
     public void draw(Graphics g)
    {
    super.paint(g);
    g.setFont(new Font("Serif",Font.BOLD,24));
    g.setColor(Color.BLUE);
    g.fillRect(x, y,w,h);
    g.setColor(Color.BLACK);
    g.drawString("repeat", x+20, y+40);
    g.drawLine(x+10, y+h, x-200, y+h+200);
    g.drawLine(x+w-10, y+h, x+w+150, y+h+200);
    g.drawLine(x+w, y+h/2, x+w+100, y+h/2);
    }
}
