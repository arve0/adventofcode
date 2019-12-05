use crate::lib;

pub fn solve() {
    assert_eq!(state_machine(vec![3,0,4,0,99], 1337), 1337);
    assert_eq!(state_machine(vec![1002,4,3,4,33], 999), -2);
    assert_eq!(state_machine(vec![1101,100,-1,4,0], 999), -2);

    let input: Vec<i64> = lib::read_input("input05.txt", ",")
        .iter()
        .flat_map(|n| n.parse())
        .collect();

    // part 1
    let bin = input.clone();
    let part1 = state_machine(bin, 1);
    println!("day 5 part 1: {}", part1);
}

fn state_machine(mut bin: Vec<i64>, input: i64) -> i64 {
    let mut pos = 0;
    let mut i = 0;
    loop {
        i += 1;
        if i > 1000_000 {
            println!("too many loops");
            return -1;
        }

        let (op, ma, mb, _mout) = get_op_and_modes(pos, &bin);

        if op == 1 {
            let a = get_param(pos + 1, &ma, &bin);
            let b = get_param(pos + 2, &mb, &bin);
            let out = bin[pos + 3] as usize;

            bin[out] = a + b;
            pos += 4;
        } else if op == 2 {
            let a = get_param(pos + 1, &ma, &bin);
            let b = get_param(pos + 2, &mb, &bin);
            let out = bin[pos + 3] as usize;

            bin[out] = a * b;
            pos += 4;
        } else if op == 3 {
            let a = bin[pos + 1] as usize;
            bin[a] = input;
            pos += 2;
        } else if op == 4 {
            let a = get_param(pos + 1, &ParamMode::MEMORY, &bin);
            let next_op = bin[pos + 2];
            if next_op == 99 && a != 0 {
                return a;
            }
            pos += 2;
        } else if op == 99 {
            return -2;
        } else {
            dbg!(pos);
            dbg!(op);
            unreachable!();
        }
    }
}

#[derive(Debug)]
enum ParamMode {
    MEMORY,
    IMMIDIATE,
}

fn get_op_and_modes(pos: usize, bin: &Vec<i64>) -> (i64, ParamMode, ParamMode, ParamMode) {
    let n = bin[pos];
    let mut digits = lib::digits(n);

    while digits.len() < 5 {
        digits.insert(0, 0);
    }

    let op = 10 * digits[3] + digits[4];
    let c = param_mode(digits[2]);
    let b = param_mode(digits[1]);
    let a = param_mode(digits[0]);

    (op, c, b, a)
}

fn param_mode(n: i64) -> ParamMode {
    use ParamMode::*;
    assert!(n == 0 || n == 1);

    if n == 0 { MEMORY } else { IMMIDIATE }
}

fn get_param(pos: usize, mode: &ParamMode, bin: &Vec<i64>) -> i64 {
    use ParamMode::*;
    match mode {
        MEMORY => get_param(bin[pos] as usize, &IMMIDIATE, bin),
        IMMIDIATE => bin[pos],
    }
}
