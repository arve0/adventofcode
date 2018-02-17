import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Day20
 */
public class Day20 {
  List<Particle> particles = new ArrayList<>();

  public static void main(String[] args) {
    new Day20();
  }

  Day20() {
    for (String line : Utils.readLinesToList("input20.txt")) {
      particles.add(new Particle(line));
    }

    for (int i = 0; i < 1E5; i++) {
      tick();
    }

    Particle closest = particles.get(0);
    for (Particle p : particles) {
      if (p.distanceToOrigo() < closest.distanceToOrigo()) {
        closest = p;
      }
    }

    System.out.println(closest);

    // part two
    particles = new ArrayList<>();
    for (String line : Utils.readLinesToList("input20.txt")) {
      particles.add(new Particle(line));
    }

    for (int i = 0; i < 1E4; i++) {
      tick();
      removeCollitions();
    }

    System.out.println(particles.size());
  }

  void tick() {
    for (Particle p : particles) {
      p.tick();
    }
  }

  void removeCollitions() {
    Set<Particle> collitions = new HashSet<>();
    for (Particle p : particles) {
      if (collitions.contains(p)) {
        continue;
      }
      for (Particle pp : particles) {
        if (p == pp) {
          continue;
        }
        if (p.position.equals(pp.position)) {
          collitions.add(p);
          collitions.add(pp);
        }
      }
    }
    particles.removeAll(collitions);
  }
}

class Particle {
  String input;
  Vector position;
  Vector velocity;
  Vector acceleration;
  int n;
  static int i = 0;

  Particle(String input) {
    this.input = input;
    String[] inputs = input.split(", ");
    this.position = new Vector(inputs[0].substring(2));
    this.velocity = new Vector(inputs[1].substring(2));
    this.acceleration = new Vector(inputs[2].substring(2));
    this.n = i++;
  }

  void tick() {
    velocity.x += acceleration.x;
    velocity.y += acceleration.y;
    velocity.z += acceleration.z;

    position.x += velocity.x;
    position.y += velocity.y;
    position.z += velocity.z;
  }

  long distanceToOrigo() {
    return Math.abs(position.x) + Math.abs(position.y) + Math.abs(position.z);
  }

  public String toString() {
    return "" + n + ": " + input;
  }
}

class Vector {
  String input;
  long x, y, z;

  Vector(String input) {
    this.input = input; // <1505,2680,-1336>
    input = input.substring(1, input.length() - 1);
    String[] scalars = input.split(",");
    this.x = Long.parseLong(scalars[0], 10);
    this.y = Long.parseLong(scalars[1], 10);
    this.z = Long.parseLong(scalars[2], 10);
  }

  boolean equals(Vector v) {
    return x == v.x && y == v.y && z == v.z;
  }
}
