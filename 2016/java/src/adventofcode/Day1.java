package adventofcode;

import java.util.HashSet;
import java.util.Set;

public class Day1 {

	public static void main(String[] args) {
		String input = "L4, L3, R1, L4, R2, R2, L1, L2, R1, R1, L3, R5, L2, R5, L4, L3, R2, R2, L5, L1, R4, L1, R3, L3, R5, R2, L5, R2, R1, R1, L5, R1, L3, L2, L5, R4, R4, L2, L1, L1, R1, R1, L185, R4, L1, L1, R5, R1, L1, L3, L2, L1, R2, R2, R2, L1, L1, R4, R5, R53, L1, R1, R78, R3, R4, L1, R5, L1, L4, R3, R3, L3, L3, R191, R4, R1, L4, L1, R3, L1, L2, R3, R2, R4, R5, R5, L3, L5, R2, R3, L1, L1, L3, R1, R4, R1, R3, R4, R4, R4, R5, R2, L5, R1, R2, R5, L3, L4, R1, L5, R1, L4, L3, R5, R5, L3, L4, L4, R2, R2, L5, R3, R1, R2, R5, L5, L3, R4, L5, R5, L3, R1, L1, R4, R4, L3, R2, R5, R1, R2, L1, R4, R1, L3, L3, L5, R2, R5, L1, L4, R3, R3, L3, R2, L5, R1, R3, L3, R2, L1, R4, R3, L4, R5, L2, L2, R5, R1, R2, L4, L4, L5, R3, L4";
		int x = 0;
		int y = 0;
		int direction = 0; // 0 north, 1 east, 2 south, 3 west
		Been been = new Been();
		
		String[] instructions = input.split(", ");
		
		for (String instruction : instructions) {
//			System.out.println(instruction);
			String dir = instruction.substring(0, 1);
			if (dir.equals("L")) {
				direction--;	
			} else if (dir.equals("R")) {
				direction++;
			}
			direction = Math.floorMod(direction, 4);

			int length = Integer.parseInt(instruction.substring(1));
			for (; length > 0; length--) {
				if (direction == 0) {
					y++;		
				} else if (direction == 1) {
					x++;
				} else if (direction == 2) {
					y--;
				} else {
					x--;
				}				
				if (been.visit(x, y)) {
					System.out.println("Been here before: " + Been.toCoordinate(x, y));
					System.out.println("Total length is " + (Math.abs(x) + Math.abs(y)));		
				}
			}
//			System.out.println("Direction is " + direction);
//			System.out.println("Coordinate is " + x + "," + y);
		}
		System.out.println("Finished all instructions");
		System.out.println("Coordinate is " + Been.toCoordinate(x, y));
		System.out.println("Total length is " + (Math.abs(x) + Math.abs(y)));		
	}
}

class Been {
	Set<String> visited = new HashSet<String>();
	Been() {
		visited.add(toCoordinate(0, 0));
	}
	static String toCoordinate(int x, int y) {
		return x + "," + y;
	}
	boolean visit(int x, int y) {
		String coordinate = toCoordinate(x, y);
		if (visited.contains(coordinate)) {
			return true;
		}
		visited.add(coordinate);
		return false;
	}
}