import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
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

  public static Stream<String> readLines(String filename) {
		try {
			return Files.lines(Paths.get(filename));
		} catch (IOException e) {
			System.out.println(e);
			System.exit(1);
			return Arrays.asList("").stream();
		}
  }

	public static List<String> readLinesToList(String filename) {
		Path path = Paths.get(filename);
		List<String> lines = null;
		try {
			lines = Files.readAllLines(path, Charset.forName("UTF-8"));
		} catch (Exception e) {
			System.out.println(e);
			System.exit(1);
		}
		return lines;
	}
}
