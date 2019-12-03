use crate::lib;
use std::collections::HashSet;

pub fn solve() {
    assert_eq!(distance_to_crossing(vec!["R8,U5,L5,D3".to_owned(), "U7,R6,D4,L4".to_owned()]), 159);
}

pub fn distance_to_crossing(wires: Vec<String>) -> i64 {
    let first_wire = trace_path(&wires[0]);
    let second_wire = trace_path(&wires[1]);

}

pub fn trace_path(wire: &str) -> HashSet<(i64, i64)> {
    let mut path = HashSet::new();
    let mut x: i64 = 0;
    let mut y: i64 = 0;

    wire.split(",")
        .for_each(|jump| {
            let direction = jump.get(0..1).unwrap();
            let distance: i64 = jump.get(1..).map(|x| x.parse().unwrap()).unwrap();

            match direction {
                "U" => y += distance,
                "R" => x += distance,
                "D" => y -= distance,
                "L" => x -= distance,
                _ => unreachable!(),
            }

            path.insert((x, y));
        });

    path
}
