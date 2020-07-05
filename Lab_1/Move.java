public class Move
{
  private int x, y;
  private boolean match;

  public Move(int x, int y)
  {
    this.x = x;
    this.y = y;
  };

  public String getStringifiedCoordinates()
  {
    return x + " " + y;
  }

  public int getX()
  {
    return x;
  };

  public int getY()
  {
    return y;
  };

  public boolean isMatch()
  {
    return match;
  };

  public void setMatch(boolean m)
  {
    match = m;
  };

  public String toString()
  {
    return "x -> " + x + ", y -> " + y;
  }
}
