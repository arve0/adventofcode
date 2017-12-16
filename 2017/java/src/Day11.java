/**
 * Day11
 */
public class Day11 {

  public static void main(String[] args) {
    int x = 0;
    int y = 0;

    String[] directions = Utils.readLinesToList("input11.txt").get(0).split(",");

    int max = 0;
    for (String direction : directions) {
      Diff d = new Diff(direction);
      x += d.x;
      y += d.y;
      if (length(x, y) > max) {
        max = length(x, y);
      }
    }

    System.out.print(String.format("(%s,%s) ", x, y));
    System.out.println(length(x, y));

    System.out.println(max);
  }

  static int length(int x, int y) {
    int distance = 0;
    Diff d;
    while (Math.abs(x) > 0) {
      if (x > 0 && y > 0) {
        d = new Diff("ne");
      } else if (x > 0 && y < 0) {
        d = new Diff("se");
      } else if (x < 0 && y > 0) {
        d = new Diff("nw");
      } else if (x < 0 && y < 0) {
        d = new Diff("sw");
      } else {
        // y == 0, north or south does not matter
        d = new Diff(x < 0 ? "sw" : "se");
      }
      x -= d.x;
      y -= d.y;
      distance++;
    }
    while (Math.abs(y) > 0) {
      if (y > 0) {
        y -= 2;
      } else if (y < 0) {
        y += 2;
      }
      distance++;
    }
    return distance;
  }
}

class Diff {
  int x;
  int y;

  Diff(String direction) {
    switch (direction) {
      case "n":
        x = 0; y = 2;
        break;
      case "ne":
        x = 1; y = 1;
        break;
      case "se":
        x = 1; y = -1;
        break;
      case "s":
        x = 0; y = -2;
        break;
      case "sw":
        x = -1; y = -1;
        break;
      case "nw":
        x = -1; y = 1;
        break;
      default:
        throw new RuntimeException("direction " + direction + " not allowed");
    }
  }
}
