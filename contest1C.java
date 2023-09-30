import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String s1 = br.readLine();
    String s2 = br.readLine();
    if (s1.length() > s2.length()) {
      return;
    }
    int base = 237;

    long hsh1 = 0;
    long hsh2 = 0;
    long h = 1;

    for (int i = 0; i < s1.length(); i++) {
      hsh1 = hsh1 * base + s1.charAt(i);
      hsh2 = hsh2 * base + s2.charAt(i);
      if (i > 0)
        h = h * base;
    }

    StringBuilder result = new StringBuilder();

    if (hsh1 == hsh2) {
      result.append("0 ");
    }

    for (int i = s1.length(); i < s2.length(); i++) {
      hsh2 = (hsh2 - s2.charAt(i - s1.length()) * h) * base + s2.charAt(i);

      if (hsh1 == hsh2) {
        result.append((i - s1.length() + 1)).append(" ");
      }
    }

    if (result.length() > 0) {
      System.out.print(result.toString());
    }
  }
}
