import support.LLNode;

public class Program18 extends OrderedList
{
  public static void main(String[] args)
  {
    LLNode<Integer> values = generateOrderedList();
    int num = 6;

    // call the remove method and store the result back to the values variable
    values = remove(num, values);
    
    System.out.println("Printing linked list after removing the number " + num + " from it.");
    
    // loop through the new, filtered, linked linked list
    // and print the results of it to the console
    LLNode<Integer> currNode = values;
    
    while (currNode != null)
    {
      System.out.println(currNode.getInfo());
      currNode = currNode.getLink();
    }
  };
  
  public static LLNode<Integer> remove(int n, LLNode<Integer> head)
  // returns a linked list with the n values removed from it
  // used a youtube video as a reference when creating this algorithm
  // can be found at -> https://www.youtube.com/watch?v=9v3a1ySOvow
  {
    // toggle this boolean to turn debug logging on/off
    boolean debugLogs = true;
  
    if (head == null)
      return null;
      
    if (head.getInfo() == n)
    {
      if (debugLogs)
        System.out.println("Match found for -> " + head.getInfo() + ".  Setting head ref to previous head ref.");

      head = remove(n, head.getLink());
      
      if (debugLogs)
        System.out.println("Skipped node since match found; head node still has a value of -> " + head.getInfo());
    }
    else
    {
      if (debugLogs)
        System.out.println("Match not found.  Linking previous head ref to new head ref." );
      
      LLNode<Integer> temp = remove(n, head.getLink());
      head.setLink(temp);
      
      if (debugLogs)
        System.out.println("Head node has a new value of -> " + head.getInfo());
    }
    
    if (debugLogs)
      System.out.println("Head value = " + head.getInfo() + " and ref - > " + head + "\n");

    return head;
  };
}