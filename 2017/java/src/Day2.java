import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

/**
 * Day2
 */
public class Day2 {

  public static void main(String[] args) throws Exception {
    Stream<String> rows = Utils.readLines("input2.txt");

    int chksum = rows.mapToInt(Day2::rowDiff).sum();

    System.out.println(chksum);
  }

  static int rowDiff(String row) {
    List<Integer> nums = Utils.stringsToInt(row.split("\t"));
    Integer max = Collections.max(nums);
    Integer min = Collections.min(nums);

    return max - min;
  }
}
