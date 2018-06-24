extern crate regex;
use regex::Regex;
use std::str::FromStr;

fn main() {
    let max = usize::max_value();
    let text = format!("text 123 {} {}1 -1", max, max);

    let numbers: Vec<usize> = get_numbers(&text);
    assert_eq!(numbers, vec![123, max]);

    let max = isize::max_value();
    let text = format!("text 123 {} {}1 -1", max, max);

    let numbers: Vec<isize> = get_numbers(&text);
    assert_eq!(numbers, vec![123, max, -1]);
}

pub fn get_numbers<T: FromStr>(text: &str) -> Vec<T> {
    let re = Regex::new(r"\-?\d{1,}").unwrap();
    re.captures_iter(text)
        .flat_map(|capture| capture.get(0))
        .map(|digit| digit.as_str())
        .flat_map(|s| s.parse::<T>())
        .collect()
}
