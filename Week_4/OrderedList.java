// helper class to return an ordered linked list of type Integer
// to be used with Program16, Program17 and Program18 classes
import support.LLNode;

public class OrderedList
{
  public static LLNode<Integer> generateOrderedList()
  // static helper method to generate the values linked
  // list that the book uses for the problem examples
  {
    int[] nums = {20, 19, 19, 18, 15, 12, 9, 6, 6, 3};
    LLNode<Integer> list = null;
    
    for(int i = 0; i < nums.length; i++)
    {
      LLNode<Integer> newNode = new LLNode<>(nums[i]);
      newNode.setLink(list);
      list = newNode;
    }
    
    return list;
  };
}