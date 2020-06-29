import support.LLNode;

public class Program17 extends OrderedList
{
  public static void main(String[] args)
  {
    LLNode<Integer> values = generateOrderedList();

    // try to find the number 10 in the linked list
    int num = 10;
    String found = contains(num, values) ? "found" : "NOT found";
    System.out.println(num + " was " + found + " in the linked list.");
    
    // try to find the number 15 in the linked list
    num = 15;
    found = contains(num, values) ? "found" : "NOT found";
    System.out.println(num + " was " + found + " in the linked list.");
  };
  
  public static boolean contains(int n, LLNode<Integer> head)
  // returns a boolean to indicate whether the value n
  // was found in the supplied head linked list variable
  {
    if (head == null)
      return false;
    
    return (head.getInfo() == n) ? true : contains(n, head.getLink());
  };
}