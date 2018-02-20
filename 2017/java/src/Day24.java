import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * Day24
 */
public class Day24 {
  List<Bridge> bridges = Utils.readLines("input24.txt")
    .map(Bridge::new)
    .collect(Collectors.toList());

  LinkedList<Bridge> nextBridges = new LinkedList<>(bridges.stream()
    .filter(b -> b.x == 0)
    .collect(Collectors.toList()));

  public static void main(String[] args) {
    new Day24();
  }

  Day24() {
    nextBridges.forEach(b -> {
      b.xConnected = true;
      b.calculateTotalStrength();
    });

    int maxStrength = 0;

    Bridge next = nextBridges.removeFirst();
    Bridge longestBridge = next;

    while (true) {
      if (next.totalStrength > maxStrength) {
        maxStrength = next.totalStrength;
        // System.out.println(maxStrength + ": " + next.connections());
      }

      if (next.length > longestBridge.length) {
        longestBridge = next;
      } else if (next.length == longestBridge.length
        && next.totalStrength > longestBridge.totalStrength) {
          longestBridge = next;
      }

      next.addChildren(bridges);

      for (Bridge b : next.children) {
        nextBridges.addFirst(b);
      }

      try {
        next = nextBridges.removeFirst();
      } catch (NoSuchElementException e) {
        break; // we are done
      }
    }

    System.out.println(maxStrength);
    System.out.println(longestBridge.totalStrength);
  }

}

class Bridge {
  String name;
  int x;
  int y;
  int strength;
  int totalStrength;
  boolean xConnected = false;
  boolean yConnected = false;
  Bridge parent;
  int length = 1;
  List<Bridge> children = new ArrayList<>();

  Bridge(String name) {
    this.name = name;
    x = Integer.parseInt(name.split("/")[0]);
    y = Integer.parseInt(name.split("/")[1]);
    strength = x + y;
  }

  public String toString() {
    return name;
  }

  String canConnectTo(Bridge b) {
    if (equals(b)) {
      return null;
    }
    int connectTo = xConnected ? y : x;
    if (connectTo == b.x) {
      return "x";
    } else if (connectTo == b.y) {
      return "y";
    }
    return null;
  }

  Bridge copy(Bridge parent, String connectTo) {
    Bridge cp = new Bridge(this.name);
    cp.parent = parent;
    cp.xConnected = connectTo == "x";
    cp.yConnected = connectTo == "y";
    cp.calculateTotalStrength();
    cp.length = parent.length + 1;
    return cp;
  }

  List<Bridge> getParents() {
    List<Bridge> parents = new ArrayList<>();
    Bridge p = parent;
    while (p != null) {
      parents.add(p);
      p = p.parent;
    }
    return parents;
  }

  void addChildren(List<Bridge> bridges) {
    if (length == bridges.size()) {
      return;
    }
    List<Bridge> parents = getParents();

    for (Bridge b : bridges) {
      if (parents.contains(b)) {
        continue;
      }
      String connectTo = canConnectTo(b);
      if (connectTo == null) {
        continue;
      }
      Bridge child = b.copy(this, connectTo);
      children.add(child);
    }
  }

  void calculateTotalStrength() {
    totalStrength = strength;
    Bridge p = parent;
    while (p != null) {
      totalStrength += p.strength;
      p = p.parent;
    }
  }

  @Override
  public int hashCode() {
    return name.hashCode();
  }

  @Override
  public boolean equals(Object o) {
    if (o == null) {
      return false;
    }
    Bridge b = (Bridge) o;

    return x == b.x && y == b.y;
  }

  String connections() {
    String s = toString();
    for (Bridge b : getParents()) {
      s = b.toString() + " -> " + s;
    }
    return s;
  }
}
