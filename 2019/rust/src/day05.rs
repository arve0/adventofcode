use crate::lib;

pub fn solve() {
    assert_eq!(state_machine(vec![3,0,4,0,99], 1337), 1337);
    assert_eq!(state_machine(vec![1002,4,3,4,33], 999), 0);
    assert_eq!(state_machine(vec![1101,100,-1,4,0], 999), 0);

    let input: Vec<i64> = lib::read_input("input05.txt", ",")
        .iter()
        .flat_map(|n| n.parse())
        .collect();

    // part 1
    let bin = input.clone();
    let part1 = state_machine(bin, 1);
    println!("day 5 part 1: {}", part1);

    // part 2
    assert_eq!(state_machine(vec![3,9,8,9,10,9,4,9,99,-1,8], 8), 1);
    assert_eq!(state_machine(vec![3,9,8,9,10,9,4,9,99,-1,8], 9), 0);

    assert_eq!(state_machine(vec![3,9,7,9,10,9,4,9,99,-1,8], 7), 1);
    assert_eq!(state_machine(vec![3,9,7,9,10,9,4,9,99,-1,8], 8), 0);

    assert_eq!(state_machine(vec![3,3,1108,-1,8,3,4,3,99], 8), 1);
    assert_eq!(state_machine(vec![3,3,1108,-1,8,3,4,3,99], 9), 0);

    assert_eq!(state_machine(vec![3,3,1107,-1,8,3,4,3,99], 7), 1);
    assert_eq!(state_machine(vec![3,3,1107,-1,8,3,4,3,99], 8), 0);

    assert_eq!(state_machine(vec![3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9], 0), 0);
    assert_eq!(state_machine(vec![3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9], 8), 1);

    assert_eq!(state_machine(vec![3,3,1105,-1,9,1101,0,0,12,4,12,99,1], 0), 0);
    assert_eq!(state_machine(vec![3,3,1105,-1,9,1101,0,0,12,4,12,99,1], 8), 1);

    let less_equal_or_more_then_eight = vec![
        3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,
        1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,
        999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99
    ];
    assert_eq!(state_machine(less_equal_or_more_then_eight.clone(), 5), 999);
    assert_eq!(state_machine(less_equal_or_more_then_eight.clone(), 8), 1000);
    assert_eq!(state_machine(less_equal_or_more_then_eight.clone(), 12), 1001);

    let bin = input.clone();
    let part2 = state_machine(bin, 5);
    println!("day 5 part 2: {}", part2);
}

fn state_machine(mut bin: Vec<i64>, input: i64) -> i64 {
    let mut pos = 0;
    let mut i = 0;
    let mut output = 0;

    loop {
        i += 1;
        if i > 1000_000 {
            println!("too many loops");
            lib::print_vec(&bin);
            return -1;
        } else if pos >= bin.len() {
            println!("index too high");
            lib::print_vec(&bin);
            return -2;
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
            output = get_param(pos + 1, &ma, &bin);
            pos += 2;
        } else if op == 5 {
            let a = get_param(pos + 1, &ma, &bin);
            let b = get_param(pos + 2, &mb, &bin) as usize;
            if a != 0 {
                pos = b;
            } else {
                pos += 3
            }
        } else if op == 6 {
            let a = get_param(pos + 1, &ma, &bin);
            let b = get_param(pos + 2, &mb, &bin) as usize;
            if a == 0 {
                pos = b;
            } else {
                pos += 3
            }
        } else if op == 7 {
            let a = get_param(pos + 1, &ma, &bin);
            let b = get_param(pos + 2, &mb, &bin);
            let out = bin[pos + 3] as usize;
            bin[out] = if a < b { 1 } else { 0 };
            pos += 4;
        } else if op == 8 {
            let a = get_param(pos + 1, &ma, &bin);
            let b = get_param(pos + 2, &mb, &bin);
            let out = bin[pos + 3] as usize;
            bin[out] = if a == b { 1 } else { 0 };
            pos += 4;
        } else if op == 99 {
            break;
        } else {
            dbg!(pos);
            dbg!(op);
            lib::print_vec(&bin);
            unreachable!();
        }
    }

    output
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
    if pos >= bin.len() {
        println!("index too high");
        dbg!(pos);
        lib::print_vec(&bin);
        return -2;
    }
    match mode {
        MEMORY => get_param(bin[pos] as usize, &IMMIDIATE, bin),
        IMMIDIATE => bin[pos],
    }
}
