var assert = require('assert');

function parse (str) {
  var floors = [];
  floors.elevator = 0;
  floors.things = 0;
  var lines = str.split('\n');
  for (var line of lines) {
    var floor = [];
    var things = line.match(/(\w+)( generator|-compatible microchip)/g);
    things ? things.forEach(t => {
      floor.push(Thing(t));
      floors.things += 1;
    }) : null;
    floors.push(floor);
  }
  return floors;
}

function Thing (str) {
  var generator = str.indexOf('generator') !== -1;
  var chip = str.indexOf('microchip') !== -1;
  var type = str[0].toUpperCase() + str[1] + str[2];
  var short = type + (generator ? 'G' : 'M');
  return { str, short, type, generator, chip };
}

/**
 * @parameter root {Array} Start configuration.
 */
function ShortestPath (root) {
  var currentSteps = 0;
  /**
   * Call `next()` until we reach a solution. Print it.
   */
  function solve () {
    while (true) {  // eslint-disable-line
      next();
      currentSteps += 1;
      var configurations = getLevel(root, currentSteps);
      process.stdout.write(`\rCurrent steps: ${currentSteps}, Configurations: ${configurations.length}`);
      for (var floors of configurations) {
        if (floors[3].length === root.things) {
          // finished, print solution
          console.log();  // new line
          while (floors.parent) {
            console.log(print(floors));
            floors = floors.parent;
          }
          console.log(print(floors));  // print root too
          console.log(`Solved with ${currentSteps} steps.`);
          return currentSteps;
        }
      }
    }
  }

  /**
   * create a new child with `things` moved in `direction`
   * returns null if new child is not allowed state
   */
  function move (parent, things, direction) {
    if (parent.elevator + direction < 0 ||
        parent.elevator + direction > parent.length - 1) {
      return null;
    }
    var child = copy(parent);
    child.parent = parent;
    child.elevator += direction;
    child.steps += 1;
    // remove from previous floor
    child[parent.elevator].remove(things);
    // add to next floor
    child[child.elevator].add(things);
    return allowedState(child) ? child : null;
  }

  var tried = [];
  /**
   * Check if state is allowed.
   */
  function allowedState (floors) {
    var equivalent = equivalentFloors(floors);
    if (tried.indexOf(equivalent) !== -1) {
      return false;
    }
    for (var floor of floors) {
      if (!allowedFloor(floor)) {
        return false;
      }
    }
    tried.push(equivalent);
    return true;
  }

  /**
   * Get floors x steps from root.
   */
  function getLevel (root, x) {
    if (x === 0) {
      return [root];
    } else {
      return flatten(root.next.map(f => getLevel(f, x - 1)));
    }
  }

  /**
   * calculate next allowed state for all configurations
   * at current level
   **/
  function next () {
    var levels = getLevel(root, currentSteps);
    levels.forEach(floors => {
      var n = [];
      var elevator = floors.elevator;
      for (var thing of floors[elevator]) {
        for (var thing2 of floors[elevator]) {
          if (thing === thing2) {
            continue;
          }
          // two up
          n.push(move(floors, [thing, thing2], +1));
          // two down
          n.push(move(floors, [thing, thing2], -1));
        }
        // one down
        n.push(move(floors, [thing], -1));
        // one up
        n.push(move(floors, [thing], +1));
      }
      floors.next = n.filter(i => i);  // remove unallowed configurations
    });
  }

  return { solve, next, getLevel };
}

// Array helper functions, add/remove several items
Array.prototype.remove = function (items) {
  items.forEach(item => {
    var i = this.indexOf(item);
    if (i !== -1) {
      this.splice(i, 1);
    }
  });
  return this;
};
assert.deepEqual([1,2,3].remove([1,2]), [3]);

Array.prototype.add = function (items) {
  items.forEach(i => this.push(i));
  return this;
};
assert.deepEqual([1,2,3].add([1,2]), [1,2,3,1,2]);

function copy (arr) {
  var c = arr.map(f => f.slice());
  for (var key in arr) {
    if (isNaN(key) && arr.hasOwnProperty(key)) {
      c[key] = arr[key];
    }
  }
  return c;
}

function flatten (arr) {
  return arr.reduce((res, f) => res.concat(f), []);
}

/**
 * Pretty-print of given state.
 */
function print (floors) {
  if (!floors) {
    return;
  }
  var str = '';
  for (var i = 0; i < floors.length; i += 1) {
    var e = i !== floors.elevator ? i + 1 : 'X';
    e += ': ';
    str += e + floors[i].map(t => t.short).join(' ') + '  ---  ';
  }
  return str;
}

function allowedFloor (floor) {
  for (var thing of floor) {
    if (thing.chip) {
      var equalGen = floor.find(t => t.generator && t.type === thing.type);
      var otherGen = floor.find(t => t.generator && t.type !== thing.type);
      if (otherGen && !equalGen) {
        return false;
      }
    }
  }
  return true;
}

// Create a hash of the floors which shows number of pairs,
// generators, chips and elevator
function equivalentFloors (floors) {
  var h = '';
  for (var j = 0; j < floors.length; j += 1) {
    floors[j].sort((a,b) => a.short > b.short);
    var pairs = 0;
    var chips = 0;
    var generators = 0;
    for (var i = 0; i < floors[j].length; i += 1) {
      if (floors[j][i] === (floors[j][i + 1]||'').type) {
        pairs += 1;
      } else if (floors[j][i].generator) {
        generators += 1;
      } else {
        chips += 1;
      }
    }
    h += `P${pairs}C${chips}G${generators}${floors.elevator === j ? 'E':''}-`;
  }
  return h;
}


var testCase = parse(`The first floor contains a hydrogen-compatible microchip and a lithium-compatible microchip.
The second floor contains a hydrogen generator.
The third floor contains a lithium generator.
The fourth floor contains nothing relevant.`);

assert.equal(ShortestPath(testCase).solve(), 11);


function getInput () {
  return `The first floor contains a promethium generator and a promethium-compatible microchip.
The second floor contains a cobalt generator, a curium generator, a ruthenium generator, and a plutonium generator.
The third floor contains a cobalt-compatible microchip, a curium-compatible microchip, a ruthenium-compatible microchip, and a plutonium-compatible microchip.
The fourth floor contains nothing relevant.`;
}

ShortestPath(parse(getInput())).solve();
