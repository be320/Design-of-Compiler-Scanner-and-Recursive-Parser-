package scanner;

import java.awt.*;
import javax.swing.*;
import java.util.*;
public class MyPanel extends JPanel{
    private JFrame mine;
    private ArrayList<JPanel> designs=new ArrayList<>();
    private ReadStatement readst;
    private OpStatement opst;
    private IfStatement ifst;
    private Identifier idst;
    private Constant constant;
    private AssignStatement asst;
    private RepeatStatement repst;
    private Connection connect;
    private WriteStatement wrst;
    private String[] symbols = {"+","-","*","/"};
    
    public MyPanel(JFrame mine,ArrayList<String> nodes) {
        int X=50;
        int Y=50;
        this.setBackground(Color.white);
        for(int i=0;i<nodes.size();i++)
        {
            String SelectedNode=nodes.get(i);
            if(SelectedNode.equals("read"))
            {
                designs.add(new ReadStatement(X, Y, nodes.get(i+1)));
            }
            else if(SelectedNode.equals("until"))
            {
            designs.add(new Connection(X+100, Y+35, X-50, Y+35));
             Y+=20;
            X+=50;
            designs.add(new OpStatement(X, Y,nodes.get(i+2)));
            X+=50;
            Y+=100;
            if(nodes.get(i+3).charAt(0)=='i')
                    {
                        designs.add(new Identifier(X,Y,nodes.get(i+3)));
                    }
                        else
                    {
                        designs.add(new Constant(X,Y,nodes.get(i+3)));
                    }
             X-=100;
             if(nodes.get(i+1).charAt(0)=='i')
                    {
                        designs.add(new Identifier(X,Y,nodes.get(i+1)));
                    }
                        else
                    {
                        designs.add(new Constant(X,Y,nodes.get(i+1)));
                    }
             Y-=370;
             X-=200;
            }
            else if(SelectedNode.equals("repeat"))
            {
                X+=100;
                designs.add(new RepeatStatement(X, Y));
                Y+=250;
                X-=350;
            }
            else if(SelectedNode.equals("else"))
            {
            designs.add(new Connection(X+100, Y+35, X-50, Y+35));    
            Y-=300;
            X-=600;
            }
            else if(SelectedNode.equals("then"))
            {    
            X-=100;    
            Y+=150;
            }
            else if(SelectedNode.equals("end"))
            {
             designs.add(new Connection(X+150, Y+35, X, Y+35));
            }
            else if(SelectedNode.equals("if"))
            {
        
            designs.add(new IfStatement(X, Y, nodes, i));
            Y+=120;
            X-=100;
            designs.add(new OpStatement(X, Y,nodes.get(i+2)));
            X+=50;
            Y+=100;
            if(nodes.get(i+3).charAt(0)=='i')
                    {
                        designs.add(new Identifier(X,Y,nodes.get(i+3)));
                    }
                        else
                    {
                        designs.add(new Constant(X,Y,nodes.get(i+3)));
                    }
             X-=100;
             if(nodes.get(i+1).charAt(0)=='i')
                    {
                        designs.add(new Identifier(X,Y,nodes.get(i+1)));
                    }
                        else
                    {
                        designs.add(new Constant(X,Y,nodes.get(i+1)));
                    }
            }
            else if(SelectedNode.equals(":="))
            {
              int temp=1;
                asst = new AssignStatement(X,Y,nodes.get(i-1));
                designs.add(asst);
                Y+=100;
                asst.children.add(nodes.get(i+1));
               if(!nodes.get(i+2).equals(";")){
                if(isoperator(nodes.get(i+2)))
                {   
                    int z = i+2;
                    while(!nodes.get(z).equals(";"))
                    {
                        asst.children.add(nodes.get(z));
                        z++;
                        if(z==nodes.size())
                            break;
                    }
                    int k=0;
                    boolean found = false; 
                    while(k<symbols.length)
                    {
                        for(int y =asst.children.size()-1;y>=0;y--)
                        {
                            found = false;
                            if(asst.children.get(y).equals(symbols[k]))
                            {
                                found =true;
                                designs.add(new OpStatement(X, Y,asst.children.get(y)));
                                X+=50;
                                Y+=100;
                                temp++;
                                if(asst.children.get(y+1).charAt(0)=='i'){
                                    designs.add(new Identifier(X,Y,asst.children.get(y+1)));
                                }
                                else{
                                    designs.add(new Constant(X,Y,asst.children.get(y+1)));
                                }
                                asst.children.set(y,"");
                                asst.children.set(y+1,"");
                                X-=100;
                                if(asst.children.get(1).equals(""))
                                {
                                    if(asst.children.get(0).charAt(0)=='i')
                                        designs.add(new Identifier(X,Y,asst.children.get(0)));
                                    else
                                        designs.add(new Constant(X,Y,asst.children.get(0)));
                             X-=50*(temp-2);
                                }
                            }
                        }
                        if(!found)
                            k++;
                    }
                }
                }
                else{
                    if(nodes.get(i+1).charAt(0)=='i')
                    {
                        designs.add(new Identifier(X,Y,asst.children.get(0)));
                    }
                        else
                    {
                        designs.add(new Constant(X,Y,asst.children.get(0)));
                    }
                  
                }
                Y-=100*temp;
            }
            else if(SelectedNode.equals("write"))
            {
                int temp=1;
                wrst = new WriteStatement(X,Y);
                designs.add(wrst);
                Y+=100;
                wrst.children.add(nodes.get(i+1));
               if(!nodes.get(i+2).equals("end")){
                if(isoperator(nodes.get(i+2)))
                {   
                    int z = i+2;
                    while(!nodes.get(z).equals(";"))
                    {
                        wrst.children.add(nodes.get(z));
                        z++;
                        if(z==nodes.size())
                            break;
                    }
                    int k=0;
                    boolean found = false; 
                    while(k<symbols.length)
                    {
                        for(int y =wrst.children.size()-1;y>=0;y--)
                        {
                            found = false;
                            if(wrst.children.get(y).equals(symbols[k]))
                            {
                                found =true;
                                designs.add(new OpStatement(X, Y,wrst.children.get(y)));
                                X+=50;
                                Y+=100;
                                temp++;
                                if(wrst.children.get(y+1).charAt(0)=='i'){
                                    designs.add(new Identifier(X,Y,wrst.children.get(y+1)));
                                }
                                else{
                                    designs.add(new Constant(X,Y,wrst.children.get(y+1)));
                                }
                                wrst.children.set(y,"");
                                wrst.children.set(y+1,"");
                                X-=100;
                                if(wrst.children.get(1).equals(""))
                                {
                                    if(wrst.children.get(0).charAt(0)=='i')
                                        designs.add(new Identifier(X,Y,wrst.children.get(0)));
                                    else
                                        designs.add(new Constant(X,Y,wrst.children.get(0)));
                              X-=50*(temp-2);
                                }
                            }
                        }
                        if(!found)
                            k++;
                    }
                }
                }
                else{
                    if(nodes.get(i+1).charAt(0)=='i')
                    {
                        designs.add(new Identifier(X,Y,wrst.children.get(0)));
                    }
                        else
                    {
                        designs.add(new Constant(X,Y,wrst.children.get(0)));
                    }
                  
                }
                Y-=100*temp;
            }
            X+=50;
           
        }
        this.mine=mine;
    }
    
