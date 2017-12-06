package knowit2017;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * Utils
 */
public class Utils {
	public static String sortString(String str) {
		String[] s = str.split("");
		Arrays.sort(s);
		return String.join("", s);
	}

	public static List<String> readFile(String filename) {
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
