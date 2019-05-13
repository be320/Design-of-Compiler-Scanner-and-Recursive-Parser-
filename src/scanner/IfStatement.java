
package scanner;

import java.awt.*;
import javax.swing.*;
import java.util.*;
public class IfStatement extends JPanel{
    private int w=100;
    private int h=70;
    private int x;
    private int y;
    private boolean iselse=false; 
    private boolean isend=false;
    
    public IfStatement(int x,int y,ArrayList<String> nodes,int i)
    {
        this.x=x;
        this.y=y;
        for(int j=i;j<nodes.size();j++)
        {
        switch(nodes.get(j))
        {
            case "else":
                iselse=true;
                break;
            case "end":
                if(j==nodes.size()-1)
                    isend=true;
                break;
        }
        }
    }
     public void draw(Graphics g)
    {
    super.paint(g);
    g.setFont(new Font("Serif",Font.BOLD,24));
    g.setColor(Color.RED);
    g.fillRect(x, y,w,h);
    g.setColor(Color.BLACK);
    g.drawString("if", x+40, y+40);
    //first line is condition
    g.drawLine(x+20, y+h, x-50, y+h+50);
    //second line is body
    g.drawLine(x+w/2, y+h, x+w/2+50, y+h+300);  
    if(iselse)
     g.drawLine(x+w-20, y+h, x+w+50, y+h+50); 
    if(!isend)
        g.drawLine(x+w, y+h/2, x+w+100, y+h/2);
    }
}
