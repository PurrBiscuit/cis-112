import ch04.queues.QueueUnderflowException;

public class PurrLinkedQueueDriver
{
  public static void main(String[] args)
  {
    PurrLinkedQueue<String> queue = new PurrLinkedQueue<>();
    
    // add some elements to the queue
    queue.enqueue("First Item");
    queue.enqueue("Second Item");
    queue.enqueue("Third Item");
    queue.enqueue("Fourth Item");
    
    // print a string representation of the queue to the console
    System.out.println(queue.toString());
    System.out.println("Queue's size is: " + queue.size() + "\n");
    
    // attempt to remove too many elements from the queue
    try {
      queue.remove(5);
    } catch (QueueUnderflowException e) {
      System.out.println("Caught exception: " + e.getMessage() + "\n");
    }
    
    // successfully remove some elements from the front of the queue
    int removeElements = 3;
    queue.remove(removeElements);
    
    // print the new state of the queue to the console
    System.out.println("State of the queue after removing " + removeElements + " elements from the queue:");
    System.out.println(queue.toString());
    System.out.println("Queue's size is: " + queue.size() + "\n");
    
    // attempt to swap the first two elements in a queue with only 1 element
    if (!queue.swapStart())
      System.out.println("Cannot swap first two elements in a queue that has less than two elements.");
    
    // attempt to swap the last two elements in a queue with only 1 element
    if (!queue.swapEnds())
      System.out.println("Cannot swap last two elements in a queue that has less than two elements.\n");
    
    // add some more elements to the queue
    queue.enqueue("Fifth Item");
    queue.enqueue("Sixth Item");
    queue.enqueue("Seventh Item");
    
    // print the new state of the queue to the console
    System.out.println(queue.toString());
    System.out.println("Queue's size is: " + queue.size() + "\n");
    
    // swap the starting values of the queue
    queue.swapStart();
    
    // print the new state of the queue to the console
    System.out.println("State of the queue after swapping the first two elements in the queue:");
    System.out.println(queue.toString());
    System.out.println("Queue's size is: " + queue.size() + "\n");
    
    // swap the starting values of the queue
    queue.swapEnds();
    
    // print the new state of the queue to the console
    System.out.println("State of the queue after swapping the last two elements in the queue:");
    System.out.println(queue.toString());
    System.out.println("Queue's size is: " + queue.size() + "\n");
  };
}