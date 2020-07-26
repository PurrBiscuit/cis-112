public class BSTMapDriver
{
  public static void main(String[] args)
  {
    // instantiate a BSTMap object
    BSTMap<Integer, String> employees = new BSTMap<Integer, String>();

    // check the size and if it's empty
    printStats(employees, 10001);

    employees.put(10001, "Steve");
    printStats(employees, 10001);
  }

  public static void printStats(BSTMap<Integer, String> m, Integer i)
  {
    System.out.println("Size is now: " + m.size());
    System.out.println("isEmpty -> " + m.isEmpty());
    System.out.println("Contains employee id #" + i + " -> " + m.contains(i) + "\n");
  }
}