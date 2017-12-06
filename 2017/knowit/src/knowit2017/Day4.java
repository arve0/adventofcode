package knowit2017;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Day4
 */
public class Day4 {
	public static void main(String[] args) {
		Timer timer = new Timer();

		List<String> words = Utils.readFile("input4.txt");

		int count = 0;
		for (String word : words) {
			if (!isPalindrom(word) && hasAnagramPalindrom(word)) {
				count++;
			}
		}

		System.out.println(count);
		timer.printElapsed();
	}

	static boolean isPalindrom(String str) {
		int end = str.length() / 2;
		for (int i = 0; i < end; i++) {
			if (str.codePointAt(i) != str.codePointAt(str.length() - i - 1)) {
				return false;
			}
		}
		return true;
	}

	static boolean hasAnagramPalindrom(String str) {
		str = Utils.sortString(str);

		Map<String, Integer> charCount = new HashMap<>();
		for (String ch : str.split("")) {
			if (charCount.containsKey(ch)) {
				int count = charCount.get(ch) + 1;
				charCount.put(ch, count);
			} else {
				charCount.put(ch, 1);
			}
		}

		int odds = 0;
		for (Integer i : charCount.values()) {
			if (i % 2 == 1) {
				odds++;
			}
		}

		return odds < 2;
	}
}
