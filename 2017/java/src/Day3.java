import java.util.Arrays;
import java.util.Collections;

/**
 * Day3
 */
public class Day3 {

  public static void main(String[] args) {
    Map map = new Map();
    System.out.println(map.fill());

    map = new Map();
    System.out.println(map.fill2());
  }
}

class Map {
  int counter = 1;
  int goal = 325489;
  int size = (int) Math.sqrt(goal) + 1;
  int[][] map = new int[size][size];
  int start = size / 2;
  int[] coordinate = { start, start };
  int[][] directions = new int[][]{
    { 0 , -1 }, // south
    { 1, 0 },   // east
    { 0, 1 },   // north
    { -1, 0 },  // west
  };

  Map() {
    map[start][start] = 1;
  }

  int[] getNextCoordinate() {
    int xx = coordinate[0] + directions[1][0];
    int yy = coordinate[1] + directions[1][1];

    if (map[xx][yy] == 0) {
      Collections.rotate(Arrays.asList(directions), -1);
    } else {
      xx = coordinate[0] + directions[0][0];
      yy = coordinate[1] + directions[0][1];
    }

    coordinate[0] = xx;
    coordinate[1] = yy;

    return new int[]{ xx, yy };
  }

  int fill() {
    int[] xy = { 0, 0 };
    while (goal > counter++) {
      xy = getNextCoordinate();
      map[xy[0]][xy[1]] = counter;
    }
    return Math.abs(xy[0] - start) + Math.abs(xy[1] - start);
  }

  int fill2() {
    int[] xy = { 0, 0 };
    int value;
    do {
      xy = getNextCoordinate();
      value = 0;
      for (int x = -1; x <= 1; x++) {
        for (int y = -1; y <= 1; y++) {
          if (x == 0 && y == 0) {
            continue;
          }
          value += map[xy[0] + x][xy[1] + y];
        }
      }
      map[xy[0]][xy[1]] = value;
    } while (goal > value);

    return value;
  }
}