    public boolean isoperator(String x)
    {
        if(x.equals("+")||x.equals("-")||x.equals("*")||x.equals("/"))
        {
            return true;
        }
        return false;
    }
    
    public boolean isaddition(String x)
    {
        if(x.equals("+")||x.equals("-"))
        {
            return true;
        }
        return false;
    }
    
    
    @Override
    public void paint(Graphics g) {
        super.paint(g); 
        for(int i=0;i<designs.size();i++)
        {
        if(designs.get(i) instanceof ReadStatement)
        {
        readst=(ReadStatement)designs.get(i);
        readst.draw(g);
        }
        else if(designs.get(i) instanceof IfStatement)
        {
        ifst=(IfStatement)designs.get(i);
        ifst.draw(g);
        }
        else if(designs.get(i) instanceof OpStatement)
        {
        opst=(OpStatement)designs.get(i);
        opst.draw(g);
        }
        else if(designs.get(i) instanceof Identifier)
        {
        idst=(Identifier)designs.get(i);
        idst.draw(g);
        }
        else if(designs.get(i) instanceof Constant)
        {
        constant=(Constant)designs.get(i);
        constant.draw(g);
        }
        else if(designs.get(i) instanceof AssignStatement)
        {
        asst=(AssignStatement)designs.get(i);
        asst.draw(g);
        }
        else if(designs.get(i) instanceof RepeatStatement)
        {
        repst=(RepeatStatement)designs.get(i);
        repst.draw(g);
        }
        else if(designs.get(i) instanceof Connection)
        {
        connect=(Connection)designs.get(i);
        connect.draw(g);
        }
        else if(designs.get(i) instanceof WriteStatement)
        {
        wrst=(WriteStatement)designs.get(i);
        wrst.draw(g);
        }
        }
        mine.repaint();
    }   
}