var assert = require('assert');

var discs = [
  { positions: 5, start: 2 },
  { positions: 13, start: 7 },
  { positions: 17, start: 10 },
  { positions: 3, start: 2 },
  { positions: 19, start: 9 },
  { positions: 7, start: 0 }
]

function solve (discs) {
  var time = 0;
  while (!getsThroughAll(discs, time)) {
    time += 1;
  }
  return time;
}

function getsThroughAll (discs, time) {
  for (var disc of discs) {
    time += 1;
    if ((disc.start + time) % disc.positions !== 0) {
      return false;
    }
  }
  return true;
}

assert.equal(solve([
  { positions: 5, start: 4 },
  { positions: 2, start: 1 }
]), 5);

console.log(solve(discs));


// part two
discs.push({ positions: 11, start: 0 });
console.log(solve(discs));
