import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Day18
 */
public class Day18 {
  Map<String, Long> registers = new HashMap<>();
  List<Instruction> instructions = Utils.readLines("input18.txt")
    .map(Instruction::new)
    .collect(Collectors.toList());
  int pos = 0;
  long lastPlayed = 0;

  public static void main(String[] args) {
    new Day18();
  }

  Day18() {
    for (Instruction i : instructions) {
      registers.put(i.x, 0L);
      if (i.y != null && i.yy == null) {
        registers.put(i.y, 0L);
      }
    }
    while (true) {
      Instruction instruction = instructions.get(pos);
      if (!instruction.type.equals("jgz")) {
        pos++;
      }

      switch (instruction.type) {
        case "set":
          set(instruction);
          break;
        case "add":
          add(instruction);
          break;
        case "mul":
          mul(instruction);
          break;
        case "mod":
          mod(instruction);
          break;
        case "jgz":
          jgz(instruction);
          break;
        case "snd":
          snd(instruction);
          break;

        case "rcv":
          if (registers.get(instruction.x) != 0) {
            System.out.println(lastPlayed);
            return;
          }
          break;

        default:
          break;
      }
    }
  }

  void set(Instruction i) {
    if (i.yy != null) {
      registers.put(i.x, i.yy);
    } else {
      registers.put(i.x, registers.get(i.y));
    }
  }

  void add(Instruction i) {
    if (i.yy != null) {
      registers.put(i.x, registers.get(i.x) + i.yy);
    } else {
      registers.put(i.x, registers.get(i.x) + registers.get(i.y));
    }
  }

  void mul(Instruction i) {
    if (i.yy != null) {
      registers.put(i.x, registers.get(i.x) * i.yy);
    } else {
      registers.put(i.x, registers.get(i.x) * registers.get(i.y));
    }
  }

  void mod(Instruction i) {
    if (i.yy != null) {
      registers.put(i.x, registers.get(i.x) % i.yy);
    } else {
      registers.put(i.x, registers.get(i.x) % registers.get(i.y));
    }
  }

  void jgz(Instruction i) {
    if (registers.get(i.x) <= 0) {
      pos++;
      return;
    }
    if (i.yy != null) {
      pos += i.yy;
    } else {
      pos += registers.get(i.y);
    }
  }

  void snd(Instruction i) {
    lastPlayed = registers.get(i.x);
  }
}

class Instruction {
  String type;
  String x;
  String y;
  Long xx;
  Long yy;

  Instruction(String str) {
    // snd X
    // rcv X
    // set X Y
    // add X Y
    // mul X Y
    // mod X Y
    // jgz X Y
    String[] input = str.split(" ");
    type = input[0];
    x = input[1];
    if (input.length == 3) {
      y = input[2];
    } else if (input.length < 2 || input.length > 3) {
      throw new RuntimeException("Unknown instruction: " + str);
    }
    parseXY();
  }

  void parseXY() {
    try {
      xx = Long.parseLong(x);
    } catch (Exception e) { }
    try {
      yy = Long.parseLong(y);
    } catch (Exception e) { }
  }
}
