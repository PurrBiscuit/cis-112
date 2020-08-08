public class AnagramHelper
{
   public static String[] generate()
   {
      String[] words = {
         "quesadilla", "snare", "hello", "race", "alter", "warder", "care", "name",
         "cars", "stare", "arcs", "acre", "reward", "redraw", "taser", "saner",
         "parts", "drawer", "traps", "acme", "tears", "alert", "education", "mane",
         "break", "tier", "acer", "strap", "mace", "later", "rates", "nears", "mean",
         "warred", "cautioned", "rite", "came", "aster", "scars", "auctioned", "amen",
         "earns", "tire", "bat", "brake", "baker"
      };
      
      return words;
   }
   
   public static void prettyPrint(String[] a)
   {
      for (int i = 0; i < a.length; i++)
      {
         String trailing = ((i + 1) % 6 == 0) ? "\n" : padding(a[i], 14);
         System.out.print(a[i] + trailing);
      }
   }
   
   private static String padding(String s, int p)
   {
      int spacesToAdd = p - s.length();
      StringBuilder pad = new StringBuilder();
      
      for (int i = 1; i <= spacesToAdd; i++)
         pad.append(" ");
         
      return pad.toString();
   }
   
   
}