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

    List<List<Integer>> disk = new ArrayList<>();
    for (int i = 0; i < 128; i++) {
      List<Integer> row = knotHash(input + "-" + i).stream()
        .reduce(new ArrayList<Integer>(), (r, j) -> {
          int jj = 7;
          while (jj >= 0) {
            int n = (j >> jj);
            n %= 2;
            r.add(n);
            jj--;
          }
          return r;
        }, (a, b) -> {
          a.addAll(b);
          return a;
        });
      disk.add(row);
    }

    int count = 0;
    int groups = 0;
    for (int i = 0; i < disk.size(); i++) {
      List<Integer> row = disk.get(i);
      for (int j = 0; j < row.size(); j++) {
        int ii = row.get(j);
        count += ii != 0 ? 1 : 0;
        if (ii == 1) {
          markGroup(i, j, disk);
          groups++;
        }
      }
    }

    System.out.println(count);
    System.out.println(groups);
    // 1295 too high
  }

  static void markGroup(int i, int j, List<List<Integer>> disk) {
    if (i < 0 || j < 0
        || i >= disk.size()
        || j >= disk.get(i).size()) {
      return;
    }

    disk.get(i).set(j, 2);

    if (i > 0 && disk.get(i - 1).get(j) == 1) {
      markGroup(i - 1, j, disk);
    }
    if (j > 0 && disk.get(i).get(j - 1) == 1) {
      markGroup(i, j - 1, disk);
    }
    if ((i + 1) < disk.size() && disk.get(i + 1).get(j) == 1) {
      markGroup(i + 1, j, disk);
    }
    if ((j + 1) < disk.get(i).size() && disk.get(i).get(j + 1) == 1) {
      markGroup(i, j + 1, disk);
    }
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
