import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;

/**
 * Day13
 */
public class Day13 {
  Firewall firewall;

  public static void main(String[] args) throws IOException {
    new Day13();
  }

  Day13() throws IOException {
    firewall = new Firewall(Utils.readLines("input13.txt")::iterator);
    // firewall = new Firewall(Arrays.asList("0: 3", "1: 2", "4: 4", "6: 4"));

    System.out.println(pass());

    int delay = 0;
    do {
      delay++;
      firewall.reset(delay);
    } while (!canPassWithoutBeingCaught());

    System.out.println(delay);
  }

  int pass() {
    int pos = 0;
    int dest = Collections.max(firewall.keySet()) + 1;
    int severity = 0;

    while (pos < dest) {
      if (firewall.caught(pos)) {
        severity += pos * firewall.get(pos).range;
      }
      pos++;
      firewall.next();
    }

    return severity;
  }

  boolean canPassWithoutBeingCaught() {
    int pos = 0;
    int dest = Collections.max(firewall.keySet()) + 1;

    while (pos < dest) {
      if (firewall.caught(pos)) {
        return false;
      }
      pos++;
      firewall.next();
    }

    return true;
  }
}

class Firewall extends HashMap<Integer, Layer> {
  static final long serialVersionUID = 0;

  Firewall(Iterable<String> input) {
    for (String str : input) {
      Layer l = new Layer(str);
      put(l.depth, l);
    }
  }

  void next() {
    for (Layer l : values()) {
      l.next();
    }
  }

  boolean caught(int pos) {
    Layer l = get(pos);
    if (l == null) {
      return false;
    }
    return l.pos == 0;
  }

  void reset(int delay) {
    for (Layer l : values()) {
      l.delay(delay);
    }
  }
}

class Layer {
  int depth;
  int range;
  int pos = 0;
  int dir = 1;

  Layer(String str) {
    // 0: 3
    depth = Integer.parseInt(str.split(":")[0]);
    range = Integer.parseInt(str.split(": ")[1]);
  }

  void next() {
    pos += dir;
    if (pos == range - 1) {
      dir = -1;
    } else if (pos == 0) {
      dir = 1;
    }
  }

  void delay(int d) {
    int a = (range - 1) * 2;
    int b = (range - 1);
    int c = Math.floorMod(d, a);
    if (c < range) {
      pos = c;
      if (c == range - 1) {
        dir = -1;
      } else {
        dir = 1;
      }
    } else {
      pos = Math.floorMod(-d, b);
      if (pos == 0) {
        dir = 1;
      } else {
        dir = -1;
      }
    }
  }
}
