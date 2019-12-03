use std::fs;

pub fn read_input(filename: &str, separator: &str) -> Vec<String> {
    fs::read_to_string(filename)
        .expect("unable to open file")
        .split(separator)
        .map(String::from)
        .collect()
}
