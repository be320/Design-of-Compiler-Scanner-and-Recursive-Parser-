
package scanner;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
public class AssignStatement extends JPanel{
    private int w=100;
    private int h=70;
    private int x;
    private int y;
    public ArrayList<String> children = new ArrayList<String>();
    String text;
    
    public AssignStatement(int x,int y,String text)
    {
        this.x=x;
        this.y=y;
        this.text=text;
    }
     public void draw(Graphics g)
    {
    super.paint(g);
    g.setFont(new Font("Serif",Font.BOLD,24));
    g.setColor(Color.ORANGE);
    g.fillRect(x, y,w,h);
    g.setColor(Color.BLACK);
    g.drawString("assign", x+20, y+25);
    g.drawString("( "+text+" ) ", x+30, y+50);
    g.drawLine(x+w/2, y+h, x+w/2, y+h+50);
    g.drawLine(x+w,y+(h/2), x+w+150, y+(h/2));
    }
}
