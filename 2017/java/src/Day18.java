import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Day18
 */
public class Day18 {

  public static void main(String[] args) {
    List<Long> tx = new ArrayList<>();
    List<Long> rx = new ArrayList<>();

    Duett program = new Duett(tx, rx);

    while(program.tick()) { }
    System.out.println(program.tx.get(program.tx.size() - 1));

    Duett program2 = new Duett(rx, tx);

    while(program.tick() || program2.tick()) { }
    System.out.println(program2.sentCounter);
  }
}


class Duett {
  Map<String, Long> registers = new HashMap<>();
  List<Instruction> instructions = Utils.readLines("input18.txt")
    .map(Instruction::new)
    .collect(Collectors.toList());
  int pos = 0;
  List<Long> tx;
  List<Long> rx;
  int sentCounter = 0;
  static long n = 0;

  Duett(List<Long> tx, List<Long> rx) {
    this.tx = tx;
    this.rx = rx;

    for (Instruction i : instructions) {
      initializeRegister(i.x);
      initializeRegister(i.y);
    }

    registers.put("p", n++);
  }

  boolean tick() {
    Instruction instruction = instructions.get(pos);

    if (!instruction.type.equals("jgz")) {
      pos++;
    }

    switch (instruction.type) {
      case "set":
        set(instruction);
        return true;
      case "add":
        add(instruction);
        return true;
      case "mul":
        mul(instruction);
        return true;
      case "mod":
        mod(instruction);
        return true;
      case "jgz":
        jgz(instruction);
        return true;
      case "snd":
        snd(instruction);
        return true;

      case "rcv":
        boolean didReceive = rcv(instruction);
        pos -= !didReceive ? 1 : 0;  // step back pos++ from above
        return didReceive;
    }
    return false;
  }

  void initializeRegister(String r) {
    if (r == null) {
      return;
    }
    try {
      Long.parseLong(r);
    } catch(Exception e) {
      registers.put(r, 0L);
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
    Long val = i.xx != null ? i.xx : registers.get(i.x);
    if (val <= 0) {
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
    tx.add(registers.get(i.x));
    sentCounter++;
  }

  boolean rcv(Instruction i) {
    if (rx.size() == 0) {
      return false;
    }
    registers.put(i.x, rx.remove(0));
    return true;
  }
}

class Instruction {
  String str;
  String type;
  String x;
  String y;
  Long xx;
  Long yy;

  Instruction(String str) {
    this.str = str;
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
