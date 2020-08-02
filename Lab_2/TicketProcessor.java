import ch04.queues.LinkedQueue;
import support.LLNode;

class TicketProcessor<T> extends Thread
{
   private static int timeoutPeriod = 5000;
   int ticketsAvailable = 10;
   LinkedQueue queue;
   
   public void addOrder(Order order)
   {
      queue.enqueue(order);
   }
   
   public void checkTickets()
   // Checks whether any orders are in the queue. If so, process all orders.
   {
      try { Thread.sleep(timeoutPeriod); } catch (Exception e) {}

      if (queue.isEmpty())
         System.out.println("No orders to process.");
      else
         processOrders();
      
      System.out.println(ticketsAvailable + " tickets remaining.");
      checkTickets();
   }
   
   public void processOrders()
   // Goes through all orders in queue, determines whether order is valid, removes appropriate number of tickets, and prints order details.
   // CURRENTLY NOT WORKING.
   {
      System.out.println(queue.dequeue());
      while (!queue.isEmpty())
      {
         Order order = (Order)queue.dequeue();
         order.setId("[PLACEHOLDER]");
         if ((ticketsAvailable - order.numTickets) >= 0)
         {
            ticketsAvailable -= order.numTickets;
            order.setStatus(true);
         }
         order.setProcessedTime("[PLACEHOLDER]");
         
         System.out.println("\nORDER PROCESSED");
         System.out.println(order + "\n\n\n");
      }
   }
   
   public void run()
   // Initializes thread
   {
      System.out.println("Initializing tix queue...");
      System.out.println(ticketsAvailable + " tickets available for order!");
      queue = new LinkedQueue();
      checkTickets();
   }
}