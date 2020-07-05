public class Board
{
  private String[][] grid;
  int height, width;

  public Board(int l)
  {
    grid = new String[l][l];
    height = l;
    width = l;

    for (int y = 0; y < grid.length; y++)
      for (int x = 0; x < grid[y].length; x++)
        grid[y][x] = "*";
  }

  public int getHeight()
  {
    return height;
  }

  public String getMark(int x, int y)
  {
    return grid[y][x];
  }

  public int getWidth()
  {
    return width;
  }

  public void setMark(int x, int y, String s)
  {
    grid[y][x] = s;
  }

  public String toString()
  {
    String result = "  ";

    for (int i = 0; i < grid[0].length; i++)
    {
      String extraSpace = (grid.length >= 10 && i < 10) ? " " : "";
      result += (extraSpace + i + " ");
    }

    result += "\n";

    for (int y = 0; y < grid.length; y++)
    {
      String extraSpace = (grid.length >= 10 && y < 10) ? " " : "";
      result += (y + " " + extraSpace);
      for (int x = 0; x < grid[y].length; x++)
      {
        String space = (grid.length >= 10) ? "  " : " ";
        String trailing = (x == grid[y].length - 1) ? "\n" : space;
        result += (grid[y][x] + trailing);
      }
    };

    return result;
  };
}
