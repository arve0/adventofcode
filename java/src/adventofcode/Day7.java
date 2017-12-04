package adventofcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Day7
 */
public class Day7 {
	public static void main(String[] args) {
		FileReader file = new FileReader("input7.txt");

		int count = 0;
		for (String row : file.readLines()) {
			IPAddress ip = new IPAddress(row);
			if(ip.TLS()) {
				count += 1;
			}
		}

		System.out.println(count);
	}
}

class IPAddress {
	String str;
	String remaining;
	List<String> addresses = new ArrayList<String>();
	List<String> hypernets = new ArrayList<String>();

	IPAddress(String s) {
		str = s;
		parseAddr();
	}

	private void parseAddr() {
		remaining = str;
		while (remaining.indexOf('[') >= 0) {
			int start = remaining.indexOf('[');
			int end = remaining.indexOf(']');
			addresses.add(remaining.substring(0, start));
			hypernets.add(remaining.substring(start + 1, end));
			remaining = remaining.substring(end + 1);
		}
		addresses.add(remaining);
	}

	private boolean hasAbba(String s) {
		for (int i = 0; i < s.length() - 3; i++) {
			int a1 = s.codePointAt(i);
			int b1 = s.codePointAt(i + 1);
			int b2 = s.codePointAt(i + 2);
			int a2 = s.codePointAt(i + 3);

			if (a1 == a2 && b1 == b2 && a1 != b1) {
				return true;
			}
		}
		return false;
	}

	public boolean TLS() {
		for (String hypernet : hypernets) {
			if (hasAbba(hypernet)) {
				return false;
			}
		}
		// do not have abba in any hypernet
		for (String address : addresses) {
			if (hasAbba(address)) {
				return true;
			}
		}
		return false;
	}

}
