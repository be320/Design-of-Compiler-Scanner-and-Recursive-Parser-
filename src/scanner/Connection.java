
package scanner;

import java.awt.*;
import javax.swing.*;
public class Connection extends JPanel{
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    
    public Connection(int x1,int y1,int x2,int y2)
    {
        this.x1=x1;
        this.y1=y1;
        this.x2=x2;
        this.y2=y2;
    }
     public void draw(Graphics g)
    {
    super.paint(g);
    g.setColor(Color.WHITE);
    g.drawLine(x1,y1, x2, y2);
    }
}
