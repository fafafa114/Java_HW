import java.util.Scanner;

class Node {
  int value;
  Node leftChild;
  Node rightChild;

  Node(int value) { this.value = value; }
}

public class BinaryTree {

  public static Node insert(Node root, int value) {
    if (root == null) {
      return new Node(value);
    }
    if (value == root.value) {
      return root;
    }
    if (value < root.value) {
      root.leftChild = insert(root.leftChild, value);
    } else {
      root.rightChild = insert(root.rightChild, value);
    }
    return root;
  }

  public static void printLeaves(Node root) {
    if (root == null) {
      return;
    }
    if (root.leftChild == null && root.rightChild == null) {
      System.out.print(root.value + " ");
    }
    printLeaves(root.leftChild);
    printLeaves(root.rightChild);
  }

  public static Node readInputAndBuildTree() {
    Scanner scanner = new Scanner(System.in);
    Node root = null;
    while (true) {
      int inputValue = scanner.nextInt();
      if (inputValue == 0) {
        break;
      }
      root = insert(root, inputValue);
    }
    return root;
  }

  public static void main(String[] args) {
    Node root = readInputAndBuildTree();
    printLeaves(root);
  }
}
