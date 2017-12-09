package knowit2017;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Day8
 */
public class Day8 {
	private final int LIMIT = (int) 1E7;
	private Set<Integer> juletall = new HashSet<>();

	public static void main(String[] args) {
		Timer timer = new Timer();
		new Day8();
		timer.elapsed();
		timer.print();
	}

	Day8() {
		juletall.add(1);

		for (int i = 2; i <= LIMIT; i++) {
			if (isJuletall(i)) {
				juletall.add(i);
			}
		}

		System.out.println(juletall.stream().mapToLong(Integer::longValue).sum());
	}


	private boolean isJuletall(int i) {
		if (juletall.contains(i)) {
			return true;
		}

		Set<Integer> sequence = new HashSet<>();

		int prev = i;
		int next_ = i;

		do {
			prev = next_;
			next_ = next(prev);
			if (next_ > LIMIT) {
				return false;
			} else if (sequence.contains(next_)) {
				return false;
			} else if (juletall.contains(next_)) {
				return true;
			}
			sequence.add(prev);
		} while (next_ != prev);

		return false;
	}

	private int next(int i ) {
		return getDigits(i).stream()
			.mapToInt(d -> d * d)
			.sum();
	}

	private List<Integer> getDigits(int i) {
		List<Integer> digits_ = new LinkedList<>();

		while (i > 0) {
			digits_.add(i % 10);
			i /= 10;
		}

		return digits_;
	}
}
