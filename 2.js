var input = getInput();

/**
 * Part two, keyboardlayout
 *
 *   1          0
 *  234         1
 * 56789        2
 *  ABC         3
 *   D          4
 */
var Position = function (pos) {
  this.x = pos.x;
  this.y = pos.y;
  this.key = () => `${this.x},${this.y}`;
  this.U = () => {
    if (this.y === 0 ||
        (this.y === 1 && (this.x === 1 || this.x === 3)) ||
        (this.y === 2 && (this.x === 0 || this.x === 4))) {
      return;
    }
    this.y -= 1;
  };
  this.D = () => {
    if (this.y === 4 ||
        (this.y === 2 && (this.x === 0 || this.x === 4)) ||
        (this.y === 3 && (this.x === 1 || this.x === 3))) {
      return;
    }
    this.y += 1;
  };
  this.L = () => {
    if (this.x === 0 ||
        (this.y === 0 && this.x === 2) ||
        (this.y === 1 && this.x === 1) ||
        (this.y === 3 && this.x === 1) ||
        (this.y === 4 && this.x === 2)) {
      return;
    }
    this.x -= 1;
  };
  this.R = () => {
    if (this.x === 4 ||
        (this.y === 0 && this.x === 2) ||
        (this.y === 1 && this.x === 3) ||
        (this.y === 3 && this.x === 3) ||
        (this.y === 4 && this.x === 2)) {
      return;
    }
    this.x += 1;
  };
};
var position = new Position({ x: 1, y: 1 });
var instructions = input.split('\n');
var code = '';

instructions.forEach((instruction) => {
  instruction.split("").forEach((letter) => position[letter]());
  code += positionToNumber(position);
});

console.log(`Code is ${code}.`);

/**
 * Part two, keyboardlayout
 *
 *   1          1*0+2-1
 *  234         2*1+1-1, 2*1+2-1
 * 56789        3*2+0-1, 3*2+1-1
 *  ABC
 *   D
 */
function positionToNumber (pos) {
  var letterKeys = {
    '1,3': 'A',
    '2,3': 'B',
    '3,3': 'C',
    '2,4': 'D'
  };
  if (pos.y >= 3) {
    return letterKeys[pos.key()];
  }
  return (pos.y + 1) * pos.y + pos.x - 1;
}

// lazy testing
assert(positionToNumber(new Position({ x: 2, y: 0 })) === 1);
assert(positionToNumber(new Position({ x: 1, y: 1 })) === 2);
assert(positionToNumber(new Position({ x: 2, y: 4 })) === 'D');

function assert (b) {
  if (b !== true) {
    throw new Error();
  }
}

