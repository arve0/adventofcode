import java.util.ArrayList;
import java.util.List;

/**
 * Day7
 */
public class Day7 {
  public static void main(String[] args) {
    List<Node> nodes = new ArrayList<>();

    for (String row : Utils.readLinesToList("input7.txt")) {
      nodes.add(new Node(row));
    }

    for (Node node : nodes) {
      node.addLinks(nodes);
    }

    Node root = null;
    for (Node node : nodes) {
      if (node.parent == null) {
        System.out.println("Root: " + node);
        root = node;
        break;
      }
    }

    addUpWeights(root);
    Node unstable = findUnstableNode(root);

    for (Node child : unstable.children) {
      System.out.println(child);
    }
  }

  static void addUpWeights(Node node) {
    for (Node child : node.children) {
      addUpWeights(child);
      node.weight += child.weight;
    }
  }

  static Node findUnstableNode(Node node) {
    for (Node child : node.children) {
      if (!isWeightsOk(child)) {
        return findUnstableNode(child);
      }
    }
    return node;
  }

  static boolean isWeightsOk(Node node) {
    if (node.children.size() == 0) {
      return true;
    }
    int weight = node.children.get(0).weight;
    for (Node child : node.children) {
      if (weight != child.weight) {
        return false;
      }
    }
    return true;
  }
}

class Node {
  String name;
  String[] childs;
  List<Node> children = new ArrayList<>();
  Node parent;
  int size;
  int weight;

  Node(String row) {
    parseRow(row);
  }

  private void parseRow(String row) {
    int i = row.indexOf(' ');
    name = row.substring(0, i);

    i = row.indexOf('>');
    if (i > 0) {
      childs = row.substring(i + 2).split(", ");
    } else {
      childs = new String[0];
    }

    i = row.indexOf('(');
    int j = row.indexOf(')');
    size = Integer.parseInt(row.substring(i + 1, j));
    weight = size;
  }

  void addLinks(List<Node> nodes) {
    for (String child : childs) {
      for (Node node : nodes) {
        if (node.name.equals(child)) {
          node.parent = this;
          this.children.add(node);
        }
      }
    }
  }

  public String toString() {
    return String.format("%s s:%d w:%d", name, size, weight);
  }
}
