public class ArrayPairDriver
{
  public static void main(String[] args) {
    // instantiate a new ArrayPair object
    PairInterface<String> pair = new ArrayPair<String>("lion", "bear");
    
    // print out the two values in the ArrayPair object
    System.out.println(pair.getFirst());
    System.out.println(pair.getSecond());
    
    // update the two items in the pair to different values
    pair.setFirst("hyena");
    pair.setSecond("gorilla");
    
    // print out the updated values in the ArrayPair object
    System.out.println("New first animal is: " + pair.getFirst());
    System.out.println("New second animal is: " + pair.getSecond());
  }
}