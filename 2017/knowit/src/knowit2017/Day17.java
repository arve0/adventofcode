package knowit2017;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Day17
 */
public class Day17 {

	public static void main(String[] args) {
		int i = 1;
		while (true) {
			if (i % 10 == 6) {
				int n = rotate(i);
				if (i * 4 == n) {
					System.out.println(i);
					break;
				}
			}
			i++;
		}
	}

	static int rotate(Integer n) {
		List<String> l = Arrays.asList(n.toString().split(""));
		Collections.rotate(l, 1);
		return Integer.parseInt(String.join("", l));
	}
}
