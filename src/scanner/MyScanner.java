package scanner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class MyScanner {
    private String MyAllCode="";
    public ArrayList<Token> tokens=new ArrayList<>();
    private ArrayList<String> reservedWords = new ArrayList<>();
    private ArrayList<String> Ids=new ArrayList<>();
    private ArrayList<Symbol> symbols=new ArrayList<>();
    private ArrayList<String> reserved=new ArrayList<>();
    private ArrayList<String> numbers=new ArrayList<>();
    private static int tokenCounter=-1;
    public MyScanner() {
        initReserved();
    }
    public void initReserved() {
        reservedWords.add("if");
        reservedWords.add("then");
        reservedWords.add("else");
        reservedWords.add("end");
        reservedWords.add("repeat");
        reservedWords.add("until");
        reservedWords.add("read");
        reservedWords.add("write");
    }
    public String ReadTheCode()
    {
      try {
            FileReader usersfile = new FileReader("myCode.txt");
            BufferedReader userbuffer = new BufferedReader(usersfile);
            String str;
            while ((str = userbuffer.readLine()) != null) {
                MyAllCode+=" ";
                MyAllCode+=str;    
            }
            userbuffer.close();
        } catch (IOException e) {
            System.out.println("Error");
        }
      return MyAllCode;
    }
    
    public Token getToken() {
        tokenCounter++;
        if(tokenCounter==tokens.size())
            return null;
        Token selected=tokens.get(tokenCounter);
        return selected;
    }

    public boolean IsLetter(char ch) {
        return Character.isLetter(ch);
    }

    public boolean IsDigit(char ch) {
        return Character.isDigit(ch);
    }

    public void StartReading(String str) {
        if (IsLetter(str.charAt(0))) 
            addIdentifier(str);
        else if (IsDigit(str.charAt(0))) 
            getNumber(str);
         else if (str.charAt(0) == '{') {
            addComment(str);
         }
        else if(str.charAt(0) == ' ')
        {
        str=str.substring(1);
            StartReading(str);
        }
        else
        {
            addSymbol(str);
        }
    }

    public void addIdentifier(String str) {
        String id="";
        int i;
        for (i=0; i < str.length(); i++) {
         id = str.substring(0, i);
            if (!IsLetter(str.charAt(i))){
                break;
            }
            else if(i==str.length()-1)
            {
            id = str.substring(0, i+1);
            }
        }
        if(reservedWords.contains(id))
        {
        reserved.add(id);
        tokens.add(new Token(id, "Reserved Word"));
        }
        else
        {
        Ids.add(id);
        tokens.add(new Token(id, "Identifier"));
        }
        str=str.substring(i);
        if(!str.equals(""))
        StartReading(str);
    }

    public void getNumber(String str) {
        String num="";
        int i;
        for (i=0; i < str.length(); i++) {
         num = str.substring(0, i);
            if (!IsDigit(str.charAt(i))){
                break;
            }
            else if(i==str.length()-1)
            {
            num = str.substring(0, i+1);
            }
        }
        numbers.add(num);
        tokens.add(new Token(num, "Number"));
        str=str.substring(i);
        if(!str.equals(""))
        StartReading(str);
    }

    public void addComment(String str) {
        String lb = str.charAt(0)+"";
        String rb="";
        String comment ="";
        for (int i = 0; i < str.length()-1; i++) {
            if (str.charAt(i) == '}') {
                comment=str.substring(1, i);
                rb = str.substring(i,i+1);
                str=str.substring(i+1);
                break;
            }
        }

        if(!str.equals(""))
        StartReading(str);
    }

    public void addSymbol(String str)
    {
    Symbol mysymbol;
    if(str.charAt(0)==':'&&str.charAt(1)=='=')
    {
    mysymbol=new Symbol(str.substring(0,2));
    str=str.substring(2);
    }
    else 
    {
    mysymbol=new Symbol(str.substring(0,1));
    str=str.substring(1);
    }
    symbols.add(mysymbol);
    tokens.add(new Token(mysymbol.value, mysymbol.text));
    if(!str.equals(""))
    StartReading(str);
    }
    
}
