extern crate regex;
use regex::Regex;

fn main() {
    let max = usize::max_value();
    let text = format!("text 123 {} {}1 -1", max, max);

    assert_eq!(get_numbers(&text), vec![123, max]);
}

pub fn get_numbers(text: &str) -> Vec<usize> {
    let re = Regex::new(r"\-?\d{1,}").unwrap();
    re.captures_iter(text)
        .flat_map(|capture| capture.get(0))
        .map(|digit| digit.as_str())
        .flat_map(|s| s.parse::<usize>())
        .collect()
}
