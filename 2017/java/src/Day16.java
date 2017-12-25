import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Day16
 */
public class Day16 {
  List<String> list = Arrays.asList("abcdefghijklmnop".split(""));
  String input = Utils.readLinesToList("input16.txt").get(0);
  Cmds cmds = new Cmds(input.split(","));
  int pos = 0;
  int len = list.size();

  public static void main(String[] args) {
    new Day16();
  }

  Day16() {
    dance();
    Collections.rotate(list, pos);
    System.out.println(String.join("", list));

    Map<Integer, Integer> map = new HashMap<>();

    int to = 0;
    for (String s : list) {
      int from = s.codePointAt(0) - 'a';
      map.put(from, to++);
    }

    System.out.println(map);

    Map<Integer, Integer> billionDances = new HashMap<>();

    for (int i = 0; i < len; i++) {
      to = i;
      Set<Integer> repeat = new HashSet<>();
      int limit = 1000_000_000;
      for (int j = 0; j < limit; j++) {
        to = map.get(to);
        if (!repeat.add(to)) {
          System.out.println(repeat.size());
''          limit %= repeat.size();
        }
        System.out.println(repeat);
      }
      billionDances.put(i, to);
    }
    System.out.println(billionDances);
  }

  void dance() {
    for (Cmd cmd : cmds) {
      if (cmd.spin) {
        spin(cmd);
      } else if (cmd.exchange) {
        exchange(cmd);
      } else if (cmd.partner) {
        partner(cmd);
      }
    }
  }

  void spin(Cmd cmd) {
    pos += cmd.s;
    pos %= len;
  }

  void exchange(Cmd cmd) {
    int a = -pos + cmd.xa;
    while (a < 0) {
      a += len;
    }
    int b = -pos + cmd.xb;
    while (b < 0) {
      b += len;
    }
    Collections.swap(list, a, b);
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
