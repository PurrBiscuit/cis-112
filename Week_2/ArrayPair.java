public class ArrayPair<T> implements PairInterface<T>
{
  @SuppressWarnings("unchecked")
  protected T[] pair = (T[]) new Object[2];
  
  public ArrayPair(T first, T second)
  {
    pair[0] = first;
    pair[1] = second;
  };
  
  public T getFirst()
  // Return the first item in the pair.
  {
    return pair[0];
  }
  
  public T getSecond()
  // Return the second item in the pair.
  {
    return pair[1];
  }
  
  public void setFirst(T first)
  // Update the value of the first item in the pair.
  {
    pair[0] = first;
  }
  
  public void setSecond(T second)
  // Update the value of the second item in the pair.
  {
    pair[1] = second;
  }
}