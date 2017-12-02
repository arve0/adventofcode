package adventofcode;

public class Day1 {

	public static void main(String[] args) {
		String input = "L4, L3, R1, L4, R2, R2, L1, L2, R1, R1, L3, R5, L2, R5, L4, L3, R2, R2, L5, L1, R4, L1, R3, L3, R5, R2, L5, R2, R1, R1, L5, R1, L3, L2, L5, R4, R4, L2, L1, L1, R1, R1, L185, R4, L1, L1, R5, R1, L1, L3, L2, L1, R2, R2, R2, L1, L1, R4, R5, R53, L1, R1, R78, R3, R4, L1, R5, L1, L4, R3, R3, L3, L3, R191, R4, R1, L4, L1, R3, L1, L2, R3, R2, R4, R5, R5, L3, L5, R2, R3, L1, L1, L3, R1, R4, R1, R3, R4, R4, R4, R5, R2, L5, R1, R2, R5, L3, L4, R1, L5, R1, L4, L3, R5, R5, L3, L4, L4, R2, R2, L5, R3, R1, R2, R5, L5, L3, R4, L5, R5, L3, R1, L1, R4, R4, L3, R2, R5, R1, R2, L1, R4, R1, L3, L3, L5, R2, R5, L1, L4, R3, R3, L3, R2, L5, R1, R3, L3, R2, L1, R4, R3, L4, R5, L2, L2, R5, R1, R2, L4, L4, L5, R3, L4";
		int x = 0;
		int y = 0;
		int direction = 0; // 0 north, 1 east, 2 south, 3 west
		
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
			if (direction == 0) {
				y += length;				
			} else if (direction == 1) {
				x += length;
			} else if (direction == 2) {
				y-= length;
			} else {
				x -= length;
			}
//			System.out.println("Direction is " + direction);
//			System.out.println("Coordinate is " + x + "," + y);
		}
		System.out.println("Coordinate is " + x + "," + y);
		System.out.println("Total length is " + (Math.abs(x) + Math.abs(y)));
		
	}

}
