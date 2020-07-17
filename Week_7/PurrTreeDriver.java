import java.util.*;

public class PurrTreeDriver
{
  public static void main(String[] args)
  {
    //----------------------------------
    //
    // Portion of Driver for Problem 29
    //
    //----------------------------------
    boxyPrinter("Problem 29 (Min Value) Output:");

    // instantiate a tree object
    PurrTree<Integer> tree = new PurrTree<>();

    // add 12 random numbers between 1 and 100 to the tree
    randomAdd(tree, 12, 100, true);

    // find the min value in the tree using the recursive min2 method
    System.out.println("\nMinimum value in tree is: " + tree.min2() + "\n");

    //----------------------------------
    //
    // Portion of Driver for Problem 30
    //
    //----------------------------------
    boxyPrinter("Problem 30 (Leaf Count) Output:");

    // instantiate new object to the existing tree ref var to start fresh
    tree = new PurrTree<>();

    // check leafCount on tree with no nodes
    System.out.println("Iterative leaf count on tree with no nodes: " + tree.leafCount());
    System.out.println("Recursive leaf count on tree with no nodes: " + tree.leafCount2() + "\n");

    // check leafCount on tree with one node
    tree.add(100);
    System.out.println("Iterative leaf count on tree with one node: " + tree.leafCount());
    System.out.println("Recursive leaf count on tree with one node: " + tree.leafCount2() + "\n");

    // reassign tree to new PurrTree object and add values
    // in specific order to tree to control leaf count
    tree = new PurrTree<>();
    orderedAdd(tree);

    // check iterative leaf count again in tree and make
    // sure it's equal to 5 based on insertion order above
    int itLeafTotal = tree.leafCount();

    if (itLeafTotal == 5)
      System.out.println("Iterative leaf count on tree after ordered insertions: " + itLeafTotal);
    else
      System.out.println("[ERROR] - Something went wrong; iterative leaf count is not 5 - total is: " + itLeafTotal);

    // check recursive leaf count again in tree and make
    // sure it's equal to 5 based on insertion order above
    int recLeafTotal = tree.leafCount2();

    if (recLeafTotal == 5)
      System.out.println("Recursive leaf count on tree after ordered insertions: " + recLeafTotal + "\n");
    else
      System.out.println("[ERROR] - Something went wrong; recursive leaf count is not 5 - total is: " + recLeafTotal + "\n");

    // final check - add a bunch of new random value and check leaf count afterward
    // add 100 more random numbers between 1 and 100 to the tree
    randomAdd(tree, 100, 100, false);
    System.out.println("Iterative leaf count on tree after random insertions: " + tree.leafCount());
    System.out.println("Recursive leaf count on tree after random insertions: " + tree.leafCount2() + "\n");

    //----------------------------------
    //
    // Portion of Driver for Problem 32
    //
    //----------------------------------
    boxyPrinter("Problem 32 (Height) Output:");

    // instantiate new object to the existing tree ref var to start fresh
    tree = new PurrTree<>();

    // check height on tree with no nodes
    System.out.println("Iterative height on tree with no nodes: " + tree.height());
    System.out.println("Recursive height on tree with no nodes: " + tree.height2() + "\n");

    // check height on tree with one node
    tree.add(100);
    System.out.println("Iterative height on tree with one node: " + tree.height());
    System.out.println("Recursive height on tree with one node: " + tree.height2() + "\n");

    // reassign tree to new PurrTree object and add values
    // in specific order to tree to control height
    tree = new PurrTree<>();
    orderedAdd(tree);

    // check iterative height again in tree and make
    // sure it's equal to 5 based on insertion order above
    int itHeight = tree.height();

    if (itHeight == 5)
      System.out.println("Iterative height on tree after ordered insertions: " + itHeight);
    else
      System.out.println("[ERROR] - Something went wrong; iterative height is not 5 - total is: " + itHeight);

    // check recursive height again in tree and make sure
    // it's equal to 5 based on insertion order above
    int recHeight = tree.height2();

    if (recHeight == 5)
      System.out.println("Recursive height on tree after ordered insertions: " + recHeight + "\n");
    else
      System.out.println("[ERROR] - Something went wrong; recursive height is not 5 - total is: " + recHeight + "\n");

    // final check - add a bunch of new random value and check height afterward
    // add 100 more random numbers between 1 and 100 to the tree
    randomAdd(tree, 100, 100, false);
   System.out.println("Iterative height on tree after random insertions: " + tree.height());
    System.out.println("Recursive height on tree after random insertions: " + tree.height2() + "\n");
  }
  
  private static void randomAdd(PurrTree<Integer> tree, int additions, int maxValue, boolean logOut)
  // helper method to add random integers to the tree
  {
    Random r = new Random();
    
    for (int i = 1; i <= additions; i++)
    {
      int n = r.nextInt(maxValue) + 1;
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
