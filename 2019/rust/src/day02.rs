use crate::lib;

pub fn solve() {
    assert_eq!(state_machine(vec![1,9,10,3,2,3,11,0,99,30,40,50]), 3500);
    assert_eq!(state_machine(vec![1,0,0,0,99]), 2);
    assert_eq!(state_machine(vec![2,3,0,3,99]), 2);
    assert_eq!(state_machine(vec![2,4,4,5,99,0]), 2);
    assert_eq!(state_machine(vec![1,1,1,4,99,5,6,0,99]), 30);

    let input: Vec<usize> = lib::read_input("input02.txt", ",")
        .iter()
        .flat_map(|n| n.parse())
        .collect();

    // part 1
    let mut bin = input.clone();
    bin[1] = 12;
    bin[2] = 2;
    let part1 = state_machine(bin);
    println!("day 2 part 1: {}", part1);

    // part 2
    for i in 0..100 {
        for j in 0..100 {
            let mut bin = input.clone();
            bin[1] = i;
            bin[2] = j;
            let out = state_machine(bin);

            if out == 19690720 {
                let part2 = 100 * i + j;
                println!("day 2 part 2: {}", part2);
                return;
            }
        }
    }

    unreachable!();
}

fn state_machine(mut bin: Vec<usize>) -> usize {
    let mut pos = 0;

    while bin[pos] != 99 {
        let op = bin[pos];
        let a = bin[bin[pos + 1]];
        let b = bin[bin[pos + 2]];
        let out = bin[pos + 3];


        if op == 1 {
            bin[out] = a + b;
        } else if op == 2 {
            bin[out] = a * b;
        } else {
            dbg!(pos);
            dbg!(op);
            break;
        }

        pos += 4;
    }

    bin[0]
}
