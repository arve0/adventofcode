package adventofcode;

/**
 * Day9
 *
 */
public class Day9 {
	public static void main(String[] args) {
		for (String row : new FileReader("input9.txt").readLines()) {
			System.out.println(new Decompress(row).simpleDecompress());
		}
	}
}

class Decompress {
	String str;
	String remaining;

	Decompress(String s) {
		str = s;
	}

	public int simpleDecompress() {
		int size = 0;
		remaining = str;

		while (remaining.length() != 0) {
			int start = remaining.indexOf('(');
			if (start == -1) {
				size += remaining.length();
				break;
			}
			int x = remaining.indexOf('x');
			int end = remaining.indexOf(')');

			int num = Integer.parseInt(remaining.substring(start + 1, x));
			int multiply = Integer.parseInt(remaining.substring(x + 1, end));

			size += start;
			size += num * multiply;

			remaining = remaining.substring(end + 1 + num);
		}

		return size;
	}
}
