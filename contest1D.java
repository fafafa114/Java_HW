import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

  static class InputReader {
    private final BufferedReader reader;
    private StringTokenizer tokenizer;

    public InputReader(InputStream stream) {
      reader = new BufferedReader(new InputStreamReader(stream), 32768);
      tokenizer = null;
    }

    public String next() {
      while (tokenizer == null || !tokenizer.hasMoreTokens()) {
        try {
          tokenizer = new StringTokenizer(reader.readLine());
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      }
      return tokenizer.nextToken();
    }

    public int nextInt() {
      return Integer.parseInt(next());
    }
  }

  public static int findUpperBound(int[] x, int n, int v) {
    int l = 1;
    int r = n;
    int ret = n + 1;
    while (l <= r) {
      int mid = (l + r) / 2;
      if (x[mid] > v) {
        ret = mid;
        r = mid - 1;
      } else {
        l = mid + 1;
      }
    }
    return ret;
  }

  public static void main(String[] args) {
    InputReader inputReader = new InputReader(System.in);
    int n = inputReader.nextInt();
    int[] x = new int[n + 2];
    int[] y = new int[n + 2];
    int[] z = new int[n + 2];
    int[] pre = new int[n + 2];

    for (int i = 1; i <= n; i++) {
      x[i] = inputReader.nextInt() + 1;
    }

    for (int i = 1; i <= n; i++) {
      int pos = findUpperBound(y, n, -x[i]);
      y[pos] = -x[i];
      z[pos] = i;
      if (pos > 1) {
        pre[i] = z[pos - 1];
      }
    }

    int ans = 0;
    for (int i = 1; i <= n + 1; i++) {
      if (y[i] == 0) {
        ans = i - 1;
        break;
      }
    }

    System.out.println(ans);

    int[] result = new int[ans + 1];
    int index = ans;
    int cur = z[ans];
    while (index > 0) {
      result[index] = cur;
      cur = pre[cur];
      index--;
    }

    for (int i = 1; i <= ans; i++) {
      System.out.print(result[i] + " ");
    }
  }
}
