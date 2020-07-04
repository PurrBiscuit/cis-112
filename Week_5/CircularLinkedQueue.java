import ch04.queues.*;
import support.LLNode;

public class CircularLinkedQueue<T> implements QueueInterface<T>
{
  protected LLNode<T> rear;      // reference to the rear of this queue
  protected int numElements = 0; // number of elements in this queue

  public CircularLinkedQueue()
  {
    rear = null;
  }

  public void enqueue(T element)
  // Adds element to the rear of this queue.
  { 
    LLNode<T> newNode = new LLNode<T>(element);
    
    if (isEmpty())
      rear = newNode;
    else
    {
      LLNode<T> front = getFront();
      
      rear.setLink(newNode);     
      newNode.setLink(front);   
      rear = newNode;
    }

    numElements++;
  }     

  public T dequeue()
  // Throws QueueUnderflowException if this queue is empty;
  // otherwise, removes front element from this queue and returns it.
  {
    if (isEmpty())
      throw new QueueUnderflowException("Dequeue attempted on empty queue.");
    else
    {
      LLNode<T> front = getFront();
      T element = front.getInfo();
      
      if (rear.getLink() == null)
        rear = null;
      else if (front.getLink() == rear)
        rear.setLink(null);
      else
        rear.setLink(front.getLink());
      
      numElements--;
      return element;
    }
  }
  
  public LLNode<T> getFront()
  {
    if (isEmpty())
      return null;
  
    return (rear.getLink() == null) ? rear : rear.getLink();
  }

  public boolean isEmpty()
  // Returns true if this queue is empty; otherwise, returns false.
  {              
    return (rear == null);
  }
  
  public boolean isFull()
  // Returns false - a linked queue is never full.
  {              
    return false;
  }

  public int size()
  // Returns the number of elements in this queue.
  {
    return numElements;
  }

  public String toString()
  // Returns a string representation of the queue.
  {
    String result = "No elements in the queue.\n";
    
    if (!isEmpty())
    {
      LLNode<T> front = getFront();
      result = "Elements in the queue from front to back:\n-----------------------------------------\n";
    
      for (int i = 1; i <= numElements; i++)
      {
        result += ("Position " + i + " -> " + front.getInfo().toString() + "\n");
        front = front.getLink();
      }
    }
    
    return result;
  };
}