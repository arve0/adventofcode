package adventofcode;

public class Day2 {

	public static void main(String[] args) {
		FileReader input = new FileReader("input2.txt");

		KeyPad keypad = new KeyPad();

		for (String line : input.readLines()) {
			for (char ch : line.toCharArray()) {
				if (ch == 'U') {
					keypad.up();
				} else if (ch == 'R') {
					keypad.right();
				} else if (ch == 'D') {
					keypad.down();
				} else if (ch == 'L') {
					keypad.left();
				}
			}
			System.out.print(keypad.currentPos());
		}
		
	}

}

class KeyPad {
	String[][] pad = {
			{"1", "2", "3"},
			{"4", "5", "6"},
			{"7", "8", "9"},
	};
	Position pos = new Position();
	void reset() {
		pos.y = 1;
		pos.x = 1;
	}
	boolean inBounds(int newY, int newX) {
		return newY >= 0 && newY < pad.length &&
				newX >= 0 && newX < pad[newY].length;
	}
	void up() {
		int newY = pos.y - 1;
		pos.y = inBounds(newY, pos.x) ? newY : pos.y; 
	}
	void down() {
		int newY = pos.y + 1;
		pos.y = inBounds(newY, pos.x) ? newY : pos.y; 
	}
	void left() {
		int newX = pos.x - 1;
		pos.x = inBounds(pos.y, newX) ? newX : pos.x; 
	}
	void right() {
		int newX = pos.x + 1;
		pos.x = inBounds(pos.y, newX) ? newX : pos.x; 
	}
	String currentPos() {
		return pad[pos.y][pos.x];
	}
}

class Position {
	int x = 1, y = 1;
}