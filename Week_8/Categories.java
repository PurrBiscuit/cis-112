import java.util.Scanner;
import java.io.*;

public class Categories
{
  public static void main(String[] args) throws IOException
  {
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

    while(inputFile.hasNext())
    {
       String word = inputFile.next();
       System.out.println("Word is: " + word);      
       counter++;
    }      
    
    System.out.println();
    System.out.println("Complete.");
    System.out.println(counter +" words processed. ");    
 
    inputFile.close();
    keyboard.close();
  }
}