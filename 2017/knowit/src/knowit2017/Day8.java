package knowit2017;

import java.util.HashSet;
import java.util.Set;

/**
 * Day8
 */
public class Day8 {
	private final long LIMIT = (long) 1E7;
	private boolean[] juletall = new boolean[568];
	private static Timer timer = new Timer();

	public static void main(String[] args) {
		int i = 0;
		while (i < 100) {
			new Day8();
			timer.elapsed();
			i++;
		}
		timer.print();
	}

	Day8() {
		juletall[0] = false;
		juletall[1] = true;

		long sum = 0;
		for (int i = 1; i < 568; i++) {
			if (isJuletall(i)) {
				juletall[i] = true;
				sum += i;
			}
		}

		for (long i = 568; i <= LIMIT; i++) {
			int j = sumDigits(i);
			if (juletall[j]) {
				sum += i;
			}
		}

		System.out.println(sum);
	}


	private boolean isJuletall(long i) {
		Set<Long> sequence = new HashSet<>();

		long prev = i;
		long next_ = i;

		do {
			prev = next_;
			next_ = sumDigits(prev);

			if (sequence.contains(next_)) {
				return false;
			} else if (next_ == 1) {
				return true;
			}
			sequence.add(prev);
		} while (next_ != prev);

		return false;
	}

	private int sumDigits(long i) {
		int sum = 0;
		while (i > 0) {
			sum += (i % 10) * (i % 10);
			i /= 10;
		}
		return sum;
	}
}
