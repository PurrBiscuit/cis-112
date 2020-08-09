import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Anagrams2 extends AnagramHelper
{
   public static void main(String[] args)
   {
      String[] words = generate();

      // print out the ungrouped array content first for comparison
      boxyPrinter("Ungrouped Array output:");
      prettyPrint(words);

      // print out the grouped array after running through sort method
      boxyPrinter("Grouped Map output:");
      sort(words);
      prettyPrint(words);
   }

   private static void sort(String[] a)
   {
      HashMap<String, ArrayList<String>> map = new HashMap<>();

      // group words by anagram into map
      for (String s : a)
      {
         String key = sortChars(s);
         if (!map.containsKey(key)) {
            map.put(key, new ArrayList<String>());
         }

         map.get(key).add(s);
      }

      // add map values back to array in correct groupings
      int i = 0;
      for (String key : map.keySet())
      {
         ArrayList<String> list = map.get(key);
         for (String s : list)
         {
            a[i] = s;
            i++;
         }
      }
   }

   private static String sortChars(String s) {
      char[] content = s.toCharArray();
      Arrays.sort(content);
      return new String(content);
   }
}