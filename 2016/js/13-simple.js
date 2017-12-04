function f (x, y, input) {
  var r = x*x + 3*x + 2*x*y + y + y*y + input;
  var ones = r.toString(2).split('').filter(b => b === '1');
  return ones.length % 2 === 0;
}

function createMap (input, width, height) {
  var map = [];
  map.width = width;
  map.height = height;
  for (var x = 0; x < width; x += 1) {
    map.push([]);
    for (var y = 0; y < height; y += 1) {
      map[x][y] = f(x, y, input);
    }
  }
  map[1][1] = 0;
  return map;
}

function solve (map, predicate) {
  var length = 0;
  while (predicate(map, length)) {
    length += 1;
    for (var x = 0; x < map.width; x += 1) {
      for (var y = 0; y < map.height; y += 1) {
        if (map[x][y] !== length - 1) {
          continue;
        }
        debugger;
        map[x][y - 1] = map[x][y - 1] === true ? length : map[x][y - 1];
        map[x][y + 1] = map[x][y + 1] === true ? length : map[x][y + 1];
        // js forgives us for arr[0][-1] but not arr[-1][0]
        if (x - 1 >= 0) {
          map[x - 1][y] = map[x - 1][y] === true ? length : map[x - 1][y];
        }
        if (x + 1 < map.width) {
          map[x + 1][y] = map[x + 1][y] === true ? length : map[x + 1][y];
        }
      }
    }
  }
}

var map = createMap(1362, 40, 48);
solve(map, (map) => map[31][39] === true);
console.log(map[31][39]);

// part two
map = createMap(1362, 50, 50);
solve(map, (map, length) => length < 50);
var sum = (s, item) => typeof item === 'number' ? s + 1 : s;
console.log(map.reduce((s, row) => s + row.reduce(sum, 0), 0));
