var input = getInput();
var directions = ['N', 'W', 'S', 'E'];
var currentDirection = 0;
// positive positions N and W
var position = [0, 0];

var instructions = input.split(', ');

var visited = ['' + position]; // cast positions to strings

instructions.forEach((instruction) => {
  currentDirection += (instruction[0] === 'L') ? -1 : 1;
  // + 4 to avoid negative numbers
  currentDirection = (currentDirection + 4) % 4;

  var length = parseInt(instruction.slice(1));

  var dx = 0;
  var dy = 0;
  switch (directions[currentDirection]) {
    case 'N':
      dy = length;
      break;
    case 'E':
      dx = length;
      break;
    case 'S':
      dy = -length;
      break;
    case 'W':
      dx = -length;
      break;
  }
  visitAll(position, dx, dy);
});

var eastWest = position[0] < 0 ? 'east' : 'west';
var northSouth = position[1] < 0 ? 'south' : 'north';

var abs = Math.abs;

console.log(`You should go ${abs(position[0])} blocks ${eastWest} and ${abs(position[1])} blocks ${northSouth}.`);
console.log(`The total distance is ${abs(position[0]) + abs(position[1])} blocks.`);

/**
 * Puts every position in visited. Prints warning if point
 * have been visited before.
 *
 * NB: Mutates pos. The repetition irritates me.
 */
function visitAll (pos, dx, dy) {
  while (dx > 0) {
    pos[0] += 1;
    visit(pos);
    dx -= 1;
  }
  while (dx < 0) {
    pos[0] -= 1;
    visit(pos);
    dx += 1;
  }
  while (dy > 0) {
    pos[1] += 1;
    visit(pos);
    dy -= 1;
  }
  while (dy < 0) {
    pos[1] -= 1;
    visit(pos);
    dy += 1;
  }
}

var foundIntersection = false;
function visit (pos) {
  var abs = Math.abs;  // damn you hoisting
  if (!foundIntersection && visited.indexOf('' + pos) !== -1) {
    foundIntersection = true;
    console.log(`Position ${pos} visited before, whichs is ${abs(pos[0]) + abs(pos[1])} blocks away.`);
  }
  visited.push('' + pos);
}

function getInput () {
  return 'L4, L3, R1, L4, R2, R2, L1, L2, R1, R1, L3, R5, L2, R5, L4, L3, R2, R2, L5, L1, R4, L1, R3, L3, R5, R2, L5, R2, R1, R1, L5, R1, L3, L2, L5, R4, R4, L2, L1, L1, R1, R1, L185, R4, L1, L1, R5, R1, L1, L3, L2, L1, R2, R2, R2, L1, L1, R4, R5, R53, L1, R1, R78, R3, R4, L1, R5, L1, L4, R3, R3, L3, L3, R191, R4, R1, L4, L1, R3, L1, L2, R3, R2, R4, R5, R5, L3, L5, R2, R3, L1, L1, L3, R1, R4, R1, R3, R4, R4, R4, R5, R2, L5, R1, R2, R5, L3, L4, R1, L5, R1, L4, L3, R5, R5, L3, L4, L4, R2, R2, L5, R3, R1, R2, R5, L5, L3, R4, L5, R5, L3, R1, L1, R4, R4, L3, R2, R5, R1, R2, L1, R4, R1, L3, L3, L5, R2, R5, L1, L4, R3, R3, L3, R2, L5, R1, R3, L3, R2, L1, R4, R3, L4, R5, L2, L2, R5, R1, R2, L4, L4, L5, R3, L4';
}
