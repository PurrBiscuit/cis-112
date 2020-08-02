import ch04.queues.LinkedQueue;
import support.LLNode;
import java.util.Date;

class TicketProcessor extends Thread
{
   private static int timeoutPeriod = 10000;
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
         if (ticketsAvailable != 0)
         {
            try { Thread.sleep(timeoutPeriod); } catch (Exception e) {}

            if (queue.isEmpty())
            {
               System.out.println("No orders to process.");
               System.out.println(ticketsAvailable + " tickets remaining.");
            }
            else
               processOrders();

            checkTickets();
         }
         else
         {
            System.out.println("No more tickets available - ticket processing stopping...");
            stop = true;
         }
      }
   }
   
   public boolean isStopped()
   {
      return stop;
   }

   public void processOrders()
   // Goes through all orders in queue, determines whether order is valid, removes appropriate number of tickets, and prints order details.
   // CURRENTLY NOT WORKING.
   {
      while (!queue.isEmpty())
      {
         Order order = queue.dequeue();

         if ((ticketsAvailable - order.numTickets) >= 0)
         {
            ticketsAvailable -= order.numTickets;
            order.setStatus(true);
         }
         else
         {
            String trailing = "no tickets available.";

            if (ticketsAvailable == 1)
               trailing = "only 1 ticket available.";

            if (ticketsAvailable > 1)
               trailing = "only " + ticketsAvailable + " tickets available.";

            System.out.println("\n[WARN] - Could not process order for " + order.numTickets +
                               " tickets placed by " + order.name + "; " + trailing);
         }

         order.setProcessedTime(new Date());

         System.out.println("\n***** ORDER PROCESSED *****");
         System.out.println(order + "\n\n" + ticketsAvailable + " tickets remaining.");
      }
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