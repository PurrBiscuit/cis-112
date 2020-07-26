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
    return true;
  }
  
  public V get(K k)
  {
    return null;
  }
  
  public boolean isEmpty()
  {
    return true;
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