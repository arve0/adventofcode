import java.util.ArrayList;
import java.util.List;

/**
 * Day21
 */
public class Day21 {
  Matrix image = new Matrix(".#./..#/###");
  List<Transform> transforms = new ArrayList<>();
  int NUMBER_OF_ITERATIONS = 18;
  Action[] permutations = new Action[]{
    Action.IGNORE, Action.ROTATE, Action.ROTATE, Action.ROTATE,
    Action.FLIP, Action.ROTATE, Action.ROTATE, Action.ROTATE
  };

  public static void main(String[] args) {
    new Day21();
  }

  Day21() {
    for (String row : Utils.readLinesToList("input21.txt")) {
      transforms.add(new Transform(row));
    }

    for (int i = 0; i < NUMBER_OF_ITERATIONS; i++) {
      image = transform(image);
      // System.out.println(i + ": " + image.n);
    }

    System.out.println(image.sum());
  }

  Matrix transform(Matrix image) {
    List<Matrix> images = image.n % 2 == 0 ? image.divide(2) : image.divide(3);

    List<Matrix> transformed = new ArrayList<>();

    for (Matrix img : images) {
      for (Action action : permutations) {
        img.rotateFlipOrIgnore(action);
        Transform t = findTransform(img);
        if (t != null) {
          transformed.add(new Matrix(t.to));
          break;
        }
      }
    }

    return new Matrix(transformed);
  }

  Transform findTransform(Matrix from) {
    for (Transform t : transforms) {
      if (from.equals(t.from)) {
        return t;
      }
    }
    return null;
  }

}

class Transform {
  Matrix from;
  String to;
  int n;
  static int i = 1;

  Transform(String s) {
    String[] fromTo = s.split(" => ");
    from = new Matrix(fromTo[0]);
    to = fromTo[1];
    n = i++;
  }

  public String toString() {
    return n + ": " + from.toString("/") + " => " + to;
  }
}

class Matrix {
  boolean[][] matrix;
  int n, m;

  Matrix(boolean[][] arr) {
    this.n = arr.length;
    this.m = arr[0].length;
    this.matrix = arr;
  }

  Matrix(String s) {
    String[] rows = s.split("/");
    this.n = rows.length;
    this.m = rows[0].length();
    this.matrix = new boolean[n][m];

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        matrix[i][j] = rows[i].charAt(j) == '#' ? true : false;
      }
    }
  }

  Matrix(List<Matrix> matrices) {
    int rows = (int) Math.sqrt(matrices.size());
    int nn = matrices.get(0).n;
    this.n = rows * nn;
    this.m = this.n;
    this.matrix = new boolean[n][m];

    for (int x = 0; x < matrices.size(); x++) {
      Matrix matrix = matrices.get(x);
      for (int i = 0; i < matrix.n; i++) {
        int ii = x / rows * nn + i;
        for (int j = 0; j < matrix.m; j++) {
          int jj = (x % rows) * nn + j;
          this.matrix[ii][jj] = matrix.matrix[i][j];
        }
      }
    }
  }

  void rotateFlipOrIgnore(Action action) {
    switch(action) {
      case ROTATE:
        rotate();
        break;
      case FLIP:
        flip();
        break;
      case IGNORE:
      default:
    }
  }

  void rotate() {
    boolean[][] rotated = new boolean[n][m];
    for (int i = 0; i < n; i++) { // y
      int ii = n - i - 1;
      for (int j = 0; j < m; j++) { // x
        rotated[i][j] = matrix[j][ii];
      }
    }
    matrix = rotated;
  }

  void flip() {
    boolean[][] rotated = new boolean[n][m];
    for (int i = 0; i < n; i++) {
      for (int j = m - 1; j >= 0; j--) {
        int jj = m - 1 - j;
        rotated[i][j] = matrix[i][jj];
      }
    }
    matrix = rotated;
  }

  public String toString(String delimiter) {
    String s = n + "x" + n + ":\n";
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        s += matrix[i][j] ? "#" : ".";
      }
      s += i != n - 1 ? delimiter : "";
    }
    return s;
  }

  public String toString() {
    return toString("\n");
  }

  boolean equals(Matrix b) {
    if (n != b.n || m != b.m) {
      return false;
    }
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (matrix[i][j] != b.matrix[i][j]) {
          return false;
        }
      }
    }
    return true;
  }

  List<Matrix> divide(int by) {
    List<Matrix> divided = new ArrayList<>();

    int rows = m / by;
    int cols = n / by;
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        divided.add(getRowCol(i, j, by));
      }
    }

    return divided;
  }

  Matrix getRowCol(int row, int col, int n) {
    Matrix rowCol = new Matrix(new boolean[n][n]);

    for (int i = row * n, ii = 0; i < (row + 1) * n; i++, ii++) {
      for (int j = col * n, jj = 0; j < (col + 1) * n; j++, jj++) {
        rowCol.matrix[ii][jj] = matrix[i][j];
      }
    }

    return rowCol;
  }

  int sum() {
    int s = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        s += matrix[i][j] ? 1 : 0;
      }
    }
    return s;
  }
}

enum Action {
  IGNORE,
  ROTATE,
  FLIP
}
