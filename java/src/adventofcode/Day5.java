package adventofcode;

import java.security.MessageDigest;
import javax.xml.bind.DatatypeConverter;

/**
 * Day5
 */
public class Day5 {
	public static void main(String[] args) {
		try {
			FindPassword fp = new FindPassword("ffykfhsq");
			System.out.println(fp.crackPassword(8));
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
			password += next();
			System.out.println(password);
			i++;
		}
		return password;
	}

	private String next() {
		do {
			index++;
		} while(!startsWithFiveZeroes());
		return digest().substring(5, 6);
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
