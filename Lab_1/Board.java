public class Board
{
  private String[][] grid;
  int height, width;

  public Board(int l)
  // constructor that takes a single argument which sets the width and
  // height to the same value; initializes the grid with "*" Strings
  {
    grid = new String[l][l];
    height = l;
    width = l;

    for (int y = 0; y < height; y++)
      for (int x = 0; x < width; x++)
        grid[y][x] = "*";
  }

  public int getHeight()
  // getter for the height instance variable
  {
    return height;
  }

  public String getMark(int x, int y)
  // returns the String at the specified location on the grid
  {
    return grid[y][x];
  }

  public int getWidth()
  // getter for the width instance variable
  {
    return width;
  }

  public boolean isSpaceFree(int x, int y)
  // checks to see if the space on the grid has already been
  // marked as a match or not; "*" indicates it's an unused spot
  {
    return grid[y][x] == "*";
  }

  public void setMark(int x, int y, String s)
  // sets a String at a specific location on the instance's grid
  {
    grid[y][x] = s;
  }

  public String toString()
  // return the string representation of the instance
  {
    String result = "  ";

    for (int i = 0; i < width; i++)
    {
      String extraSpace = (width >= 10 && i < 10) ? " " : "";
      result += (extraSpace + i + " ");
    }

    result += "\n";

    for (int y = 0; y < height; y++)
    {
      String extraSpace = (height >= 10 && y < 10) ? " " : "";
      result += (y + " " + extraSpace);
      for (int x = 0; x < width; x++)
      {
        String space = (width >= 10) ? "  " : " ";
        String trailing = (x == width - 1) ? "\n" : space;
        result += (grid[y][x] + trailing);
      }
    };

    return result;
  };
}
