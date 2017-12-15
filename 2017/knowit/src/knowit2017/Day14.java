package knowit2017;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Day14
 */
public class Day14 {
	public static void main(String[] args) {
		int STEPS = 30;
		// int steps = STEPS;
		Set<String> solutions = new TreeSet<>();

		int n = 29;
		while (n >= STEPS / 3) {

			int distribute = STEPS - n;  		// how much extra to distribute
			int distributeTo = distribute;  // start with uniform distribution

			while (distributeTo >= 0 && (float) distribute / distributeTo <= 2) {
				List<Integer> steps = new ArrayList<>(Collections.nCopies(n, 1));
				for (int i = 0; i < distribute; i++) {
					int j = i % distributeTo;
					if (j >= steps.size()) {
						continue;
					}
					steps.set(j, steps.get(j) + 1);
				}

				if (sum(steps) == STEPS) {
					String s = steps.toString().replaceAll("[\\[\\] ,]", "");
					solutions.add(s);
				}

				distributeTo--;
			}
			n--;

		}

		long sum = 1;
		for (String solution : solutions) {
			sum += permutations(solution.length(), countChars(solution).values());
		}
		System.out.println(sum);

	}

	static int sum(List<Integer> list) {
		int sum = 0;
		for (Integer n : list) {
			sum += n;
		}
		return sum;
	}

	static Map<String, Long> countChars(String s) {
		return s.chars()
			.mapToObj(c -> Character.toString((char) c))
			.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
	}

	static long permutations(int length, Collection<Long> charCount) {
		long f = 1;
		long max = Collections.max(charCount);
		charCount.remove(max);
		while ((length - max) >= 1) {
			f *= length--;
		}
		for (long c : charCount) {
			while (c > 1) {
				f /= c--;
			}
		}
		return f;
	}
}
