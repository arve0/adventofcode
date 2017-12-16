import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Day8
 */
public class Day8 {
  Map<String, Long> registers = new HashMap<>();

  public static void main(String[] args) {
    new Day8();
    // 3145 too low
    // 3955 too low
  }

  Day8() {
    List<String> instructions = Utils.readLinesToList("input8.txt");

    // initialize registers
    for (String row : instructions) {
      String name = row.substring(0, row.indexOf(' '));
      registers.put(name, 0L);
    }

    long max = 0;
    for (String row : instructions) {
      Instruction instruction = new Instruction(row);
      if (instruction.checkCondition()) {
        long val = registers.get(instruction.register) + instruction.diff;
        registers.put(instruction.register, val);
        if (val > max) {
          max = val;
        }
      }
    }

    System.out.println(max);

    max = Collections.max(registers.values());

    System.out.println(max);
    System.out.println(registers);
  }

  class Instruction {
    Pattern pattern = Pattern.compile("([^ ]+) ([^ ]+) ([^ ]+) if ([^ ]+) ([^ ]+) ([^ ]+)");
    String register;
    long diff;
    String checkRegister;
    String condition;
    long checkValue;
    String input;

    Instruction(String instruction) {
      this.input = instruction;
      // kd dec -37 if gm <= 9
      Matcher m = pattern.matcher(instruction);
      m.find();
      register = m.group(1);
      String cmd = m.group(2);
      long val = Long.parseLong(m.group(3));
      diff = cmd.equals("inc") ? val : -val;

      checkRegister = m.group(4);
      condition = m.group(5);
      checkValue = Long.parseLong(m.group(6));
    }

    boolean checkCondition() {
      long value = registers.get(checkRegister);

      switch (condition) {
        case "==":
          return value == checkValue;
        case ">=":
          return value >= checkValue;
        case "<=":
          return value <= checkValue;
        case "!=":
          return value != checkValue;
        case "<":
          return value < checkValue;
        case ">":
          return value > checkValue;
        default:
          throw new RuntimeException("condition " + condition + " not allowed");
      }
    }

    public String toString() {
      return input;
    }
  }
}

