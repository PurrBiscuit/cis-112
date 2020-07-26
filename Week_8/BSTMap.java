import ch07.trees.BinarySearchTree;
import java.util.Iterator;

public class BSTMap<K, V> implements MapInterface<K, V>
{
  // declare a BinarySearchTree object to wrap this class around.
  private BinarySearchTree<MapEntry<K, V>> map;
  
  public BSTMap()
  // constructor which instantiates the wrapper BinarySearchTree object.
  {
    map = new BinarySearchTree<MapEntry<K, V>>();
  }
  
  public boolean contains(K k)
  // returns true if key is found in map otherwise returns false.
  // wrapper around BinarySearchTree contains method.
  {
    return map.contains(new MapEntry<K, V>(k, null));
  }
  
  public V get(K k)
  // looks for a key in the map and returns the value if found
  // otherwise returns null.  wrapper around BinarySearchTree get method.
  {
    if (k == null)
      throw new IllegalArgumentException("Maps do not allow null keys.");

    MapEntry<K, V> result = map.get(new MapEntry<K, V>(k, null));

    return result == null ? null : result.getValue();
  }
  
  public boolean isEmpty()
  // return true if no keys in map; otherwise returns false.
  // wrapper around BinarySearchTree isEmpty method.
  {
    return map.isEmpty();
  }
  
  public boolean isFull()
  // always returns false since a map can never be full.
  // wrapper around BinarySearchTree isFull method.
  {
    return map.isFull();
  }
  
  public Iterator<MapEntry<K, V>> iterator()
  // Returns the Iterator provided by BinarySearchTree.
  {
    return map.iterator();
  }
  
  public V put(K k, V v)
  // adds a key-value pair to the map; will return the old value at
  // a key if found and replace the old key-value; otherwise will return null.
  // wrapper around BinarySearchTree add method.
  {
    if (k == null)
      throw new IllegalArgumentException("Maps do not allow null keys.");

    V result = get(k);

    if (result != null)
      map.remove(new MapEntry<K, V>(k, null));

    map.add(new MapEntry<K, V>(k, v));

    return result;
  }
  
  public V remove(K k)
  // removes a key-value pair from the map if found
  // and returns the old value; otherwise returns null.
  // wrapper around BinarySearchTree remove method.
  {
    if (k == null)
      throw new IllegalArgumentException("Maps do not allow null keys.");

    V result = get(k);

    if (result != null)
      map.remove(new MapEntry<K, V>(k, null));

    return result;
  }
  
  public int size()
  // wrapper around BinarySearchTree size method.
  {
    return map.size();
  }
}