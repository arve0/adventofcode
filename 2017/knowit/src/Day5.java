import java.util.ArrayList;
import java.util.List;

/**
 * Day5
 */
public class Day5 {
	public static void main(String[] args) {
		List<Integer> nums = new ArrayList<>();
		nums.add(1); nums.add(2); nums.add(2);
		Integer n = 3;

		while (nums.size() < 1E6) {
			Integer times = nums.get(n - 1);
			addNTimes(nums, n, times);
			n++;
		}

		Long sum = nums.subList(0, (int) 1E6).stream().mapToLong(i -> i).sum();
		System.out.println(sum);
	}

	static void addNTimes(List<Integer> nums, Integer n, Integer times) {
		for (int i = 0; i < times; i++) {
			nums.add(n);
		}
	}
}
