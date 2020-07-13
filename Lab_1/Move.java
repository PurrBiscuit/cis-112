public class Move
{
  private int x, y;
  private boolean match;

  public Move(int x, int y)
  {
    this.x = x;
    this.y = y;
  };

  public boolean equals(Object obj)
  // checks to see if two Move objects are equal to each other
  {
    if (obj == this)
      return true;

    if (obj == null || obj.getClass() != this.getClass())
      return false;

    Move m = (Move) obj;

    return m.x == x && m.y == y;
  };

  public int getX()
  // getter for the x instance variable
  {
    return x;
  };

  public int getY()
  // getter for the y instance variable
  {
    return y;
  };

  public boolean isMatch()
  // indicates whether the instance has been marked as a match already
  {
    return match;
  };

  public void setMatch(boolean m)
  // setter for the match instance variable
  {
    match = m;
  };

  public String toString()
  // return the string representation of the instance
  {
    return "x -> " + x + ", y -> " + y;
  };
}
