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
    int commentCounter = 0;

    Scanner inputFile = new Scanner(f);

    System.out.println();

    while(inputFile.hasNext())
    {
      String word = inputFile.next();

      // look for input files matches that start with // to indicate a comment line
      if (word.startsWith("//"))
      {
        // increment the comment line counter and skip to the next line in the file
        commentCounter++;
        inputFile.nextLine();
      }
      else
      {
        // regex matcher to look for word characters only
        Pattern pattern = Pattern.compile("\\w+");
        Matcher m = pattern.matcher(word);

        // check if a match was found and if the word doesn't start with a
        // double quote which indicates it's a string literal
        if (m.find() && !word.startsWith("\""))
        {
          // look the matched word up in the reservedWords map
          String reserveWord = reservedWords.get(m.group());

          // if a value was returned for the key that wasn't null
          // then print it to the console and increment the counter
          if (reserveWord != null)
          {
            System.out.println(m.group() + " -> " + reserveWord);
            counter++;
          }
        }
      }
    }      

    // print the results of the file scanning to the console
    System.out.println("\n\n**** Complete - Results: ****\n-----------------------------\n");
    System.out.println("-> " + counter + " reserved words found.");
    System.out.println("-> " + commentCounter + " comment lines skipped.");
 
    inputFile.close();
    keyboard.close();
  }

  public static void addReservedWords(BSTMap<String, String> m)
  // helper method to insert the reserved words key-value pairs into the map
  {
    // reserved word categories from -> https://www.journaldev.com/33226/java-keywords
    String[][] reservedWords = {
      {"boolean", "byte", "char", "short", "int", "long", "float", "double", "void"}, // primitive types
      {"public", "protected", "private", "abstract", "static", "final", "transient", "volatile", "synchronized", "native"}, // modifiers
      {"class", "interface", "enum", "extends", "implements", "package", "throws"}, // declarations
      {"if", "else", "try", "catch", "finally", "do", "while", "for", "continue", "break", "switch", "case", "default", "throw", "return"}, // control flow
      {"this", "new", "super", "import", "instanceof", "null", "true", "false", "strictfp", "assert", "_", "goto", "const"} // miscellaneous
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