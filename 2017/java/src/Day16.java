import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Day16
 */
public class Day16 {
  List<String> list = Arrays.asList("abcdefghijklmnop".split(""));
  Set<List<String>> lists = new LinkedHashSet<>();
  String input = Utils.readLinesToList("input16.txt").get(0);
  Cmds cmds = new Cmds(input.split(","));

  public static void main(String[] args) {
    new Day16();
  }

  Day16() {
    lists.add(new ArrayList<>(list));

    dance();
    System.out.println(String.join("", list));

    int limit = 1000_000_000;
    int i;
    for (i = 1; i < limit; i++) {
      if (!dance()) {
        int n = i + 1;
        while ((i + n) < limit) {
          i += n;
        }
        n++;
      }
    }

    System.out.print(String.join("", list));
  }

  boolean dance() {
    for (Cmd cmd : cmds) {
      if (cmd.spin) {
        Collections.rotate(list, cmd.s);
      } else if (cmd.exchange) {
        Collections.swap(list, cmd.xa, cmd.xb);
      } else if (cmd.partner) {
        partner(cmd);
      }
    }
    return lists.add(new ArrayList<>(list));
  }

  void partner(Cmd cmd) {
    int a = list.indexOf(cmd.pa);
    int b = list.indexOf(cmd.pb);
    Collections.swap(list, a, b);
  }
}

class Cmds extends ArrayList<Cmd> {
  static final long serialVersionUID = 0;

  Cmds(String[] cmds) {
    for (String cmd : cmds) {
      add(new Cmd(cmd));
    }
  }
}

class Cmd {
  boolean spin = false;
  boolean exchange = false;
  boolean partner = false;

  int s = 0;
  int xa = 0;
  int xb = 0;
  String pa = "";
  String pb = "";

  Cmd(String str) {
    spin = str.startsWith("s");
    exchange = str.startsWith("x");
    partner = str.startsWith("p");

    str = str.substring(1);

    if (spin) {
      s = Integer.parseInt(str);
    } else if (exchange) {
      xa = Integer.parseInt(str.split("/")[0]);
      xb = Integer.parseInt(str.split("/")[1]);
    } else if (partner) {
      pa = str.split("/")[0];
      pb = str.split("/")[1];
    }
  }
}
