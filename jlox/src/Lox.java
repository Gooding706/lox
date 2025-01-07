package jlox.src;


import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Lox 
{   
    static private boolean hadError = false;

    public static void error(int line, String message)
    {
       report(line, "", message);
    }

    private static void report(int line, String where, String message)
    {
        System.err.println("[Error](line:" + line + ")\n" + where + "\n" + message);
        hadError = true;
    }

    private static void run(String source)
    {
        Lexer lexer = new Lexer(source);
        List<Token> tokens = lexer.scanSource();

        for(Token t : tokens)
        {
            System.out.println(t.toString());
        }
    }

    private static void runFile(String pathString) throws IOException
    {
        //read bytes
        byte[] bytes = Files.readAllBytes(Paths.get(pathString));
        //run with string created from bytes
        run(new String(bytes, Charset.defaultCharset()));
        if (hadError) System.exit(1);
    }   

    public static void runPrompt()
    {
        Scanner scanner = new Scanner(System.in);
        String lineBuffer = new String();

        while(true)
        {
            System.out.print("> ");
            lineBuffer = scanner.nextLine();
            if (lineBuffer == null) break;

            run(lineBuffer);
            hadError = false;
        }
        scanner.close();
    }

     public static void main(String[] args) throws IOException
    {
        if(args.length > 1)
        {
            System.out.println("lox script ;p");
            System.exit(64);
        }else if(args.length == 1)
        {
            runFile(args[0]);
        }else{
            runPrompt();
        }
    }   
}