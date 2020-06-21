import ch02.stacks.LinkedStack;
import ch02.stacks.StackUnderflowException;
import support.LLNode;

public class PurrLinkedStack<T> extends LinkedStack<T>
{
  public T inspect(int index)
  // returns null if the index provided is NOT a valid location in the stack,
  // otherwise returns the value from the Nth position in the stack.
  {    
    if (index <= size() && index > 0)
    {
      LLNode<T> currNode = top;
      
      for(int i = 1; i < index; i++)
        currNode = currNode.getLink();
        
      return currNode.getInfo();
    }
    else
      return null;
  };
  
  public void popSome(int count)
  // Throws StackUnderFlowException if this stack is empty,
  // otherwise removes the top N elements from this stack.
  {
    int size = size();
    
    if (count <= size)
      for (int i = 1; i <= count; i++)
        top = top.getLink();
    else
      throw new StackUnderflowException("Cannot pop " + count + " elements from stack of size " + size);
  }
  
  public T poptop()
  // Throws StackUnderFlowException if this stack is empty,
  // otherwise returns AND removes top element of this stack.
  {
    if (size() > 0)
    {
      T result = top.getInfo();
      top = top.getLink();
      return result;
    }
    else
      throw new StackUnderflowException("Poptop attempted on an empty stack.");
  }

  public int size()
  // Returns the number of elements in this stack.
  {
    int count = 0;
    LLNode<T> currNode = top;
    
    while (currNode != null)
    {
      count++;
      currNode = currNode.getLink();
    }
    
    return count;
  };
  
  public boolean swapStart()
  // Swaps the first two elements at the top of this stack
  // and returns a boolean to indicate that elements were swapped.
  {
    boolean hasTwoElements = (size() >= 2);
    
    if (hasTwoElements)
    {
      T temp = top.getInfo();
      top.setInfo(top.getLink().getInfo());
      top.getLink().setInfo(temp);
    }
    
    return hasTwoElements;
  }

  public String toString()
  // Returns a string representation of this stack.
  {
    String result = (top != null) ? "Elements in the stack from top to bottom:\n-------------------------------------\n" : "No elements on stack.\n";
    
    LLNode<T> currNode = top;
    int position = 1;
    
    while (currNode != null)
    {
      result += ("Position " + position + " -> " + currNode.getInfo().toString() + "\n");
      currNode = currNode.getLink();
      position++;
    }
    
    return result;
  };
}