package knowit2017;

import java.util.HashSet;
import java.util.Set;

/**
 * Day16
 */
public class Day16 {
	public static void main(String[] args) {
		int[] who = Utils.readLines("input16.txt").mapToInt(Integer::parseInt).toArray();

		Set<Integer> hasTurnedOn = new HashSet<>();
		boolean lamp = false;
		int count = 0;
		int i = 0;
		for (int picked : who) {
			i++;
			if (picked != 1 && !lamp && !hasTurnedOn.contains(picked)) {
				lamp = true;
				hasTurnedOn.add(picked);
				continue;
			}
			if (picked == 1 && lamp) {
				count++;
				lamp = false;
			}
			if (picked == 1 && count == 99) {
				System.out.println(i);
				break;
			}
		}
	}
}
