package knowit2017;

import java.util.Arrays;

/**
 * Day11
 */
public class Day11 {
	private boolean[] primes = new boolean[1000];

	public static void main(String[] args) {
		Timer t = new Timer();
		new Day11();
		t.elapsedPrint();
	}

	Day11() {
		Arrays.fill(primes, true);

		primes[0] = false;
		primes[1] = false;
		for (int i = 2; i < primes.length; i++) {
			if (!primes[i]) {
				continue;
			}
			for (int j = 2; j * i < primes.length; j++) {
			// every multiplum of i is not a prime
			primes[j * i] = false;
			}
		}

		int sum = 0;
		for (int i = 2; i < primes.length; i++) {
			if (primes[i]) {
				int r = reverse(i);
				if (r != i && primes[r]) {
					sum += 1;
				}
			}
		}

		System.out.println(sum);
	}

	private int reverse(int n) {
		int rev = 0;
		while (n > 0) {
			rev *= 10;
			rev += n % 10;
			n /= 10;
		}
		return rev;
	}
}
