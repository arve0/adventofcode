package adventofcode;

import java.security.MessageDigest;
import java.util.Arrays;

import javax.xml.bind.DatatypeConverter;

/**
 * Day5
 */
public class Day5 {
	public static void main(String[] args) {
		try {
			FindPassword fp = new FindPassword("ffykfhsq");
			System.out.println(fp.crackPassword(8));
			System.out.println(fp.crackSecondPassword(8));
		} catch (Exception e) {
			System.out.println(e);
			System.exit(1);
		}
	}
}

class FindPassword {
	MessageDigest md;
	String input;
	int index = 0;
	String currentString;

	FindPassword(String in) throws Exception {
		input = in;
		currentString = in + Integer.toString(index);
		md = MessageDigest.getInstance("MD5");
	}

	public String crackPassword(int n) {
		int i = 0;
		String password = "";
		while (i < n) {
			password += next().substring(5, 6);
			System.out.println(password);
			i++;
		}
		return password;
	}

	public String crackSecondPassword(int n) {
		index = 0;
		String[] password = new String[n];

		while (!isFull(password)) {
			String hash = next();
			try {
				int i = Integer.parseInt(hash.substring(5, 6));
				if (i < 8 && password[i] == null) {
					password[i] = hash.substring(6, 7);
					System.out.println(Arrays.toString(password));
				}
			} catch (NumberFormatException e) {
				// pass
			}
		}
		return String.join("", password);
	}

	private static boolean isFull(Object[] o) {
		for (int i = 0; i < o.length; i++) {
			if (o[i] == null) {
				return false;
			}
		}
		return true;
	}

	private String next() {
		do {
			index++;
		} while(!startsWithFiveZeroes());
		return digest();
	}

	private String digest() {
		currentString = input + Integer.toString(index);
		byte[] bytes = md.digest(currentString.getBytes());
		return DatatypeConverter.printHexBinary(bytes);
	}

	private boolean startsWithFiveZeroes() {
		return digest().substring(0, 5).equals("00000");
	}

}
