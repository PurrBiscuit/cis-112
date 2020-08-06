import java.util.UUID;
import java.util.Date;

public class Order
{
   private String id, name;
   private int numTickets;
   private boolean status;
   private Date processedTime;
   
   public Order(String name, int numTickets)
   {
      this.name = name;
      this.numTickets = numTickets;
      this.id = UUID.randomUUID().toString();
      this.status = false;
      this.processedTime = null;
   }
   
   public String getName()
   {
      return this.name;
   }

   public int getNumTickets()
   {
      return this.numTickets;
   }
   
   public boolean getStatus()
   {
      return this.status;
   }

   public void setStatus(boolean status)
   {
      this.status = status;
   }
   
   public void setProcessedTime(Date processedTime)
   {
      this.processedTime = processedTime;
   }
   
   public String toString()
   {
      return "Order ID: " + id + "\n" +
             "Name: " + name + "\n" + 
             "Quantity: " + numTickets + "\n" +
             "Status: " + status + "\n" +
             "Processed: " + processedTime;
   }
}
