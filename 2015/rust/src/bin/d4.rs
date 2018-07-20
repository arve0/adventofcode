extern crate md5;
extern crate aoc15;

use aoc15::time_since;
use std::time::Instant;

fn main () {
    assert!(hash("abcdef609043").starts_with("000001dbbfa"));
    assert!(find_first_coin("pqrstuv") == 1048970);

    let start = Instant::now();
    println!("{}", find_first_coin("iwrupvqb"));
    time_since(start);

    let start = Instant::now();
    println!("{}", find_super_coin("iwrupvqb"));
    time_since(start);
}

fn hash(s: &str) -> String {
    let digest = md5::compute(s);
    format!("{:x}", digest)
}

fn find_first_coin(s: &str) -> i32 {
    let mut i = 0;
    let mut h = hash_i(s, &i);

    while !is_coin(h) {
        i += 1;
        h = hash_i(s, &i);
    }

    i
}

fn hash_i(s: &str, i: &i32) -> String {
    hash(&format!("{}{}", s, i))
}

fn is_coin(s: String) -> bool {
    s.starts_with("00000")
}

fn find_super_coin(s: &str) -> i32 {
    let mut i = 0;
    let mut h = hash_i(s, &i);

    while !is_super_coin(h) {
        i += 1;
        h = hash_i(s, &i);
    }

    i
}

fn is_super_coin(s: String) -> bool {
    s.starts_with("000000")
}
