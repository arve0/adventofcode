use std::fs;

pub fn read_input(filename: &str) -> Vec<String> {
    fs::read_to_string(filename)
        .expect("unable to open file")
        .split("\n")
        .map(String::from)
        .collect()
}
