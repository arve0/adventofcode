// Generator A starts with 634
// Generator B starts with 301

/**
 * Day15
 */
public class Day15 {

  public static void main(String[] args) {
    // long a = 65;
    // long b = 8921;
    long a = 634;
    long b = 301;

    long i = 0;
    long count = 0;
    int wrap = (int) Math.pow(2, 16);

    while (i < 40E6) {
      a *= 16807;
      a %= Integer.MAX_VALUE;

      b *= 48271;
      b %= Integer.MAX_VALUE;

      if ((a % wrap) == (b % wrap)) {
        count++;
      }

      i++;
    }

    System.out.println(count);

    // problem 2
    // a = 65;
    // b = 8921;
    a = 634;
    b = 301;

    i = 0;
    count = 0;

    while (i < 5E6) {
      a *= 16807;
      a %= Integer.MAX_VALUE;
      while (a % 4 != 0) {
        a *= 16807;
        a %= Integer.MAX_VALUE;
      }

      b *= 48271;
      b %= Integer.MAX_VALUE;
      while (b % 8 != 0) {
        b *= 48271;
        b %= Integer.MAX_VALUE;
      }

      if ((a % wrap) == (b % wrap)) {
        count++;
      }

      i++;
    }

    System.out.println(count);
  }
}
