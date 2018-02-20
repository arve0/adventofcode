/**
 * Day25
 */
public class Day25 {
  Slot currentSlot = new Slot();
  State[] states = {
    new State("A", 1, "right", "B", 0, "right", "C"),
    new State("B", 0, "left", "A", 0, "right", "D"),
    new State("C", 1, "right", "D", 1, "right", "A"),
    new State("D", 1, "left", "E", 0, "left", "D"),
    new State("E", 1, "right", "F", 1, "left", "B"),
    new State("F", 1, "right", "A", 1, "right", "E")
  };
  int i = 0;
  int checksumSteps = 12_399_302;
  State currentState = states[0];

  public static void main(String[] args) {
    new Day25();
  }

  Day25() {
    while (i < checksumSteps) {
      currentState = currentState.tick();
      i++;
    }
    System.out.println(checksum());
    // 2793 too low
  }

  int checksum() {
    while (currentSlot.left != null) {
      currentSlot = currentSlot.left;
    }
    int sum = 0;
    while (currentSlot.right != null) {
      sum += currentSlot.value;
      currentSlot = currentSlot.right;
    }
    sum += currentSlot.value;
    return sum;
  }

  State getState(String state) {
    for (State s : states) {
      if (s.name.equals(state)) {
        return s;
      }
    }
    return null;
  }

  class State {
    String name;
    int zeroWrite;
    String zeroDirection;
    String zeroState;
    int oneWrite;
    String oneDirection;
    String oneState;

    State(String name, int zeroWrite, String zeroDirection, String zeroState, int oneWrite, String oneDirection, String oneState) {
      this.name = name;
      this.zeroWrite = zeroWrite;
      this.zeroDirection = zeroDirection;
      this.zeroState = zeroState;
      this.oneWrite = oneWrite;
      this.oneDirection = oneDirection;
      this.oneState = oneState;
    }

    State tick() {
      if (currentSlot.value == 0) {
        currentSlot.value = zeroWrite;
        if (zeroDirection == "right") {
          if (currentSlot.right == null) {
            newRightSlot();
          }
          currentSlot = currentSlot.right;
        } else {
          if (currentSlot.left == null) {
            newLeftSlot();
          }
          currentSlot = currentSlot.left;
        }
        return getState(zeroState);
      } else {
        currentSlot.value = oneWrite;
        if (oneDirection == "right") {
          if (currentSlot.right == null) {
            newRightSlot();
          }
          currentSlot = currentSlot.right;
        } else {
          if (currentSlot.left == null) {
            newLeftSlot();
          }
          currentSlot = currentSlot.left;
        }
        return getState(oneState);
      }
    }

    void newRightSlot() {
      currentSlot.right = new Slot();
      currentSlot.right.left = currentSlot;
    }

    void newLeftSlot() {
      currentSlot.left = new Slot();
      currentSlot.left.right = currentSlot;
    }
  }
}

class Slot {
  Slot left = null;
  Slot right = null;
  int value = 0;
}

