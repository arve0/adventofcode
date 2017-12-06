package knowit2017;

import java.util.ArrayList;
import java.util.List;

public class Timer {
	long lastTime = System.nanoTime();
	List<Long> elapsedTimes = new ArrayList<>();
	List<String> what = new ArrayList<>();

	public void elapsed() {
		elapsedTimes.add(System.nanoTime() - lastTime);
		what.add("");
		lastTime = System.nanoTime();
	}

	public void elapsed(String w) {
		elapsedTimes.add(System.nanoTime() - lastTime);
		what.add(w);
		lastTime = System.nanoTime();
	}

	public void print() {
		int i = 0;
		for (long diff : elapsedTimes) {
			String out = what.get(i++) + ":\t";
			if (diff > 1e9) {
				out += String.format("%f s", diff / 1e9);
			} else if (diff > 1e6) {
				out += String.format("%f ms", diff / 1e6);
			} else if (diff > 1e3) {
				out += String.format("%f µs", diff / 1e3);
			} else {
				out += String.format("%d ns", diff);
			}
			System.out.println(out);
		}
	}
}
