package scanner;

public class RunningFile {
     public static void main(String[] args) {
    MyScanner scan=new MyScanner();
    MyParser parse=new MyParser(scan);
    scan.StartReading(scan.ReadTheCode());
    parse.getTokens();
    parse.program();
    parse.textFile();
    SyntaxTree mytree=new SyntaxTree(parse.nodes);
    mytree.setVisible(true);
//    for(int i=0;i<parse.nodes.size();i++)
//    {
//        System.out.println(parse.nodes.get(i));
//    }
    }
}
