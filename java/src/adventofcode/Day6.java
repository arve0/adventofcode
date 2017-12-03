package adventofcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Day6
 */
public class Day6 {
	List<String> rows = new ArrayList<String>();
	int rowLength;

	public static void main(String[] args) {
		Day6 me = new Day6();
		System.out.println(me.findMostFrequentCharacters());
	}

	Day6() {
		FileReader file = new FileReader("input6.txt");
		for (String row : file.readLines()) {
			rows.add(row);
		}
		rowLength = rows.get(0).length();
	}

	private List<String> getColumn(int i) {
		return rows.stream()
				.map(r -> r.substring(i, i +1))
				.collect(Collectors.toList());
	}

	private String findMostFrequentCharacters() {
		String solution = "";
		for (int i = 0; i < rowLength; i++) {
			List<String> col = getColumn(i);
			solution += findMostFrequentCharacter(col);
		}
		return solution;
	}

	private String findMostFrequentCharacter(List<String> chars) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (String c : chars) {
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

		return m.get(0).getKey();
	}

}

class MostFrequentChar {

}
