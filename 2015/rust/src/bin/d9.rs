extern crate permutohedron;

use permutohedron::heap_recursive;
use std::collections::HashMap;

fn main() {
    let mut data = parse_input(get_test_input());

    let mut permutations = Vec::new();
    heap_recursive(&mut data, |permutation| {
        permutations.push(permutation.to_vec())
    });

    println!("{:?}", permutations);
}

fn parse_input(s: &str) -> Vec<(String, String, usize)> {
    let mut distances = Vec::new();

    for line in s.split("\n") {
        let [stage, dist] = split(line, " = ");
        let [from, to] = split(stage, " to ");
        distances.push((from.to_owned(), to.to_owned(), dist.parse().unwrap()));
    }

    distances
}

fn split<'a>(s: &'a str, delimiter: &str) -> [&'a str; 2] {
    let splitted: Vec<_> = s.split(delimiter).collect();
    [splitted[0], splitted[1]]
}

fn get_test_input() -> &'static str {
    "London to Dublin = 464
London to Belfast = 518
London to Oslo = 555
Dublin to Belfast = 141
Dublin to Oslo = 666
Belfast to Oslo = 777"
}
