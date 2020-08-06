import javax.swing.JOptionPane;

public class Main
{
   public static int ticketPrice = 50;
   
   public static String genericPrompt = "Enter a name and quantity for order separated by a colon.\n" +
                                        "Each ticket costs $" + ticketPrice + ".00.\n" +
                                        "Example: 'Michael: 3'.\n" +
                                        "Enter STOP to exit program.";

   public static TicketProcessor processor = new TicketProcessor();
   
   public static void main(String[] args)
   {
      processor.start();
      takeOrders();
   }
   
   public static void userStop()
   {
      System.out.println("\n*********************************************\n" +
                         "Safely shutting down the ticket processor...\n" +
                         "Fulfilling last requests before exiting...\n" +
                         "*********************************************\n");
      processor.end();
   }

   public static void placeOrder(String name, int numTickets)
   // Eventually, this will call the second thread.
   {
      processor.addOrder(new Order(name, numTickets, ticketPrice));
   }

   public static void takeOrders()
   // Prompts user for order input sends the order to the ticket
   // processor if it's valid; allows user to stop processor as well.
   {
      String prompt = genericPrompt;
      String input;
      boolean invalid = false;

      do
      {
         if (invalid && !prompt.contains("Invalid"))
            prompt = "Invalid entry. Please try again.\n" + prompt;

         input = JOptionPane.showInputDialog(prompt);
         input = input == null ? "stop" : input.replaceAll(" ", "");

         if (input.matches("([A-Za-z])+:([0-9])+"))
         {
            String name = input.split(":")[0];
            int numTickets = Integer.parseInt(input.split(":")[1]);
            placeOrder(name, numTickets);
            invalid = false;
            prompt = genericPrompt;
         }
         else
            invalid = true;
      } while (!input.toLowerCase().equals("stop") && !processor.isStopped());

      if (input.toLowerCase().equals("stop") && !processor.isStopped())
         userStop();
   }
}