import ch07.trees.BinarySearchTree;
import java.util.Iterator;

public class BSTMap<K, V> implements MapInterface<K, V>
{
  private BinarySearchTree<MapEntry<K, V>> map;
  
  public BSTMap() 
  {
    map = new BinarySearchTree<MapEntry<K, V>>();
  }
  
  public boolean contains(K k)
  {
    return map.contains(new MapEntry<K, V>(k, null));
  }
  
  public V get(K k)
  {
    MapEntry<K, V> result = map.get(new MapEntry<K, V>(k, null));

    return result == null ? null : result.getValue();
  }
  
  public boolean isEmpty()
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
    MapEntry<K, V> entry = new MapEntry<K, V>(k, v);
    
    map.add(entry);
    
    return entry.getValue();
  }
  
  public V remove(K k)
  {
    return null;
  }
  
  public int size()
  {
    return map.size();
  }
}