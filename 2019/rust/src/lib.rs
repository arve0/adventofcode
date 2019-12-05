use std::fs;

pub fn read_input(filename: &str, separator: &str) -> Vec<String> {
    fs::read_to_string(filename)
        .expect("unable to open file")
        .split(separator)
        .map(|s| s.trim())
        .map(String::from)
        .collect()
}

pub fn digits(mut n: i64) -> Vec<i64> {
    let mut digits = Vec::new();
    n = n.abs();

    while n != 0 {
        digits.insert(0, n % 10);
        n /= 10;
    }

    digits
}

pub fn print_vec<T>(vec: &Vec<T>) where T: std::fmt::Display {
    vec.iter().enumerate()
        .for_each(|(i, n)| print!("{}={}, ", i, n));

    println!("");
}
