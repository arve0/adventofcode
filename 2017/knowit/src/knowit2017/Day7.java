package knowit2017;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * Day7
 */
public class Day7 {
	private final static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private final static Map<String, String> decryptMap = alphabet.chars()
		.mapToObj(Day7::encryptMap)
		.collect(Collectors.toMap(entry -> entry.first, entry -> entry.second));

	public static void main(String[] args) {
		Timer timer = new Timer();
		String result = "OTUJNMQTYOQOVVNEOXQVAOXJEYA".chars()
			.mapToObj(Day7::decrypt)
			.reduce(new String(), (a, b) -> a + b);
		timer.elapsed();

		System.out.println(result);
		timer.print();
	}

	private static String encrypt(int ch) {
		int i = alphabet.indexOf(ch);
		i = i + i + 1;  // A has position 1 in alphabet
		i += ch;
		i %= alphabet.length();
		return alphabet.substring(i, i + 1);
	}

	private static Tuple<String, String> encryptMap(int ch) {
		String original = Character.toString((char) ch);
		String encrypted = encrypt(ch);
		return new Tuple<>(encrypted, original);
	}

	private static String decrypt(int ch) {
		String key = Character.toString((char) ch);
		return decryptMap.get(key);
	}
}
