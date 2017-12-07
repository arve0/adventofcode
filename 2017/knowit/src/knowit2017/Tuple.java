package knowit2017;

public class Tuple<T, U> {
  final T first;
  final U second;

	Tuple(T f, U s) {
    first = f;
    second = s;
  }

  public String toString() {
    return "(" + first + ", " + second + ")";
  }
}
