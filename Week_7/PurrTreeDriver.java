import java.util.*;

public class PurrTreeDriver
{
  // instantiate the PurrTree object
  private static PurrTree<Integer> purrTree = new PurrTree<>();
    
  public static void main(String[] args)
  {
    //----------------------------------
    //
    // Portion of Driver for Problem 29
    //
    //-----------------------------------

    boxyPrinter("Problem 29 Output:");
    
    // add 12 random numbers between 1 and 100 to the tree
    randomAdd(12, 100, true);
    
    // find the min value in the tree using the recursive min2 method
    System.out.println("\nMinimum value in tree is: " + purrTree.min2());
  }
  
  private static void randomAdd(int additions, int maxValue, boolean logOut)
  // helper method to add random integers to the tree
  {
    Random r = new Random();
    
    for (int i = 1; i <= additions; i++)
    {
      int n = r.nextInt(maxValue - 1) + 1;
      purrTree.add(n);
      
      if (logOut)
        System.out.println("Adding -> " + n);
    };
  };
  
  private static void boxyPrinter(String text)
  // helper method to print text to the console in a boxed format
  {
    int length = text.length();
    String result = "";
    
    for (int y = 0; y < 5; y++ )
    {
      result += "*";
      
      if (y != 2)
      {
        String s = (y == 0 || y == 4) ? "*" : " ";
        for (int x = 0; x < length + 2; x++)
          result += s;
      }
      else
        result += " " + text + " ";
      
      result += "*\n";
    }
    
    System.out.println(result);
  };
}