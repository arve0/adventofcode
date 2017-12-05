import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Day10
 *
 * value 5 goes to bot 2
 * bot 2 gives low to bot 1 and high to bot 0
 * value 3 goes to bot 1
 * bot 1 gives low to output 1 and high to bot 0
 * bot 0 gives low to output 2 and high to output 0
 * value 2 goes to bot 2
 */
public class Day10 {
	public static void main(String[] args) {
		Map<Integer, Bot> bots = getSample().stream()
			.
	}

	static List<String> getSample() {
		return Arrays.asList("value 5 goes to bot 2\n",
					"bot 2 gives low to bot 1 and high to bot 0\n",
					"value 3 goes to bot 1\n",
					"bot 1 gives low to output 1 and high to bot 0\n",
					"bot 0 gives low to output 2 and high to output 0\n",
					"value 2 goes to bot 2\n");
	}
}

interface Receiver {
	void receive (int n);
}

class Bot implements Receiver{
	List<Integer> values = new ArrayList<>();
	int num, high, low;

	Bot(String str) {
		// "bot 1 gives low to output 1 and high to bot 0\n"
		int end = str.indexOf(' ', 4);
		num = Integer.parseInt(str.substring(4, end));

		int start = str.indexOf("low to ") + "low to ".length();
		end = str.indexOf(' ', start);
		high = Integer.parseInt(str.substring(start, end));

		start = str.indexOf("output ") + "output ".length();
		end = str.indexOf(' ', start);
		low = Integer.parseInt(str.substring(start, end));
	}

	public void receive(int n) {
		values.add(n);
		if (values.size() == 2) {
			//giveAway
		}
	}
}
