public class BSTMapDriver
{
  static String[] names = {"Steve", "Mike", "Nicky", "Mary", "Alan", "Rick", "Marge", "Bosco", "Rosie", "Grover", "Biff"};
  static int startingId = 1000;

  public static void main(String[] args)
  {
    // instantiate a BSTMap object
    BSTMap<Integer, String> employees = new BSTMap<Integer, String>();

    // check the size and if it's empty - this should
    // result in 0 and true since nothing has been added yet
    printStats(employees);

    // add the employees to the id map and print out a report of all id assignments
    addValues(employees);
    printIdReport(employees);

    // Check the contains method with some ids
    System.out.println("[should be true] employees map contains id #1000 -> " + employees.contains(1000));
    System.out.println("[should be true] employees map contains id #1006 -> " + employees.contains(1006));
    System.out.println("[should be false] employees map contains id #1012 -> " + employees.contains(1012) + "\n");

    // remove a couple employees from the id map and reprint the id report
    System.out.println("Removing " + employees.remove(1002) + " from the system at id #1002.");
    System.out.println("Removing " + employees.remove(1008) + " from the system at id #1008.");
    System.out.println("Assinging id #1004 to Annie; previous assignee to #1004 was " + employees.put(1004, "Annie") + ".\n");

    // print a final report after the map manipulations performed above
    printIdReport(employees);

    // demonstrate iterator
    boxyPrinter("BSTMap iterator: ");
    System.out.println("Printing only ASSIGNED ids:");

    for (MapEntry<Integer, String> t: employees)
      System.out.println(t.getKey() + " -> " + t.getValue());
  }

  private static void printStats(BSTMap<Integer, String> m)
  {
    System.out.println("Size is now: " + m.size());
    System.out.println("isFull -> " + m.isFull());
    System.out.println("isEmpty -> " + m.isEmpty() + "\n");
  }

  private static void addValues(BSTMap<Integer, String> m)
  {
    for (int i = 0; i < names.length; i++)
      m.put((startingId + i), names[i]);
  }

  private static void printIdReport(BSTMap<Integer, String> m)
  {
    boxyPrinter("Printing Employee ID Report: ");
    printStats(m);

    for (int i = 0; i < names.length; i++)
      System.out.println((startingId + i) + " = " + m.get(startingId + i));

    System.out.println();
  }

  private static void boxyPrinter(String text)
  // helper method to print text to the console in a boxed format
  {
    int length = text.length();
    String result = "";

    for (int y = 0; y < 5; y++ )
    {
      result += "*";

      if (y != 2)
      {
        String s = (y == 0 || y == 4) ? "*" : " ";
        for (int x = 0; x < length + 2; x++)
          result += s;
      }
      else
        result += " " + text + " ";

      result += "*\n";
    }

    System.out.println(result);
  }
}