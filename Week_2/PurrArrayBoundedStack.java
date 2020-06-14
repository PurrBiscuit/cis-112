import ch02.stacks.ArrayBoundedStack;
import ch02.stacks.StackUnderflowException;

public class PurrArrayBoundedStack<T> extends ArrayBoundedStack<T>
{
  public PurrArrayBoundedStack(int maxSize) 
  {
    super(maxSize);
  }
  
  public void popSome(int count)
  // Throws StackUnderFlowException if this stack is empty,
  // otherwise removes the top N elements from this stack.
  {
    int size = size();
    
    if (count <= size)
    {
      for (int i = 1; i <= count; i++)
      {
        elements[topIndex] = null;
        topIndex--;
      } 
    }
    else
      throw new StackUnderflowException("Cannot pop " + count + " elements from stack of size " + size);
  }
  
  public T poptop()
  // Throws StackUnderFlowException if this stack is empty,
  // otherwise returns AND removes top element of this stack.
  {
    if (size() > 0)
    {
      T result = elements[topIndex];
      elements[topIndex] = null;
      topIndex--;
      return result;
    }
    else
      throw new StackUnderflowException("Poptop attempted on an empty stack.");
  }
  
  public int size()
  // Returns the number of elements in this stack.
  {
    return topIndex + 1;
  }
  
  public boolean swapStart()
  // Swaps the first two elements at the top of this stack
  // and returns a boolean to indicate that elements were swapped.
  {
    boolean hasTwoElements = (size() >= 2);
    
    if (hasTwoElements)
    {
      T temp = elements[topIndex];
      elements[topIndex] = elements[topIndex - 1];
      elements[topIndex - 1] = temp;
    }
    
    return hasTwoElements;
  }

  public String toString()
  // Returns a string representation of this stack.
  {
    String result = (size() != 0) ? "Elements in the stack from top to bottom:\n-------------------------------------\n" : "No elements on stack.\n";
    
    for (int i = topIndex; i >= 0; i--)
        result += ("Index " + i + " -> " + elements[i].toString() + "\n");
    
    return result;
  };
}