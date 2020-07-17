import ch02.stacks.*;
import ch04.queues.*;
import ch07.trees.*;
import java.text.DecimalFormat;
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
  // iterative solution to finding the number of leaf nodes on the tree
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
  // entrypoint to the recursive solution for leaf node counting
  {
    return recLeafCount(root);
  };

  private int recLeafCount(BSTNode<T> node)
  // recursive solution to finding the number of leaf nodes on the tree
  {
    if (node == null)
      return 0;
    if (node.getLeft() == null && node.getRight() == null)
      return 1;
    else
      return recLeafCount(node.getLeft()) + recLeafCount(node.getRight());
  };

  //----------------------------------
  //
  // Problem 32 - height methods
  //
  //----------------------------------
  public int height()
  // iterative solution to finding the height of a tree
  // referenced a copy of the "Cracking the Coding Interview" for help
  // on different strategies to traverse the binary tree structure
  {
    int height = -1;
    LinkedQueue<BSTNode<T>> queue = new LinkedQueue<>();

    if (root != null)
      queue.enqueue(root);

    int nodeCount = queue.size();

    while(nodeCount > 0) {
      height++;

      for(; nodeCount > 0; nodeCount--)
      {
        BSTNode<T> node = queue.dequeue();

        if (node.getLeft() != null)
          queue.enqueue(node.getLeft());

        if (node.getRight() != null)
          queue.enqueue(node.getRight());
      }

      nodeCount = queue.size();
    }

    return height;
  };

  public int height2()
  // entrypoint to the recursive solution for tree height
  {
    return recHeight(root);
  };

  public int recHeight(BSTNode<T> node)
  // recursive solution to finding the height of a tree
  // referenced a copy of the "Cracking the Coding Interview" for help
  // on different strategies to traverse the binary tree structure
  {
    if (node == null)
      return -1;
    else
      return Math.max(recHeight(node.getRight()), recHeight(node.getLeft())) + 1;
  };

  //----------------------------------
  //
  // Problem 48 - fRatio method
  //
  //----------------------------------
  public double fRatio()
  // method to calculate the fullness ration of a tree based on
  // the minimum height of the tree / the actual height of tree
  {
    DecimalFormat df = new DecimalFormat("#.##");
    int m = minHeight();
    String r;

    if (m == -1)
      r = df.format(0.00);
    else if (m == 0)
      r = df.format(1.00);
    else
      r = df.format((double)(m) / (double)(this.height2()));

    return Double.parseDouble(r);
  };

  public int minHeight()
  // method to calculate the minimum height of the tree
  // based on the number of nodes in the tree
  {
    int size = this.size();

    if (size == 0)
      return -1;

    if (size == 1)
      return 0;

    return (int)(Math.log(size) / Math.log(2));
  };
}