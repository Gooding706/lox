package jlox.tools;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

public class GenerateAst 
{
    public static void main(String[] args) throws IOException
    {
        if(args.length != 1)
        {
            System.err.println("Usage: java GenerateAst <output dir>");
            System.exit(1);
        }

        String outputDir = args[0];
        defineAst(outputDir, "Expr", Arrays.asList(
            "Binary : Expr left, Token operator, Expr right",
            "Grouping : Expr expression",
            "Literal : Object value",
            "Unary : Token operator, Expr right"
        ));
    }
    private static void defineAst(String outputDir, String baseName, List<String> types) throws IOException
    {
        String path = outputDir + '/' + baseName + ".java";
        PrintWriter pw = new PrintWriter(path, "UTF-8");

        pw.println("package jlox.src;");
        pw.println();
        pw.println("import java.util.List;");
        pw.println();
        pw.println("abstract class " + baseName + "{");

        for(String type : types)
        {
            String className = type.split(":")[0].trim();
            String fields = type.split(":")[1].trim();
            defineType(pw, baseName, className, fields);
        }

        pw.println("}");
        pw.close();
    }

    private static void defineType(PrintWriter writer, String baseName, String className, String fieldList)
    {
        writer.println("    static class " + className + " extends " + baseName + " {");
        writer.println("    " + className + "(" + fieldList + ") {");

        String[] fields =  fieldList.split(", ");
        for(String field : fields)
        {
            String name = field.split(" ")[1];
            writer.println("        this." + name + " = " + name + ";"); 
        }
        writer.println("    }");

        writer.println();
        for (String field : fields)
        {
            writer.println("    final " + field + ";");
        }
        writer.println("    }");
    }
}