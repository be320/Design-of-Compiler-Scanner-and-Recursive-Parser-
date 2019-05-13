package scanner;
import java.util.*;
import java.io.*;
public class MyParser {
    private MyScanner scanner;
    private ArrayList<String> taki=new ArrayList<>();
    private ArrayList<String> takiFlag=new ArrayList<>();
    private static int counter=0;
    private String selectedToken;
    private String selectedFlag;
    public ArrayList<String> outputs=new ArrayList<>();
    public ArrayList<String> nodes=new ArrayList<>();
    public ArrayList<String> nodesFlag=new ArrayList<>();
    public MyParser(MyScanner scanner)
    {
    this.scanner=scanner;
    }
    public void textFile()
    {
        try(BufferedWriter bw=new BufferedWriter(new FileWriter("parser_output.txt"))) {
    for(int i=0;i<outputs.size();i++)
            {
                bw.write(outputs.get(i));
                bw.newLine();
            }
        } catch (Exception e) {
            
        }
    
    }
    public void getTokens()
    {
     for(int i=0;i<scanner.tokens.size();i++)
    {
        Token token=scanner.getToken();
       taki.add(token.value);
       takiFlag.add(token.flag);
    }
     selectedToken=taki.get(counter);
     selectedFlag=takiFlag.get(counter);
    }
    public void print()
    {
    for(int i=0;i<scanner.tokens.size();i++)
    {
        System.out.println(takiFlag.get(i));
    }
    }
    public void output()
    {
    for(int i=0;i<outputs.size();i++)
    {
        System.out.println(outputs.get(i));
    }
    }
    public void match(String expectedToken)
    {
        nodes.add(selectedToken);
    if(selectedToken.equals(expectedToken))
    {
    outputs.add(expectedToken);
    counter++;    
    if(counter<taki.size()){
    selectedToken=taki.get(counter);
    selectedFlag=takiFlag.get(counter);
    }
    }
    else 
    {    
        outputs.add("Syntax Error: "+expectedToken+"  should be found but "+selectedToken+"  was found instead"); 
    }
    }
    
    public void program()
    {
        outputs.add("Program is found");
        stmt_sequence();        
    }
    
    public void stmt_sequence()
    {
        outputs.add("Stmt-Sequence is found");
            statement();
            while(selectedToken.equals(";"))
            {
                match(";");
                statement();
            }
    }
    
    public void statement()
    {
        outputs.add("Statement is found");
      if(selectedToken.equals("if"))
          if_stmt();
      else if(selectedToken.equals("repeat"))
          repeat_stmt();
      else if(selectedToken.equals("read"))
          read_stmt();
      else if(selectedToken.equals("write"))
          write_stmt();
      else if(selectedFlag.equals("Identifier"))
          assign_stmt();
      else{
      outputs.add("Syntax Error : No Appropriate Statement");
      }
            
    }
    
    public void if_stmt()
    {
        outputs.add("IF Statement found");
        match("if");
        exp();
        match("then");
        stmt_sequence();
        if(selectedToken.equals("else"))
        {
            match("else");
            stmt_sequence();
        }
        match("end");     
    }
    
    public void repeat_stmt()
    {
        outputs.add("Repeat Statement Found");
        match("repeat");
        stmt_sequence();
        match("until");
        exp(); 
    }
    
    public void assign_stmt()
    {
        outputs.add("Assign Statement found");
        //  nodes.add(":=");
      if(selectedFlag.equals("Identifier")){  
          nodes.add(selectedToken);
          outputs.add(selectedToken);
        counter++;    
       if(counter<taki.size()){ 
    selectedToken=taki.get(counter);
    selectedFlag=takiFlag.get(counter);
       }
      }
      else
          outputs.add("No Identifier found");
        match(":=");
    exp();
    
    }
    
    public void read_stmt()
    {
        outputs.add("Read Statement is found"); 
        match("read");
        if(selectedFlag.equals("Identifier")){ 
            nodes.add("i"+selectedToken);
            outputs.add(selectedToken);
        counter++;    
        if(counter<taki.size()){
    selectedToken=taki.get(counter);
    selectedFlag=takiFlag.get(counter);
        }
      }
        else{
            outputs.add("Syntax Error : Expecting Identifier");
            nodes.add("error");
        }
      
    }
    
    public void write_stmt()
    {
        outputs.add("Write Statement is found");
        match("write");
        exp();  
    }
    
    public void exp()
    {
        outputs.add("exp is found");
    simple_exp();
    if(selectedToken.equals("<")||selectedToken.equals("=")){
        comparison_op();
        simple_exp();
    }
    }
    
    public void comparison_op()
    {
        outputs.add("comparison-op is found");
    if(selectedToken.equals("<")){
        match("<");
    }
    else if(selectedToken.equals("=")){
        match("=");
    }
    else
        outputs.add("no comparison sign is found");
    }
    
    public void simple_exp()
    {
        outputs.add("simple-exp is found");
    term();
    while(selectedToken.equals("+")||selectedToken.equals("-"))
    {
    addop();
    term();
    }
    }
    
    public void addop()
    {
        outputs.add("addop is found");
    if(selectedToken.equals("+")){
        match("+");
    }
    else if(selectedToken.equals("-")){
        match("-");
    }
    else
        outputs.add("addop sign not found");
    }
    
    public void term()
    {
        outputs.add("term is found");
    factor();
    while(selectedToken.equals("*")||selectedToken.equals("/"))
    {
    mulop();
    factor();
    }
    }
    
    public void mulop()
    {
        outputs.add("mulop is found");
     if(selectedToken.equals("*")){
        match("*");
     }
    else if(selectedToken.equals("/"))
    {
    match("/");
    }
        
     else
        outputs.add("mulop sign not found");
    }
    
    public void factor()
    {
         outputs.add("factor is found");
    if(selectedToken.equals("("))
    {
        match("(");
        exp();
        match(")");
    }
    else if(selectedFlag.equals("Number"))
    {
        outputs.add(selectedToken);
        nodes.add(selectedToken);
    counter++;    
    if(counter<taki.size()){
    selectedToken=taki.get(counter);
    selectedFlag=takiFlag.get(counter);
    }
    }
    else if(selectedFlag.equals("Identifier"))
    {
        outputs.add(selectedToken);
        nodes.add("i"+selectedToken);
    counter++;   
    if(counter<taki.size()){
    selectedToken=taki.get(counter);
    selectedFlag=takiFlag.get(counter);
    }
    }
    else
    {
        outputs.add("Syntax Error : No Factor Found");
    }
    }
}
