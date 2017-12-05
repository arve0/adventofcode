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

	public long complexDecompress() {
		return complexDecompress(str);
	}

	private long complexDecompress(String str) {
		long sum = 0;
		int i = 0;
		while (i < str.length()) {
			// String workingStr = str.substring(i);
			char first = str.charAt(i);
			if (first != '(') {
				sum += 1;
				i += 1;
				continue;
			}
			int x = str.indexOf('x', i);
			int stop = str.indexOf(')', i);
			int num = Integer.parseInt(str.substring(i + 1, x));
			int times = Integer.parseInt(str.substring(x + 1, stop));
			int end = stop + 1 + num;
			String sub = str.substring(stop + 1, end);
			long subSum = complexDecompress(sub);
			sum += times * subSum;
			i = end;
		}
		return sum;
	}
}
