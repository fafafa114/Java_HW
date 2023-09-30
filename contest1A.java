import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int a = scanner.nextInt();
    int b = scanner.nextInt();
    while (b != 0) {
      int t = a % b;
      a = b;
      b = t;
    }
    System.out.println(a);
    scanner.close();
  }
}
