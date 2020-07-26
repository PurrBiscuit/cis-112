import ch07.trees.BinarySearchTree;
import java.util.Iterator;

public class BSTMap<K, V> implements MapInterface<K, V>
{
  // declare a BinarySearchTree object to wrap this class around
  private BinarySearchTree<MapEntry<K, V>> map;
  
  public BSTMap()
  // constructor which instantiates the wrapper BinarySearchTree object
  {
    map = new BinarySearchTree<MapEntry<K, V>>();
  }
  
  public boolean contains(K k)
  // returns true if key is found in map otherwise returns false
  // wrapper around BinarySearchTree contains method
  {
    return map.contains(new MapEntry<K, V>(k, null));
  }
  
  public V get(K k)
  // looks for a key in the map and returns the value if found
  // otherwise returns null; wrapper around BinarySearchTree get method
  {
    if (k == null)
      throw new IllegalArgumentException("Maps do not allow null keys.");

    MapEntry<K, V> result = map.get(new MapEntry<K, V>(k, null));

    return result == null ? null : result.getValue();
  }
  
  public boolean isEmpty()
  // return true if no keys in map; otherwise returns false
  // wrapper around BinarySearchTree isEmpty method
  {
    return map.isEmpty();
  }
  
  public boolean isFull()
  {
    return false;
  }
  
  public Iterator<MapEntry<K, V>> iterator()
  // Returns the Iterator provided by BinarySearchTree.
  {
    return map.iterator();
  }
  
  public V put(K k, V v)
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
  {
    if (k == null)
      throw new IllegalArgumentException("Maps do not allow null keys.");

    V result = get(k);

    if (result != null)
      map.remove(new MapEntry<K, V>(k, null));

    return result;
  }
  
  public int size()
  {
    return map.size();
  }
}