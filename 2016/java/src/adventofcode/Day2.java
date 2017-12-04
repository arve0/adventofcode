package adventofcode;

public class Day2 {

	public static void main(String[] args) {
		FileReader input = new FileReader("input2.txt");

		KeyPad keypad = new KeyPad();
		SickKeyPad skp = new SickKeyPad();

		for (String line : input.readLines()) {
			for (char ch : line.toCharArray()) {
//				System.out.println(ch);
				if (ch == 'U') {
					keypad.up();
					skp.up();
				} else if (ch == 'R') {
					keypad.right();
					skp.right();
				} else if (ch == 'D') {
					keypad.down();
					skp.down();
				} else if (ch == 'L') {
					keypad.left();
					skp.left();
				}
			}
//			System.out.print(keypad.currentPos());
			System.out.print(skp.currentPos());
		}
		
	}

}

class SickKeyPad extends KeyPad {
	SickKeyPad() {
		pos.y = 2;
		pos.x = 0;
	}
	String[][] pad = {
		{ null, null, "1", null, null },
		{ null,  "2", "3",  "4", null },
		{ "5",   "6", "7",  "8", "9"  },
		{ null,  "A", "B",  "C", null },
		{ null, null, "D", null, null },
	};
	String[][] getPad() {
		return pad;
	}
	boolean inBounds(int newY, int newX) {
//		System.out.println("new y: " + newY);
//		System.out.println("new x: " + newX);
		boolean ret = newY >= 0 && newY < pad.length &&
				newX >= 0 && newX < pad[newY].length &&
				pad[newY][newX] != null;
//		if (ret) {
//			System.out.println("new value: " + pad[newY][newX]);			
//		}
		return ret;
	}
}

class KeyPad {
	String[][] pad = {
			{"1", "2", "3"},
			{"4", "5", "6"},
			{"7", "8", "9"},
	};
	String[][] getPad() {
		return pad;
	}
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
		return getPad()[pos.y][pos.x];
	}
}

class Position {
	int x = 1, y = 1;
}