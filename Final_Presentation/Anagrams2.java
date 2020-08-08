public class Anagrams2 extends AnagramHelper
{
   public static void main(String[] args)
   {
      String[] words = generate();

      // print out the ungrouped array content first for comparison
      boxyPrinter("Ungrouped Array output:");
      prettyPrint(words);
   }
}