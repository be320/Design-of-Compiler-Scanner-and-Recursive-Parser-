
package scanner;


public class Symbol {
    public String value;
    public String text;

    public Symbol(String value) {
        this.value=value;
        switch(value)
        {
            case "+":
            text="plus";
            break;
            case "-":
            text="minus";    
                break;
            case "*":
            text="multiply";    
                break;
            case "/":
            text="divide";    
                break;
            case "=":
            text="equal";    
                break;
            case "<":
            text="arrow";    
                break;
            case "(":
            text="left bracket";    
                break;
            case ")":
            text="right bracket";    
                break;
            case ";":
            text="semi-colon";    
                break;
            case ":=":  
            text="assignment";    
                break;
            default:
            text="Not Identified";
            break;
        }
    }
    
}
