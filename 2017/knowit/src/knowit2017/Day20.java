package knowit2017;

/**
 * Day20
 */
public class Day20 {

	public static void main(String[] args) {
		long sum = 0;
		for (int i = 1; i <= 1024; i++) {
			int times = 1025 - i;
			sum += times * i;
		}
		System.out.println(sum);
		sum = 0;
		for (int i = 1; i <= 1024; i++) {
			for (int j = 1; j <= i; j++) {
				sum += j;
			}
		}
		System.out.println(sum);
		sum = 0;
		for (int i = 1; i <= 1024; i++) {
			sum += i;
		}
		System.out.println(sum);
	}
}
