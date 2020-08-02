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
      takeOrder(false);
   }
   
   public static void parseOrder(String input)
   // Validates and parses the order string.
   {
      boolean invalid = false;
      
      if (input.matches("([A-Za-z])+:([0-9])+"))
      {
         String name = input.split(":")[0];
         int numTickets = Integer.parseInt(input.split(":")[1]);
         placeOrder(name, numTickets);
      }
      else
         invalid = true;
      
      takeOrder(invalid);
   }
   
   public static void placeOrder(String name, int numTickets)
   // Eventually, this will call the second thread.
   {
      processor.addOrder(new Order(name, numTickets));
   }
   
   public static void takeOrder(boolean previousInvalid)
   // Takes order string and passes to parseOrder method.
   {
      String prompt = genericPrompt;
      
      if (previousInvalid)
         prompt = "Invalid entry. Please try again.\n" + prompt;
      
      String input = JOptionPane.showInputDialog(prompt).replaceAll(" ", "");
      
      parseOrder(input);
   }
}