function getInput () {
  return `LRRLLLRDRURUDLRDDURULRULLDLRRLRLDULUDDDDLLRRLDUUDULDRURRLDULRRULDLRDUDLRLLLULDUURRRRURURULURRULRURDLULURDRDURDRLRRUUDRULLLLLDRULDDLLRDLURRLDUURDLRLUDLDUDLURLRLDRLUDUULRRRUUULLRDURUDRUDRDRLLDLDDDLDLRRULDUUDULRUDDRLLURDDRLDDUDLLLLULRDDUDDUUULRULUULRLLDULUDLLLLURRLDLUDLDDLDRLRRDRDUDDDLLLLLRRLLRLUDLULLDLDDRRUDDRLRDDURRDULLLURLRDLRRLRDLDURLDDULLLDRRURDULUDUDLLLDDDLLRLDDDLLRRLLURUULULDDDUDULUUURRUUDLDULULDRDDLURURDLDLULDUDUDDDDD
RUURUDRDUULRDDLRLLLULLDDUDRDURDLRUULLLLUDUDRRUDUULRRUUDDURDDDLLLLRRUURULULLUDDLRDUDULRURRDRDLDLDUULUULUDDLUDRLULRUDRDDDLRRUUDRRLULUULDULDDLRLURDRLURRRRULDDRLDLLLRULLDURRLUDULDRDUDRLRLULRURDDRLUDLRURDDRDULUDLDLLLDRLRUDLLLLLDUDRDUURUDDUDLDLDUDLLDLRRDLULLURLDDUDDRDUDLDDUULDRLURRDLDLLUUDLDLURRLDRDDLLDLRLULUDRDLLLDRLRLLLDRUULUDLLURDLLUURUDURDDRDRDDUDDRRLLUULRRDRULRURRULLDDDUDULDDRULRLDURLUDULDLDDDLRULLULULUDLDDRDLRDRDLDULRRLRLRLLLLLDDDRDDULRDULRRLDLUDDDDLUDRLLDLURDLRDLDRDRDURRDUDULLLDLUDLDRLRRDDDRRLRLLULDRLRLLLLDUUURDLLULLUDDRLULRDLDLDURRRUURDUDRDLLLLLLDDDURLDULDRLLDUDRULRRDLDUDRLLUUUDULURRUR
URRRLRLLDDDRRLDLDLUDRDRDLDUDDDLDRRDRLDULRRDRRDUDRRUUDUUUDLLUURLRDRRURRRRUDRLLLLRRDULRDDRUDLRLUDURRLRLDDRRLUULURLURURUDRULDUUDLULUURRRDDLRDLUDRDLDDDLRUDURRLLRDDRDRLRLLRLRUUDRRLDLUDRURUULDUURDRUULDLLDRDLRDUUDLRLRRLUDRRUULRDDRDLDDULRRRURLRDDRLLLRDRLURDLDRUULDRRRLURURUUUULULRURULRLDDDDLULRLRULDUDDULRUULRRRRRLRLRUDDURLDRRDDULLUULLDLUDDDUURLRRLDULUUDDULDDUULLLRUDLLLRDDDLUUURLDUDRLLLDRRLDDLUDLLDLRRRLDDRUULULUURDDLUR
UULDRLUULURDRLDULURLUDULDRRDULULUDLLDURRRURDRLRLLRLDDLURRDLUUDLULRDULDRDLULULULDDLURULLULUDDRRULULULRDULRUURRRUDLRLURDRURDRRUDLDDUURDUUDLULDUDDLUUURURLRRDLULURDURRRURURDUURDRRURRDDULRULRRDRRDRUUUUULRLUUUDUUULLRRDRDULRDDULDRRULRLDLLULUUULUUDRDUUUDLLULDDRRDULUURRDUULLUUDRLLDUDLLLURURLUDDLRURRDRLDDURLDLLUURLDUURULLLRURURLULLLUURUUULLDLRDLUDDRRDDUUDLRURDDDRURUURURRRDLUDRLUULDUDLRUUDRLDRRDLDLDLRUDDDDRRDLDDDLLDLULLRUDDUDDDLDDUURLDUDLRDRURULDULULUDRRDLLRURDULDDRRDLUURUUULULRURDUUDLULLURUDDRLDDUDURRDURRUURLDLLDDUUDLLUURDRULLRRUUURRLLDRRDLURRURDULDDDDRDD
LLRUDRUUDUDLRDRDRRLRDRRUDRDURURRLDDDDLRDURDLRRUDRLLRDDUULRULURRRLRULDUURLRURLRLDUDLLDULULDUUURLRURUDDDDRDDLLURDLDRRUDRLDULLRULULLRURRLLURDLLLRRRRDRULRUDUDUDULUURUUURDDLDRDRUUURLDRULDUDULRLRLULLDURRRRURRRDRULULUDLULDDRLRRULLDURUDDUULRUUURDRRLULRRDLDUDURUUUUUURRUUULURDUUDLLUURDLULUDDLUUULLDURLDRRDDLRRRDRLLDRRLUDRLLLDRUULDUDRDDRDRRRLUDUDRRRLDRLRURDLRULRDUUDRRLLRLUUUUURRURLURDRRUURDRRLULUDULRLLURDLLULDDDLRDULLLUDRLURDDLRURLLRDRDULULDDRDDLDDRUUURDUUUDURRLRDUDLRRLRRRDUULDRDUDRLDLRULDL`;
}
