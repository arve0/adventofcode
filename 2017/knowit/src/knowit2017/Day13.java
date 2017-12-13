package knowit2017;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Day13
 */
public class Day13 {
	public static void main(String[] args) {
		Timer t = new Timer();
		List<Integer> numbers = new ArrayList<>();

		for (String row : Utils.readFile("input13.txt")) {
			int i = row.indexOf(',') + 2;
			numbers.add(Integer.parseInt(row.substring(i)));
		}

		Collections.sort(numbers, Collections.reverseOrder());

		int i = 0;
		long sum = 0;
		for (Integer n : numbers) {
			if (isBobsTurn(i++)) {
				sum += n;
			}
		}
		t.elapsedPrint();

		System.out.println(sum);
	}

	/**
	 * Thue-Morse, nth bit is one?
	 */
	static boolean isBobsTurn(int n) {
		int bitCount = 0;
		while (n > 0) {
			bitCount += n & 1; // count last bit
			n >>= 1;  // shift one right
		}
		return bitCount % 2 == 1;
	}
}
