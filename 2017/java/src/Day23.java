import java.util.ArrayList;
import java.util.List;

/**
 * Day23
 */
public class Day23 {
  public static void main(String[] args) {
    new CoProcessor().run();
  }
}

class CoProcessor {
  int pos = 0;
  long[] registers = { 0, 0, 0, 0, 0, 0, 0, 0 };
  List<IInstruction> instructions = new ArrayList<>();
  long mulInvoked = 0;

  CoProcessor() {
    for (String line : Utils.readLinesToList("input23.txt")) {
      instructions.add(new IInstruction(line));
    }
  }

  void run() {
    while (pos < instructions.size()) {
      tick();
    }
    System.out.println(mulInvoked);
  }

  void tick() {
    IInstruction instruction = instructions.get(pos);

    switch(instruction.type) {
      case "set":
        set(instruction);
        pos++;
        break;
      case "sub":
        sub(instruction);
        pos++;
        break;
      case "mul":
        mul(instruction);
        pos++;
        break;
      case "jnz":
        jnz(instruction);
        break;
    }
  }

  void set(IInstruction instruction) {
    if (instruction.ry != null) {
      registers[instruction.rx] = registers[instruction.ry];
    } else {
      registers[instruction.rx] = instruction.yy;
    }
  }

  void sub(IInstruction instruction) {
    if (instruction.ry != null) {
      registers[instruction.rx] -= registers[instruction.ry];
    } else {
      registers[instruction.rx] -= instruction.yy;
    }
  }

  void mul(IInstruction instruction) {
    mulInvoked++;
    if (instruction.ry != null) {
      registers[instruction.rx] *= registers[instruction.ry];
    } else {
      registers[instruction.rx] *= instruction.yy;
    }
  }

  void jnz(IInstruction instruction) {
    Long val = instruction.xx != null ? instruction.xx : registers[instruction.rx];
    if (val == 0) {
      pos++;
      return;
    }
    if (instruction.ry != null) {
      pos += registers[instruction.ry];
    } else {
      pos += instruction.yy;
    }
  }

}

class IInstruction {
  String str;
  String type;
  String x;
  String y;
  Long xx;
  Long yy;
  Integer rx;
  Integer ry;

  IInstruction(String str) {
    this.str = str;
    String[] input = str.split(" ");
    type = input[0];
    x = input[1];
    y = input[2];
    parseXY();
  }

  void parseXY() {
    try {
      xx = Long.parseLong(x);
    } catch (Exception e) {
      rx = x.codePointAt(0) - 'a';
    }
    try {
      yy = Long.parseLong(y);
    } catch (Exception e) {
      ry = y.codePointAt(0) - 'a';
    }
  }
}
