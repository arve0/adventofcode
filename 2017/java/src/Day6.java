import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Day6
 */
public class Day6 {

  public static void main(String[] args) {
    List<Integer> input = Arrays.asList(new Integer[]{ 11, 11, 13, 7, 0, 15, 5, 5, 4, 4, 1, 1, 7, 1, 15, 11 });
    Set<List<Integer>> seen = new HashSet<>();
    List<String> history = new LinkedList<>();

    while (seen.add(input)) {
      history.add(input.toString());
      int[] max = max(input);
      int pos = max[0];
      int value = max[1];
      input.set(pos, 0);

      while (value > 0) {
        pos = Math.floorMod(++pos, input.size());
        input.set(pos, input.get(pos) + 1);
        value--;
      }
    }

    System.out.println(seen.size());
    System.out.println(seen.size() - history.indexOf(input.toString()));
  }

  static int[] max(List<Integer> input) {
    int value = input.get(0);
    int pos = 0;
    for (int i = 1; i < input.size(); i++) {
      if (input.get(i) > value) {
        value = input.get(i);
        pos = i;
      }
    }
    return new int[]{ pos, value };
  }
}
