package adventofcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Day8
 */
public class Day8 {
	public static void main(String[] args) {
		Display d = new Display();
		FileReader file = new FileReader("input8.txt");

		for (String cmd : file.readLines()) {
			d.command(cmd);
		}

		System.out.println(d.count());
		System.out.println(d);
	}
}

class Display {
	int xsize = 50;
	int ysize = 6;
	ArrayList<ArrayList<Boolean>> display = new ArrayList<ArrayList<Boolean>>(ysize);

	Display() {
		for (int i = 0; i < ysize; i++) {
			display.add(new ArrayList<Boolean>(xsize));
			ArrayList<Boolean> row = display.get(i);
			for (int j = 0; j < xsize; j++) {
				row.add(false);
			}
		}
		// display.get(y).get(x);
	}

	public void command(String cmd) {
		// rect AxB
		if (cmd.indexOf("rect ") == 0) {
			rect(cmd.substring("rect ".length()));
		}
		// rotate row y=A by B
		else if (cmd.indexOf("rotate row y=") == 0) {
			rotateRow(cmd.substring("rotate row y=".length()));
		}
		// rotate column x=A by B
		else if (cmd.indexOf("rotate column x=") == 0) {
			rotateColumn(cmd.substring("rotate column x=".length()));
		}
	}

		// rect AxB
		private void rect(String axb) {
		int x = axb.indexOf('x');
		int a = Integer.parseInt(axb.substring(0, x));
		int b = Integer.parseInt(axb.substring(x + 1));

		for (int i = 0; i < b; i++) {
			ArrayList<Boolean> row = display.get(i);
			for (int j = 0; j < a; j++) {
				row.set(j, true);
			}
		}
	}

	// rotate row y=A by B
	private void rotateRow(String abyb) {
		int by = abyb.indexOf(" by ");
		int a = Integer.parseInt(abyb.substring(0, by));
		int b = Integer.parseInt(abyb.substring(by + 4));

		ArrayList<Boolean> row = display.get(a);
		Collections.rotate(row, b);
	}

	// rotate column x=A by B
	private void rotateColumn(String abyb) {
		int by = abyb.indexOf(" by ");
		int a = Integer.parseInt(abyb.substring(0, by));
		int b = Integer.parseInt(abyb.substring(by + 4));

		List<Boolean> col = getColumn(a);
		Collections.rotate(col, b);
		setColumn(a, col);
	}

	private List<Boolean> getColumn(int j) {
		List<Boolean> col = new ArrayList<>();

		for (List<Boolean> row : display) {
			col.add(row.get(j));
		}

		return col;
	}

	private void setColumn(int j, List<Boolean> col) {
		for (int i = 0; i < ysize; i++) {
			display.get(i).set(j, col.get(i));
		}
	}

	public int count() {
		int c = 0;
		for (List<Boolean> row : display) {
			for (Boolean pixel : row) {
				c += pixel ? 1 : 0;
			}
		}
		return c;
	}

	public String toString() {
		List<String> output = new ArrayList<>();

		for (int i = 0; i < ysize; i++) {
			ArrayList<Boolean> row = display.get(i);
			String rowOutput = "";
			for (int j = 0; j < xsize; j++) {
				rowOutput += row.get(j) ? "x" : " ";
			}
			output.add(rowOutput);
		}
		return String.join("\n", output);
	}
}
