import ch04.queues.QueueUnderflowException;

public class CircularLinkedQueueDriver
{
  public static void main(String[] args)
  {
    CircularLinkedQueue<String> queue = new CircularLinkedQueue<>();
    
    // add some elements to the queue
    queue.enqueue("First Item");
    queue.enqueue("Second Item");
    queue.enqueue("Third Item");
    queue.enqueue("Fourth Item");
    
    // print a string representation of the queue to the console
    System.out.println(queue.toString());
    System.out.println("Queue's size is: " + queue.size() + "\n");
    
    // dequeue the first element from the queue
    System.out.println("Dequeuing an item from the queue..." + queue.dequeue() + "\n");
    
    // print the current state of the queue after the dequeue to the console
    System.out.println(queue.toString());
    System.out.println("Queue's size is: " + queue.size() + "\n");
    
    // add some more elements to the queue
    queue.enqueue("Fifth Item");
    queue.enqueue("Sixth Item");
    
    // print the current state of the queue after the dequeue to the console
    System.out.println(queue.toString());
    System.out.println("Queue's size is: " + queue.size() + "\n");
    
    // dequeue some more elements from the queue
    System.out.println("Dequeuing an item from the queue..." + queue.dequeue());
    System.out.println("Dequeuing an item from the queue..." + queue.dequeue());
    System.out.println("Dequeuing an item from the queue..." + queue.dequeue());
    System.out.println("Dequeuing an item from the queue..." + queue.dequeue() + "\n");
    
    // print the current state of the queue after the dequeue to the console
    System.out.println(queue.toString());
    System.out.println("Queue's size is: " + queue.size() + "\n");
    
    // dequeue the last element from the queue
    System.out.println("Dequeuing an item from the queue..." + queue.dequeue());
    
    // print the current state of the queue after the dequeue to the console
    System.out.println(queue.toString());
    System.out.println("Queue's size is: " + queue.size() + "\n");
    
    // try to dequeue an item from an empty queue
    try {
      System.out.println("Attempting to dequeue an item from an empty queue...");
      queue.dequeue();
    } catch (QueueUnderflowException e) {
      System.out.println("Caught exception: " + e.getMessage());
    }
  };
}