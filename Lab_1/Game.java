import ch02.stacks.LinkedStack;
import ch02.stacks.StackUnderflowException;
import java.util.*;

public class Game
{
  // toggle this variable to turn debug logging on/off
  final static boolean DEBUG_LOGS = true;

  private static Random r = new Random();
  private static int boardLength = 8;
  private static int incorrectRemaining = 3;
  private static int stepsRemaining = r.nextInt(boardLength) + 1;
  private static Board board = new Board(boardLength);
  private static Move[] correctMoves = new Move[stepsRemaining];
  private static LinkedStack<Move> redoStack = new LinkedStack<>();
  private static LinkedStack<Move> undoStack = new LinkedStack<>();

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
        getMove();

      if (input.equals("r"))
        redoMove();

      if (input.equals("u"))
        undoMove();

    } while (!quit && incorrectRemaining > 0 && stepsRemaining > 0);

    endGame();
  };

  private static void checkMove(Move m)
  {
    int x = m.getX();
    int y = m.getY();

    if (isMatch(m))
    {
      board.setMark(x, y, "o");
      m.setMatch(true);
      stepsRemaining--;
    }
    else {
      board.setMark(x, y, "x");
      m.setMatch(false);
      incorrectRemaining--;
    };
  };

  private static void clearRedo()
  {
    while (!redoStack.isEmpty())
      redoStack.pop();
  };

  private static void endGame()
  {
    System.out.println("\n**************\n  GAME OVER  \n**************\n");

    printSolution();

    printFinalMessage();
  };

  private static void getMove()
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

    if (board.isSpaceFree(x, y))
    {
      checkMove(move);
      undoStack.push(move);
      clearRedo();
      System.out.println();
    } else {
      System.out.println("\nThe coordinates " + move + " were already input previously, skipping....\n");
    };
  };

  private static boolean isMatch(Move m)
  {
    for (int i = 0; i < correctMoves.length; i++)
    {
      if (correctMoves[i] != null && correctMoves[i].equals(m))
      {
        if (DEBUG_LOGS)
          System.out.println("[DEBUG] Match Found!:  " + correctMoves[i] + " = " + m);

        return true;
      }
    }
    
    return false;
  };

  private static boolean isValidInput(String input)
  {
    String[] inputArr = input.split(" ");

    if (inputArr.length >= 2)
    {
      try {
        int x = Integer.parseInt(inputArr[0]);
        int y = Integer.parseInt(inputArr[1]);
        return isValidMove(x, y);
      } catch (Exception e) {}
    }

    System.out.println("\n" + input + " is not a valid input\n");

    return false;
  };

  private static boolean isValidMove(int x, int y)
  {
    if ((x > boardLength - 1) || (y > boardLength - 1))
    {
      System.out.println("\n" + x + " " + y + " is not a valid move\n");
      return false;
    }

    return true;
  };

  private static void populateCorrectMoves()
  {
    for (int i = 0; i < correctMoves.length; i++)
    {
      Move m;
      
      do {
        m = new Move(r.nextInt(boardLength), r.nextInt(boardLength));
        
        if (DEBUG_LOGS)
          System.out.println("[DEBUG] Trying -> " + m.getX() + " " + m.getY());
      } while (isMatch(m));
      
      correctMoves[i] = m;
      
      if (DEBUG_LOGS)
        System.out.println("[DEBUG] Added coordinates: " + m);
    }
  };
  
  private static void printFinalMessage()
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

  private static void printSolution()
  {
    for (int y = 0; y < board.getHeight(); y++)
      for (int x = 0; x < board.getWidth(); x++)
        board.setMark(x, y, "x");

    for (int i = 0; i < correctMoves.length; i++)
    {
      int x = correctMoves[i].getX();
      int y = correctMoves[i].getY();

      board.setMark(x, y, "o");
    }

    System.out.println(board);
  };

  private static void printStatus()
  {
    System.out.println("Steps Remaining: " + stepsRemaining);
    System.out.println("Incorrect Guesses Remaining: " + incorrectRemaining + "\n");

    System.out.println(board);
  };

  private static void redoMove()
  {
    try {
      Move prevUndo = redoStack.top();

      if (DEBUG_LOGS)
        System.out.println("\n[DEBUG] Redoing the last undo move: " + prevUndo + "\n");

      checkMove(prevUndo);

      undoStack.push(prevUndo);

      redoStack.pop();
    } catch (StackUnderflowException err) {
      System.out.println("\nNo more moves to redo...\n");
    }
  };

  private static String sanitizeInput(String input)
  {
    input = input.replaceAll("\\s+", " ").trim();

    if (DEBUG_LOGS)
      System.out.println("\n[DEBUG] Input after sanitizing: " + input);

    return input;
  };

  private static void startGame()
  {
    populateCorrectMoves();
    
    String startMessage = "\n**************\n  FIND THE o  \n**************\n";
    
    System.out.println(startMessage);
  };

  private static void undoMove()
  {
    try {
      Move prevMove = undoStack.top();

      if (DEBUG_LOGS)
        System.out.println("\n[DEBUG] Undoing the last move: " + prevMove + "\n");

      if (prevMove.isMatch())
        stepsRemaining++;
      else
        incorrectRemaining++;

      board.setMark(prevMove.getX(), prevMove.getY(), "*");

      redoStack.push(prevMove);

      undoStack.pop();
    } catch (StackUnderflowException err) {
      System.out.println("\nNo more moves to undo...\n");
    }
  };
}
