import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StringMatching {

  private static final int BASE = 237;

  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader =
        new BufferedReader(new InputStreamReader(System.in));

    String pattern = bufferedReader.readLine();
    String text = bufferedReader.readLine();

    findPositions(pattern, text);
  }

  private static void findPositions(String pattern, String text) {
    if (pattern.length() > text.length()) {
      return;
    }

    long hashPattern = 0;
    long hashText = 0;
    long h = 1;

    for (int i = 0; i < pattern.length(); i++) {
      hashPattern = hashPattern * BASE + pattern.charAt(i);
      hashText = hashText * BASE + text.charAt(i);
      if (i > 0) {
        h = h * BASE;
      }
    }

    if (hashPattern == hashText) {
      System.out.print("0 ");
    }

    for (int i = pattern.length(); i < text.length(); i++) {
      hashText = (hashText - text.charAt(i - pattern.length()) * h) * BASE +
                 text.charAt(i);
      if (hashPattern == hashText) {
        System.out.print((i - pattern.length() + 1) + " ");
      }
    }
  }
}
