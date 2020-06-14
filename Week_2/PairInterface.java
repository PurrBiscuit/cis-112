public interface PairInterface<T>
{
  T getFirst();
  // return the first item in the pair
  
  T getSecond();
  // return the second item in the pair

  void setFirst(T element);
  // set the first item in the pair to a value
  
  void setSecond(T element);
  // set the sceond item in the pair to a value
}