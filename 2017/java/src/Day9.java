/**
 * Day9
 */
public class Day9 {
  public static void main(String[] args) {
    String input = Utils.readLinesToList("input9.txt").get(0);

    int level = 0;
    int i = 0;
    int score = 0;
    boolean garbage = false;
    int discarded = 0;

    while (i < input.length()) {
      int ch = input.charAt(i);
      if (ch == '!') {
        i += 1; // jump past next char
      } else if (ch == '<' && !garbage) {
        garbage = true;
      } else if (ch == '>' && garbage) {
        garbage = false;
      } else if (ch == '{' && !garbage) {
        level += 1;
      } else if (ch == '}' && !garbage) {
        score += level;
        level -= 1;
      } else if (garbage) {
        discarded++;
      }
      i += 1;
    }

    System.out.println(score);
    System.out.println(discarded);
  }
}
