package adventofcode;

import java.security.MessageDigest;
import javax.xml.bind.DatatypeConverter;

/**
 * Test
 */
public class Test {

	public static void main(String[] args) throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5");

		String password = "abc";
		int index = 5017308;
		byte[] start = (password + Integer.toString(index)).getBytes();
		byte[] digest = md.digest(start);
		String res = DatatypeConverter.printHexBinary(digest);
		System.out.println(res);
		System.out.println(res.substring(5, 6));
	}
}
