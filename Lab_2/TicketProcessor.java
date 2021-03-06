import ch04.queues.LinkedQueue;
import support.LLNode;
import java.util.Date;

class TicketProcessor extends Thread
{
   private static int timeoutPeriod = 10000;
   int moneyMade = 0;
   int ticketsAvailable = 10;
   LinkedQueue<Order> queue;
   private volatile boolean stop = false;
   
   public void addOrder(Order order)
   {
      queue.enqueue(order);
   }

   public void end()
   // sets the stop variable to true to stop the thread processing loop
   {
      stop = true;
   }

   public void checkTickets()
   // Checks whether any orders are in the queue. If so, process all orders.
   // will stop the thread if there are no more tickets available as well.
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
               System.out.println("$" + moneyMade + ".00 earned so far.");
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
   // goes through all orders in queue, determines whether order is valid,
   // removes appropriate number of tickets, and prints order details.
   {
      while (!queue.isEmpty())
      {
         Order order = queue.dequeue();

         if ((ticketsAvailable - order.getNumTickets()) >= 0)
         {
            ticketsAvailable -= order.getNumTickets();
            order.setStatus(true);
            moneyMade += order.getPrice() * order.getNumTickets();
         }
         else
         {
            String trailing = "no tickets available.";

            if (ticketsAvailable == 1)
               trailing = "only 1 ticket available.";

            if (ticketsAvailable > 1)
               trailing = "only " + ticketsAvailable + " tickets available.";

            System.out.println("\n[WARN] - Could not process order for " + order.getNumTickets() +
                               " tickets placed by " + order.getName() + "; " + trailing);
         }

         order.setProcessedTime(new Date());

         String processedStatus = order.getStatus() ? "ORDER PROCESSED" : "ORDER NOT PROCESSED";

         System.out.println("\n***** " + processedStatus + " *****");
         System.out.println(order + "\n\n" + ticketsAvailable + " tickets remaining.");
         System.out.println("$" + moneyMade + ".00 earned so far.");
      }
   }
   
   public void run()
   // initializes thread
   {
      System.out.println("Initializing tix queue...");
      System.out.println(ticketsAvailable + " tickets available for order!");
      queue = new LinkedQueue<>();
      checkTickets();
   }
}