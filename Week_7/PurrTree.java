import ch02.stacks.*;
import ch07.trees.*;
import support.BSTNode;

public class PurrTree<T> extends BinarySearchTree<T>
{
  //---------------------------------------
  //
  // Problem 29 - min2 and recMin2 methods
  //
  //---------------------------------------

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

  //---------------------------------------
  //
  // Problem 30 -  leafCount methods
  //
  //---------------------------------------
  public int leafCount()
  {
    int count = 0;
    if (root != null)
    {
      LinkedStack<BSTNode<T>> nodeStack = new LinkedStack<BSTNode<T>>();
      BSTNode<T> currNode;
      nodeStack.push(root);

      while (!nodeStack.isEmpty())
      {
        currNode = nodeStack.top();
        nodeStack.pop();
        if (currNode.getLeft() != null)
          nodeStack.push(currNode.getLeft());
        if (currNode.getRight() != null)
          nodeStack.push(currNode.getRight());

        if (currNode.getRight() == null && currNode.getLeft() == null)
          count++;
      }
    }
    return count;
  };

  public int leafCount2()
  {
    return recLeafCount(root);
  };

  private int recLeafCount(BSTNode<T> node)
  {
    if (node == null)
      return 0;
    if (node.getLeft() == null && node.getRight() == null)
      return 1;
    else
      return recLeafCount(node.getLeft()) + recLeafCount(node.getRight());
  };
}