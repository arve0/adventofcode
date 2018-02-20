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

    pos = 0;
    int length = 1;
    int toInsert = 50_000_000;
    int insertedNextToZero = 0;
    while ((length - 1) < toInsert) {
      pos = (pos + jump) % length;
      if (pos == 0) {
        insertedNextToZero = length;
      }
      length++;
      pos++;
    }
    System.out.println(insertedNextToZero);
  }
}
