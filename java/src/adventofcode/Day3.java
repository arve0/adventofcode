package adventofcode;
/**
 * Find possible triangles. Given sides a, b, c,
 * - a + b should be larger then c
 * - b + c should be larger than a
 * - and a + c should be larger than b
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {

	public static void main(String[] args) {
		FileReader file = new FileReader("input3.txt");
		
		int count = 0;
		for (String line : file.readLines()) {
			Triangle t = new Triangle(line);
			count += t.possible ? 1 : 0;
		}
		System.out.println(count);
	}

}

class Triangle {
	boolean possible;
	// \\G: end of previous match
	//  *: any number of spaces
	// (): group
	// \\d+: one or more digits
	final static Pattern pattern = Pattern.compile("\\G *(\\d+)");
	int[] sides = new int[3];
	Triangle(String line) {
		Matcher match = pattern.matcher(line);
		int count = 0;
		while (match.find()) {
			String g = match.group(1);
			sides[count++] = Integer.parseInt(g);
		}
		possible = count == 3 &&
				sides[0] + sides[1] > sides[2] &&
				sides[1] + sides[2] > sides[0] &&
				sides[2] + sides[0] > sides[1];
	}
}