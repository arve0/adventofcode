package adventofcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Test
 */
public class Test {

	public static void main(String[] args) throws Exception {
		String[] s = "abcdefgasbasqwetcsadcb".split("");

		Map<String, Integer> map = new HashMap<String, Integer>();
		for (String c : s) {
			if (map.containsKey(c)) {
				Integer val = map.get(c) + 1;
				map.put(c, val);
			} else {
				map.put(c, 1);
			}
		}

		List<Map.Entry<String, Integer>> m = map.entrySet().stream()
		      .sorted((a, b) -> {
						int valueSort = a.getValue().compareTo(b.getValue());
						if (valueSort == -1 || valueSort == 1) {
							return -valueSort;
						}
						return a.getKey().compareTo(b.getKey());
					})
					.collect(Collectors.toList());

		System.out.println(map);
		System.out.println(m.get(0).getKey());
	}

}
