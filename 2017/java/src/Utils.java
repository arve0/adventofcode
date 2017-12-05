import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Utils
 */
public class Utils {
  public static List<Integer> stringsToInt(String[] str) {
    return Arrays.stream(str)
      .map(Integer::parseInt)
      .collect(Collectors.toList());
  }

  public static Stream<String> readLines(String filename) throws IOException {
    return Files.lines(Paths.get(filename));
  }
}
