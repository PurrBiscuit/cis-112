public class PurrArrayBoundedStackDriver
{
  public static void main(String[] args)
  {
    // instantiate the stack
    PurrArrayBoundedStack<String> stack = new PurrArrayBoundedStack<>(10);
    
    // Add some test elements to the stack
    stack.push("ItemOne");
    stack.push("ItemTwo");
    stack.push("ItemThree");
    stack.push("ItemFour");
    stack.push("ItemFive");
    stack.push("ItemSix");
    
    // print out some information about the current state of the stack
    System.out.println("There are " + stack.size() + " items in the stack.");
    System.out.println(stack.toString());
    
    // pop a group of elements off the stack
    int popCount = 4;
    System.out.println("INFO: Popping " + popCount + " items off the stack.");
    stack.popSome(popCount);
    
    // print out the new current state of the stack
    System.out.println("There are now " + stack.size() + " items in the stack after the popSome.");
    System.out.println(stack.toString());
    
    // attempt to swap the first two elements of the stack
    if (stack.swapStart())
    {
      // print out new state of the stack if it was successful
      System.out.println("INFO: Swapped the first two elements in the stack.");
      System.out.println(stack.toString());
    }
    else
    {
      // print out warning message that the swap operation was not successful
      System.out.println("WARN: Not enough elements in the stack to do a swap.\n");
    }
    
    // execute the poptop method on the stack and print the result
    System.out.println("INFO: Poptop returned the following item from stack: " + stack.poptop() + "\n");
    
    // print the final state of the stack after all the above operations
    System.out.println("Printing the FINAL state of the stack.");
    System.out.println("There are " + stack.size() + " items remaining on the stack.");
    System.out.println(stack.toString());
  }
}