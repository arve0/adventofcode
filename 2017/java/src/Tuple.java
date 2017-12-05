import java.util.Arrays;

public class Tuple<T, U> {
  T first;
  U second;
  Tuple(T f, U s) {
    first = f;
    second = s;
  }

  public String toString() {
    return first + ", " + second;
  }
}
