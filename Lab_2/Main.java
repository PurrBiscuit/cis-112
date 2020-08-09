import javax.swing.JOptionPane;

public class Main
{
   private static int ticketPrice = 50;
   
   private static String genericPrompt = "Enter a name and quantity for order separated by a colon.\n" +
                                        "Each ticket costs $" + ticketPrice + ".00.\n" +
                                        "Example: 'Michael: 3'.\n" +
                                        "Enter STOP to exit program.";

   private static TicketProcessor processor = new TicketProcessor();
   
   public static void main(String[] args)
   // main method to start the ticket processor thread
   // and the jOptionPane UI loop to take the orders
   {
      processor.start();
      takeOrders();
   }
   
   private static void userStop()
   // stops the processor thread if the user stops the processing
   {
      System.out.println("\n*********************************************\n" +
                         "Safely shutting down the ticket processor...\n" +
                         "Fulfilling last requests before exiting...\n" +
                         "*********************************************\n");
      processor.end();
   }

   private static void placeOrder(String name, int numTickets)
   // add a valid order to the ticket processor
   {
      processor.addOrder(new Order(name, numTickets, ticketPrice));
   }

   private static void takeOrders()
   // prompts user for order input and sends the order to the ticket
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