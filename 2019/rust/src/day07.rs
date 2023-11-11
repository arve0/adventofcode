use crate::lib;
use crate::day05::{get_param, get_op_and_modes};

pub fn solve() {
    let program: Vec<i64> = lib::read_input("input07.txt", ",")
        .iter()
        .flat_map(|n| n.parse())
        .collect();

    state_machine
}

fn highest_amplification(bin: Vec<i64>, inputs: Vec<i64>, phase_settings: Vec<i64>) -> i64 {
    for n in phase_settings {

    }
    let output =
}

fn state_machine(mut bin: Vec<i64>, inputs: Vec<i64>) -> i64 {
    let mut input_pos = 0;
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
            bin[a] = inputs[input_pos];
            input_pos += 1;
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
