import ch07.trees.*;
import support.BSTNode;

public class PurrTree<T> extends BinarySearchTree<T>
{
  // Problem 29 - min2 and recMin2 methods
  public T min2()
  // entrypoint to recursive recMin2 method
  {
    return recMin2(root);
  };
  
  public T recMin2(BSTNode<T> node)
  // recursive method to get the minimum value in the tree
  {
    if (node == null)
      return null;
      
    return node.getLeft() != null ? recMin2(node.getLeft()) : node.getInfo();
  };
}