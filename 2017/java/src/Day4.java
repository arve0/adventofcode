import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Day4
 */
public class Day4 {

  public static void main(String[] args) {
    int validPassphrases = 0;
    int validPassphrases2 = 0;
    for (String passphrase : Utils.readLinesToList("input4.txt")) {
      if (validPassphrase(passphrase)) {
        validPassphrases++;
      }
      if (validPassphrase2(passphrase)) {
        validPassphrases2++;
      }
    }
    System.out.println(validPassphrases);
    System.out.println(validPassphrases2);
    // 210 too high
  }

  static boolean validPassphrase(String passphrase) {
    List<String> words = Arrays.asList(passphrase.split(" "));
    for (String word : words) {
      if (words.indexOf(word) != words.lastIndexOf(word)) {
        return false;
      }
    }
    return true;
  }

  static boolean validPassphrase2(String passphrase) {
    List<String> words = Arrays.asList(passphrase.split(" "));
    Object[] distinctWords = words.stream()
      .map(word -> sort(word))
      .distinct()
      .toArray();

    return words.size() == distinctWords.length;
  }

  static String sort(String word) {
    List<String> letters = Arrays.asList(word.split(""));
    Collections.sort(letters);
    return String.join("", letters);
  }
}
