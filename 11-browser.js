Array.prototype.remove = function (item) {
  var i = this.indexOf(item);
  if (i !== -1) {
    this.splice(i, 1);
  }
};

var input = document.getElementById('input');

var state;
function start() {
  var lines = (input.value || input.innerHTML).split('\n').filter(l => l.match(/\w/));

  state = [];
  state.elevator = { floor: 0, items: [] };
  state.steps = 0;
  for (var line of lines) {
    var floor = [];
    var generators = line.match(/(\w+) generator/g);
    var microchips = line.match(/(\w+)-compatible microchip/g);

    if (generators) {
      generators.forEach(g => floor.push(thing(g)));
    }
    if (microchips) {
      microchips.forEach(m => floor.push(thing(m)));
    }

    state.push(floor);
  }
  draw();
}

function thing (str) {
  var generator = str.indexOf('generator') !== -1;
  var chip = str.indexOf('microchip') !== -1;
  var type = str[0].toUpperCase() + str[1] + str[2];
  var short = type + (generator ? 'G' : 'M');
  return { str, short, type, generator, chip };
}

function stateToHTML (state) {
  var html = '';
  for (var i = state.length - 1; i >= 0; i -= 1) {
    var floor = state[i];
    html += `<div class="floor">${i + 1}: `;
    for (var item of floor) {
      html += itemToHTML(item) + ' ';
    }
    if (i === state.elevator.floor) {
      html += elevatorToHTML(state.elevator);
    }
    html += '</div>';
  }
  html += `Steps: ${state.steps}`;
  return html;
}

function itemToHTML (item) {
  var color = 'style="background-color:' + (item.generator ? 'red' : 'blue') + '"';
  var onclick = (item.elevator) ?
                  `onclick="itemToFloor('${item.short}')"` :
                  `onclick="itemToElevator('${item.short}')"`;
  return `<span ${color} ${onclick}>${item.short}</span>`;
}

function elevatorToHTML (elevator) {
  var items = elevator.items.map(i => itemToHTML(i)).join(' ');
  return `<span>[ ${items} ]</span>`;
}

function itemToElevator (short) {
  var me;
  for (var i = 0; i < state.length; i += 1) {
    var floor = state[i];
    me = floor.find(i => i.short === short);
    if (me) {
      break;
    }
  }
  if (i === state.elevator.floor) {
    // only put in elevator if it is at the same floor
    floor.remove(me);
    state.elevator.items.push(me);
    me.elevator = true;
    draw();
  }
}

function itemToFloor (short) {
  var me = state.elevator.items.find(i => i.short === short);
  state[state.elevator.floor].push(me);
  state.elevator.items.remove(me);
  me.elevator = false;
  draw();
}

function draw () {
  for (var floor of state) {
    floor.sort((a, b) => {
      if ((a.generator && b.generator) || (a.chip && b.chip)) {
        return a.short > b.short ? -1 : 1;
      }
      // generators first
      return a.generator ? -1 : 1;
    });
  }
  document.getElementById('output').innerHTML = stateToHTML(state);
}

window.onkeydown = function (event) {
  var f = state.elevator.floor;
  if (state.elevator.items.length === 0) {
    return;
  }
  if (event.keyIdentifier === 'Up') {
    if (f === 3) {
      return;
    }
    state.elevator.floor += 1;
  } else if (event.keyIdentifier === 'Down') {
    if (f === 0) {
      return;
    }
    state.elevator.floor -= 1;
  }
  if (!allowedState(state)) {
    state.elevator.floor = f;
  } else {
    state.steps += 1;
    draw();
  }
};

function allowedState (state) {
  for (var i = 0; i < state.length; i += 1) {
    var floor = state[i];
    if (i === state.elevator.floor) {
      floor = floor.concat(state.elevator.items);
    }
    if (!allowedFloor(floor)) {
      return false;
    }
  }
  return true;
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

// 59
// 51
// 45

input.onchange = () => start();
start();
