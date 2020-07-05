import ch02.stacks.LinkedStack;
import ch02.stacks.StackUnderflowException;
import java.util.*;

public class Game
{
  // toggle this variable to turn debug logging on/off
  final static boolean DEBUG_LOGS = false;

  static Random r = new Random();
  static int boardLength = 8;
  static int incorrectRemaining = 3;
  static int stepsRemaining = r.nextInt(boardLength) + 1;
  static String[][] board = new String[boardLength][boardLength];
  static String[] correctCoordinates = new String[stepsRemaining];
  static LinkedStack<Move> redoStack = new LinkedStack<>();
  static LinkedStack<Move> undoStack = new LinkedStack<>();

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

      if (input.equals("r"))
        redoMove();

      if (input.equals("u"))
        undoMove();

    } while (!quit && incorrectRemaining > 0 && stepsRemaining > 0);

    endGame();
  };

  public static void clearRedo()
  {
    while (!redoStack.isEmpty())
      redoStack.pop();
  };

  public static void endGame()
  {
    System.out.println("\n**************\n  GAME OVER  \n**************\n");

    printSolution();

    printFinalMessage();
  };

  public static void getCoordinates()
  {
    String input;

    do {
      System.out.print("Coordinates (enter format as \"x y\"): ");
      Scanner kb = new Scanner(System.in);
      input = sanitizeInput(kb.nextLine());
    } while (!isValidInput(input));

    String[] inputArr = input.split(" ");

    int x = Integer.parseInt(inputArr[0]);
    int y = Integer.parseInt(inputArr[1]);

    Move move = new Move(x, y);

    if (isUniqueCoordinate(move))
    {
      setCoordinates(move);
      undoStack.push(move);
      clearRedo();
      System.out.println();
    } else {
      System.out.println("\nThe coordinates " + input + " where already input previously...skipping....\n");
    };
  };

  public static void initializeBoard()
  {
    for (int y = 0; y < board.length; y++)
      for (int x = 0; x < board[y].length; x++)
        board[y][x] = "*";

    populateCorrectCoordinates();
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

  public static boolean isUniqueCoordinate(Move m)
  {
    int x = m.getX();
    int y = m.getY();

    return board[y][x] == "*";
  };

  public static boolean isValidCoordinate(int x, int y)
  {
    if ((x > boardLength - 1) || (y > boardLength - 1))
    {
      System.out.println("\n" + x + " " + y + " is not a valid coordinate\n");
      return false;
    }

    return true;
  };

  public static boolean isValidInput(String input)
  {
    String[] inputArr = input.split(" ");

    if (inputArr.length >= 2)
    {
      try {
        int x = Integer.parseInt(inputArr[0]);
        int y = Integer.parseInt(inputArr[1]);
        return isValidCoordinate(x, y);
      } catch (Exception e) {}
    }

    System.out.println("\n" + input + " is not a valid input\n");

    return false;
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
          System.out.println("Trying -> " + x + " " + y);
      } while (isMatch(x + " " + y));
      
      correctCoordinates[i] = (x + " " + y);
      
      if (DEBUG_LOGS)
        System.out.println("Random coordinates... x = " + x + ", y = " + y);
    }
  };
  
  public static void printBoard()
  {
    System.out.print("  ");

    for (int i = 0; i < board[0].length; i++)
    {
      String extraSpace = (board.length >= 10 && i < 10) ? " " : "";
      System.out.print(extraSpace + i + " ");
    }

    System.out.println();

    for (int y = 0; y < board.length; y++)
    {
      String extraSpace = (board.length >= 10 && y < 10) ? " " : "";
      System.out.print(y + " " + extraSpace);
      for (int x = 0; x < board[y].length; x++)
      {
        String space = (board.length >= 10) ? "  " : " ";
        String trailing = (x == board[y].length - 1) ? "\n" : space;
        System.out.print(board[y][x] + trailing);
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
    for (int y = 0; y < board.length; y++)
      for (int x = 0; x < board[y].length; x++)
      {
        String coord = Integer.toString(x) + " " + Integer.toString(y);

        if (isMatch(coord))
          board[y][x] = "o";
        else
          board[y][x] = "x";
      }

    printBoard();
  };

  public static void printStatus()
  {
    System.out.println("Steps Remaining: " + stepsRemaining);
    System.out.println("Incorrect Guesses Remaining: " + incorrectRemaining + "\n");

    printBoard();
  };

  public static void redoMove()
  {
    try {
      Move prevUndo = redoStack.top();

      if (DEBUG_LOGS)
        System.out.println("\nRedoing the last undo move: " + prevUndo.toString() + "\n");

      setCoordinates(prevUndo);

      undoStack.push(prevUndo);

      redoStack.pop();
    } catch (StackUnderflowException err) {
      System.out.println("\nNo more moves to redo...\n");
    }
  };

  public static String sanitizeInput(String input)
  {
    input = input.replaceAll("\\s+", " ").trim();

    if (DEBUG_LOGS)
      System.out.println("\nInput after sanitizing: " + input);

    return input;
  };

  public static void setCoordinates(Move m)
  {
    int x = m.getX();
    int y = m.getY();

    if (isMatch(m.getStringifiedCoordinates()))
    {
      board[y][x] = "o";
      m.setMatch(true);
      stepsRemaining--;
    }
    else {
      board[y][x] = "x";
      m.setMatch(false);
      incorrectRemaining--;
    };
  };

  public static void startGame()
  {
    initializeBoard();
    
    String startMessage = "\n**************\n  FIND THE o  \n**************\n";
    
    System.out.println(startMessage);
  };

  public static void undoMove()
  {
    try {
      Move prevMove = undoStack.top();

      if (DEBUG_LOGS)
        System.out.println("\nUndoing the last move: " + prevMove.toString() + "\n");

      if (prevMove.isMatch())
        stepsRemaining++;
      else
        incorrectRemaining++;

      int x = prevMove.getX();
      int y = prevMove.getY();

      board[y][x] = "*";

      redoStack.push(prevMove);

      undoStack.pop();
    } catch (StackUnderflowException err) {
      System.out.println("\nNo more moves to undo...\n");
    }
  };
}
