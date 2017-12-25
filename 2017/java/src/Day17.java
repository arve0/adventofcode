import java.util.ArrayList;
import java.util.List;

/**
 * Day17
 */
public class Day17 {
  List<Integer> numbers = new ArrayList<>();
  int pos = 0;
  int jump = 380;

  public static void main(String[] args) {
    new Day17();
  }

  Day17() {
    int i = 0;
    numbers.add(i++);
    while (i <= 2017) {
      pos += jump;
      pos %= i;
      numbers.add(++pos, i++);
    }
    System.out.println(numbers.get(pos + 1));

    // problem 2, not possible to insert at index 0
    // -> index 1 is after 0
    int n = numbers.get(1);
    while (i < 50_000_000) {
      pos += jump;
      pos %= ++i;
      if (pos == 1) {
        n = i;
      }
    }
    System.out.println(n);
    // 28954212 too high
    // 6223059 too low
    // 6598349 wrong
  }
}
