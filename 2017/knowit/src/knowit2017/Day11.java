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
		int e = 1;
		while (e * 10 <= n) {
			e *= 10;
		}
		int rev = 0;
		while (e >= 1) {
			rev += (n % 10) * e;
			n /= 10;
			e /= 10;
		}
		return rev;
	}
}
