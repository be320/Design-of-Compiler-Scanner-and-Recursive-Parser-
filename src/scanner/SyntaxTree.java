
package scanner;
import java.awt.*;
import javax.swing.*;
import java.util.*;

public class SyntaxTree extends JFrame{
    private Container c;
    private Toolkit tk = Toolkit.getDefaultToolkit();
    private Dimension d = tk.getScreenSize();
    private MyPanel panel;
    JScrollPane scrollPane ;

    public SyntaxTree(ArrayList<String> nodes)  {
        c = this.getContentPane();
        c.setLayout(null);
        panel=new MyPanel(this,nodes); 
        panel.setPreferredSize(new Dimension(10000, 10000));
        scrollPane = new JScrollPane(panel);
        scrollPane.setBounds(0,0,d.width,d.height-80);
        this.add(scrollPane); 
        this.setBounds(0, 0,d.width, d.height);
        this.setTitle("Syntax Tree");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
    }
    
}
