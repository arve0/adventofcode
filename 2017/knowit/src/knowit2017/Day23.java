package knowit2017;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Day23
 */
public class Day23 {
	public static void main(String[] args) {
		Games games = new Games(Utils.readFile("input23.txt").get(0));

		boolean xenaStart = true;
		int xenaWins = 0;
		int opheliaWins = 0;
		int n = 0;
		int noone = 0;

		while (n < games.size()) {
			int w = games.get(n++);
			if (w == 0) {
				noone++;
				if (noone == 3) {
					noone = 0;
					xenaStart = !xenaStart;
				}
			} else if (w == 1) {
				noone = 0;
				xenaWins += xenaStart ? 1 : 0;
				opheliaWins += !xenaStart ? 1 : 0;
				xenaStart = xenaStart ? false : true;
			} else {
				noone = 0;
				xenaWins += !xenaStart ? 1 : 0;
				opheliaWins += xenaStart ? 1 : 0;
				xenaStart = xenaStart ? true : false;
			}
		}
		System.out.println(xenaWins + " " + opheliaWins + " " + (n - xenaWins - opheliaWins));
	}

}

class Games extends ArrayList<Integer> {
	List<String> wins = Arrays.asList(
		"123", "456", "789",  // horisontal
		"147", "258", "369",  // vertikal
		"159", "357"  // diagonal
	);

	Games(String input) {
		while (input.length() > 9) {
			String first = "";
			String second = "";
			int j;

			// xo
			//  o
			//xxo

			for (j = 0; j < 9 && j < input.length(); j++) {
				if (j % 2 == 0) {
					first += input.substring(j, j + 1);
				} else {
					second += input.substring(j, j + 1);
				}

				if (win(first)) {
					add(1);
					input = input.substring(j + 1);
					break;
				} else if (win(second)) {
					add(2);
					input = input.substring(j + 1);
					break;
				} else if (j == 8) {
					add(0);
					input = input.substring(j + 1);
				}
			}
		}
	}

	boolean win(String s) {
		for (String win : wins) {
			boolean w = win.chars()
				.mapToObj(ch -> s.indexOf(ch) != -1)
				.reduce(true, (acc, hit) -> acc && hit);
			if (w) {
				return true;
			}
		}
		return false;
	}
}
