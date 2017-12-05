import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Day2
 */
public class Day2 {

  public static void main(String[] args) throws Exception {
    List<String> rows = Utils.readLines("input2.txt").collect(Collectors.toList());

    int chksum = rows.stream().mapToInt(Day2::rowDiff).sum();
    System.out.println(chksum);

    int divsum = rows.stream().map(Day2::divisable).mapToInt(t -> t.first / t.second).sum();
    System.out.println(divsum);
  }

  static int rowDiff(String row) {
    List<Integer> nums = Utils.stringsToInt(row.split("\t"));
    Integer max = Collections.max(nums);
    Integer min = Collections.min(nums);

    return max - min;
  }

  static Tuple<Integer, Integer> divisable(String row) {
    List<Integer> nums = Utils.stringsToInt(row.split("\t"));

    int len = nums.size();
    for (int i = 0; i < len; i++) {
      for (int j = 0; j < len; j++) {
        if (i == j) { continue; }
        int a = nums.get(i);
        int b = nums.get(j);
        if (a % b == 0) {
          double c = (double) a / b;
          return new Tuple<Integer,Integer>(a, b);
        }
      }
    }
    return new Tuple<Integer,Integer>(0, 0);
  }
}
