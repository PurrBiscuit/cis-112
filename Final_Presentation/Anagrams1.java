import java.util.Arrays;
import java.util.Comparator;

public class Anagrams1 extends AnagramHelper
{
   static class AnagramComparator implements Comparator<String>
   {
      private String sortChars(String s) {
         char[] content = s.toCharArray();
         Arrays.sort(content);
         return new String(content);
      }

      public int compare(String s1, String s2)
      {
         return sortChars(s1).compareTo(sortChars(s2));
      }
   }

   public static void main(String[] args)
   {
      String[] words = generate();
      
      boxyPrinter("Ungrouped Array output:");
      prettyPrint(words);

      boxyPrinter("Grouped Array output:");
      Arrays.sort(words, new AnagramComparator());
      prettyPrint(words);
   }
}