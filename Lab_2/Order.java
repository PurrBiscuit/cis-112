import java.util.UUID;

public class Order
{
   public String id, name, processedTime;
   public int numTickets;
   public boolean status;
   
   public Order(String name, int numTickets)
   {
      this.name = name;
      this.numTickets = numTickets;
      this.id = UUID.randomUUID().toString();
      this.status = false;
      this.processedTime = null;
   }
   
   public void setId(String id)
   {
      this.id = id;
   }
   
   public void setStatus(boolean status)
   {
      this.status = status;
   }
   
   public void setProcessedTime(String processedTime)
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