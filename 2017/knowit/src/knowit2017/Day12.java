package knowit2017;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Day12
 */
public class Day12 {
	private boolean[][] board = new boolean[10][10];

	public static void main(String[] args) {
		new Day12();
	}

	Day12() {
		Knight knight = new Knight();

		int i = 0;
		while (200 > i++) {
			knight.move(board);
		}

		int sum = 0;
		for (i = 0; i < board.length; i++) {
			boolean[] row = board[i];
			for (int j = 0; j < board.length; j++) {
				sum += row[j] ? 1 : 0;
			}
		}

		System.out.println(sum);
	}
}

class Knight {
	int x = 0;
	int y = 0;
	// possible dx and dy
	List<Diff> moves = new ArrayList<>(Arrays.asList(
		new Diff(2, 1),
		new Diff(2, -1),
		new Diff(1, 2),
		new Diff(1, -2),
		new Diff(-2, 1),
		new Diff(-2, -1),
		new Diff(-1, 2),
		new Diff(-1, -2)
	));

	void move(boolean[][] board) {
		List<Diff> possibleMoves = moves.stream()
			.filter(diff -> isMovePossible(diff))
			.sorted()
			.collect(Collectors.toList());

		board[x][y] = !board[x][y];
		for (Diff m : possibleMoves) {
			if (board[x][y] != board[x + m.x][y + m.y]) {
				x += m.x;
				y += m.y;
				return;
			}
		}

		Diff last = possibleMoves.get(possibleMoves.size() - 1);
		x += last.x;
		y += last.y;
	}

	boolean isMovePossible(Diff diff) {
		int newX = x + diff.x;
		int newY = y + diff.y;
		if (newX < 0 || newY < 0) {
			return false;
		} else if (newX >= 10 || newY >= 10) {
			return false;
		}
		return true;
	}

	public int positionValue(Diff diff) {
		return 10 * (x + diff.x) + y + diff.y;
	}

	class Diff implements Comparable<Diff> {
		int x = 0;
		int y = 0;

		Diff(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int compareTo(Diff other) {
			Integer a = positionValue(this);
			Integer b = positionValue(other);
			return a.compareTo(b);
		}
	}
}

