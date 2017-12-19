package knowit2017;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Day19
 */
public class Day19 {
	Map<Integer, List<Integer>> coords = new HashMap<>();
	int x = 0;
	int y = 0;

	public static void main(String[] args) {
		new Day19();
	}

	Day19() {
		for (String row : Utils.readFile("input19.txt")) {
			int steps = Integer.parseInt(row.split(",")[0]);
			String dir = row.split(", ")[1];
			walk(steps, dir);
		}
		draw();
	}

	void walk(int steps, String dir) {
		while (steps > 0) {
			x += dx(dir);
			y += dy(dir);
			add(x, y);
			steps--;
		}
	}

	int dx(String dir) {
		if (dir.equals("east")) {
			return 1;
		} else if (dir.equals("west")) {
			return -1;
		}
		return 0;
	}

	int dy(String dir) {
		if (dir.equals("north")) {
			return 1;
		} else if (dir.equals("south")) {
			return -1;
		}
		return 0;
	}

	void add(int x, int y) {
		if (!coords.containsKey(y)) {
			coords.put(y, new ArrayList<>());
		}
		coords.get(y).add(x);
	}

	void draw() {
		List<List<Integer>> lines = coords.entrySet().stream()
			.sorted((a, b) -> b.getKey() - a.getKey())
			.map(a -> a.getValue())
			.collect(Collectors.toList());

		int min = lines.stream()
			.flatMap(a -> a.stream())
			.mapToInt(a -> a)
			.min().getAsInt();

		for (List<Integer> line : lines) {
			Collections.sort(line, Collections.reverseOrder());

			int max = Collections.max(line);

			for (int i = min; i <= max; i++) {
				System.out.print(line.contains(i) ? 'x' : ' ');
			}
			System.out.println();
		}
	}
}
