import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Day14
 */
public class Day14 {

  public static void main(String[] args) {
    String input = "amgozmfv";

    int count = 0;
    for (int i = 0; i < 128; i++) {
      List<Integer> s = knotHash(input + "-" + i);
      count += s.stream()
        .map(Integer::toBinaryString)
        .mapToInt(str -> {
          return str.replaceAll("0", "").length();
        })
        .sum();
    }
    System.out.println(count);
  }

  static List<Integer> knotHash(String input) {
    List<Integer> lengths = input.chars().mapToObj(Integer::new).collect(Collectors.toList());
    List<Integer> salt = Arrays.asList(17, 31, 73, 47, 23);

    List<Integer> sparseHash = Day10.hash(lengths, salt, 64);
    List<Integer> denseHash = new ArrayList<>();

    for (int i = 0; i < sparseHash.size(); i += 16) {
      int dense = sparseHash.get(i);
      for (int j = i + 1; j < (i + 16); j++) {
        dense ^= sparseHash.get(j);
      }
      denseHash.add(dense);
    }

    return denseHash;
  }
}
