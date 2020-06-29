import java.util.Scanner;

public class Program2
{
  public static void main(String[] args)
  {
      Scanner kb = new Scanner(System.in);
      System.out.print("Please enter a number greater than 0: ");
      int num = kb.nextInt();
      
      // print out results of the three different recursive methods
      System.out.println("Summation of " + num + " = " + sum(num));
      System.out.println("2 raised to the " + num + " power = " + biPower(num));
      System.out.println("5 times " + num + " = " + fiveTimes(num));
  };
  
  public static int biPower(int n)
  // Preconditions: n must be greater than or equal to 1
  // returns the result of 2 raised to the n power
  {
    if (n == 1)
      return 2;
    else
      return 2 * biPower(n - 1);
  };
  
  public static int fiveTimes(int n)
  // Preconditions: n must be greater than or equal to 1
  // returns the result of 5 times n
  {
    if (n == 1)
      return 5;
    else
      return 5 + fiveTimes(n - 1);
  };
  
  public static int sum(int n)
  // Preconditions: n must be greater than or equal to 1
  // returns the summation of a number
  {
    if (n == 1)
      return 1;
    else
      return n + sum(n - 1);
  };
}