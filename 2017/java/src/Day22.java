import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
  Map<Coordinate, State> map2 = new HashMap<>();
  Worm2 worm2 = new Worm2();

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

    // part two
    for (int i = 0; i < rows; i++) {
      String row = input.get(i);
      for (int j = 0; j < columns; j++) {
        if (row.charAt(j) == '#') {
          map2.put(new Coordinate(i - yOffset, j - xOffset), State.INFECTED);
        }
      }
    }

    infections = 0;
    ITERATIONS = 10_000_000;
    for (int i = 0; i < ITERATIONS; i++) {
      if (worm2.burst(map2)) {
        infections++;
      }
    }
    System.out.println(infections);

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

  public Direction reverse() {
    return values()[(this.ordinal() + 2) % values().length];
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

enum State {
  WEAKENED, INFECTED, FLAGGED, CLEAN;

  public State next() {
    return values()[(this.ordinal() + 1) % values().length];
  }
}

class Worm2 extends Worm {
  boolean burst(Map<Coordinate, State> map) {
    State s = map.get(position);

    if (s == null) {
      s = State.CLEAN;
    }

    switch (s) {
      case CLEAN:
        direction = direction.left();
        break;
      case WEAKENED:
        // do not turn
        break;
      case INFECTED:
        direction = direction.right();
        break;
      case FLAGGED:
        direction = direction.reverse();
        break;
    }
    map.put(position, s.next());
    position = position.move(direction);

    return s.next() == State.INFECTED ? true : false;
  }
}
