import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

  private static final int ARRAY_SIZE = 32768;

  static class InputReader {
    private final BufferedReader reader;
    private StringTokenizer tokenizer;

    public InputReader(InputStream stream) {
      reader = new BufferedReader(new InputStreamReader(stream), ARRAY_SIZE);
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

    public int nextInt() { return Integer.parseInt(next()); }
  }

  public static int upperBound(int[] lis, int size, int value) {
    int left = 1;
    int right = size;
    int result = size + 1;
    while (left <= right) {
      int mid = (left + right) / 2;
      if (lis[mid] > value) {
        result = mid;
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }
    return result;
  }

  public static void main(String[] args) {
    InputReader inputReader = new InputReader(System.in);
    int size = inputReader.nextInt();
    int[] a = new int[size + 2];
    int[] lis = new int[size + 2];
    int[] idx = new int[size + 2];
    int[] lastPosition = new int[size + 2];

    inputA(a, size, inputReader);
    calcLIS(lis, idx, lastPosition, a, size);
    int answer = getAns(lis, size);
    printAns(lis, idx, lastPosition, answer);
  }

  private static void inputA(int[] a, int size, InputReader inputReader) {
    for (int i = 1; i <= size; i++) {
      a[i] = inputReader.nextInt() + 1;
    }
  }

  private static void calcLIS(int[] lis, int[] idx, int[] lastPosition, int[] a,
                              int size) {
    for (int i = 1; i <= size; i++) {
      int position = upperBound(lis, size, -a[i]);
      lis[position] = -a[i];
      idx[position] = i;
      if (position > 1) {
        lastPosition[i] = idx[position - 1];
      }
    }
  }

  private static int getAns(int[] lis, int size) {
    int answer = 0;
    for (int i = 1; i <= size + 1; i++) {
      if (lis[i] == 0) {
        answer = i - 1;
        break;
      }
    }
    return answer;
  }

  private static void printAns(int[] lis, int[] idx, int[] lastPosition,
                               int answer) {
    System.out.println(answer);

    int[] result = new int[answer + 1];
    int index = answer;
    int current = idx[answer];
    while (index > 0) {
      result[index] = current;
      current = lastPosition[current];
      index--;
    }

    for (int i = 1; i <= answer; i++) {
      System.out.print(result[i] + " ");
    }
  }
}
