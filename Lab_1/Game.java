import java.util.*;

public class Game
{
  // toggle this variable to turn debug logging on/off
  final static boolean DEBUG_LOGS = true;

  static Random r = new Random();
  static int boardLength = 8;
  static int incorrectRemaining = 3;
  static int stepsRemaining = r.nextInt(boardLength) + 1;
  static String[][] board = new String[boardLength][boardLength];
  static String[] correctCoordinates = new String[stepsRemaining];

  public static void main(String[] args)
  {
    startGame();

    boolean quit = false;

    do {
      printStatus();

      System.out.print("\n[m]ove, [u]ndo, [r]edo, [q]uit: ");
      Scanner kb = new Scanner(System.in);
      String input = kb.nextLine();

      quit = input.equals("q");

    } while (!quit);
  };
  
  public static boolean isMatch(String coord)
  {
    for (int i = 0; i < correctCoordinates.length; i++)
    {
      if (correctCoordinates[i] != null && correctCoordinates[i].equals(coord))
      {
        if (DEBUG_LOGS)
          System.out.println("Match Found! -> " + correctCoordinates[i] + " = " + coord);

        return false;
      }
    }
    
    return true;
  };
  
  public static void initializeBoard()
  {    
    for (int r = 0; r < board.length; r++)
      for (int c = 0; c < board[r].length; c++)
        board[r][c] = "*";
        
    populateCorrectCoordinates();
  };
  
  public static void populateCorrectCoordinates()
  {
    for (int i = 0; i < correctCoordinates.length; i++)
    {
      int x, y;
      
      do {
        x = r.nextInt(boardLength);
        y = r.nextInt(boardLength);
        
        if (DEBUG_LOGS)
          System.out.println("Trying -> " + x + "" + y);
      } while (!isMatch(x + "" + y));
      
      correctCoordinates[i] = (x + "" + y);
      
      if (DEBUG_LOGS)
        System.out.println("Random coordinates... x = " + x + ", y = " + y);
    }
  };
  
  public static void printBoard()
  {
    System.out.println("  0 1 2 3 4 5 6 7");
    for (int r = 0; r < board.length; r++)
    {
      System.out.print(r + " ");
      for (int c = 0; c < board[r].length; c++)
      {
        String trailing = (c == board[r].length - 1) ? "\n" : " ";
        System.out.print(board[r][c] + trailing);
      }
    };
  };
  
  public static void printStatus()
  {
    System.out.println("Steps Remaining: " + stepsRemaining);
    System.out.println("Incorrect Guesses Remaining: " + incorrectRemaining + "\n");
    
    printBoard();
  };
  
  public static void startGame()
  {
    initializeBoard();
    
    String startMessage = "\n**************\n  FIND THE o  \n**************\n";
    
    System.out.println(startMessage);
  };
}