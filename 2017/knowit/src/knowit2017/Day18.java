package knowit2017;

import java.util.HashMap;
import java.util.Map;

/**
 * Day18
 */
public class Day18 {
	String alphabet = "AÁBDÐEÉFGHIÍJKLMNOÓPRSTUÚVXYÝÞÆÖ";
	String msg = "1110010101000001011000000011101110100101010011011010101101100000010001111101000001010010001011101001100100100011010000110101111101010011100010110001100111110010";
	String key;

	public static void main(String[] args) {
		new Day18();
	}

	Day18() {
		Map<String, Integer> keyMap = new HashMap<>();
		for (String row : Utils.readFile("input18.txt")) {
			for (String ch : row.split("")) {
				if (keyMap.containsKey(ch)) {
					keyMap.put(ch, keyMap.get(ch) + 1);
				} else {
					keyMap.put(ch, 1);
				}
			}
		}

		key = keyStringToBinaryString(
			keyMap.entrySet().stream()
				.sorted((a, b) -> b.getValue().compareTo(a.getValue()))
				.map(a -> a.getKey())
				.reduce("", (a, b) -> a + b)
		);

		System.out.println(decrypt());
	}

	String decrypt() {
		String s = "";

		for (int i = 0; i < msg.length(); i += 8) {
			int ch = 0;
			for (int j = 0; j < 8; j++) {
				int power = 7 - (j % 8);
				char a = msg.charAt(i + j);
				char b = key.charAt(i + j);
				if (a != b) {
					ch += Math.pow(2, power);
				}
			}
			s += (char) ch;
		}

		return s;
	}

	String keyStringToBinaryString(String s) {
		String ret = "";

		for (String ch : s.split("")) {
			String i = Integer.toBinaryString(alphabet.indexOf(ch));
			while (i.length() < 5) {
				i = "0" + i;
			}
			ret += i;
		}

		return ret;
	}
}
