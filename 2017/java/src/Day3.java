/**
 * Day3
 */
public class Day3 {
  public static void main(String[] args) {
    int counter = 1;
    int number = 1;
    int goal = 325489;

    Tuple<Integer, Integer> coordinate = new Tuple<>(0, 0);

    while (number < goal) {
      if (counter % 2 == 0) {
        coordinate.first -= counter;
        coordinate.second -= counter;
        number += 2 * counter;
        counter++;
      } else {
        coordinate.first += counter;
        coordinate.second += counter;
        number += 2 * counter;
        counter++;
      }
    }

    counter--;

    int diff = number - goal;
    if (coordinate.first < 0) {
      // lower corner
      if (diff < counter) {
        // closest path
        coordinate.second += diff;
      } else {
        coordinate.second += counter;
        coordinate.first += diff - counter;
      }
    } else {
      // higher corner
      if (diff < counter) {
        // closest path
        coordinate.second -= diff;
      } else {
        coordinate.second -= counter;
        coordinate.first -= diff - counter;
      }
    }

    System.out.println(Math.abs(coordinate.first) + Math.abs(coordinate.second));
  }
}
