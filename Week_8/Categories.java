import java.util.Scanner;
import java.util.regex.*;
import java.io.*;

public class Categories
{
  public static void main(String[] args) throws IOException
  {
    BSTMap<String, String> reservedWords = new BSTMap<String, String>();
    addReservedWords(reservedWords);

    Scanner keyboard = new Scanner(System.in);

    System.out.println("*******************************");
    System.out.println(" Process File 1 Word at a Time ");      
    System.out.println("*******************************");
    System.out.println(" Program assumes files to read\n" + 
                       " are in same directory as\n" +
                       " the running Java program.\n");


    System.out.print("Enter the name of a file to read: ");
    String fileName = keyboard.nextLine();
    File f = new File(fileName);

    if(!f.exists())
    {
       System.out.println("File not found. Exiting....");
       return;
    }

    int counter = 0;

    Scanner inputFile = new Scanner(f);

    System.out.println();

    while(inputFile.hasNext())
    {
      String word = inputFile.next();

      Pattern pattern = Pattern.compile("\\w+");
      Matcher m = pattern.matcher(word);

      if (m.find() && !word.startsWith("\""))
      {
        String reserveWord = reservedWords.get(m.group());

        if (reserveWord != null)
        {
          System.out.println(m.group() + " -> " + reserveWord);
          counter++;
        }
      }
    }      
    
    System.out.println();
    System.out.println("Complete.");
    System.out.println(counter + " reserved words found. ");
 
    inputFile.close();
    keyboard.close();
  }

  public static void addReservedWords(BSTMap<String, String> m)
  {
    // reserved word categories from -> https://www.journaldev.com/33226/java-keywords
    String[][] reservedWords = {
      {"boolean", "byte", "char", "short", "int", "long", "float", "double", "void"},
      {"public", "protected", "private", "abstract", "static", "final", "transient", "volatile", "synchronized", "native"},
      {"class", "interface", "enum", "extends", "implements", "package", "throws"},
      {"if", "else", "try", "catch", "finally", "do", "while", "for", "continue", "break", "switch", "case", "default", "throw", "return"},
      {"this", "new", "super", "import", "instanceof", "null", "true", "false", "strictfp", "assert", "_", "goto", "const"}
    };

    for (int r = 0; r < reservedWords.length; r++)
      for (int c = 0; c < reservedWords[r].length; c++)
      {
        String cat = "primitive type";

        if (r == 1)
          cat = "modifier";
        else if (r == 2)
          cat = "declaration";
        else if (r == 3)
          cat = "control flow";
        else if (r == 4)
          cat = "miscellaneous";

        m.put(reservedWords[r][c], cat);
      }
  }
}