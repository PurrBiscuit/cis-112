import javax.swing.JOptionPane;

public class Main
{
   public static String genericPrompt = "Enter a name and quantity for order separated by a colon.\n" +
                                        "Example: 'Michael: 3'.\n" +
                                        "Enter STOP to exit program.";

   public static TicketProcessor processor = new TicketProcessor();
   
   
   public static void main(String[] args)
   {
      processor.start();
      takeOrder();
   }
   
   public static void exit()
   {
      System.out.println("Safely shutting down the ticket processor...fulfilling last requests before exiting...");
      processor.end();
      System.out.println("Exiting program.");
   }

   public static void placeOrder(String name, int numTickets)
   // Eventually, this will call the second thread.
   {
      processor.addOrder(new Order(name, numTickets));
   }

   public static void takeOrder()
   // Takes order string and passes to parseOrder method.
   {
      String prompt = genericPrompt;
      
      String input;
      boolean invalid = false;

      do
      {
         if (invalid)
            prompt = "Invalid entry. Please try again.\n" + prompt;

         input = JOptionPane.showInputDialog(prompt).replaceAll(" ", "");

         if (input.matches("([A-Za-z])+:([0-9])+"))
         {
            String name = input.split(":")[0];
            int numTickets = Integer.parseInt(input.split(":")[1]);
            placeOrder(name, numTickets);
            invalid = false;
         }
         else
            invalid = true;
      } while (!input.toLowerCase().equals("stop"));

      exit();
   }
}