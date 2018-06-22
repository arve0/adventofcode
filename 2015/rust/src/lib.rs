extern crate regex;

use regex::Regex;
use std::ops::Range;
use std::time::Instant;

pub fn get_numbers(text: &str) -> Vec<usize> {
    let re = Regex::new(r"\-?\d{1,}").unwrap();
    re.captures_iter(text)
        .flat_map(|capture| capture.get(0))
        .map(|digit| digit.as_str())
        .flat_map(|s| s.parse::<usize>())
        .collect()
}

pub fn map_mut<T, F>(arr: &mut [T], range: Range<usize>, f: F)
where
    F: Fn(&T) -> T
{
    let splice = arr[range].iter_mut();

    for x in splice {
        *x = f(x);
    }
}

pub fn map_mut_2d<T, F>(arr: &mut Vec<Vec<T>>, xrange: Range<usize>, yrange: Range<usize>, f: F)
where
    F: Fn(&T) -> T
{
    arr[yrange].iter_mut().for_each(|r| map_mut(r, xrange.clone(), &f));
}

pub fn time_since(start: Instant) {
    let elapsed = start.elapsed();
    println!("{:?}", elapsed);
    println!("Elapsed: {} ms", elapsed.as_secs() * 1_000 + elapsed.subsec_millis() as u64);
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn parses_all_valid_numbers() {
        let max = usize::max_value();
        let text = format!("text 123 {} {}1 -1", max, max);

        assert_eq!(get_numbers(&text), vec![123, max]);
    }
}