import ch04.queues.*;
import support.LLNode;

public class PurrLinkedQueue<T> extends LinkedQueue<T>
{
  public void remove(int count)
  // removes the front count elements from the queue.
  {
    if (count > numElements)
      throw new QueueUnderflowException("Cannot remove " + count + " elements from a queue with " + numElements + " elements.");
    
    for (int i = 1; i <= count; i++, numElements--)
      front = front.getLink();
  };
  
  public boolean swapEnds()
  // swap the last two elements in the queue if there are two
  // or more present and return true; return false otherwise
  {
    if (numElements <= 2)
      return swapStart();

    LLNode<T> secLast = front;
    for (int i = 1; i < numElements - 1; i++)
      secLast = secLast.getLink();
      
    rear.setLink(front.getLink());
    front.setLink(null);
    secLast.setLink(front);
    front = rear;
    rear = secLast.getLink();
    
    return true;
  };
  
  public boolean swapStart()
  // swap the first two elements in the queue if there are two
  // or more present and return true; return false otherwise
  {
    if (numElements < 2)
      return false;

    LLNode<T> temp = front.getLink();
    front.setLink(temp.getLink());
    temp.setLink(front);
    front = temp;
    
    if (numElements == 2)
      rear = front.getLink();
    
    return true;
  };
  
  public String toString()
  // Returns a string representation of the queue.
  {
    String result = (numElements > 0) ? "Elements in the queue from front to back:\n-----------------------------------------\n" : "No elements in the queue.\n";
    
    LLNode<T> currNode = front;
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