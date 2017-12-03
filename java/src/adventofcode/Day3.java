/**
 * Find possible triangles. Given sides a, b, c,
 * - a + b should be larger then c
 * - b + c should be larger than a
 * - and a + c should be larger than b
 */

package adventofcode;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day3 {

	public static void main(String[] args) {
		FileReader file = new FileReader("input3.txt");

		int count = 0;
		for (String row : file.readLines()) {
			Triangle t = new Triangle(row);
			count += t.possible ? 1 : 0;
		}
		System.out.println(count);

		count = 0;
		for (String col : new Columns(file)) {
			Triangle t = new Triangle(col);
			count += t.possible ? 1 : 0;
		}
		System.out.println(count);
	}
}

class Columns implements Iterable<String> {
	String[] rows = new String[3];
	FileReader file;
	List<String> lines;
	int i = 0;

	Columns(FileReader f) {
		file = f;
		lines = f.readLines().stream().map(s -> s.trim()).collect(Collectors.toList());
	}

	public Iterator<String> iterator() {
		return new Iterator<String>() {
			private int currentIndex = 0;

			@Override
			public boolean hasNext() {
				return currentIndex < lines.size();
			}

			@Override
			public String next() {
				return getColumn(currentIndex++);
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}

	String getColumn(int i) {
		int row = i / 3 * 3;
		int col = i % 3;
		// System.out.println(row + "," + col);
		String a = lines.get(row).split(" +")[col];
		String b = lines.get(row + 1).split(" +")[col];
		String c = lines.get(row + 2).split(" +")[col];
		return a + " " + b + " " + c;
	}
}

class Triangle {
	boolean possible;
	// \\G: end of previous match
	// *: any number of spaces
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