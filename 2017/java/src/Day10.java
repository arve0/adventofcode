import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Day10
 */
public class Day10 {

  public static void main(String[] args) {
    List<Integer> lengths = Arrays.asList(88,88,211,106,141,1,78,254,2,111,77,255,90,0,54,205);

    String input = "88,88,211,106,141,1,78,254,2,111,77,255,90,0,54,205";

    List<Integer> hashed = hash(lengths, Arrays.asList(), 1);
    System.out.println(hashed.get(0) * hashed.get(1));

    System.out.println(knotHash(input));
  }

  static List<Integer> hash(List<Integer> data, List<Integer> salt, int times) {
    List<Integer> list = new Range(0, 256);
    int skip = 0;
    int position = 0;

    while (times > 0) {
      for (int length : data) {
        Collections.reverse(list.subList(0, length));
        position += length + skip;
        Collections.rotate(list, -(length + skip++));
      }
      for (int length : salt) {
        Collections.reverse(list.subList(0, length));
        position += length + skip;
        Collections.rotate(list, -(length + skip++));
      }
      times--;
    }

    // reverse rotation
    Collections.rotate(list, position);
    return list;
  }

  static String knotHash(String input) {
    List<Integer> lengths = input.chars().mapToObj(Integer::new).collect(Collectors.toList());
    List<Integer> salt = Arrays.asList(17, 31, 73, 47, 23);

    List<Integer> sparseHash = hash(lengths, salt, 64);
    List<Integer> denseHash = new ArrayList<>();

    for (int i = 0; i < sparseHash.size(); i += 16) {
      int dense = sparseHash.get(i);
      for (int j = i + 1; j < (i + 16); j++) {
        dense ^= sparseHash.get(j);
      }
      denseHash.add(dense);
    }

    return denseHash.stream()
      .map(i ->
        i < 16
          ? '0' + Integer.toHexString(i)
          : Integer.toHexString(i)
      )
      .collect(StringBuilder::new,
        StringBuilder::append,
        StringBuilder::append)
      .toString();
  }
}

class Range extends ArrayList<Integer> {
  static final long serialVersionUID = 0;

  Range(int from, int to) {
    for (int i = from; i < to; i++) {
      add(i);
    }
  }
}
