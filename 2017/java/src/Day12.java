import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Day12
 */
public class Day12 {
  Programs programs;

  public static void main(String[] args) throws IOException {
    new Day12();
  }

  Day12() throws IOException {
    programs = new Programs(Utils.readLines("input12.txt")::iterator);

    Set<Integer> inGroup = new HashSet<>();
    int groups = 0;

    for (Program program : programs) {
      if (inGroup.contains(program.n)) {
        continue;
      }

      LinkedList<Integer> toBeChecked = new LinkedList<>();
      toBeChecked.add(program.n);

      inGroup.addAll(resolveGroup(toBeChecked));
      groups++;
      if (groups == 1) {
        System.out.println(inGroup.size());
      }
    }

    System.out.println(groups);
  }

  Set<Integer> resolveGroup(LinkedList<Integer> toBeChecked) {
    Set<Integer> inGroup = new HashSet<>();

    while (toBeChecked.size() != 0) {
      int i = toBeChecked.pop();

      if (!inGroup.add(i)) {
        continue;
      }

      for (int pipe : programs.get(i).pipes) {
        toBeChecked.add(pipe);
      }
    }

    return inGroup;
  }
}

class Programs extends ArrayList<Program> {
  static final long serialVersionUID = 0;

  Programs(Iterable<String> input) {
    for (String row : input) {
      add(new Program(row));
    }
  }
}

class Program {
  int n;
  List<Integer> pipes = new ArrayList<>();

  Program(String row) {
    // 0 <-> 950, 1039
    n = Integer.parseInt(row.split(" <-> ")[0]);
    for (String p : row.split(" <-> ")[1].split(", ")) {
      pipes.add(Integer.parseInt(p));
    }
  }

  public String toString() {
    return n + ":" + pipes;
  }
}
