import java.util.*;

public class PurrTreeDriver
{
  public static void main(String[] args)
  {
    //----------------------------------
    //
    // Portion of Driver for Problem 29
    //
    //-----------------------------------
    boxyPrinter("Problem 29 (Min Value) Output:");

    // instantiate a tree1 object
    PurrTree<Integer> tree1 = new PurrTree<>();

    // add 12 random numbers between 1 and 100 to the tree
    randomAdd(tree1, 12, 100, true);

    // find the min value in the tree using the recursive min2 method
    System.out.println("\nMinimum value in tree1 is: " + tree1.min2() + "\n");

    //----------------------------------
    //
    // Portion of Driver for Problem 30
    //
    //-----------------------------------
    boxyPrinter("Problem 30 (Leaf Count) Output:");

    // instantiate a tree2 object
    PurrTree<Integer> tree2 = new PurrTree<>();

    // check leafCount on tree with no nodes
    System.out.println("Iterative leaf count on tree2 with no nodes: " + tree2.leafCount());
    System.out.println("Recursive leaf count on tree2 with no nodes: " + tree2.leafCount2() + "\n");

    // check leafCount on tree with one node
    tree2.add(100);
    System.out.println("Iterative leaf count on tree2 with one node: " + tree2.leafCount());
    System.out.println("Recursive leaf count on tree2 with one node: " + tree2.leafCount2() + "\n");

    // reassign tree2 to new PurrTree object and add values
    // in specific order to tree2 to control leaf count
    tree2 = new PurrTree<>();
    orderedAdd(tree2);

    // check iterative leaf count again in tree2 and make
    // sure it's equal to 5 based on insertion order above
    int itLeafTotal = tree2.leafCount();

    if (itLeafTotal == 5)
      System.out.println("Iterative leaf count on tree2 after ordered insertions: " + itLeafTotal);
    else
      System.out.println("[ERROR] - Something went wrong; iterative leaf count is not 5 - total is: " + itLeafTotal);

    // check recursive leaf count again in tree2 and make
    // sure it's equal to 5 based on insertion order above
    int recLeafTotal = tree2.leafCount2();

    if (recLeafTotal == 5)
      System.out.println("Recursive leaf count on tree2 after ordered insertions: " + recLeafTotal + "\n");
    else
      System.out.println("[ERROR] - Something went wrong; recursive leaf count is not 5 - total is: " + recLeafTotal + "\n");

    // final check - add a bunch of new random value and check leaf count afterward
    // add 100 more random numbers between 1 and 100 to the tree
    randomAdd(tree2, 100, 100, false);
    System.out.println("Iterative leaf count on tree2 after random insertions: " + tree2.leafCount());
    System.out.println("Recursive leaf count on tree2 after random insertions: " + tree2.leafCount2() + "\n");
  }
  
  private static void randomAdd(PurrTree<Integer> tree, int additions, int maxValue, boolean logOut)
  // helper method to add random integers to the tree
  {
    Random r = new Random();
    
    for (int i = 1; i <= additions; i++)
    {
      int n = r.nextInt(maxValue - 1) + 1;
      tree.add(n);
      
      if (logOut)
        System.out.println("Adding -> " + n);
    };
  };
  
  private static void orderedAdd(PurrTree<Integer> tree)
  // helper method to insert items into a tree in a controlled
  // fashion so the height is 5 and the leaf count is 5
  {
    int[] nums = {100, 60, 150, 40, 80, 120, 200, 20, 50, 10, 55, 5};

    for (int i = 0; i < nums.length; i++)
      tree.add(nums[i]);
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
