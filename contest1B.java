import java.util.Scanner;

class Node {
  int v;
  Node ls, rs;
}

public class Main {
  public static Node insert(Node rt, int v) {
    if (rt == null) {
      rt = new Node();
      rt.v = v;
      return rt;
    }
    if (v == rt.v) return rt;
    if (v < rt.v) {
      rt.ls = insert(rt.ls, v);
      return rt;
    }
    rt.rs = insert(rt.rs, v);
    return rt;
  }

  public static void dfs(Node rt) {
    if (rt == null)
      return;
    if (rt.ls == null && rt.rs == null) {
      System.out.print(rt.v + " ");
    }
    dfs(rt.ls);
    dfs(rt.rs);
  }

  public static void main(String[] args) {
    Node rt = null;
    Scanner scanner = new Scanner(System.in);
    int x;
    while (true) {
      x = scanner.nextInt();
      if (x == 0)
        break;
      rt = insert(rt, x);
    }
    dfs(rt);
  }
}
