import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Day22
 */
public class Day22 {
  List<String> input = Utils.readLinesToList("input22.txt");
  int rows = input.size();
  int columns = input.get(0).length();
  int yOffset = rows / 2;
  int xOffset = columns / 2;
  Set<Coordinate> map = new HashSet<>();
  Worm worm = new Worm();
  int ITERATIONS = 10_000;

  public static void main(String[] args) {
    new Day22();
  }

  Day22() {
    for (int i = 0; i < rows; i++) {
      String row = input.get(i);
      for (int j = 0; j < columns; j++) {
        if (row.charAt(j) == '#') {
          map.add(new Coordinate(i - yOffset, j - xOffset));
        }
      }
    }

    int infections = 0;
    for (int i = 0; i < ITERATIONS; i++) {
      if (worm.burst(map)) {
        infections++;
      }
    }

    System.out.println(infections);
    // System.out.println(mapToString());
  }

  String mapToString() {
    String s = "";
    int minY = 0;
    int maxY = 0;
    int minX = 0;
    int maxX = 0;
    for (Coordinate c : map) {
      minY = c.y < minY ? c.y : minY;
      maxY = c.y > maxY ? c.y : maxY;
      minX = c.x < minX ? c.x : minX;
      maxX = c.x > maxX ? c.x : maxX;
    }
    maxY++;
    maxX++;

    for (int i = minY; i < maxY; i++) {
      for (int j = minX; j < maxX; j++) {
        if (worm.position.y == i && worm.position.x == j) {
          s += "*";
        } else if (map.contains(new Coordinate(i, j))) {
          s += "#";
        } else {
          s += ".";
        }
      }
      s += "\n";
    }
    return s;
  }
}

enum Direction {
  NORTH, EAST, SOUTH, WEST;

  public Direction right() {
    return values()[(this.ordinal() + 1) % values().length];
  }

  public Direction left() {
    int i  = Math.floorMod(this.ordinal() - 1, values().length);
    return values()[i];
  }
}

class Worm {
  Direction direction = Direction.NORTH;
  Coordinate position = new Coordinate(0, 0);

  boolean burst(Set<Coordinate> map) {
    if (map.contains(position)) {
      direction = direction.right();
      map.remove(position);
      position = position.move(direction);
      return false;
    } else {
      direction = direction.left();
      map.add(position);
      position = position.move(direction);
      return true;
    }
  }
}

class Coordinate {
  int x;
  int y;

  Coordinate(int y, int x) {
    this.y = y;
    this.x = x;
  }

  Coordinate move(Direction direction) {
    Coordinate newPosition = new Coordinate(y, x);
    switch (direction) {
      case NORTH:
        newPosition.y -= 1;
        break;
      case SOUTH:
        newPosition.y += 1;
        break;
      case EAST:
        newPosition.x += 1;
        break;
      case WEST:
        newPosition.x -= 1;
        break;
    }
    return newPosition;
  }

  public String toString() {
    return y + "," + x;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof Coordinate)) {
      return false;
    }
    Coordinate c = (Coordinate) o;

    return y == c.y && x == c.x;
  }

  @Override
  public int hashCode() {
    return y + x;
  }
}
