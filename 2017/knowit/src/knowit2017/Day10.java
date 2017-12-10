package knowit2017;

import java.util.ArrayList;
import java.util.List;

/**
 * Day10
 *
 * 1, x, 3, x, 5, x, 7, x, 9, x, 11, x , 13.... 1497, x, 1499, x
 * 1, x, x, x, 5, x, x, x, 9, x,  x, x ,  x.... 1497, x, x, x
 * 2, 4, 6, 8, ... 2n
 * 3, 7, 11, 15, ... 4n
 */
public class Day10 {
	public static void main(String[] args) {
		List<Integer> remaining = new ArrayList<>();

		for (int i = 1; i <= 1500; i += 2) {
			remaining.add(i);
		}

		int i = 1;
		while (remaining.size() != 1) {
			remaining.remove(i++);
			i %= remaining.size();
		}

		System.out.println(remaining);
	}
}
