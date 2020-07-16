import ch02.stacks.LinkedStack;
import ch02.stacks.StackUnderflowException;
import java.util.*;

public class Game
{
  // toggle this variable to turn debug logging on/off
  final static boolean DEBUG_LOGS = false;
  
  // variables initialized in the createBoard method
  private static int boardLength;
  private static int stepsRemaining;
  private static Board board;
  private static Move[] correctMoves;
  
  private static Random r = new Random();
  private static int incorrectRemaining = 3;
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
  // checks whether the input move by the user is a match or not;
  // also calls the appropriate helper method to adjust board and counters
  {
    if (isMatch(m))
    {
      m.setMatch(true);
      setCorrectMatch(m);
    }
    else {
      m.setMatch(false);
      setIncorrectMatch(m);
    };
  };

  private static void clearRedo()
  // clears out the redo stack
  {
    while (!redoStack.isEmpty())
      redoStack.pop();
  };

  private static void endGame()
  // prints out the game over messaging
  {
    System.out.println("\n**************\n  GAME OVER  \n**************\n");

    printSolution();

    printFinalMessage();
  };

  private static void getMove()
  // prompt the user for a coordinate (move) and does some validation
  // to make sure the move is valid and the space is free before proceeding
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
  // checks to see if the move is a match against the
  // list of moves in the correctMoves array
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
  // validation method that checks to see if the input
  // from the user contains at least two integers
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
  // validation method that checks to see if the coordinates
  // input by the user fall within the board's grid
  {
    if ((x > board.getWidth() - 1) || (y > board.getHeight() - 1))
    {
      System.out.println("\n" + x + " " + y + " is not a valid move\n");
      return false;
    }

    return true;
  };

  private static void populateCorrectMoves()
  // helper method which populates the list of correct moves at the start
  // of a game; validation included to protect from duplicate values being added
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
  // logging method which prints out a final message to
  // the user depending on how the game was exited
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
  // logging method which sets the board to the
  // correct solution and prints it out to the user
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
  // logging method which prints the current status of the game to the user
  {
    System.out.println("Steps Remaining: " + stepsRemaining);
    System.out.println("Incorrect Guesses Remaining: " + incorrectRemaining + "\n");

    System.out.println(board);
  };

  private static void redoMove()
  // pops any redo moves off the redoMove stack and calls the appopriate
  // method to set the board and counters based on saved state from move object
  {
    try {
      Move prevUndo = redoStack.top();

      if (DEBUG_LOGS)
        System.out.println("\n[DEBUG] Redoing the last undo move: " + prevUndo + "\n");

      if (prevUndo.isMatch())
        setCorrectMatch(prevUndo);
      else
        setIncorrectMatch(prevUndo);

      undoStack.push(prevUndo);

      redoStack.pop();
    } catch (StackUnderflowException err) {
      System.out.println("\nNo more moves to redo...\n");
    }
  };

  private static String sanitizeInput(String input)
  // helper method to trim any unwanted whitespace off that a user may have entered
  {
    input = input.replaceAll("\\s+", " ").trim();

    if (DEBUG_LOGS)
      System.out.println("\n[DEBUG] Input after sanitizing: " + input);

    return input;
  };

  private static void setCorrectMatch(Move m)
  // helper method to adjust the stepsRemaing counter
  // and mark the board with a correct match symbol ("o")
  {
    board.setMark(m.getX(), m.getY(), "o");
    stepsRemaining--;
  }

  private static void setIncorrectMatch(Move m)
  // helper method to adjust the incorrectRemaing counter
  // and mark the board with an incorrect match symbol ("x")
  {
    board.setMark(m.getX(), m.getY(), "x");
    incorrectRemaining--;
  }
  
  private static void createBoard()
  // creates the board by validating and using user input to determine size.
  // then initializes related variables stepsRemaining, boardLength, and correctMoves.
  {
    String input;
    do {
      System.out.print("Please enter your board length (between 4 and 16) or enter \"r\" for a randomly sized board: ");
      Scanner kb = new Scanner(System.in);
      input = sanitizeInput(kb.nextLine());
    } while (!isValidLength(input));
    
    if (input.toLowerCase().equals("r"))
      boardLength = r.nextInt(13) + 4;
    else 
      boardLength = Integer.parseInt(input);
    
    stepsRemaining = r.nextInt(boardLength) + 1;
    board = new Board(boardLength);
    correctMoves = new Move[stepsRemaining];
  }
  
  private static boolean isValidLength(String input)
  // determines whether a given input is valid. input should be "r"
  // or a digit between 4 and 16 inclusive.
  {
    if (input.toLowerCase().equals("r"))
      return true;
    try
    { 
      int parsedInput = Integer.parseInt(input);
      return parsedInput >= 4 && parsedInput <= 16;
    }
    catch (NumberFormatException err)
    {
      return false;
    }
  }

  private static void startGame()
  // helper method to create the board, trigger the populateCorrectMoves
  // method and print the welcome message to the user for a new game
  {
    createBoard();

    populateCorrectMoves();
    
    System.out.println("\n**************\n  FIND THE o  \n**************\n");
  };

  private static void undoMove()
  // pops any previously input or redone moves off the undoMove stack and
  // marks the board back to "*" as well as adjusting the appropriate counter
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
