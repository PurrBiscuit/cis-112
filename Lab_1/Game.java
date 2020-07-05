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

      if (input.equals("m"))
        getCoordinates();

    } while (!quit && incorrectRemaining > 0 && stepsRemaining > 0);

    endGame();
  };

  public static void endGame()
  {
    System.out.println("\n**************\n  GAME OVER  \n**************\n");

    printSolution();

    printFinalMessage();
  };

  public static void getCoordinates()
  {
    System.out.print("Coordinates: ");
    Scanner kb = new Scanner(System.in);
    String input = kb.nextLine();
    int x = Character.getNumericValue(input.charAt(0));
    int y = Character.getNumericValue(input.charAt(1));

    if (isMatch(input))
    {
      board[x][y] = "o";
      stepsRemaining--;
    }
    else {
      board[x][y] = "x";
      incorrectRemaining--;
    };

    System.out.println();
  };
  
  public static boolean isMatch(String coord)
  {
    for (int i = 0; i < correctCoordinates.length; i++)
    {
      if (correctCoordinates[i] != null && correctCoordinates[i].equals(coord))
      {
        if (DEBUG_LOGS)
          System.out.println("Match Found! -> " + correctCoordinates[i] + " = " + coord);

        return true;
      }
    }
    
    return false;
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
      } while (isMatch(x + "" + y));
      
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
  
  public static void printFinalMessage()
  {
    String message;

    if (incorrectRemaining != 0 && stepsRemaining != 0)
      message = "User quit game.";
    else if (incorrectRemaining == 0)
      message = "Too many incorrect guesses!";
    else
      message = "Congratulations, you won!";

    System.out.println("\n" + message);
  };

  public static void printSolution()
  {
    for (int x = 0; x < board.length; x++)
      for (int y = 0; y < board[x].length; y++)
      {
        String coord = Integer.toString(x) + Integer.toString(y);

        if (isMatch(coord))
          board[x][y] = "o";
        else
          board[x][y] = "x";
      }

    printBoard();
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
