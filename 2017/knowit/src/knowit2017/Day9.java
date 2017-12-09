package knowit2017;

/**
 * Day9
 */
public class Day9 {
	private static int LIMIT = 130_000;

	public static void main(String[] args) {
		Timer timer = new Timer();

		long budsjett = 0;
		for (int i = 1; i < LIMIT / 2; i++) {
			for (int j = i + 1; j <= LIMIT / 2; j++) {
				int s = sum(i, j);
				if (s > LIMIT) {
					break;
				}
				budsjett += 1;
			}
		}
		timer.elapsed();

		System.out.println(budsjett);
		timer.print();
	}

	private static int sum(int from, int to) {
		// arithmetic sequence Sn = n (a1 + an) / 2
		return (to - from + 1) * (from + to) / 2;
	}
}
