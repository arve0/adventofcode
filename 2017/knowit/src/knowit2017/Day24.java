package knowit2017;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Day24
 */
public class Day24 {
	static final Timer t = new Timer();
	int SIZE = 10_000;
	Map<String, Integer> board = new HashMap<>();
	Map<String, String> portals;
	int[][] moves = {
		{1, 0},
		{-1, 0},
		{0, 1},
		{0, -1},
	};
	List<String> queue = new LinkedList<>();
	String destination = posToStr(SIZE - 1, SIZE - 1);

	public static void main(String[] args) {
		Day24 d = new Day24();
		t.elapsed("total");
		t.print();
		System.out.println(d.board.get(d.destination));
	}

	Day24() {
		board.put("0,0", 0);
		queue.add("0,0");

		// t.elapsed("filled");

		portals = Utils.readLines("input24.txt")
			.map(str -> parseFromTo(str))
			.reduce(
				new HashMap<String, String>(),
				(map, x) -> {
					map.put(x[0], x[1]);
					return map;
				},
				(a, b) -> {
					a.putAll(b);
					return a;
				});

		// t.elapsed("portals parsed");

		while (board.get(destination) == null) {
			shiftQueue();
		}
	}

	String posToStr(int x, int y) {
		return x + "," + y;
	}

	int[] strToPos(String str) {
		int[] ret = new int[2];
		ret[0] = Integer.parseInt(str.split(",")[0]);
		ret[1] = Integer.parseInt(str.split(",")[1]);

		return ret;
	}

	String[] parseFromTo(String str) {
		// (8806,8954)->(4802,3168)
		// from -> to
		str = str.substring(1, str.length()-1);
		String[] fromTo = new String[2];
		fromTo[0] = str.split("\\)->\\(")[0];
		fromTo[1] = str.split("\\)->\\(")[1];

		return fromTo;
	}

	void shiftQueue() {
		String from = queue.remove(0);
		int distance = board.get(from) + 1;
		int x = strToPos(from)[0];
		int y = strToPos(from)[1];

		for (int[] move : moves) {
			int xx = x + move[0];
			int yy = y + move[1];
			if (xx < 0 || yy < 0 || xx >= SIZE || yy >= SIZE) {
				continue;
			}
			String pos = posToStr(xx, yy);

			if (board.get(pos) == null) {
				board.put(pos, distance);
				queue.add(pos);
			}
			String jump = portals.get(pos);
			if (jump != null) {
				board.put(jump, distance);
				queue.add(jump);
			}
		}
	}
}
