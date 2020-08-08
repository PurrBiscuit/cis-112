public class Anagrams1 extends AnagramHelper
{
   public static void main(String[] args)
   {
      String[] words = generate();
      
      boxyPrinter("Ungrouped Array output:");
      prettyPrint(words);
   }
}