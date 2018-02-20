import java.util.ArrayList;
import java.util.List;

/**
 * Day23
 */
public class Day23 {
  public static void main(String[] args) {
    new CoProcessor().run();

    // part two
    int b = 105_700;
    int c = b + 17_000;

    int h = 0;
    while (true) {
      h += isMultiple(b) ? 1 : 0;
      if (b == c) {
        break;
      }
      b += 17;
    }

    System.out.println(h);
  }

  static boolean isMultiple(int b) {
    int largestPossibleMultiple = b / 2;
    int d = 2;
    while (d < largestPossibleMultiple) {
      if (b % d == 0) return true;
      d++;
    }
    return false;
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

/** part two, input translated

b = 105 700
c = b + 17000

while (true) {
  f = 1
  d = 2
  do {
    e = 2
    do {
      if (d * e - b == 0) f = 0
      e++
    } while (e - b != 0)
    d++
  } while (d - b != 0)
  if (f == 0) h++
  if (b - c == 0) exit
  b += 17
}

optimized

b = 105 700
c = b + 17000

isMultiple(b) {
  d = 2
  do {
    e = 2
    do {
      if (d * e == b) return true
      e++
    } while (e < b / 2)
    d++
  } while (d < b / 2)
  return false
}

while (true) {
  h += isMultiple(b) ? 1 : 0;
  if (b == c) break;
  b += 17
}

*/

