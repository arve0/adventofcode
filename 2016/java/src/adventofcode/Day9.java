package adventofcode;

/**
 * Day9
 *
 */
public class Day9 {
	public static void main(String[] args) {
		for (String row : new FileReader("input9.txt").readLines()) {
			System.out.println(new Decompress(row).simpleDecompress());
			System.out.println(new Decompress(row).complexDecompress());
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

	public int complexDecompress() {
		return complexDecompress(str);
	}

	private int complexDecompress(String str) {
		if (str.length() == 0) {
			return 0;
		}
		int start = str.indexOf('(');
		if (start == -1) {
			return str.length();
		} else if (start > 0) {
			return start + complexDecompress(str.substring(start));
		}
		int x = str.indexOf('x');
		int end = str.indexOf(')');

		int num = Integer.parseInt(str.substring(start + 1, x));
		int multiply = Integer.parseInt(str.substring(x + 1, end));

		return multiply * complexDecompress(str.substring(end + 1));
	}
}
