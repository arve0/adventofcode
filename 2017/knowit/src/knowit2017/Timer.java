package knowit2017;

public class Timer {
	long startTime = System.nanoTime();

	public String elapsed() {
		long diff = System.nanoTime() - startTime;
		if (diff > 1e6) {
			return String.format("%f ms", diff / 1e6);
		} else if (diff > 1e3) {
			return String.format("%f µs", diff / 1e3);
		}
		return String.format("%f ns", diff);
	}

	public void printElapsed() {
		System.out.println(elapsed());
	}
}
