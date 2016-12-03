var input = getInput();
var directions = ['N', 'W', 'S', 'E'];
var currentDirection = 0;
// positive positions N and W
var position = [0, 0];

var instructions = input.split(', ');

instructions.forEach((instruction) => {
  currentDirection += (instruction[0] === 'L') ? -1 : 1;
  // + 4 to avoid negative numbers
  currentDirection = (currentDirection + 4) % 4;

  var length = parseInt(instruction.slice(1));

  switch (directions[currentDirection]) {
    case 'N':
      position[1] += length;
    break;
    case 'E':
      position[0] += length;
    break;
    case 'S':
      position[1] -= length;
    break;
    case 'W':
      position[0] -= length;
    break;
  }
});

var eastWest = position[0] < 0 ? 'east' : 'west';
var northSouth = position[1] < 0 ? 'south' : 'north';

var abs = Math.abs;

console.log(`You should go ${abs(position[0])} blocks ${eastWest} and ${abs(position[1])} blocks ${northSouth}.`);
console.log(`The total distance is ${abs(position[0]) + abs(position[1])} blocks.`);

function getInput () {
  return 'L4, L3, R1, L4, R2, R2, L1, L2, R1, R1, L3, R5, L2, R5, L4, L3, R2, R2, L5, L1, R4, L1, R3, L3, R5, R2, L5, R2, R1, R1, L5, R1, L3, L2, L5, R4, R4, L2, L1, L1, R1, R1, L185, R4, L1, L1, R5, R1, L1, L3, L2, L1, R2, R2, R2, L1, L1, R4, R5, R53, L1, R1, R78, R3, R4, L1, R5, L1, L4, R3, R3, L3, L3, R191, R4, R1, L4, L1, R3, L1, L2, R3, R2, R4, R5, R5, L3, L5, R2, R3, L1, L1, L3, R1, R4, R1, R3, R4, R4, R4, R5, R2, L5, R1, R2, R5, L3, L4, R1, L5, R1, L4, L3, R5, R5, L3, L4, L4, R2, R2, L5, R3, R1, R2, R5, L5, L3, R4, L5, R5, L3, R1, L1, R4, R4, L3, R2, R5, R1, R2, L1, R4, R1, L3, L3, L5, R2, R5, L1, L4, R3, R3, L3, R2, L5, R1, R3, L3, R2, L1, R4, R3, L4, R5, L2, L2, R5, R1, R2, L4, L4, L5, R3, L4';
}
