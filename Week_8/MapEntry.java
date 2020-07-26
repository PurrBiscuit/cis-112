//---------------------------------------------------------------------------
// MapEntry.java              by Dale/Joyce/Weems                   Chapter 8
//
// With compareTo method added - Purr
//---------------------------------------------------------------------------
public class MapEntry<K, V> implements Comparable<MapEntry<K, V>>
{
  protected K key;
  protected V value;
  
  MapEntry(K k, V v)
  {
    key = k; value = v;
  }
  
  public K getKey()  {return key;}
  public V getValue(){return value;}
  public void setValue(V v){value = v;}
  
  @SuppressWarnings("unchecked")
  public int compareTo(MapEntry<K, V> m)
  {
    K thisKey = this.key;
    K argKey = m.key;

    if (!(thisKey instanceof Comparable))
      throw new RuntimeException("Keys of MapEntry must be Comparable");

    return ((Comparable)thisKey).compareTo(argKey);
  }

  @Override
  public String toString()
  // Returns a string representing this MapEntry.
  {
    return "Key  : " + key + "\nValue: " + value;
  }
}
 