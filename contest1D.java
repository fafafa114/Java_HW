import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StringMatching {

  public static void main(String[] args) throws IOException {

    BufferedReader bufferedReader =
        new BufferedReader(new InputStreamReader(System.in));

    String substring = bufferedReader.readLine();
    String fullString = bufferedReader.readLine();

    if (substring.length() > fullString.length()) {
      return;
    }

    int BASE = 237;

    long hashSubstring = 0;
    long hashFullString = 0;
    long h = 1;

    for (int i = 0; i < substring.length(); i++) {
      hashSubstring = hashSubstring * BASE + substring.charAt(i);
      hashFullString = hashFullString * BASE + fullString.charAt(i);
      if (i > 0) {
        h = h * BASE;
      }
    }

    StringBuilder result = new StringBuilder();

    if (hashSubstring == hashFullString) {
      result.append("0 ");
    }

    for (int i = substring.length(); i < fullString.length(); i++) {
      hashFullString =
          (hashFullString - fullString.charAt(i - substring.length()) * h) *
              BASE +
          fullString.charAt(i);
      if (hashSubstring == hashFullString) {
        result.append((i - substring.length() + 1)).append(" ");
      }
    }

    if (result.length() > 0) {
      System.out.print(result.toString());
    }
  }
}
