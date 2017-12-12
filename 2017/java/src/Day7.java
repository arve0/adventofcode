import java.util.ArrayList;
import java.util.Arrays;
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

    for (Node node : nodes) {
      if (node.parent == null) {
        System.out.println(node.name);
      }
    }
  }
}

class Node {
  String name;
  String[] childs;
  Node parent;

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
  }

  void addLinks(List<Node> nodes) {
    for (String child : childs) {
      for (Node node : nodes) {
        if (node.name.equals(child)) {
          node.parent = this;
        }
      }
    }
  }

  public String toString() {
    return "Node " + name;
  }
}
