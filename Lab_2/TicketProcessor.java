import ch04.queues.LinkedQueue;
import support.LLNode;
import java.util.Date;

class TicketProcessor extends Thread
{
   private static int timeoutPeriod = 5000;
   int ticketsAvailable = 10;
   LinkedQueue<Order> queue;
   private volatile boolean stop = false;
   
   public void addOrder(Order order)
   {
      queue.enqueue(order);
   }
   
   public void end()
   {
      stop = true;
   }

   public void checkTickets()
   // Checks whether any orders are in the queue. If so, process all orders.
   {
      while (!stop)
      {
         try { Thread.sleep(timeoutPeriod); } catch (Exception e) {}

         if (queue.isEmpty())
            System.out.println("No orders to process.");
         else
            processOrders();

         System.out.println(ticketsAvailable + " tickets remaining.");
         checkTickets();
      }
   }
   
   public void processOrders()
   // Goes through all orders in queue, determines whether order is valid, removes appropriate number of tickets, and prints order details.
   // CURRENTLY NOT WORKING.
   {
      Order order = queue.dequeue();

      if ((ticketsAvailable - order.numTickets) >= 0)
      {
         ticketsAvailable -= order.numTickets;
         order.setStatus(true);
      }
      order.setProcessedTime(new Date());

      System.out.println("\nORDER PROCESSED");
      System.out.println(order + "\n");
   }
   
   public void run()
   // Initializes thread
   {
      System.out.println("Initializing tix queue...");
      System.out.println(ticketsAvailable + " tickets available for order!");
      queue = new LinkedQueue<>();
      checkTickets();
   }
}