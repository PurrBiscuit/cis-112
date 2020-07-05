public class Board
{
  private String[][] grid;
  int height, width;

  public Board(int l)
  {
    grid = new String[l][l];
    height = l;
    width = l;

    for (int y = 0; y < height; y++)
      for (int x = 0; x < width; x++)
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
  public boolean isSpaceFree(int x, int y)
  {
    return grid[y][x] == "*";
  }

  public void setMark(int x, int y, String s)
  {
    grid[y][x] = s;
  }

  public String toString()
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
