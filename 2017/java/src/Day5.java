import java.io.IOException;

/**
 * Day5
 */
public class Day5 {

  public static void main(String[] args) throws IOException {
    int[] jumps = Utils.readLines("input5.txt")
      .mapToInt(Integer::parseInt)
      .toArray();

    int pos = 0;
    int steps = 0;
    while (pos >= 0 && pos < jumps.length) {
      int jump = jumps[pos];
      jumps[pos]++;
      pos += jump;
      steps++;
    }
    System.out.println(steps);

    jumps = Utils.readLines("input5.txt")
      .mapToInt(Integer::parseInt)
      .toArray();

    pos = 0;
    steps = 0;
    while (pos >= 0 && pos < jumps.length) {
      int jump = jumps[pos];
      if (jump >= 3) {
        jumps[pos]--;
      } else {
        jumps[pos]++;
      }
      pos += jump;
      steps++;
    }

    System.out.println(steps);
  }
}
