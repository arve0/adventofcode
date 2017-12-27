import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Day19
 */
public class Day19 {
  List<List<String>> map = Utils.readLines("input19.txt")
    .map(row -> Arrays.asList(row.split("")))
    .collect(Collectors.toList());
  int x = map.get(0).indexOf("|");
  int y = 0;
  int dx = 0;
  int dy = 1;
  String solution = "";
  int steps = 1;

  public static void main(String[] args) {
    new Day19();
  }

  Day19() {
    while (true) {
      x += dx;
      y += dy;

      String ch = peek(x, y);

      if (" ".equals(ch)) {
        break;
      }
      steps++;

      if ("+".equals(ch) && dy != 0) {
        dy = 0;
        dx = !" ".equals(peek(x + 1, y)) ? 1 : -1;
      } else if ("+".equals(ch) && dx != 0) {
        dx = 0;
        dy = !" ".equals(peek(x, y + 1)) ? 1 : -1;
      } else if (!"+".equals(ch) && !"-".equals(ch) && !"|".equals(ch)) {
        solution += ch;
      }
    }

    System.out.println(solution);
    System.out.println(steps);
  }

  String peek(int x, int y) {
    if (y >= map.size() || x >= map.get(y).size()) {
      return " ";
    }
    return map.get(y).get(x);
  }
}
