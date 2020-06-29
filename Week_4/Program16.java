import support.LLNode;

public class Program16 extends OrderedList
{
  public static void main(String[] args)
  {
    LLNode<Integer> values = generateOrderedList();
    
    // print out result of even number count method
    System.out.println("Number of even numbers in the list = " + numEvens(values));
  };
  
  public static int numEvens(LLNode<Integer> head)
  // returns the number of even numbers found in a linked list
  {
    if (head == null)
      return 0;

    int countAsEven = (head.getInfo() % 2 == 0) ? 1 : 0;
    return countAsEven + numEvens(head.getLink());
  };
}