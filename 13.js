var assert = require('assert');

function f (x, y, input) {
  var r = x*x + 3*x + 2*x*y + y + y*y + input;
  var ones = r.toString(2).split('').filter(b => b === '1');
  return ones.length % 2 === 0;
}

function createMap (width, height, input) {
  var map = [];
  map.width = width;
  map.height = height;
  for (var y = 0; y < height; y += 1) {
    map.push([]);
    for (var x = 0; x < width; x += 1) {
      map[y][x] = f(x, y, input);
    }
  }
  map.toString = () => map.map(row =>
                         row.map(i => i ? '.' : '#').join('')
                       ).join('\n');
  return map;
}

function Pos (x, y, parent) {
  var length = parent ? parent.length + 1 : 0;
  var next = [];

  var me = {
    x, y, parent, length,
    up: move(0, -1),
    down: move(0, 1),
    left: move(-1, 0),
    right: move(1, 0),
    sameAs: (pos) => x === pos.x && y === pos.y
  };

  function move (dx, dy) {
    return function () {
      var p = Pos(x + dx, y + dy, me);
      next.push(p);
      me.next = next;
      return p;
    };
  }

  return me;
}

function reach (x, y, map, predicate) {
  predicate = predicate || function () { return true; };
  var pos = Pos(1, 1);
  var end = Pos(x, y);

  var visited = [pos];
  function hasNotVisisted (p) {
    var been = visited.reduce((been, pos) => been || pos.sameAs(p), false);
    if (!been) {
      visited.push(p);
    }
    return !been;
  }

  while (pos !== undefined && !pos.sameAs(end)) {
    debugger;
    if (pos.next === undefined) {
      pos.up();
      pos.down();
      pos.left();
      pos.right();
      pos.next = pos.next.filter(p => map[p.y] && map[p.y][p.x]
                                      && predicate(p)
                                      && hasNotVisisted(p));
    }
    // pick position with lowest length and no next
    var n = visited.filter(p => p.next === undefined).sort((a, b) => {
      if (a.length > b.length) {
        return 1;
      } else if (a.length < b.length) {
        return -1;
      } else {
        return 0;
      }
    });
    pos = n[0];
  }
  return pos || visited.length;
}

// Array helper functions, add/remove several items
Array.prototype.remove = function (item) {
  var i = this.indexOf(item);
  if (i !== -1) {
    this.splice(i, 1);
  }
  return this;
};

var testMap = createMap(10, 7, 10);
assert.equal(reach(7, 4, testMap).length, 11);

var m = createMap(40, 48, 1362);
console.log(reach(31, 39, m).length);

// part two
m = createMap(50, 50, 1362);
console.log(reach(49, 49, m, (p) => p.length <= 50));
