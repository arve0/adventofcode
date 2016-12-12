var registers = { a: 0, b: 0, c: 0, d: 0 };

var position = 0;
var instructions = {
    cpy: (x, y) => registers[y] = (x in registers) ? registers[x] : x,
    inc: (x) => registers[x] += 1,
    dec: (x) => registers[x] -= 1,
    jnz: (x, y) => position += registers[x] === 0 ? 1 : y
};

function parse (str) {
    var lines = str.split('\n');
    var cmds = lines.map(l => {
        var items = l.split(' ');
        var instruction = items[0];
        var x = items[1];
        x = isNaN(parseInt(x)) ? x : parseInt(x);
        var y = items[2];
        y = isNaN(parseInt(y)) ? y :parseInt(y);
        return { instruction, x, y, str: l };
    });
    return cmds;
}

var cmds = parse(getInput());

function stateMachine (cmds) {
    position = 0;
    while (position < cmds.length) {
        var cmd = cmds[position];
        debugger;
        instructions[cmd.instruction](cmd.x, cmd.y);
        if (cmd.instruction !== 'jnz') {
            position += 1;
        }
        debugger;
    }
    console.log(registers);
}

stateMachine(cmds);

// part two
registers = { a: 0, b: 0, c: 1, d: 0 };
stateMachine(cmds);

function getInput () {
    return `cpy 1 a
cpy 1 b
cpy 26 d
jnz c 2
jnz 1 5
cpy 7 c
inc d
dec c
jnz c -2
cpy a c
inc a
dec b
jnz b -2
cpy c b
dec d
jnz d -6
cpy 19 c
cpy 14 d
inc a
dec d
jnz d -2
dec c
jnz c -5`;
}